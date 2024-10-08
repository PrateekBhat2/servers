package com.ukg.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayserverApplication {

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder){
		return builder.routes().
				route(p -> p
						.path("/payroll/payroll/**")
						.filters(f -> f.rewritePath("/payroll/payroll/(?<segment>.*)", "/${segment}"))
						.uri("lb://PAYROLL")
				).
				route(p -> p
						.path("/payroll/leave/**")
						.filters(f -> f.rewritePath("/payroll/leave/(?<segment>.*)", "/${segment}"))
						.uri("lb://LEAVE")
				).route(p -> p
						.path("/payroll/employee/**")
						.filters(f -> f.rewritePath("/payroll/employee/(?<segment>.*)", "/${segment}"))
						.uri("lb://EMPLOYEE")
				).
				build();
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

}
