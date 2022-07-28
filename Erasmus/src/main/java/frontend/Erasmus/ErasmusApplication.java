package frontend.Erasmus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ErasmusApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErasmusApplication.class, args);
	}

}
