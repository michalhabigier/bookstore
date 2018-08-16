package pl.mh.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"pl.mh.bookstore.service", "pl.mh.bookstore.web.security", "pl.mh.bookstore.web.controller"})
@EnableJpaRepositories("pl.mh.bookstore.repository")
@EntityScan("pl.mh.bookstore.domain")
public class BookstoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);

	}
}
