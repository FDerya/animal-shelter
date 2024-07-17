package animal.shelter.dao;

import animal.shelter.model.AdoptionRequest;
import animal.shelter.model.Animal;
import animal.shelter.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class JdbcAdoptionRequestDAO implements AdoptionRequestDAO {

    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    public JdbcAdoptionRequestDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.dataSource = dataSource;
    }

    // Updates the status of an animal to 'adopted'.
    public void adoptAnimal(int idAnimal) {
        String sql = "UPDATE animal SET status = 'adopted' WHERE idAnimal = ?";
        jdbcTemplate.update(sql, idAnimal);
    }

    // Saves a new adoption request
    @Override
    public void saveAdoption(AdoptionRequest adoption) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("adoption_request")
                .usingGeneratedKeyColumns("idAdoption");
        int newId = simpleJdbcInsert.executeAndReturnKey(mapInsertParameters(adoption)).intValue();
        adoption.setIdAdoption(newId);
    }

    // Finds an adoption request by its ID.
    @Override
    public Optional<AdoptionRequest> findAdoptionById(int idAdoption) {
        String sql = "SELECT * FROM adoption_request WHERE idAdoption = ?";
        List<AdoptionRequest> resultList = jdbcTemplate.query(sql, new AdoptionRowMapper(), idAdoption);
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }

    // Retrieves all adoption requests
    @Override
    public List<AdoptionRequest> findAllAdoption() {
        String sql = "SELECT * FROM adoption_request";
        return jdbcTemplate.query(sql, new AdoptionRowMapper());
    }

    // Deletes an existing adoption request.
    @Override
    public void deleteAdoption(AdoptionRequest adoptionRequest) {
        String sql = "DELETE FROM adoption_request WHERE idAdoption = ?";
        jdbcTemplate.update(sql, adoptionRequest.getIdAdoption());
    }


    // Updates an existing adoption request.
    @Override
    public void updateAdoption(AdoptionRequest adoption) {
        String sql = "UPDATE adoption_request SET idUser = ?, idAnimal = ?, requestDate = ?, status = ? WHERE idAdoption = ?";
        jdbcTemplate.update(sql, adoption.getUser().getIdUser(), adoption.getAnimal().getIdAnimal(), adoption.getRequestDate(), adoption.getStatus(), adoption.getIdAdoption());
    }

    // Maps the parameters of an AdoptionRequest to a map for insertion.
    private Map<String, Object> mapInsertParameters(AdoptionRequest adoption) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("idUser", adoption.getUser().getIdUser());
        parameters.put("idAnimal", adoption.getAnimal().getIdAnimal());
        parameters.put("requestDate", adoption.getRequestDate());
        parameters.put("status", adoption.getStatus());
        return parameters;
    }

    // RowMapper implementation for mapping rows of a ResultSet to AdoptionRequest objects.
    private static class AdoptionRowMapper implements RowMapper<AdoptionRequest> {
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


