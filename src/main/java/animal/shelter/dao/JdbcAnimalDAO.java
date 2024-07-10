package animal.shelter.dao;

import animal.shelter.model.Animal;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@Repository
public class JdbcAnimalDAO implements AnimalDAO {

    private JdbcTemplate jdbcTemplate;

    public JdbcAnimalDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);

    }

    @Override
    public void adoptAnimal(int idAnimal) {
        String sql = "UPDATE animal SET status = 'adopted' WHERE idAnimal = ?";
        jdbcTemplate.update(sql, idAnimal);
    }


    @Override
    public void saveAnimal(Animal animal) {
        String sql = "INSERT INTO animal(name, species, age, gender, description, status) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(sql, animal.getName(), animal.getSpecies(), animal.getGender(), animal.getDescription(), animal.getStatus());


    }

    @Override
    public Optional<Animal> findAnimalById(int idAnimal) {
        String sql = "SELECT * FROM animal WHERE idAnimal = ?";
        List<Animal> resultList = jdbcTemplate.query(sql, new AnimalRowMapper(), idAnimal);
        if (resultList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(resultList.get(0));

        }

    }

    @Override
    public List<Animal> findAllAnimal() {
        String sql = "SELECT * FROM animal";
        return jdbcTemplate.query(sql, new AnimalRowMapper());

    }
    @Override
    public void updateAnimal(Animal animal) {
        String sql = "UPDATE animal SET name = ?, species = ?, age = ?, gender = ?, description =?, status = ?";
        jdbcTemplate.update(sql, animal.getName(),
                animal.getSpecies(), animal.getAge(),
                animal.getGender(),
                animal.getDescription(),
                animal.getStatus());
    }

    @Override
    public void deleteAnimal(Animal animal) {
        String sql = "DELETE FROM animal WHERE idAnimal = ?";
        jdbcTemplate.update(sql, animal.getIdAnimal());
    }

    @Override
    public List<Animal> getAllCats() {
        String sql = "SELECT * FROM animal WHERE species = 'cat'";
        return jdbcTemplate.query(sql, new AnimalRowMapper());
    }

    @Override
    public List<Animal> getByType(String type) {
        String sql = "SELECT * FROM animal WHERE species = ?";
        return jdbcTemplate.query(sql, new AnimalRowMapper(), type);
    }

    private static class AnimalRowMapper implements RowMapper<Animal> {
        @Override
        public Animal mapRow(ResultSet rs, int rowNum) throws SQLException {
            Animal result = new Animal(
                    rs.getInt("idAnimal"),
                    rs.getString("name"),
                    rs.getString("species"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getString("description"),
                    rs.getString("status"));
            return result;
        }
    }

}

