package hu.cowork.gateway;

import hu.cowork.advertising.AdvertisingApiClient;
import hu.cowork.advertising.RatingApiClient;
import hu.cowork.comment_service.CommentApiClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackageClasses = {AdvertisingApiClient.class,RatingApiClient.class,CommentApiClient.class})
public class CoworkGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoworkGatewayApplication.class, args);
    }

}
