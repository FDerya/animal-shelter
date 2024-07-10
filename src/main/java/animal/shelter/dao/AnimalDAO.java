package animal.shelter.dao;

import animal.shelter.model.Animal;

import java.util.List;
import java.util.Optional;

public interface AnimalDAO {

    void adoptAnimal(int idAnimal);

    void saveAnimal(Animal animal);
    Optional<Animal> findAnimalById(int idAnimal);
    List<Animal> findAllAnimal();
    void deleteAnimal(Animal animal);
    void updateAnimal(Animal animal);

    List<Animal> getAllCats();

    List<Animal> getByType(String type);
}

