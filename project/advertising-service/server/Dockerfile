FROM eclipse-temurin:11-jre-alpine as builder
WORKDIR /var/lib/advertising-service
COPY ./target/advertising-server-1.0.0-SNAPSHOT.jar advertising-service.jar
RUN java -Djarmode=layertools -jar advertising-service.jar extract

FROM eclipse-temurin:11-jre-alpine
EXPOSE 8081
VOLUME /tmp
ARG DEPENDENCY=/var/lib/advertising-service
COPY --from=builder ${DEPENDENCY}/dependencies/ ./
COPY --from=builder ${DEPENDENCY}/spring-boot-loader/ ./
COPY --from=builder ${DEPENDENCY}/snapshot-dependencies/ ./
COPY --from=builder ${DEPENDENCY}/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]