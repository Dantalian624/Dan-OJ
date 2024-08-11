package com.dantalian.danoj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dantalian.danoj.model.dto.post.PostQueryRequest;
import com.dantalian.danoj.model.entity.Post;
import javax.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 帖子服务测试
 *
 * @author <a href="https://github.com/Dantalian624">Dantalian</a>
 * @from <a href="https://github.com/Dantalian624/Dan-OJ">Dan-OJ</a>
 */
@SpringBootTest
class PostServiceTest {

    @Resource
    private PostService postService;

    @Test
    void searchFromEs() {
        PostQueryRequest postQueryRequest = new PostQueryRequest();
        postQueryRequest.setUserId(1L);
        Page<Post> postPage = postService.searchFromEs(postQueryRequest);
        Assertions.assertNotNull(postPage);
    }

}