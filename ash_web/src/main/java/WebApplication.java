

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@ComponentScan({"idv.amazingsora"})
@EntityScan("idv.amazingsora")
@SpringBootApplication(scanBasePackages = "idv.amazingsora")
@EnableJpaRepositories("idv.amazingsora.Repository")

public class WebApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

		SpringApplication.run(WebApplication.class, args);

	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(WebApplication.class);
	}

}
