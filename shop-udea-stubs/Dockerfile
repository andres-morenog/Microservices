FROM anapsix/alpine-java:8
VOLUME /tmp
ADD target/shop-udea-stubs-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-cp","/app.jar", "co.udea.microservices.stubs.WireMockServerBootstrap"]