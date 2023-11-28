FROM eclipse-temurin:11-jre-alpine as builder
WORKDIR /var/lib/cowork-gateway
COPY ./target/cowork-gateway-server*[^stubs].jar cowork-gateway.jar
RUN java -Djarmode=layertools -jar cowork-gateway.jar extract

FROM eclipse-temurin:11-jre-alpine
ARG DEPENDENCY=/var/lib/cowork-gateway
WORKDIR /var/lib/cowork-gateway
COPY --from=builder ${DEPENDENCY}/dependencies/ ./
COPY --from=builder ${DEPENDENCY}/spring-boot-loader/ ./
COPY --from=builder ${DEPENDENCY}/snapshot-dependencies/ ./
COPY --from=builder ${DEPENDENCY}/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]