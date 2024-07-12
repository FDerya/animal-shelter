package animal.shelter.controller;

import animal.shelter.model.Animal;
import animal.shelter.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/animal")
public class AnimalController {

    private final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/getAllCats")
    public List<Animal> getAllCats() {
        return animalService.getAllCats();
    }

    @GetMapping("getAllDogs")
    public List<Animal> getAllDogs(){ return animalService.getAllDogs();}

    @PostMapping("/create")
    public ResponseEntity<Animal> createAnimal(@RequestBody Animal animal) {
        animalService.saveAnimal(animal);
        URI uri = URI.create(String.format("http://localhost:8080/animal/%d", animal.getIdAnimal()));
        return ResponseEntity.created(uri).body(animal);
    }

    @GetMapping("/{idAnimal}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable("idAnimal") int idAnimal) {
        Optional<Animal> result = animalService.findAnimalById(idAnimal);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public List<Animal> getAll() {
        return animalService.findAllAnimal();
    }

    @DeleteMapping("/delete/{idAnimal}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable("idAnimal") int idAnimal) {
        Optional<Animal> result = animalService.findAnimalById(idAnimal);
        if (result.isPresent()) {
            animalService.deleteAnimal(result.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
