package br.edu.postech.shopinildo.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Value("${app.item-service.url}")
    private String itemServiceUrl;

    @Value("${app.estoque-service.url}")
    private String estoqueServiceUrl;

    @Value("${app.carrinho-service.url}")
    private String carrinhoServiceUrl;

    @Value("${app.pedido-service.url}")
    private String pedidoServiceUrl;

    @Value("${app.pagamento-mock.url}")
    private String pagamentoMockUrl;

    @Value("${app.usuario-service.url}")
    private String usuarioServiceUrl;

    private final AuthenticationFilter filter;

    public GatewayConfig(AuthenticationFilter filter) {
        this.filter = filter;
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("item-service", r -> r.path("/items/**")
                        .and().not(p -> p.path("/items/api/**"))
                        .filters(f -> f.filter(filter))
                        .uri(itemServiceUrl))
                .route("estoque-service", r -> r.path("/estoque/**")
                        .and().not(p -> p.path("/estoque/api/**"))
                        .filters(f -> f.filter(filter))
                        .uri(estoqueServiceUrl))
                .route("carrinho-service", r -> r.path("/carrinho/**")
                        .filters(f -> f.filter(filter))
                        .uri(carrinhoServiceUrl))
                .route("pedido-service", r -> r.path("/pedidos/**")
                        .filters(f -> f.filter(filter))
                        .uri(pedidoServiceUrl))
                .route("usuario-service", r -> r.path("/users/**")
                        .filters(f -> f.filter(filter))
                        .uri(usuarioServiceUrl))
                .build();
    }
}

