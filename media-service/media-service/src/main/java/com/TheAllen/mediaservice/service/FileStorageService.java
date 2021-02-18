package com.TheAllen.mediaservice.service;

import com.TheAllen.mediaservice.exception.InvalidFileException;
import com.TheAllen.mediaservice.exception.InvalidFilenameException;
import com.TheAllen.mediaservice.model.ImageMetadata;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.InvalidFileNameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.InetAddress;
import java.util.UUID;

@Service
public class FileStorageService {

    Logger log = LoggerFactory.getLogger(FileStorageService.class);

    @Value("${file.upload-dir}")
    private String uploadDirectory;

    @Value("${file.path.prefix}")
    private String filePathPrefix;

    @Autowired
    private Environment environment;

    public ImageMetadata store(MultipartFile file, String username) {

        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        //Log message
        log.info("storing file {}...", filename);

        try {
            if(file.isEmpty()) {
                log.warn("{} is empty", filename);
                throw new InvalidFileException("Cannot store file that is empty " + filename);
            }

            if(filename.contains("..")) {
                log.warn("Cannot store file with relative path {}", filename);
                throw new InvalidFilenameException("Cannot store file outside of current dir " + filename);
            }

            String extension = FilenameUtils.getExtension(filename);
            String newFilename = UUID.randomUUID() + "." + extension;

            //Port and hostname
            String port = environment.getProperty("local.server.port");
            String hostName = InetAddress.getLocalHost().getHostName();

            String fileUrl = String.format("http://%s:%s%s/%s/%s", hostName, port, filePathPrefix, username, newFilename);

            return new ImageMetadata(newFilename, fileUrl, file.getContentType());


        } catch(IOException e) {
            log.error("failed to store file {} error: {}", filename, e);
        }
    }
}
