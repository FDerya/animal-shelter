package animal.shelter.dao;

import animal.shelter.model.AdoptionRequest;
import animal.shelter.model.Animal;
import animal.shelter.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Repository
public class JdbcAdoptionRequestDAO implements AdaptionRequestDAO {

    private JdbcTemplate jdbcTemplate;

    public JdbcAdoptionRequestDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveAdoption(AdoptionRequest adoption) {
        String sql = "INSERT INTO adoption_request(idUser, idAnimal, requestDate, status) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, adoption.getUser().getIdUser(), adoption.getAnimal().getIdAnimal(), adoption.getRequestDate(), adoption.getStatus());
    }

    @Override
    public Optional<AdoptionRequest> findAdoptionById(int idAdoption) {
        String sql = "SELECT * FROM adoption_request WHERE idAdoption = ?";
        List<AdoptionRequest> resultList = jdbcTemplate.query(sql, new AdoptionRowMapper(), idAdoption);
        if (resultList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(resultList.get(0));
        }
    }

    @Override
    public List<AdoptionRequest> findAllAdoption() {
        String sql = "SELECT * FROM adoption_request";
        return jdbcTemplate.query(sql, new AdoptionRowMapper());
    }

    @Override
    public void deleteAdoption(AdoptionRequest adoptionRequest) {
        String sql = "DELETE FROM adoption_request WHERE idAdoption = ?";
        jdbcTemplate.update(sql, adoptionRequest.getIdAdoption());
    }

    @Override
    public void updateAdoption(AdoptionRequest adoption) {
        String sql = "UPDATE adoption_request SET idUser = ?, idAnimal = ?, requestDate = ?, status = ? WHERE idAdoption = ?";
        jdbcTemplate.update(sql, adoption.getUser().getIdUser(), adoption.getAnimal().getIdAnimal(), adoption.getRequestDate(), adoption.getStatus(), adoption.getIdAdoption());
    }


    private class AdoptionRowMapper implements RowMapper<AdoptionRequest> {
        @Override
        public AdoptionRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
            int idAdoption = rs.getInt("idAdoption");
            int idUser = rs.getInt("idUser");
            int idAnimal = rs.getInt("idAnimal");
            Date requestDate = rs.getDate("requestDate");
            String status = rs.getString("status");

            User user = new User();
            user.setIdUser(idUser);

            Animal animal = new Animal();
            animal.setIdAnimal(idAnimal);

            return new AdoptionRequest(idAdoption, user, animal, requestDate, status);
        }
    }


}


