# Trabalho de IoT

## Como rodar o back-end?

### Requisitos
- Ter a versão 17 do Java instalada;
- Ter o Docker ou similar instalado;

### Rodando

- Para rodar o ambiente MongoDB, é necessário executar o comando abaixo, na raiz do projeto:

```sh
docker-compose up -d
```

- Para rodar a API é necessário entrar na pasta `backend/api` e rodar o comando:

```sh
./mvnw spring-boot:run
```

- É possível acessar uma página swagger que facilita na visualização dos dados: http://localhost:8080/swagger-ui/index.html#