package com.anthonykim.blog.entity.post;

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
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post_subscriber", indexes = {
        @Index(columnList = "username"),
})
public class PostSubscriber implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", updatable = false)
    private UUID postSubscriberId;

    @Column(length = 64, updatable = false, nullable = false)
    @Convert(converter = StringAttributeConverter.class)
    private String username;

    @Column(length = 64)
    @Convert(converter = StringAttributeConverter.class)
    private String userFullName;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "post_id")
    private Post post;

    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
}
