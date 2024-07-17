package animal.shelter.service;

import animal.shelter.model.Animal;
import animal.shelter.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {
   private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    // Retrieves all cats from the repository.
    public List<Animal> getAllCats() {
        return animalRepository.findByType("cat");
    }

    // Retrieves all dogs from the repository
    public List<Animal> getAllDogs() {
        return animalRepository.findByType("dog");
    }

    // Saves a new animal to the repository.
    public void saveAnimal(Animal animal) {
        animalRepository.saveAnimal(animal);
    }

    // Finds an animal by its ID.
    public Optional<Animal> findAnimalById(int idAnimal) {
        return animalRepository.findAnimalById(idAnimal);
    }

    // Retrieves all animals from the repository.
    public List<Animal> findAllAnimal() {
        return animalRepository.findAllAnimal();
    }

    // Updates an existing animal in the repository.
    public void updateAnimal(Animal animal) {
        animalRepository.updateAnimal(animal);
    }

    // Deletes an animal from the repository.
    public void deleteAnimal(Animal animal) {
        animalRepository.deleteAnimal(animal);
    }
}
