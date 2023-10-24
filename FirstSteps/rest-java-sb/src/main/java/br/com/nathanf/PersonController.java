package br.com.nathanf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nathanf.data.dto.v1.PersonDto;
import br.com.nathanf.data.dto.v2.PersonDtoV2;
import br.com.nathanf.services.PersonServices;
import br.com.nathanf.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/person")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController {
	
	
	@Autowired
	private PersonServices service;
	
	@GetMapping(value = "/{id}", 
			produces = { MediaType.APPLICATION_JSON, 
						MediaType.APPLICATION_XML,
						MediaType.APPLICATION_YML })
	@Operation(summary = "Find a Person", description = "Find a Person",
	tags = {"People"},
	responses = {
		@ApiResponse(description = "Success", responseCode = "200", 
				content = @Content(schema = @Schema(implementation = PersonDto.class))
		),
		@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
		@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
	})
	public PersonDto findById(@PathVariable(value = "id") Long id ){
		return service.findById(id);
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON, 
							MediaType.APPLICATION_XML,
							MediaType.APPLICATION_YML })
	@Operation(summary = "Finds all people", description = "Finds all people",
	tags = {"People"},
	responses = {
		@ApiResponse(description = "Success", responseCode = "200", 
				content = {
					@Content(
						mediaType = "application/json",
						array = @ArraySchema(schema = @Schema(implementation = PersonDto.class))
					)
				}),
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
		@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
	})
	public List<PersonDto> findAll(){
		return service.findAll();
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON, 
			produces = { MediaType.APPLICATION_JSON, 
						MediaType.APPLICATION_XML,
						MediaType.APPLICATION_YML })
	@Operation(summary = "Create a new Person", description = "Create a new Person",
	tags = {"People"},
	responses = {
		@ApiResponse(description = "Success", responseCode = "200", 
				content = @Content(schema = @Schema(implementation = PersonDto.class))
		),
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
	})
	public PersonDto create(@RequestBody PersonDto person){
		return service.create(person);
	}
	
	/*@PostMapping(value = "/v2", consumes = MediaType.APPLICATION_JSON, 
			produces = { MediaType.APPLICATION_JSON, 
						MediaType.APPLICATION_XML,
						MediaType.APPLICATION_YML })
	public PersonDtoV2 createV2(@RequestBody PersonDtoV2 person){
		return service.createV2(person);
	}*/
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON, 
			produces = { MediaType.APPLICATION_JSON, 
						MediaType.APPLICATION_XML,
						MediaType.APPLICATION_YML })
	@Operation(summary = "Update a Person", description = "Create a Person",
	tags = {"People"},
	responses = {
		@ApiResponse(description = "Updated", responseCode = "200", 
				content = @Content(schema = @Schema(implementation = PersonDto.class))
		),
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
		@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
	})
	public PersonDto update(@RequestBody PersonDto person){
		return service.update(person);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Delete a Person", description = "Delete a Person",
	tags = {"People"},
	responses = {
		@ApiResponse(description = "No content", responseCode = "204", content = @Content()),
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
		@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
