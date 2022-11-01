package com.example.obrestdemo.controller;

import com.example.obrestdemo.entities.Book;
import com.example.obrestdemo.repository.BookRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private final Logger log = LoggerFactory.getLogger(BookController.class);
    private BookRepository bookrepository;

    public BookController(BookRepository bookrepository) {
        this.bookrepository = bookrepository;
    }

    // CRUD Sobre la entidad de Book

    // Buscar todos los libros (Lista )

    /**
     * url: http://localhost:8080/api/books
     * @return una Lista de Books desde el repository
     */
    @GetMapping("/api/books")
    public List<Book> findAll() {

        return bookrepository.findAll();
    }

    // Buscar un solo libro en caso de datos segun su id

    /**
     * Request
     * Response
     * @param id
     * @return
     */
    @GetMapping("/api/books/{id}")
    @ApiOperation(
            "Buscar un libro por clava primvaria id Long"
    )
    public ResponseEntity<Book> findOneById(@ApiParam("Clave primaria tipo Long") @PathVariable Long id) {
        Optional<Book> bookOpt = bookrepository.findById(id);
        return bookOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Creare un nuevo Libro


    @PostMapping("/api/books")
    public ResponseEntity<Book> create(@RequestBody Book book) {
        // Guardar el libro recebito por parametro en la base de datos
        if(book.getId() != null) {
            log.warn("Trying to create a book with ID");
            return ResponseEntity.badRequest().build();
        }
        Book result = bookrepository.save(book);
        return ResponseEntity.ok(result);
    }

    /**
     * Actualizar libro existente (editar)
     */
    @PutMapping("/api/books/")
    public ResponseEntity<Book> update(@RequestBody Book book){
        if(book.getId() == null) {
            log.warn("Trying to update a book without ID");
            return ResponseEntity.badRequest().build();
        }
        if(!bookrepository.existsById(book.getId())){
            log.warn("Trying to update a book that not exist");
            return ResponseEntity.notFound().build();
        }
        Book result = bookrepository.save(book);
        return ResponseEntity.ok(result);
    }

    @ApiIgnore
    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id) {

        if(!bookrepository.existsById(id)){
            log.warn("Trying to delete a non existent book");
            return ResponseEntity.notFound().build();
        }

        bookrepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @ApiIgnore
    @DeleteMapping("/api/books")
    public ResponseEntity<Book> deleteAll() {
        log.info("Deleting all Books");
        bookrepository.deleteAll();

        return ResponseEntity.noContent().build();
    }
}
