package com.nwhite.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator whiteBankRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(p -> p
						.path("/whitebank/accounts/**")
						.filters( f -> f.rewritePath("/whitebank/accounts/(?<segment>.*)","/${segment}"))
						.uri("lb://ACCOUNTS"))
				.route(p -> p
						.path("/whitebank/loans/**")
						.filters( f -> f.rewritePath("/whitebank/loans/(?<segment>.*)","/${segment}"))
						.uri("lb://LOANS"))
				.route(p -> p
						.path("/whitebank/cards/**")
						.filters( f -> f.rewritePath("/whitebank/cards/(?<segment>.*)","/${segment}"))
						.uri("lb://CARDS")).build();

	}

}
