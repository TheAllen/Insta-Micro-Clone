package com.TheAllen.mediaservice.service;

import com.TheAllen.mediaservice.model.ImageMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class FileStorageService {

    @Value()
    private String uploadDirectory;

    @Value()
    private String filePathPrefix;

    @Autowired
    private Environment environment;

    public ImageMetadata store(MultipartFile file, String username) {

        String filename
    }
}
