package com.example.stupek.person;

import com.example.stupek.exception.NotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonMapper personMapper;
    private final PersonListMapper personListMapper;
    private final PersonRepository personRepository;

    @Override
    @Transactional
    public PersonViewDto create(@Valid PersonDto personDto) {
        Person newPerson = personMapper.toPerson(personDto);
        personRepository.save(newPerson);
        log.info("Person with id={} was registration successfully", newPerson.getId());
        return personMapper.toPersonViewDto(newPerson);
    }

    @Override
    @Transactional
    public PersonViewDto updateById(Long personId, PersonDto updatedPerson) {
        Person foundPerson = getPersonById(personId);
        if (!foundPerson.getLogin().equals(updatedPerson.getLogin())) {
            foundPerson.setLogin(updatedPerson.getLogin());
        }
        if (!foundPerson.getPassword().equals(updatedPerson.getPassword())) {
            foundPerson.setPassword(updatedPerson.getPassword());
        }
        if (!foundPerson.getEmail().equals(updatedPerson.getEmail())) {
            foundPerson.setEmail(updatedPerson.getEmail());
        }
        if (!foundPerson.getBalance().equals(updatedPerson.getBalance())) {
            foundPerson.setBalance(updatedPerson.getBalance());
        }
        if (!foundPerson.getPersonRole().equals(updatedPerson.getPersonRole())) {
            foundPerson.setPersonRole(updatedPerson.getPersonRole());
        }
        foundPerson.setLastUpdate(LocalDateTime.now());
        personRepository.save(foundPerson);
        log.info("Person with id={} was updated successfully", personId);
        return personMapper.toPersonViewDto(foundPerson);
    }


    @Override
    @Transactional(readOnly = true)
    public PersonViewDto findById(Long personId) {
        Person foundPerson = getPersonById(personId);
        log.info("Person with id={} was found successfully", personId);
        return personMapper.toPersonViewDto(foundPerson);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonViewDto> findAll(Integer offset, Integer limit) {
        List<Person> persons = personRepository.findAll(
                        PageRequest.of(offset, limit))
                .toList();
        log.info("Persons was found successfully");
        return personListMapper.toPersonViesDtoList(persons);
    }

    @Override
    @Transactional
    public void deleteById(Long personId) {
        Person foundPerson = getPersonById(personId);
        personRepository.delete(foundPerson);
        log.info("Person with id={} was deleted successfully", personId);
    }

    private Person getPersonById(Long personId) {
        return personRepository
                .findById(personId)
                .orElseThrow(() -> {
                    log.warn("Person with id={} was not found", personId);
                    return new NotFoundException("Person with id=" + personId + " was not found");
                });
    }
}