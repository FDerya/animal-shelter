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

    //Validates the user information in the request and processes the adoption if valid.
    @PostMapping("/adopt")
    public ResponseEntity<Void> adoptAnimal(@RequestBody AdoptionRequest adoptionRequest) {
        if (adoptionRequest.getUser() == null || adoptionRequest.getUser().getIdUser() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User information is missing.");
        }
        adoptionRequestService.adoptAnimal(adoptionRequest.getAnimal().getIdAnimal(), adoptionRequest);
        return ResponseEntity.ok().build();
    }

    // If the request with the given ID exists, it updates the existing request.
    // Otherwise, it creates a new request.
    @PutMapping("/{idAdoption}")
    ResponseEntity<AdoptionRequest> createOrUpdateAdoption(@RequestBody AdoptionRequest adoptionRequest, @PathVariable("idAdoption") int idAdoption) {
        Optional<AdoptionRequest> optionalAdoptionRequest = adoptionRequestService.findAdoptionById(idAdoption);
        // If the adoptionID is present, update the adoption information
        if (optionalAdoptionRequest.isPresent()) {
            adoptionRequestService.updateAdoption(adoptionRequest);
            return ResponseEntity.ok().body(adoptionRequest);
        }
        // If the adoptionID is not present, create a new adoption
        else {
            adoptionRequestService.saveAdoption(adoptionRequest);
            URI uri = URI.create(String.format("http://localhost:8080/adoption_request/%d", adoptionRequest.getIdAdoption()));
            return ResponseEntity.created(uri).body(adoptionRequest);
        }
    }

    // Endpoint to retrieve an adoption request by its ID.
    @GetMapping("/{idAdoption}")
    public AdoptionRequest getAdoptionById(@PathVariable("idAdoption") int idAdoption) {
        Optional<AdoptionRequest> result = adoptionRequestService.findAdoptionById(idAdoption);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Adoption request not found.");
        }
        return result.get();
    }

    // Endpoint to retrieve all adoption requests.
    @GetMapping()
    public List<AdoptionRequest> getAll() {
        return adoptionRequestService.findAllAdoption();
    }

    // Endpoint to delete an adoption request by its ID.
    @DeleteMapping("/delete/{idAdoption}")
    public  void deleteAdoption(@PathVariable int idAdoption) {
        AdoptionRequest adoptionDelete = getAdoptionById(idAdoption);
        adoptionRequestService.deleteAdoption(adoptionDelete);
    }
}














