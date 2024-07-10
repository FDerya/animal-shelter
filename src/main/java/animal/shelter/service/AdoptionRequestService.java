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
    private JdbcAdoptionRequestDAO jdbcAdoptionRequestDAO;

    @Autowired
    public AdoptionRequestService(AdoptionRequestRepository adoptionRequestRepository, JdbcAdoptionRequestDAO jdbcAdoptionRequestDAO) {
        this.adoptionRequestRepository = adoptionRequestRepository;
        this.jdbcAdoptionRequestDAO = jdbcAdoptionRequestDAO;
    }

    public AdoptionRequestService(AdoptionRequestRepository adoptionRequestRepository) {
        this.adoptionRequestRepository = adoptionRequestRepository;
    }

    public void adoptAnimal(int idAnimal, AdoptionRequest adoptionRequest) {
        // Save the adoption request
        adoptionRequestRepository.saveAdoption(adoptionRequest);

        // Update
        jdbcAdoptionRequestDAO.adoptAnimal(idAnimal);
    }


    public void saveAdoption(AdoptionRequest adoptionRequest) {
        adoptionRequestRepository.saveAdoption(adoptionRequest);

    }

    public Optional<AdoptionRequest> findAdoptionById(int idAdoption) {
        return adoptionRequestRepository.findAdoptionById(idAdoption);
    }

    public List<AdoptionRequest> findAllAdoption() {
        return adoptionRequestRepository.findAllAdoption();
    }

    public void deleteAdoption(AdoptionRequest adoptionRequest) {
        adoptionRequestRepository.deleteAdoption(adoptionRequest);
    }

    public void updateAdoption(AdoptionRequest adoptionRequest) {
        adoptionRequestRepository.updateAdoption(adoptionRequest);
    }
}