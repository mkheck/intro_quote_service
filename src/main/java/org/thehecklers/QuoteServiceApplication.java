package org.thehecklers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.List;

@SpringBootApplication
public class QuoteServiceApplication {
    @Bean
    CommandLineRunner commandLineRunner(QuoteRepo quoteRepo) {
        return args -> {
            quoteRepo.save(new Quote("I can resist everything except temptation.", "Oscar Wilde"));
            quoteRepo.save(new Quote("I never said most of the things I said.", "Yogi Berra"));
            quoteRepo.save(new Quote("The older I get, the older I want to get.", "Keith Richards"));

            quoteRepo.findAll().forEach(System.out::println);
        };
    }

	public static void main(String[] args) {
		SpringApplication.run(QuoteServiceApplication.class, args);
	}
}

@RepositoryRestResource
interface QuoteRepo extends CrudRepository<Quote, Long> {
    @Query("select quote from Quote quote order by rand()")
    List<Quote> randomQuote();
}

@RestController
class QuoteController {
    @Autowired
    QuoteRepo quoteRepo;

    @RequestMapping("/random")
    public Quote getRandomQuote() {
        return quoteRepo.randomQuote().getFirst();
    }

    @RequestMapping("/test")
    public String getTest() {
        return "This is a test of the emergency broadcast system.";
    }
}

@Entity
class Quote {
    @Id
    @GeneratedValue
    private Long id;
    private String text;
    private String source;

    Quote() {
    }

    public Quote(String text, String source) {
        this.text = text;
        this.source = source;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getSource() {
        return source;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "text='" + text + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
