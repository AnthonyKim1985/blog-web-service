package com.anthonykim.blog.entity.chat;

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
@Table(name = "chat_interest", indexes = {
        @Index(columnList = "username"),
        @Index(columnList = "createdAt"),
})
public class ChatInterest implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", updatable = false)
    private UUID chatInterestId;

    @Column(length = 64, updatable = false, nullable = false)
    @Convert(converter = StringAttributeConverter.class)
    private String username;

    @Column(length = 64)
    @Convert(converter = StringAttributeConverter.class)
    private String userFullName;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
}
