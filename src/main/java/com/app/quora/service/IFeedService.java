package com.app.quora.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IFeedService {

    Flux<?> getUserFeed(String userId);
}
