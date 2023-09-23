package br.com.nathanf.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nathanf.exceptions.ResourceNotFoundException;
import br.com.nathanf.model.Person;
import br.com.nathanf.repositories.PersonRepository;

@Service
public class PersonServices {
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	public List<Person> findAll() {
		logger.info("Finding all people");
		return repository.findAll() ;
	}

	public Person findById(Long personId) {
		logger.info("Finding one person");
		Person person = repository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return person;
	}
	
	public Person create(Person person) {
		logger.info("Creating a person");
		return repository.save(person);
	}
	
	public Person update(Person person) {
		logger.info("Updating a person");
		
		Person updatedPerson = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		updatedPerson.setFirstName(person.getFirstName());
		updatedPerson.setLastName(person.getLastName());
		updatedPerson.setAddress(person.getAddress());
		updatedPerson.setGender(person.getGender());
		
		return repository.save(updatedPerson);
	}
	
	public void delete(Long id) {
		logger.info("Deleting a person");
		
		Person person = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		repository.delete(person);
	}
}
