package br.com.nathanf.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nathanf.data.dto.v1.PersonDto;
import br.com.nathanf.data.dto.v2.PersonDtoV2;
import br.com.nathanf.exceptions.ResourceNotFoundException;
import br.com.nathanf.mapper.DozerMapper;
import br.com.nathanf.mapper.custom.PersonMapper;
import br.com.nathanf.model.Person;
import br.com.nathanf.repositories.PersonRepository;

@Service
public class PersonServices {
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonMapper mapper;
	
	public List<PersonDto> findAll() {
		logger.info("Finding all people");
		return DozerMapper.parseListObjects(repository.findAll(), PersonDto.class);
	}

	public PersonDto findById(Long personId) {
		logger.info("Finding one person");
		Person personEntity = repository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerMapper.parseObject(personEntity, PersonDto.class);
	}
	
	public PersonDto create(PersonDto person) {
		logger.info("Creating a person");
		Person personEntity = DozerMapper.parseObject(person, Person.class);
		PersonDto personDto = DozerMapper.parseObject(repository.save(personEntity), PersonDto.class);
		return personDto;
	}
	
	public PersonDtoV2 createV2(PersonDtoV2 person) {
		logger.info("Creating a person with V2");
		Person personEntity = mapper.convertDtoToEntity(person);
		PersonDtoV2 personDto = mapper.convertEntityToDto(repository.save(personEntity));
		return personDto;
	}
	
	public PersonDto update(PersonDto person) {
		logger.info("Updating a person");
		
		Person updatedPersonEntity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		updatedPersonEntity.setFirstName(person.getFirstName());
		updatedPersonEntity.setLastName(person.getLastName());
		updatedPersonEntity.setAddress(person.getAddress());
		updatedPersonEntity.setGender(person.getGender());
		
		PersonDto updatedPersonDto = DozerMapper.parseObject(repository.save(updatedPersonEntity), PersonDto.class);
		
		return updatedPersonDto;
	}
	
	public void delete(Long id) {
		logger.info("Deleting a person");
		
		Person personEntity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		repository.delete(personEntity);
	}
}
