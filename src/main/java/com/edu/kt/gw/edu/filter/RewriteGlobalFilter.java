package com.edu.kt.gw.edu.filter;

import com.edu.kt.gw.edu.transform.RequestBodyTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyRequestBodyGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
public class RewriteGlobalFilter implements GlobalFilter, Ordered {

    @Autowired
    private ModifyRequestBodyGatewayFilterFactory modifyRequestBodyFilter;

    @Autowired
    private RequestBodyTransform requestBodyTransform;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("===================== pre filter =====================");

        chain.filter(exchange).then(Mono.fromRunnable(() -> {
            log.info("X_GUID : ", exchange.getRequest().getHeaders().get("x_guid"));
        }));

        HttpHeaders headers = exchange.getRequest().getHeaders();

        /*
        headers.forEach((key, value) -> {
            log.info(String.format("Header '%s' = %s", key, value));
        });*/

        log.info("EDU : ", headers);

        return modifyRequestBodyFilter
                .apply(new ModifyRequestBodyGatewayFilterFactory
                        .Config()
                        .setRewriteFunction(String.class, String.class, requestBodyTransform))
                .filter(exchange, chain)
                .then(Mono.fromRunnable(() -> {
                    log.info("===================== post filter =====================");
                }));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
