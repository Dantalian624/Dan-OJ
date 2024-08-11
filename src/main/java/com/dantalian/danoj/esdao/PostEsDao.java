package com.dantalian.danoj.esdao;

import com.dantalian.danoj.model.dto.post.PostEsDTO;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 帖子 ES 操作
 *
 * @author <a href="https://github.com/Dantalian624">Dantalian</a>
 * @from <a href="https://github.com/Dantalian624/Dan-OJ">Dan-OJ</a>
 */
public interface PostEsDao extends ElasticsearchRepository<PostEsDTO, Long> {

    List<PostEsDTO> findByUserId(Long userId);
}