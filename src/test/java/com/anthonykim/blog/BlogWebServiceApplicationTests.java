package com.anthonykim.blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("local")
@SpringBootTest(classes = BlogWebServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BlogWebServiceApplicationTests {

    @Test
    void test() {
    }

}
