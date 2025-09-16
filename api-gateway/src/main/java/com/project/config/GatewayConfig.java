package com.project.config;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Ruta para Auth Service
                .route("auth-service", r -> r
                        .path("/auth/**")
                        .uri("lb://auth-service"))

                // Ruta para Booking Service
                .route("booking-service", r -> r
                        .path("/api/bookings/**")
                        .filters(f -> f
                                .addRequestHeader("Authorization", "forward:Authorization")
                                .rewritePath("/api/bookings/(?<segment>.*)", "/${segment}"))
                        .uri("lb://booking-service"))

                // Ruta para Wash Service (futuro)
                .route("wash-service", r -> r
                        .path("/api/washes/**")
                        .filters(f -> f
                                .addRequestHeader("Authorization", "forward:Authorization")
                                .rewritePath("/api/washes/(?<segment>.*)", "/${segment}"))
                        .uri("lb://wash-service"))

                // Ruta para Eureka Console
                .route("eureka-console", r -> r
                        .path("/eureka/web")
                        .filters(f -> f.setPath("/"))
                        .uri("http://localhost:8761"))
                .build();
    }
}