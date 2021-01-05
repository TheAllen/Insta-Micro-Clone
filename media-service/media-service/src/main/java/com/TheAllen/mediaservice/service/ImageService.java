package com.TheAllen.mediaservice.service;

import com.TheAllen.mediaservice.model.ImageMetadata;
import com.TheAllen.mediaservice.repository.ImageMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ImageMetadataRepository imageMetadataRepository;

    public ImageMetadata addImage(MultipartFile file, String username) {

    }
}
