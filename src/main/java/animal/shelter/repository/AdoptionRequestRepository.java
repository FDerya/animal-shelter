package animal.shelter.repository;

import animal.shelter.dao.JdbcAdoptionRequestDAO;
import animal.shelter.model.AdoptionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AdoptionRequestRepository {
//  veritabanına erişim için repository sınıfını kullanırsın. Bu sınıf, CRUD (Create, Read, Update, Delete) işlemlerini yapmak için hazır metotlar sunar.
//  Örneğin, save() metodu yeni bir kayıt oluşturabilir ya da mevcut kaydı güncelleyebilir.
    private final JdbcAdoptionRequestDAO jdbcAdoptionRequestDAO;

    @Autowired
    public AdoptionRequestRepository(JdbcAdoptionRequestDAO jdbcAdoptionRequestDAO) {
        this.jdbcAdoptionRequestDAO = jdbcAdoptionRequestDAO;
    }

    // Saves a new adoption request.
    public void saveAdoption(AdoptionRequest adoptionRequest) {
        jdbcAdoptionRequestDAO.saveAdoption(adoptionRequest);
    }

    // Finds an adoption request by its ID.
    public Optional<AdoptionRequest> findAdoptionById(int idAdoption) {
        return jdbcAdoptionRequestDAO.findAdoptionById(idAdoption);
    }

    // Retrieves all adoption requests.
    public List<AdoptionRequest> findAllAdoption() {
        return jdbcAdoptionRequestDAO.findAllAdoption();
    }

    // Deletes an existing adoption request.
    public void deleteAdoption(AdoptionRequest adoptionRequest) {
        jdbcAdoptionRequestDAO.deleteAdoption(adoptionRequest);
    }

    // Updates an existing adoption request.
    public void updateAdoption(AdoptionRequest adoptionRequest) {
        jdbcAdoptionRequestDAO.updateAdoption(adoptionRequest);
    }

    // deneme 2
    public List<AdoptionRequest> findAdoptionRequestByUSerId(int idUser) {
        return jdbcAdoptionRequestDAO.findAdoptionRequestByUSerId(idUser);
    }


    }


