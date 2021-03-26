package com.anthonykim.blog.service.grpc;

import com.anthonykim.blog.grpc.service.CreatePostRequest;
import com.anthonykim.blog.grpc.service.CreatePostResponse;
import com.anthonykim.blog.grpc.service.GrpcResponseState;
import com.anthonykim.blog.grpc.service.PostServiceGrpc;
import com.anthonykim.blog.service.mysql.post.PostService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class PostGrpcService extends PostServiceGrpc.PostServiceImplBase {
    private final PostService postService;

    @Override
    public void createPost(CreatePostRequest request, StreamObserver<CreatePostResponse> responseObserver) {
        final CreatePostResponse.Builder responseBuilder = CreatePostResponse.newBuilder();
        final CreatePostResponse response = null;

        try {
            // TODO: create post

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            responseObserver.onNext(response != null ? response : responseBuilder.setState(GrpcResponseState.INTERNAL_SERVER_ERROR).build());
            responseObserver.onCompleted();
        }
    }
}
