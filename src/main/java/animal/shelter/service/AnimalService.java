package animal.shelter.service;

import animal.shelter.model.Animal;
import animal.shelter.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {
    AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;

    }

    public List<Animal> getAllCats() {
        return animalRepository.findByType();
    }

    public List<Animal> getAllDogs(){ return animalRepository.findByType();}


    public void saveAnimal(Animal animal) {
        animalRepository.saveAnimal(animal);

    }

    public Optional<Animal> findAnimalById(int idAnimal) {
        return animalRepository.findAnimalById(idAnimal);
    }

    public List<Animal> findAllAnimal() {
        return animalRepository.findAllAnimal();
    }

    public void updateAnimal(Animal animal) {
        animalRepository.updateAnimal(animal);
    }

    public void deleteAnimal(Animal animal) {
        animalRepository.deleteAnimal(animal);
    }


}
