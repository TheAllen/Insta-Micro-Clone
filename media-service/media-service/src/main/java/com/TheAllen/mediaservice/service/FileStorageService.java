package com.TheAllen.mediaservice.service;

import com.TheAllen.mediaservice.model.ImageMetadata;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
                throw new InvalidFile
            }
        } catch(IOException e) {
            log.error("failed to store file {} error: {}", filename, e);
        }
    }
}
