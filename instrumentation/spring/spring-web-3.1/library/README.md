# Manual Instrumentation for Spring-Web

Provides OpenTelemetry instrumentation for Spring's RestTemplate.

## Quickstart

### Add these dependencies to your project.

Replace `SPRING_VERSION` with the version of spring you're using.
`Minimum version: 3.1`

Replace `OPENTELEMETRY_VERSION` with the latest
stable [release](https://mvnrepository.com/artifact/io.opentelemetry).
`Minimum version: 1.4.0`

For Maven add to your `pom.xml`:

```xml

<dependencies>
  <!-- opentelemetry -->
  <dependency>
    <groupId>io.opentelemetry.instrumentation</groupId>
    <artifactId>opentelemetry-spring-web-3.1</artifactId>
    <version>OPENTELEMETRY_VERSION</version>
  </dependency>

  <!-- provides opentelemetry-sdk -->
  <dependency>
    <groupId>io.opentelemetry</groupId>
    <artifactId>opentelemetry-exporters-logging</artifactId>
    <version>OPENTELEMETRY_VERSION</version>
  </dependency>

  <!-- required to instrument spring-web -->
  <!-- this artifact should already be present in your application -->
  <dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-web</artifactId>
    <version>SPRING_VERSION</version>
  </dependency>

</dependencies>
```

For Gradle add to your dependencies:

```groovy
implementation("io.opentelemetry.instrumentation:opentelemetry-spring-web-3.1:OPENTELEMETRY_VERSION")
implementation("io.opentelemetry:opentelemetry-exporters-logging:OPENTELEMETRY_VERSION")

//this artifact should already be present in your application
implementation("org.springframework:spring-web:SPRING_VERSION")
```

### Features

#### Telemetry-producing `ClientHttpRequestInterceptor` implementation

`SpringWebTracing` allows creating a
custom [ClientHttpRequestInterceptor](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/http/client/ClientHttpRequestInterceptor.html)
that produces telemetry for HTTP requests sent using a `RestTemplate`. Example:

##### Usage

```java

import io.opentelemetry.instrumentation.spring.web.SpringWebTracing;
import io.opentelemetry.api.OpenTelemetry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

  @Bean
  public RestTemplate restTemplate(OpenTelemetry openTelemetry) {

    RestTemplate restTemplate = new RestTemplate();
    SpringWebTracing springWebTracing = SpringWebTracing.create(openTelemetry);
    restTemplate.getInterceptors().add(springWebTracing.newInterceptor());

    return restTemplate;
  }
}
```

### Starter Guide

Check out the
OpenTelemetry [quick start](https://github.com/open-telemetry/opentelemetry-java/blob/master/QUICKSTART.md)
to learn more about OpenTelemetry instrumentation.
