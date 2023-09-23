package br.com.nathanf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nathanf.model.Person;
import br.com.nathanf.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonServices service;
	//private PersonServices service = new PersonServices();
	
	@GetMapping("/{id}")
	public Person findById(@PathVariable(value = "id") Long id ){
		return service.findById(id);
	}
	
	@GetMapping()
	public List<Person> findAll(){
		return service.findAll();
	}
	
	@PostMapping()
	public Person create(@RequestBody Person person){
		return service.create(person);
	}
	
	@PutMapping()
	public Person update(@RequestBody Person person){
		return service.update(person);
	}
	
	@DeleteMapping("/{id}")
	public void update(@PathVariable(value = "id") Long id){
		service.delete(id);
	}

}
