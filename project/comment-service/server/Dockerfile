FROM eclipse-temurin:11-jre-alpine as builder
WORKDIR /var/lib/comment-service
COPY ./target/comment-service-server-1.0.0-SNAPSHOT.jar comment-service.jar
RUN java -Djarmode=layertools -jar comment-service.jar extract

FROM eclipse-temurin:11-jre-alpine
EXPOSE 8080
VOLUME /tmp
ARG DEPENDENCY=/var/lib/comment-service
COPY --from=builder ${DEPENDENCY}/dependencies/ ./
COPY --from=builder ${DEPENDENCY}/spring-boot-loader/ ./
COPY --from=builder ${DEPENDENCY}/snapshot-dependencies/ ./
COPY --from=builder ${DEPENDENCY}/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]