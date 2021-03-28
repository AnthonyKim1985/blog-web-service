package com.anthonykim.blog.entity.chat;

import com.anthonykim.blog.entity.post.Post;
import com.anthonykim.blog.entity.util.StringAttributeConverter;
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
@Table(name = "chat", indexes = {
        @Index(columnList = "username"),
})
public class Chat implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", updatable = false)
    private UUID chatId;

    @Column(length = 64, updatable = false, nullable = false)
    @Convert(converter = StringAttributeConverter.class)
    private String username;

    @Column(length = 64)
    @Convert(converter = StringAttributeConverter.class)
    private String userFullName;

    @Column(columnDefinition = "TEXT")
    private String body;

    @Column(length = 128)
    private String fileKey;
    @Column(length = 16)
    private String fileCategory;
    @Column(columnDefinition = "TEXT")
    private String fileDownloadUri;
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
}
