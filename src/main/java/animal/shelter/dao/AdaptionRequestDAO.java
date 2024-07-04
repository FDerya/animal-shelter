package animal.shelter.dao;

import animal.shelter.model.AdoptionRequest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AdaptionRequestDAO {
    void saveAdoption(AdoptionRequest adoption);
    Optional<AdoptionRequest> findAdoptionById(int idAdoption);
    List<AdoptionRequest> findAllAdoption();
    void deleteAdoption(AdoptionRequest adoption);
    void updateAdoption(AdoptionRequest adoption);

}
