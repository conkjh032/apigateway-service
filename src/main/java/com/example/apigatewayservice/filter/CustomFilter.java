package com.example.apigatewayservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {
    public CustomFilter(){
        super();
    }


    @Override
    public GatewayFilter apply(Config config){
        // Custom Pre Filter
        return (exchange, chain) -> { // exchange와 chain을 인자로 받음

            // 비동기 객체
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Custom Pre Filter : request id -> {}", request.getId());

            // Custom Post Filter
            // chain filter에 post filter를 정의하고
            // then() : 반환 값으로 이것들을 줌
            // Mono : 반환 값으로 data type이 mono라는 것을 1개 줌
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                log.info("Custom Post Filter : reponse id -> {}", response.getStatusCode());
            }));
        };
    }

    // inner class
    public static class Config{
        // Put the configuration properties
    };
}
