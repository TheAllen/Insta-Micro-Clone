package com.TheAllen.mediaservice.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@RequiredArgsConstructor
@Document
public class ImageMetadata {

    @Id
    private String id;

    @CreatedBy
    private String username;

    @CreatedDate
    private Instant createdAt;

    @NotNull(message = "filename cannot be null")
    private String filename;

    @NonNull
    private String url;

    @NonNull
    private String fileType;
}
