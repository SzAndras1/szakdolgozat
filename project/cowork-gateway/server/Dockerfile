FROM nexus.capsys.hu:5000/capsys/base-image:latest as builder
WORKDIR /var/lib/cs_home-banking-gateway
COPY cs_home-banking-gateway-server*[^stubs].jar /var/lib/cs_home-banking-gateway/cs_home-banking-gateway-server.jar
RUN java -Djarmode=layertools -jar /var/lib/cs_home-banking-gateway/cs_home-banking-gateway-server.jar extract

FROM nexus.capsys.hu:5000/capsys/base-image:latest
WORKDIR /var/lib/cs_home-banking-gateway
COPY --from=builder /var/lib/cs_home-banking-gateway/dependencies/ ./
COPY --from=builder /var/lib/cs_home-banking-gateway/spring-boot-loader/ ./
COPY --from=builder /var/lib/cs_home-banking-gateway/snapshot-dependencies/ ./
COPY --from=builder /var/lib/cs_home-banking-gateway/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
