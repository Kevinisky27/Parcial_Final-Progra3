package com.progra3.petstore.controllers;

import java.util.List;

import com.progra3.petstore.entities.Pet;
import com.progra3.petstore.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/mascotas")
public class PetController {

	@Autowired
	private PetService service;

	@GetMapping
	public List<Pet> findAll(){
		return service.listAll();
	}

	@GetMapping("/{id}")
	public Pet findPet(Long id) {
		return service.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pet createPet(@Valid @RequestBody Pet pet) {
		return service.createPet(pet);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Pet updatePet(@PathVariable Long id, @Valid @RequestBody Pet pet) {
		return service.updatePet(id, pet);
	}

	@DeleteMapping("/{id}")
	public void deletePet(@PathVariable Long id) {
		service.deletePet(id);
	}

}
