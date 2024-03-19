# Shopinildo

Shopinildo é um back-end de e-commerce desenvolvido como elemento avaliativo da pós-graduação em Arquitetura e Desenvolvimento Java, Póstech, Fiap.

A plataforma é construído sobre uma arquitetura de serviços e utiliza as funcionalidades do framework Spring.

## Módulos e Serviços

- Gateway: roteamento de caminhos e controle de acesso dos endpoints;

- Login: autenticação e autorização de usuários;

- Usuario: cadastro de clientes

- Item: cadastro de produtos

- Estoque: controle de estoque

- Preço: histórico de preços

- carrinho: carrinho de compras do cliente/usuário

- pagamento: mock do sistema de pagamento e efetivação de compra

## Banco de Dados Local

Na pasta `mongo` está presente um arquivo `docker-compose` que configura e realiza a subida de containeres do banco de dados mongoDB e da ferramenta de gestão Express.

Para subir o banco de dados local:

```bash
cd mongo
docker compose up -d
```

