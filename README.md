# Caching Example

This is a sample project to illustrate how to configure hibernate 2nd level caching using redis as a cache provider. The project also contains configuration of swagger API documentation for secured APIs.

To test the caching implementation you must have [Redis](https://redis.io/download) installed.

### Reference Documentation

For reference on technologies used in this project, please consider the following sections:

* [Hibernate 2nd Level Caching with Redis](https://github.com/redisson/redisson/tree/master/redisson-hibernate)
* [Step by step guide on integrating Redis with Hibernate](https://dzone.com/articles/caching-in-hibernate-with-redis)

For more general reference, consider the following section:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.2/maven-plugin/reference/html/#build-image)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#boot-features-security)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#boot-features-jpa-and-spring-data)

### Guides

1. To run the application using docker:
    - Must have [docker](https://docs.docker.com/get-started/) up and running (obviously!)
    - Build the application image using: `docker build -t springio/caching-example .`
    - Run the built image using: `docker run --net="host" --name caching-example springio/caching-example`


2. To run the application using maven:
    - Must have [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) or later installed
    - Must have [Maven 3.2+](https://maven.apache.org/download.cgi) installed
    - Build the application jar using: `mvn package`
    - Run the application using: `java -jar target/caching-example-0.0.1-SNAPSHOT.jar`


3. To get an authentication token, use:

        username: testing
        password: tester

The following guides illustrate how to use some features concretely:

* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

