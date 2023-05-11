package com.example.stupek.person;

import com.example.stupek.exception.NotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.nio.CharBuffer;
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
    private final PasswordEncoder passwordEncoder;

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
    public PersonViewDto updateById(@Min(1) Long personId, @Valid PersonDto updatedPerson) {
        Person foundPerson = getPersonById(personId);
        if (!foundPerson.getLogin().equals(updatedPerson.getLogin())) {
            foundPerson.setLogin(updatedPerson.getLogin());
        }
        if (!passwordEncoder.matches(CharBuffer.wrap(updatedPerson.getPassword()), foundPerson.getPassword())) {
            foundPerson.setPassword(passwordEncoder.encode(CharBuffer.wrap(updatedPerson.getPassword())));
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
    public PersonViewDto findById(@Min(1) Long personId) {
        Person foundPerson = getPersonById(personId);
        log.info("Person with id={} was found successfully", personId);
        return personMapper.toPersonViewDto(foundPerson);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonViewDto> findAll(@Min(0) Integer offset, @Min(1) Integer limit) {
        List<Person> persons = personRepository.findAll(
                        PageRequest.of(offset, limit))
                .toList();
        log.info("Persons was found successfully");
        return personListMapper.toPersonViesDtoList(persons);
    }

    @Override
    @Transactional
    public void deleteById(@Min(1) Long personId) {
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
