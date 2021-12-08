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
        // 상위 클래스의 config 호출
        super(Config.class);
    }

    // Override할 매소드를 쉽게 불러오는 방법
    // 마우스 우측 클릭 -> generate -> implement methods -> 원하는 매개변수를 갖는 메소드 선택
    @Override
    public GatewayFilter apply(Config config){

        // Pre Filter
        return (exchange, chain) -> {

            // 비동기 객체
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Custom Pre Filter : request id -> {}", request.getId());

            //Post Filter
            // chain.filter( arg ) : arg는 필터에 적용할 객체
            // then() : 필터가 적용된 후, 반환 값으로 내부에 코딩된 내용을 반환
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
