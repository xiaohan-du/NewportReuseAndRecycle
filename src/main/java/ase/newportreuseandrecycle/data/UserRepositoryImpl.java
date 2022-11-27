package ase.newportreuseandrecycle.data;

import ase.newportreuseandrecycle.domain.User;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository{
    private final JdbcTemplate jdbcTemplate;
    private RowMapper<User> userRowMapper;
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        setUserRowMapper();
    }

    private void setUserRowMapper() {
        userRowMapper = (rm, index) -> new User(
                rm.getInt("id"),
                rm.getString("email"),
                rm.getString("password"),
                rm.getString("first_name"),
                rm.getString("last_name")
        );
    }

    @Override
    public List<User> getUsers() {
        String getUsersSQL = "SELECT * FROM project_user";
        return jdbcTemplate.query(getUsersSQL, userRowMapper);
    }

    @Override
    public void addNewUser(User aUser) {
        String addAUSerSQL = "INSERT INTO project_user (id, email, password, first_name, last_name) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(addAUSerSQL, aUser.getId(), aUser.getEmail(), aUser.getPassword(), aUser.getFirstName(), aUser.getLastName());
    }

    @Override
    public Optional<User> getAUserByEmail(String email) {
        String getAUserByEmailSQL = "SELECT * FROM project_user WHERE email = ?";
        Optional<User> aUser;
        try {
            aUser = Optional.of(jdbcTemplate.queryForObject(getAUserByEmailSQL, userRowMapper, email));
            return aUser;
        } catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
    }
}
