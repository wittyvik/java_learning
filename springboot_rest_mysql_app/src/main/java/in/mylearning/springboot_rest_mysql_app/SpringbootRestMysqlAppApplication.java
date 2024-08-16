package in.mylearning.springboot_rest_mysql_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringbootRestMysqlAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestMysqlAppApplication.class, args);
	}

}
