package com.progra3.petstore.services;

import com.progra3.petstore.entities.Pet;
import com.progra3.petstore.exceptions.NotFoundException;
import com.progra3.petstore.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService{
    @Autowired
    private PetRepository petRepository;


    @Override
    public List<Pet> listAll() {
        return (List<Pet>) petRepository.findAll();
    }

    @Override
    public Pet findById(Long id) {
        if (!petRepository.existsById(id)){
            throw new NotFoundException("Su mascota no fué encontrada");
        }
        return petRepository.findById(id).orElse(null);
    }

    @Override
    public Pet createPet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public Pet updatePet(Long id, Pet pet) {
        if (!petRepository.existsById(id)){
            throw new NotFoundException("Su mascota no fué actualizada");
        }
        Optional<Pet> mascota = petRepository.findById(id);
        mascota.get().setId(id);
        mascota.get().setName(pet.getName());
        mascota.get().setPrice(pet.getPrice());
        mascota.get().setBirthDay(pet.getBirthDay());

        return petRepository.save(mascota.get());
    }

    @Override
    public void deletePet(Long id) {
        if (!petRepository.existsById(id)){
            throw new NotFoundException("Su mascota no fué encontrada");
        }
        petRepository.deleteById(id);
    }
}
