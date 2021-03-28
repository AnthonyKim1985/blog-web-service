package com.anthonykim.blog.service.mysql.post;

import com.anthonykim.blog.entity.post.Post;
import com.anthonykim.blog.grpc.service.AttachedFile;
import com.anthonykim.blog.persistence.mysql.post.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

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

    @Transactional
    public Post createOne(final String username,
                          final String userFullName,
                          final String title,
                          final String body,
                          final List<String> tags,
                          final List<AttachedFile> attachedFiles) {
        return postRepository.save(new Post(username, userFullName, title, body, tags, attachedFiles));
    }
}
