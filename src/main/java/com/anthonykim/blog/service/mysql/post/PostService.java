package com.anthonykim.blog.service.mysql.post;

import com.anthonykim.blog.persistence.mysql.post.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final EntityManager entityManager;
    private final PostRepository postRepository;
    private final PostTagRepository postTagRepository;
    private final PostViewerRepository postViewerRepository;
    private final PostInterestRepository postInterestRepository;
    private final PostSubscriberRepository postSubscriberRepository;
    private final PostAttachedFileRepository postAttachedFileRepository;
}
