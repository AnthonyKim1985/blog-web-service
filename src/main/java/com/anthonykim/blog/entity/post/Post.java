package com.anthonykim.blog.entity.post;

import com.anthonykim.blog.entity.chat.Chat;
import com.anthonykim.blog.entity.util.StringAttributeConverter;
import com.anthonykim.blog.grpc.service.AttachedFile;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post", indexes = {
        @Index(columnList = "username"),
        @Index(columnList = "removed"),
        @Index(columnList = "createdAt"),
})
public class Post implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", updatable = false)
    private UUID postId;

    @Column(length = 64, updatable = false, nullable = false)
    @Convert(converter = StringAttributeConverter.class)
    private String username;

    @Column(length = 64)
    @Convert(converter = StringAttributeConverter.class)
    private String userFullName;

    @Column(length = 128)
    private String title;
    @Column(columnDefinition = "LONGTEXT")
    private String body;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "post", orphanRemoval = true)
    private Set<PostAttachedFile> postAttachedFiles;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "post", orphanRemoval = true)
    private Set<PostInterest> postInterests;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "post", orphanRemoval = true)
    private Set<PostSubscriber> postSubscribers;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "post", orphanRemoval = true)
    private Set<PostViewer> postViewers;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "post", orphanRemoval = true)
    private Set<PostTag> postTags;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "post", orphanRemoval = true)
    private Set<Chat> chats;

    private Integer countOfInterestingPostSubscribers;
    private Integer countOfPostAttachedFiles;
    private Integer countOfChats;
    private Boolean removed;

    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    public Post(final String username,
                final String userFullName,
                final String title,
                final String body,
                final List<String> tags,
                final List<AttachedFile> attachedFiles) {
        this.username = username;
        this.userFullName = userFullName;
        this.title = title;
        this.body = body;
        this.countOfChats = 0;
        this.countOfInterestingPostSubscribers = 0;
        this.removed = Boolean.FALSE;

        if (tags != null && !tags.isEmpty())
            this.postTags = tags.stream().map(t -> new PostTag(t, this)).collect(Collectors.toSet());

        if (attachedFiles != null && !attachedFiles.isEmpty()) {
            this.postAttachedFiles = attachedFiles.stream().map(p -> new PostAttachedFile(p, this)).collect(Collectors.toSet());
            this.countOfPostAttachedFiles = attachedFiles.size();
        } else {
            this.countOfPostAttachedFiles = 0;
        }
    }
}
