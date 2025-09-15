package com.app.quora.service;

import com.app.quora.model.Question;
import com.app.quora.model.elasticSearch.QuestionElasticDocument;

public interface IQuestionIndexService {

    QuestionElasticDocument createQuestionIndex(Question question) ;
}
