package animal.shelter.dao;

import animal.shelter.model.AdoptionRequest;

import java.util.List;
import java.util.Optional;

public interface AdoptionRequestDAO {

    void saveAdoption(AdoptionRequest adoption);

    Optional<AdoptionRequest> findAdoptionById(int idAdoption);

    List<AdoptionRequest> findAllAdoption();

    void deleteAdoption(AdoptionRequest adoption);

    void updateAdoption(AdoptionRequest adoption);

    //deneme 2
    List<AdoptionRequest> findAdoptionRequestByUSerId(int idUser);


}
