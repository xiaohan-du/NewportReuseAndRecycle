package ase.newportreuseandrecycle.data;

import ase.newportreuseandrecycle.domain.User;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private RowMapper<User> userRowMapper;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        setUserRowMapper();
    }

    private void setUserRowMapper() {
        userRowMapper = (rm, index) -> new User(
                rm.getInt("id"),
                rm.getString("username"),
                rm.getString("password"),
                rm.getString("role"),
                rm.getBoolean("enabled")
        );
    }

    @Override
    public List<User> getUsers() {
        String getUsersSQL = "SELECT * FROM users";
        return jdbcTemplate.query(getUsersSQL, userRowMapper);
    }

    @Override
    public void addNewUser(User aUser) {
        String addAUSerSQL = "INSERT INTO users (username, password, role, enabled) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(addAUSerSQL, aUser.getUsername(), aUser.getPassword(), aUser.getRole(), Boolean.TRUE);
    }

    @Override
    public Optional<User> getAUserByUsername(String username) {
        String getAUserByUsernameSQL = "SELECT * FROM users WHERE username = ?";
        Optional<User> aUser;
        try {
            aUser = Optional.of(jdbcTemplate.queryForObject(getAUserByUsernameSQL, userRowMapper, username));
            return aUser;
        } catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
    }



}