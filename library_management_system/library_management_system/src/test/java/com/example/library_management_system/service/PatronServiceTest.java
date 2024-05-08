package com.example.library_management_system.service;

import com.example.library_management_system.dto.BookDto;
import com.example.library_management_system.dto.PatronDto;
import com.example.library_management_system.entity.Book;
import com.example.library_management_system.entity.Patron;
import com.example.library_management_system.repository.PatronRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PatronServiceTest {

    @Mock
    private PatronRepository patronRepository;

    @InjectMocks
    private PatronService patronService;


    @Test
    public void createBookTest() {
        PatronDto patronDto = new PatronDto("test patron", "test@email.com", "0123567");
        Patron patron = new Patron("test Patron1", "testEmail@email.com", "0123567");
        Mockito.when(patronRepository.save(Mockito.any(Patron.class))).thenReturn(patron);
        String createdPatron = patronService.insert(patronDto);
        Assertions.assertThat(createdPatron).isNotEmpty();
    }

    @Test
    public void findAllTest() {
        List<Patron> patrons= new ArrayList<>();
        Mockito.when(patronRepository.findAll()).thenReturn(patrons);
        List<Patron> returned = patronService.findAll();
        Assertions.assertThat(returned).isEmpty();
    }

    @Test
    public void findByIdTest() {
        Optional<Patron> patron = Optional.of(new Patron( "patron", "email1@email.com", "0123567"));
        Mockito.when(patronRepository.findById(Mockito.anyLong())).thenReturn(patron);
        Optional<PatronDto> patronDto = Optional.ofNullable(patronService.findById(1L));
        assertEquals(true, patronDto.isPresent());
    }


    @Test
    public void updateBookTest() {
        PatronDto patronDto = new PatronDto("test patron", "test@email.com", "0123567");
        Patron patron = new Patron("test Patron1", "testEmail@email.com", "0123567");
        Mockito.when(patronRepository.save(Mockito.any(Patron.class))).thenReturn(patron);
        Mockito.when(patronRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(patron));
        String updatedPatron = patronService.update(1L,patronDto);
        Assertions.assertThat(updatedPatron).isNotEmpty();
    }

    @Test
    public void deleteBookTest(){
        Patron patron= new Patron("test Patron1", "testEmail@email.com", "0123567");
        Mockito.when(patronRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(patron));
        assertAll(()-> patronService.deleteById(1L));
    }


}
