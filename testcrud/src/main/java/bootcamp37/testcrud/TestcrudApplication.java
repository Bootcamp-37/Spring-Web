package bootcamp37.testcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TestcrudApplication {
    @Bean
        public RestTemplate getRestTemplate(){
            return new RestTemplate();
        }

	public static void main(String[] args) {
                
		SpringApplication.run(TestcrudApplication.class, args);
                System.out.println("Aplikasi Berjalan");
                
	}

}
