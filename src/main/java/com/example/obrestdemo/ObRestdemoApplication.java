package com.example.obrestdemo;

import com.example.obrestdemo.entities.Book;
import com.example.obrestdemo.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class ObRestdemoApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ObRestdemoApplication.class, args);
		BookRepository repository = context.getBean(BookRepository.class);

		// Crear un libro
		Book book1 = new Book(null, "Homo Deus","Yuval Noah", 450, 29.99, LocalDate.of(2018, 12, 1), true);
		Book book2 = new Book(null, "Homo Sapiens","Yuval Noah", 450, 19.99, LocalDate.of(2013, 12, 1), true);


		// Almacenar un libro
		repository.save(book1);
		repository.save(book2);


	}

}
