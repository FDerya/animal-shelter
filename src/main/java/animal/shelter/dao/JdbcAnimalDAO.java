package animal.shelter.dao;

import animal.shelter.model.Animal;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAnimalDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // Saves a new animal to the database.
    @Override
    public void saveAnimal(Animal animal) {
        String sql = "INSERT INTO animal (name, species, age, gender, description, status, color) VALUES (?, ?, ?, ?, ?, ?,?)";
        jdbcTemplate.update(sql,
                animal.getName(),
                animal.getSpecies(),
                animal.getAge(),
                animal.getGender(),
                animal.getDescription(),
                animal.getStatus(),
                animal.getColor());
    }

    // Finds an animal by its ID.
    @Override
    public Optional<Animal> findAnimalById(int idAnimal) {
        String sql = "SELECT * FROM animal WHERE idAnimal = ?";
        List<Animal> resultList = jdbcTemplate.query(sql, new AnimalRowMapper(), idAnimal);
        // If animal is not found, return empty else return the found animal
        if (resultList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(resultList.get(0));
        }
    }

    // Retrieves all animals from the database.
    @Override
    public List<Animal> findAllAnimal() {
        String sql = "SELECT * FROM animal";
        return jdbcTemplate.query(sql, new AnimalRowMapper());
    }

    // Updates an existing animal in the database.
    @Override
    public void updateAnimal(Animal animal) {
        String sql = "UPDATE animal SET name = ?, species = ?, age = ?, gender = ?, description = ?, status = ? WHERE idAnimal = ?";
        jdbcTemplate.update(sql, animal.getName(),
                animal.getSpecies(), animal.getAge(),
                animal.getGender(),
                animal.getDescription(),
                animal.getStatus(),
                animal.getIdAnimal());
    }

    // Deletes an animal from the database.
    @Override
    public void deleteAnimal(Animal animal) {
        String sql = "DELETE FROM animal WHERE idAnimal = ?";
        jdbcTemplate.update(sql, animal.getIdAnimal());
    }

    // Retrieves animals by their type (species) from the database.
    @Override
    public List<Animal> getByType(String type) {
        String sql = "SELECT * FROM animal WHERE species = ?";
        return jdbcTemplate.query(sql, new AnimalRowMapper(), type);
    }


    @Override
    public List<Animal> getAnimalByColor(String color) {
        String sql = "SELECT * FROM animal WHERE color = ?";
        return jdbcTemplate.query(sql, new AnimalRowMapper(), color);


    }
    // deneme 3
    @Override
    public String findStatusById(int idAnimal) {
        String  sql = "SELECT status FROM animal WHERE idAnimal = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{idAnimal},String.class);

    }


    // RowMapper implementation for mapping rows of a ResultSet to Animal objects.
        private static class AnimalRowMapper implements RowMapper<Animal> {
            @Override
            public Animal mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Animal(
                        rs.getInt("idAnimal"),
                        rs.getString("name"),
                        rs.getString("species"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getString("color"));
            }
        }
    }


