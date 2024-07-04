package animal.shelter.repository;

import animal.shelter.dao.JdbcAdoptionRequestDAO;
import animal.shelter.model.AdoptionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AdoptionRequestRepository {

    private JdbcAdoptionRequestDAO jdbcAdoptionRequestDAO;
    @Autowired

    public AdoptionRequestRepository(JdbcAdoptionRequestDAO jdbcAdoptionRequestDAO){
        this.jdbcAdoptionRequestDAO = jdbcAdoptionRequestDAO;
    }
    public void saveAdoption(AdoptionRequest adoptionRequest) {
        jdbcAdoptionRequestDAO.saveAdoption(adoptionRequest);
    }

    public Optional<AdoptionRequest> findAdoptionById(int idAdoption) {
        return jdbcAdoptionRequestDAO.findAdoptionById(idAdoption);

    }
    public List<AdoptionRequest> findAllAdoption() {
        return jdbcAdoptionRequestDAO.findAllAdoption();
    }

    public void deleteAdoption(AdoptionRequest adoptionRequest) {
        jdbcAdoptionRequestDAO.deleteAdoption(adoptionRequest);
    }

    public void updateAdoption(AdoptionRequest adoptionRequest) {
        jdbcAdoptionRequestDAO.updateAdoption(adoptionRequest);
    }
}

