package com.anthonykim.blog.entity.file;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "file", indexes = {
        @Index(columnList = "fileKey"),
        @Index(columnList = "s3Key"),
        @Index(columnList = "fileKey,s3Key", unique = true),
})
public class File implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", updatable = false)
    private UUID fileId;

    @Column(columnDefinition = "VARCHAR(64) NOT NULL COMMENT '파일 고유 키'")
    private String fileKey;
    @Column(columnDefinition = "VARCHAR(255) NOT NULL COMMENT 'Amazon S3 파일 고유 키'")
    private String s3Key;
    @Column(columnDefinition = "VARCHAR(255) NOT NULL COMMENT '파일 이름'")
    private String filename;
    @Column(columnDefinition = "VARCHAR(16) NOT NULL COMMENT '파일 확장자'")
    private String extension;
    @Column(columnDefinition = "INT(11) NOT NULL COMMENT '파일 크기'")
    private Long size;
    @Column(columnDefinition = "VARCHAR(32) NOT NULL COMMENT '파일 컨텐트 타입'")
    private String contentType;
    @Column(columnDefinition = "BIT(1) NOT NULL COMMENT '파일 확정 여부'")
    private Boolean committed;

    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
}
