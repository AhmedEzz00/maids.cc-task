package com.example.library_management_system.service;

import com.example.library_management_system.Error.CustomErrorAttribute;
import com.example.library_management_system.Error.exceptions.DuplicatedRecordException;
import com.example.library_management_system.Error.exceptions.RecordNotFoundException;
import com.example.library_management_system.dto.PatronDto;
import com.example.library_management_system.entity.Patron;
import com.example.library_management_system.repository.PatronRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatronService {

    @Autowired
    private PatronRepsitory patronRepsitory;

    @Cacheable(value = "allPatronsCache",key = "#root.methodName")
    public List<Patron> findAll() {
        return patronRepsitory.findAll();
    }

    @Cacheable(value = "patronsByIdCache",key = "#id")
    public PatronDto findById(long id) {
        Optional<Patron> optionalPatron= patronRepsitory.findById(id);
        if(!optionalPatron.isPresent()){
            throw new RecordNotFoundException("Patron with id: " +id+" not found");
        }
        PatronDto patronDto= new PatronDto();
        mapPatronToDto(optionalPatron.get(),patronDto);
        return patronDto;
    }

    @CacheEvict(value = {"allPatronsCache","patronsByIdCache"},key = "#root.methodName",allEntries = true)
    public String insert(PatronDto patronDto) {
        patronValidator(patronDto);
        Patron patron = new Patron();
        mapDtoToPatron(patronDto, patron);
        patronRepsitory.save(patron);
        return "patron with name: "+patronDto.getName()+" inserted";
    }


    @CacheEvict(value = {"allPatronsCache","patronsByIdCache"},key = "#root.methodName",allEntries = true)
    public String update(long id, PatronDto patronDto) {
        Optional<Patron> optionalPatron= patronRepsitory.findById(id);
        if(!optionalPatron.isPresent()){
            throw new RecordNotFoundException("patron with id: "+id+" not found");
        }
        Patron newPatron = optionalPatron.get();
        assignPatronValues(patronDto, newPatron);
        patronRepsitory.save(newPatron);
        return "patron with id: "+id+" updated";

    }


    @CacheEvict(value = {"allPatronsCache","patronsByIdCache"},key = "#root.methodName",allEntries = true)
    public String deleteById(long id) {
        Optional<Patron> optionalPatron= patronRepsitory.findById(id);
        if(!optionalPatron.isPresent()){
            throw new RecordNotFoundException("patron with id: "+id+" not found");
        }
        patronRepsitory.deleteById(id);
        return "patron with id: "+id+" deleted";

    }

    private void mapPatronToDto(Patron patron,PatronDto patronDto){

        patronDto.setId(patron.getId());
        patronDto.setEmail(patron.getEmail());
        patronDto.setName(patron.getName());
        patronDto.setMobileNumber(patron.getMobileNumber());
        patronDto.setBorrowingRecords(patron.getBorrowingRecords());

    }

    private void mapDtoToPatron(PatronDto patronDto, Patron patron) {
        patron.setName(patronDto.getName());
        patron.setEmail(patronDto.getEmail());
        patron.setMobileNumber(patronDto.getMobileNumber());
    }

    private void patronValidator(PatronDto patronDto) {
        Optional<Patron> optionalPatron= patronRepsitory.findByEmail(patronDto.getEmail());
        if(optionalPatron.isPresent()){
            throw new DuplicatedRecordException("patron with email: "+ patronDto.getEmail()+" already exists");
        }
        optionalPatron= patronRepsitory.findByMobileNumber(patronDto.getMobileNumber());
        if(optionalPatron.isPresent()){
            throw new DuplicatedRecordException("patron with mobile number: "+ patronDto.getMobileNumber()+" already exists");
        }
    }

    private static void assignPatronValues(PatronDto patronDto, Patron newPatron) {
        newPatron.setName(patronDto.getName()==null? newPatron.getName() : patronDto.getName());
        newPatron.setMobileNumber(patronDto.getMobileNumber()==null? newPatron.getMobileNumber() : patronDto.getMobileNumber());
        newPatron.setEmail(patronDto.getEmail()==null? newPatron.getEmail() : patronDto.getEmail());
    }
}
