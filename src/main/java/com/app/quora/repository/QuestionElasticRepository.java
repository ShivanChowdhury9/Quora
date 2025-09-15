package com.app.quora.repository;

import com.app.quora.model.elasticSearch.QuestionElasticDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import reactor.core.publisher.Mono;

import java.util.List;

public interface QuestionElasticRepository extends ElasticsearchRepository<QuestionElasticDocument, String> {

    List<QuestionElasticDocument> findByTitleContainingOrContentContaining(String title, String content);

}
