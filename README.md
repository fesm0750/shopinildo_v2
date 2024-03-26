# Shopinildo

Shopinildo é um back-end de e-commerce desenvolvido como elemento avaliativo da pós-graduação em Arquitetura e Desenvolvimento Java, Póstech, Fiap.

A plataforma é construído sobre uma arquitetura de serviços e utiliza as funcionalidades do framework Spring.

Requsitos: Java 17, Mongo DB, Docker e Docker Composer.

## Módulos e Serviços

Os serviços módulos e ou serviços compõem a aplicação:

- Gateway: roteamento de caminhos, controle de acesso dos endpoints, authentication and authorization;

- Usuario: cadastro de clientes

- Item: cadastro de produtos

- Estoque: controle de estoque

- Carrinho: carrinho de compras do cliente/usuário

- Pedido: efetivação de compra

- Pagamento Mock: mock do sistema de pagamento

## Documentação da API

A biblioteca de requisições foi publicada via Postman e pode ser acessada no seguinte endereço:

[Shopinildo Gateway Postman](https://documenter.getpostman.com/view/28287436/2sA35D53cX)

## Como inicar o projeto

### Com o docker

Caso tenha o docker, basta executar o seguinte comando na **raiz do projeto**:

```bash
docker compose up -d
```

Os projetos serão construídos e inicializados como containeres individuais, juntamente com instâncias do Mongo DB e do Mongo Express.

### Execução local

Para executar os projetos de forma local pode-se utilizar o wrapper do Maven disponibilizado em cada serviço. Para isto será necessário ter o Java 17 instalado e configurado e uma instância do Mongo DB rodando na porta 27017. Com os requisitos ajustados, basta apontar o terminal para a pasta do serviço ou módulo que se deseja executar e rodar o comando abaixo.

```bash
./mvnw spring-boot:run
```

O banco de dados pode ser executado via docker. Para isto, na pasta `mongo` está presente um arquivo `docker-compose` que configura e realiza a subida de containeres contendo o banco de dados Mongo DB e da ferramenta de gestão Mongo Express.

Para subir somente o banco de dados via docker, basta entrar na pasta `mongo` e executar o `docker compose`, conforme os comandos abaixo:

```bash
cd mongo
docker compose up -d
```


## Estoque



## Notas

Foi escolhido o uso de um repositório de código único (monorepo) para todos os serviços em razão da simplicidade da solução e à escala do projeto (educacional).

Padrão de commit:

SERVICE tag: message

Sessão de usuário -> fazer stateless (sem sessão), controlar acesso via token jwt

Fazer tudo síncrono e depois adicionar chamadas assíncronas se necessário, ex.: estoque.

Estranho Bug em que o Map na entidade carrinho estava salvando as quantidades como array. Tive que reforçar em todas as chamadas ao Map que o tipo do valor era Integer Map<String, Integer> and HashMap<String,Integer>. Provavelmente em alguma das chamadas padrão, sem a identificação concreta dos tipos, estava sendo gerado por baixo dos panos valor do tipo Array ou List.

Discussões sobre endpoints Post, Put e Patch
- Post: adicionar item ao carrinho, se carrinho não existir, cria o carrinho
- Put: estoque possui apenas quantidade, então ao chamar o PUT irá criar ou alterar todo o objeto já existente
- Patch: increase and decrease estoque
