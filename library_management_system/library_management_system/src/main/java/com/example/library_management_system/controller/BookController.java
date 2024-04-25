package com.example.library_management_system.controller;
import com.example.library_management_system.dto.BookDto;
import com.example.library_management_system.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        return ResponseEntity.ok( bookService.findById(id));
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @PostMapping()
    public void insert(@RequestBody @Valid BookDto book) {
        bookService.insert(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id ,@RequestBody BookDto bookDto) {
        return ResponseEntity.ok(bookService.update(id,bookDto));
    }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable long id) {
      bookService.deleteById(id);
  }

//    @DeleteMapping("/author/{id}")
//    public ResponseEntity<?> deleteByAuthorId(@PathVariable long id) {
//       return ResponseEntity.ok( bookService.deleteByAuthorId(id));
//    }

}
