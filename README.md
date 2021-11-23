# Spring-Webflux-Template

# Execução local

## Utilização de base de dados Postgres em Docker

- Instalar Docker
- Criar arquivo docker-compose.yml conforme exemplo abaixo:

```yaml
version: '2'
services:
  postgres:
    image: postgres:13.4
    container_name: postgres
    ports:
      - "5432:5432"
    volumes:
      - "./data:/var/lib/postgresql/data"
    environment:
      POSTGRES_PASSWORD: admin12345
      POSTGRES_USER: root
      POSTGRES_DB: template
```

- Acessar a base de dados com um client SQL, usando usuário e senha como está no Docker-Compose e criar o schema `template` através do comando:
```sql
CREATE SCHEMA template
```

## Execução das migrações das bases de dados

Assumindo que o nome do usuário é root e a senha é admin12345 em uma base Postgres local com o schema template criado.

```shell
scripts/db-migrate.sh 'jdbc:postgresql://localhost:5432/postgres?currentSchema=template' root admin12345
```

## Execução do serviço

```shell
./gradlew service:bootRun
```

## Execução dos testes de driver
```shell
scripts/driver-tests.sh
```

# Criação dos Artefatos

Irá criar os arquivos .jar de contrato e driver para que sejam publicados em repositório de artefatos e importados em outros projetos.

```shell
scripts/build-artifacts.sh
```

# Criação da Imagem

Imagem criada com Buildpack através de comando

```shell
./gradlew service:bootBuildImage
```

Para executar a imagem:
```shell
docker run -p8080:8080 docker.io/selat/spring-webflux-template:1.0
```
