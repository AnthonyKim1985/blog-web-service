package com.anthonykim.blog.entity.post;

import com.anthonykim.blog.grpc.service.AttachedFile;
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
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post_attached_file", indexes = {
        @Index(columnList = "fileKey", unique = true),
})
public class PostAttachedFile implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", updatable = false)
    private UUID postAttachedFileId;

    @Column(length = 128)
    private String fileKey;
    @Column(columnDefinition = "TEXT")
    private String downloadUri;
    @Column
    private String filename;
    @Column(length = 16)
    private String fileExtension;
    private Long fileSize;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "post_id")
    private Post post;

    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    public PostAttachedFile(final AttachedFile attachedFile, final Post post) {
        this.fileKey = attachedFile.getFileKey();
        this.downloadUri = attachedFile.getDownloadUri();
        this.filename = attachedFile.getFilename();
        this.fileExtension = attachedFile.getFileExtension();
        this.fileSize = attachedFile.getFileSize();
        this.post = post;
    }
}
