package com.TheAllen.mediaservice.repository;

import com.TheAllen.mediaservice.model.ImageMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageMetadataRepository extends MongoRepository<ImageMetadata, String> {
}
