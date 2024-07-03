package animal.shelter.repository;

import animal.shelter.dao.JdbcAnimalDAO;
import animal.shelter.model.Animal;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AnimalRepository {
    private  JdbcAnimalDAO jdbcAnimalDAO;

    public AnimalRepository(JdbcAnimalDAO jdbcAnimalDAO){
        this.jdbcAnimalDAO = jdbcAnimalDAO;

    }


    public void saveAnimal(Animal animal){
        jdbcAnimalDAO.saveAnimal(animal);
    }
    public Optional<Animal> findAnimalById(int idAnimal){
        return jdbcAnimalDAO.findAnimalById(idAnimal);
    }
    public List<Animal> findAllAnimal(){
        return jdbcAnimalDAO.findAllAnimal();
    }
    public void updateAnimal(Animal animal){
        jdbcAnimalDAO.updateAnimal(animal);
    }
    public  void deleteAnimal(Animal animal){
        jdbcAnimalDAO.deleteAnimal(animal);
    }
}
