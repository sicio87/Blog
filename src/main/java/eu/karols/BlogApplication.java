package eu.karols;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class BlogApplication extends SpringBootServletInitializer {
    
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BlogApplication.class);
    }
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
}
