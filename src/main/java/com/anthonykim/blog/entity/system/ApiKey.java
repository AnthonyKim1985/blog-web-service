package com.anthonykim.blog.entity.system;

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
@Table(name = "api_key", indexes = {
        @Index(columnList = "username"),
        @Index(columnList = "removed"),
})
public class ApiKey implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", updatable = false)
    private UUID apiKeyId;

    @Column(length = 64, updatable = false, nullable = false)
    @Convert(converter = StringAttributeConverter.class)
    private String username;

    @Column(length = 64, updatable = false, nullable = false)
    private String apiKeyValue;

    private Boolean removed;

    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
}
