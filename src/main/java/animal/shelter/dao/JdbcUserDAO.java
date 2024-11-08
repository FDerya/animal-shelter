package animal.shelter.dao;

import animal.shelter.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcUserDAO implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // Saves a new user to the database.
    @Override
    public void saveUser(User user) {
        String sql = "INSERT INTO user (email, password, role) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getRole());
    }

    // Finds a user by their ID.
    @Override
    public Optional<User> findUserById(int idUser) {
        String sql = "SELECT * FROM user WHERE idUser = ?";
        List<User> resultList = jdbcTemplate.query(sql, new UserRowMapper(), idUser);
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }

    // Retrieves all users from the database.
    @Override
    public List<User> findAllUser() {
        String sql = "SELECT * FROM User ";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    // Deletes a user from the database.
    @Override
    public void deleteUser(User user){
        String sql = "DELETE FROM User WHERE idUser = ?";
        jdbcTemplate.update(sql, user.getIdUser());
    }

    // Updates an existing user in the database.
    public void updateUser(User user) {
        String sql = "UPDATE User SET email = ?, password = ?, role = ? WHERE idUser = ?";
        jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getRole(), user.getIdUser());
    }

    public void blockUser(User user){
        String  sql = "UPDATE User SET block = 1 WHERE idUser =?";
        jdbcTemplate.update(sql ,user.getIdUser());


    }




    // Finds a user by their email address.
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";
        List<User> resultList = jdbcTemplate.query(sql, new UserRowMapper(), email);
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }



    //kullanıcı ID'sine göre email bilgisini almak için
public Optional<String> findEmailById(int idUser){
        String sql = "SELECT email FROM user WHERE idUser = ?";
    List<String> resultList = jdbcTemplate.queryForList(sql, String.class, idUser);
    return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
}



    // RowMapper implementation for mapping rows of a ResultSet to User objects.
    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(
                    rs.getInt("idUser"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("role")
                    );
        }
    }
}



