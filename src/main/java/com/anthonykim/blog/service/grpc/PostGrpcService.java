package com.anthonykim.blog.service.grpc;

import com.anthonykim.blog.entity.post.Post;
import com.anthonykim.blog.entity.system.ApiKey;
import com.anthonykim.blog.grpc.service.*;
import com.anthonykim.blog.service.mysql.post.PostService;
import com.anthonykim.blog.service.mysql.system.ApiKeyService;
import com.google.protobuf.ProtocolStringList;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class PostGrpcService extends PostServiceGrpc.PostServiceImplBase {
    private final PostService postService;
    private final ApiKeyService apiKeyService;

    @Override
    public void createPost(CreatePostRequest request, StreamObserver<CreatePostResponse> responseObserver) {
        final CreatePostResponse.Builder responseBuilder = CreatePostResponse.newBuilder();
        CreatePostResponse response = null;

        try {
            final String apiKeyValue = request.getApiKey();
            final String username = request.getUsername();
            final String userFullName = request.getUserFullName();
            final String title = request.getTitle();
            final String body = request.getBody();
            final AttachedFile attachedFile = request.getAttachedFile();
            final ProtocolStringList tagProtocolStringList = request.getTagList();
            final List<String> tags = tagProtocolStringList.asByteStringList().stream().map(s -> s.toString(StandardCharsets.UTF_8)).collect(Collectors.toList());

            final boolean apiKeyEmpty = apiKeyValue.isEmpty();

            if (apiKeyEmpty) {
                response = responseBuilder.setState(GrpcResponseState.BAD_REQUEST).build();
            } else {
                final ApiKey apiKey = apiKeyService.readOne(apiKeyValue);

                if (apiKey == null) {
                    response = responseBuilder.setState(GrpcResponseState.BAD_REQUEST).build();
                } else {
                    final boolean usernameEmpty = username.isEmpty();
                    final boolean userFullNameEmpty = userFullName.isEmpty();
                    final boolean titleEmpty = title.isEmpty();
                    final boolean bodyEmpty = body.isEmpty();
                    final boolean tagsEmpty = tags.isEmpty();

                    if (usernameEmpty ||userFullNameEmpty ||  titleEmpty || bodyEmpty || tagsEmpty) {
                        response = responseBuilder.setState(GrpcResponseState.BAD_REQUEST).build();
                    } else {
                        final Post post = postService.createOne(username, userFullName, title, body, tags, attachedFile);
                        log.debug("Created post: {}", post);

                        response = responseBuilder
                                .setState(GrpcResponseState.OK)
                                .setPostId(post.getPostId().toString())
                                .build();
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            responseObserver.onNext(response != null ? response : responseBuilder.setState(GrpcResponseState.INTERNAL_SERVER_ERROR).build());
            responseObserver.onCompleted();
        }
    }
}
