package animal.shelter.controller;

import animal.shelter.model.AdoptionRequest;
import animal.shelter.service.AdoptionRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("adoption_request")
public class AdoptionRequestController {


    private final AdoptionRequestService adoptionRequestService;

    @Autowired
    public AdoptionRequestController(AdoptionRequestService adoptionRequestService) {
        this.adoptionRequestService = adoptionRequestService;
    }

    //This method accepts an AdoptionRequest object, verifies user information.Does not return any value
    @PostMapping("/adopt")
    public ResponseEntity<Void> adoptAnimal(@RequestBody AdoptionRequest adoptionRequest) {
        if (adoptionRequest.getUser() == null || adoptionRequest.getUser().getIdUser() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User information is missing.");
        }
        adoptionRequestService.adoptAnimal(adoptionRequest.getAnimal().getIdAnimal(), adoptionRequest);
        return ResponseEntity.ok().build();
    }

    // Update en create
    @PutMapping("/{idAdoption}")
    ResponseEntity<AdoptionRequest> createAdoption(@RequestBody AdoptionRequest adoptionRequest, @PathVariable("idAdoption") int idAdoption) {
        Optional<AdoptionRequest> optionalAdoptionRequest = adoptionRequestService.findAdoptionById(idAdoption);
        if (optionalAdoptionRequest.isPresent()) {
            adoptionRequestService.updateAdoption(adoptionRequest);
            return ResponseEntity.ok().body(adoptionRequest);
        } else {
            adoptionRequestService.saveAdoption(adoptionRequest);
            URI uri = URI.create(String.format("http://localhost:8080/adoption_request/%d", adoptionRequest.getIdAdoption()));
            return ResponseEntity.created(uri).body(adoptionRequest);
        }
    }


    //Read
    @GetMapping("/{idAdoption}")
    @ResponseBody
    public AdoptionRequest getAdoptionById(@PathVariable("idAdoption") int idAdoption) {
        Optional<AdoptionRequest> result = adoptionRequestService.findAdoptionById(idAdoption);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Adoption request geen gevonden");

        } else {
            AdoptionRequest adoptionRequest = result.get();
            adoptionRequest.setIdAdoption(idAdoption);
            return adoptionRequest;
        }
    }

    @GetMapping()
    public List<AdoptionRequest> getAll() {
        return adoptionRequestService.findAllAdoption();
    }


    @DeleteMapping("/delete/{idAdoption}")
    public @ResponseBody void deleteAdoption(@PathVariable int idAdoption) {
        AdoptionRequest adoptionDelete = getAdoptionById(idAdoption);
        adoptionRequestService.deleteAdoption(adoptionDelete);
    }


}














