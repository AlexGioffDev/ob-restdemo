package com.example.obrestdemo.service;

import com.example.obrestdemo.entities.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookPriceCalculatorTest {

    @Test
    void calculatePrice() {
        Book book = new Book(
                1L,
                "Prueba",
                "Author",
                1000,
                49.99,
                LocalDate.now(),
                true
        );
        BookPriceCalculator calculator = new BookPriceCalculator();
        double price = calculator.calculatePrice(book);
        assertTrue(price > 0.0);
        assertEquals(57.980000000000004, price);


    }
}