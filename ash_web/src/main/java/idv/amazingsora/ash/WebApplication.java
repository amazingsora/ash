package idv.amazingsora.ash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "idv.amazingsora.ash")
public class WebApplication {

    public static void main(String[] args) {

        SpringApplication.run(WebApplication.class, args);

    }

}
