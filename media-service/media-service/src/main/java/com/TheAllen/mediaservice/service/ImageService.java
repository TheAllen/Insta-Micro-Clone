package com.TheAllen.mediaservice.service;

import com.TheAllen.mediaservice.model.ImageMetadata;
import com.TheAllen.mediaservice.repository.ImageMetadataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class ImageService {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ImageMetadataRepository imageMetadataRepository;

    public ImageMetadata addImage(MultipartFile file, String username) {

        //Get filename from file
        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        //Log message

        //pass in file and username
        ImageMetadata metadata = fileStorageService.store(file, username);

        return imageMetadataRepository.save(metadata);
    }
}
