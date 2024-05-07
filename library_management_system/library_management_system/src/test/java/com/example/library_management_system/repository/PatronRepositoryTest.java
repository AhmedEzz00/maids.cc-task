package com.example.library_management_system.repository;


import com.example.library_management_system.entity.Patron;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class PatronRepositoryTest {

    @Autowired
    private PatronRepository patronRepository;

    @Test
    public void saveTest() {
        Patron patron = new Patron("Patron1", "111111111", "save@email.com");
        patronRepository.save(patron);
        Assertions.assertThat(patron).isNotNull();
        Assertions.assertThat(patron.getId()).isGreaterThan(0);
    }

    @Test
    public void findByEmailTest() {
        patronRepository.save(new Patron("Patron2", "222222222", "byEmail@email.com"));
        Optional<Patron> optionalPatron = patronRepository.findByEmail("byEmail@email.com");
        Assertions.assertThat(optionalPatron.get()).isNotNull();
        Assertions.assertThat(optionalPatron.get().getId()).isGreaterThan(0);
    }

    @Test
    public void findByMobileNumberTest() {
        patronRepository.save(new Patron("Patron3", "333333333", "byMobile@email.com"));
        Optional<Patron> optionalPatron = patronRepository.findByMobileNumber("333333333");
        Assertions.assertThat(optionalPatron.get()).isNotNull();
        Assertions.assertThat(optionalPatron.get().getId()).isGreaterThan(0);
    }

}
