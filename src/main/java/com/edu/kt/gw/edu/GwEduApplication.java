package com.edu.kt.gw.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GwEduApplication {

	public static void main(String[] args) {
		SpringApplication.run(GwEduApplication.class, args);
	}
/*
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("employee-service", r -> r.path("/backend")
						//.filters(f -> f.tokenRelay())
						.uri("http://localhost:8080"))
				.route("host_route", r -> r.host("*.myhost.org")
						.uri("http://httpbin.org"))
				.build();
	}*/
}
