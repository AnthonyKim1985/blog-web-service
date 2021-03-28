package com.anthonykim.blog.persistence.mysql.chat;

import com.anthonykim.blog.entity.chat.ChatInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChatInterestRepository  extends JpaRepository<ChatInterest, UUID> {
}
