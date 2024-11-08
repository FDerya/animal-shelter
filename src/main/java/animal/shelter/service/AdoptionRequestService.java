package animal.shelter.service;

import animal.shelter.dao.JdbcAdoptionRequestDAO;
import animal.shelter.model.AdoptionRequest;
import animal.shelter.repository.AdoptionRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdoptionRequestService {

    private final AdoptionRequestRepository adoptionRequestRepository;
    private final JdbcAdoptionRequestDAO jdbcAdoptionRequestDAO;

    @Autowired
    public AdoptionRequestService(AdoptionRequestRepository adoptionRequestRepository, JdbcAdoptionRequestDAO jdbcAdoptionRequestDAO) {
        this.adoptionRequestRepository = adoptionRequestRepository;
        this.jdbcAdoptionRequestDAO = jdbcAdoptionRequestDAO;
    }

    // Saves an adoption request and updates the animal's status to 'adopted'.
    public void adoptAnimal(int idAnimal, AdoptionRequest adoptionRequest) {
        // Save the adoption request
        adoptionRequestRepository.saveAdoption(adoptionRequest);

        // Update the animal's status to 'adopted'
        jdbcAdoptionRequestDAO.adoptAnimal(idAnimal);
    }

    // Saves an adoption request.
    public void saveAdoption(AdoptionRequest adoptionRequest) {
        adoptionRequestRepository.saveAdoption(adoptionRequest);
    }

    // Finds an adoption request by its ID.
    public Optional<AdoptionRequest> findAdoptionById(int idAdoption) {
        return adoptionRequestRepository.findAdoptionById(idAdoption);
    }

    // Retrieves all adoption requests.
    public List<AdoptionRequest> findAllAdoption() {
        return adoptionRequestRepository.findAllAdoption();
    }

    // Deletes an adoption request.
    public void deleteAdoption(AdoptionRequest adoptionRequest) {
        adoptionRequestRepository.deleteAdoption(adoptionRequest);
    }

    // Updates an adoption request.
    public void updateAdoption(AdoptionRequest adoptionRequest) {
        adoptionRequestRepository.updateAdoption(adoptionRequest);
    }

    // deneme 2
    public List<AdoptionRequest> findAdoptionRequestByUSerId(int idUser) {
        return adoptionRequestRepository.findAdoptionRequestByUSerId(idUser);
    }




    }
