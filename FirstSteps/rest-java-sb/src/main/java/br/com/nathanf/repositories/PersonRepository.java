package br.com.nathanf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nathanf.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	
}
