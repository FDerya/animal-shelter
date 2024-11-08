package animal.shelter.repository;

import animal.shelter.dao.JdbcAnimalDAO;
import animal.shelter.model.Animal;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
public class AnimalRepository {

    private final JdbcAnimalDAO jdbcAnimalDAO;

    public AnimalRepository(JdbcAnimalDAO jdbcAnimalDAO){
        this.jdbcAnimalDAO = jdbcAnimalDAO;
    }

    // Saves a new animal to the database.
    public void saveAnimal(Animal animal){
        jdbcAnimalDAO.saveAnimal(animal);
    }

    // Finds an animal by its ID.
    public Optional<Animal> findAnimalById(int idAnimal){
        return jdbcAnimalDAO.findAnimalById(idAnimal);
    }

    // Retrieves all animals from the database.
    public List<Animal> findAllAnimal(){
        return jdbcAnimalDAO.findAllAnimal();
    }

    // Updates an existing animal in the database.
    public void updateAnimal(Animal animal){
        jdbcAnimalDAO.updateAnimal(animal);
    }

    // Deletes an animal from the database.
    public  void deleteAnimal(Animal animal){
        jdbcAnimalDAO.deleteAnimal(animal);
    }

    // Retrieves animals by their type (species) from the database.
    public List<Animal> findByType(String type) {
        return jdbcAnimalDAO.getByType(type);
    }


    public List<Animal> findByAnimalColor(String color){
        return jdbcAnimalDAO.getAnimalByColor(color);


    }



     // deneme 3
    public String findStatusById(int idAnimal) {
        return jdbcAnimalDAO.findStatusById(idAnimal);
    }


    // 4
    public Optional<Animal> getAnimalsById (@PathVariable("id") int idAnimal){
        return jdbcAnimalDAO.findAnimalById(idAnimal);

    }


}
