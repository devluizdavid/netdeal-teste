# colaborador-netdeal-api


## Requisitos

Para executar a aplicação você precisa de :

- [JDK 17] (https://www.oracle.com/br/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 3] (https://maven.apache.org)
- [Docker] (https://docs.docker.com/get-docker/)

## Subindo o docker

Primeiramente para executar o projeto, você precisa subir o container Docker acessando a pasta sales através do prompt de comando e executando o seguinte comando


```shell
docker-compose up -d
```

## Executando a aplicação localmente

Existem várias maneiras de executar um aplicativo Spring Boot em sua máquina local. Uma maneira é executar o método `main` na classe `com.codebrain.sales.SalesApplication` do seu IDE.

Alternativamente, você pode usar o [plugin Spring Boot Maven](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) assim:

```shell
mvn spring-boot:run
```

## Documentação da Api

Após realizar o deploy da aplicação e subir com êxito, será disponibilizado uma url com as apis necessárias geradas pela aplicação. Acesse pelo navegador de sua preferência a url:

```shell
http://localhost:8083/swagger-ui.html
```

Informe o usuário: teste e senha: 123456

