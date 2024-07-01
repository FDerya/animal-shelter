package animal.shelter.dao;

import animal.shelter.model.AdoptionRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        String sql = "SELECT * FROM adoption_request WHERE id = ?";
        List<AdoptionRequest> resulList = jdbcTemplate.query(sql, new AdoptionRowMapper(), idAdoption);
        if (resulList.isEmpty()) {
            return Optional.empty();

        } else {
            return Optional.of(resulList.get(0));
        }
    }

    @Override
    public List<AdoptionRequest> findAllAdoption() {
        String sql = "SELECT * FROM adoption_request";
        return jdbcTemplate.query(sql, new AdoptionRowMapper());

    }

    @Override
    public void deleteAdoption(AdoptionRequest adoptionRequest) {
        String sql = "DELETE FROM adoption_request WHERE id = ?";
        jdbcTemplate.update(sql, adoptionRequest.getIdAdoption());

    }

    @Override
    public void updateAdoption(AdoptionRequest adoption) {
        String sql = "UPDATE adoption_request SET idUser = ?, idAnimal = ?, requestDate = ?, status = ?";
        jdbcTemplate.update(sql, adoption.getUser().getIdUser(), adoption.getAnimal().getIdAnimal(), adoption.getRequestDate(), adoption.getStatus());


    }

    private static class AdoptionRowMapper implements RowMapper<AdoptionRequest> {
        @Override
        public AdoptionRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
            int idAdoption = rs.getInt("id");
            int idUser = rs.getInt("idUser");
            int idAnimal = rs.getInt("idAnimal");
            Date requestDate = rs.getDate("requestDate");
            String status = rs.getString("status");


            return null;
        }


    }
}



