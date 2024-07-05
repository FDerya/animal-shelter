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
    private JdbcTemplate jdbcTemplate;

    public JdbcUserDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);


    }

    @Override
    public void saveUser(User user) {
        String sql = "INSERT INTO user (email, password, role) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getRole());

    }

    @Override
    public Optional<User> findUserById(int idUser) {
        String sql = "SELECT * FROM user WHERE idUser = ?";
        List<User> resultList = jdbcTemplate.query(sql, new UserRowMapper(), idUser);
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));

    }

    @Override
    public List<User> findAllUser() {
        String sql = "SELECT * FROM User ";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    @Override
    public void deleteUser(User user){
        String sql = "DELETE FROM User WHERE idUser = ?";
        jdbcTemplate.update(sql, user.getIdUser());

    }

    // Update method
    public void updateUser(User user) {
        String sql = "UPDATE User SET email = ?, password = ?, role = ? WHERE idUser = ?";
        jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getRole(), user.getIdUser());


    }


    private class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User(rs.getInt("idUser"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("role"));
            user.setIdUser(rs.getInt("idUser"));
            return user;
        }

    }

    //findByEmail method searches the database for a user with the given email address and returns the information of that user.
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";
        List<User> resultList = jdbcTemplate.query(sql, new UserRowMapper(), email);
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }

}



