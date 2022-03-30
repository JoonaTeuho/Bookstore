package com.example.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;


@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository bookrepository, CategoryRepository catrepository) {
		return (args) -> {
			log.info("save some books");
			catrepository.save(new Category("Fantasy"));
			catrepository.save(new Category("Sci-Fi"));
			catrepository.save(new Category("Horror"));
			bookrepository.save(new Book("Example", "John Johnson", 2012, "999-3-12-123456-1", 10, catrepository.findByName("Fantasy").get(0)));
			bookrepository.save(new Book("Esimerkki", "Jane Johnson", 1977, "999-3-12-123456-2", 20, catrepository.findByName("Sci-Fi").get(0)));	
			
			log.info("fetch all books");
			for (Book book : bookrepository.findAll()) {
				log.info(book.toString());
			}

		};
	}

}