package animal.shelter.service;

import animal.shelter.model.AdoptionRequest;
import animal.shelter.repository.AdoptionRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdoptionRequestService {

    AdoptionRequestRepository adoptionRequestRepository;

    public AdoptionRequestService(AdoptionRequestRepository adoptionRequestRepository) {
        this.adoptionRequestRepository = adoptionRequestRepository;
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