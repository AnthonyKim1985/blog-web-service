package com.anthonykim.blog.persistence.mysql.post;

import com.anthonykim.blog.entity.post.PostAttachedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostAttachedFileRepository extends JpaRepository<PostAttachedFile, UUID> {
}
