FROM eclipse-temurin:11-jre-alpine as builder
WORKDIR /var/lib/advertising-service
COPY ./target/advertising-server*[^stubs].jar advertising-service.jar
RUN java -Djarmode=layertools -jar advertising-service.jar extract

FROM eclipse-temurin:11-jre-alpine
ARG DEPENDENCY=/var/lib/advertising-service
COPY --from=builder ${DEPENDENCY}/dependencies/ ./
COPY --from=builder ${DEPENDENCY}/spring-boot-loader/ ./
COPY --from=builder ${DEPENDENCY}/snapshot-dependencies/ ./
COPY --from=builder ${DEPENDENCY}/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]