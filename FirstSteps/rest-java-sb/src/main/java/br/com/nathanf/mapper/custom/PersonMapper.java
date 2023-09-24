package br.com.nathanf.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.nathanf.data.dto.v2.PersonDtoV2;
import br.com.nathanf.model.Person;

@Service
public class PersonMapper {
	public PersonDtoV2 convertEntityToDto(Person person) {
		PersonDtoV2 personDtoV2 = new PersonDtoV2();
		personDtoV2.setId(person.getId());
		personDtoV2.setFirstName(person.getFirstName());
		personDtoV2.setLastName(person.getLastName());
		personDtoV2.setBirthDay(new Date());
		personDtoV2.setAddress(person.getAddress());
		personDtoV2.setGender(person.getGender());
		return personDtoV2;
	}
	
	public Person convertDtoToEntity(PersonDtoV2 person) {
		Person personEntity = new Person();
		personEntity.setFirstName(person.getFirstName());
		personEntity.setLastName(person.getLastName());
		personEntity.setAddress(person.getAddress());
		personEntity.setGender(person.getGender());
		return personEntity;
	}
}
