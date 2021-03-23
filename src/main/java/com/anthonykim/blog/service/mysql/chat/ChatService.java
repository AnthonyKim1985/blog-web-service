package com.anthonykim.blog.service.mysql.chat;

import com.anthonykim.blog.persistence.mysql.chat.ChatInterestRepository;
import com.anthonykim.blog.persistence.mysql.chat.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {
    private final EntityManager entityManager;
    private final ChatRepository chatRepository;
    private final ChatInterestRepository chatInterestRepository;
}
