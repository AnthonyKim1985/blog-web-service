package com.anthonykim.blog.service.mysql.system;

import com.anthonykim.blog.entity.system.ApiKey;
import com.anthonykim.blog.persistence.mysql.system.ApiKeyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApiKeyService {
    private final EntityManager entityManager;
    private final ApiKeyRepository apiKeyRepository;

    public ApiKey readOne(final String apiKeyValue) {
        return apiKeyRepository.findByApiKeyValue(apiKeyValue).orElse(null);
    }
}
