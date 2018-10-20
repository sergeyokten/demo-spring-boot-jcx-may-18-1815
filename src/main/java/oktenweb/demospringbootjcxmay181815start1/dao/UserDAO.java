package oktenweb.demospringbootjcxmay181815start1.dao;

import oktenweb.demospringbootjcxmay181815start1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDAO extends JpaRepository<User, Integer> {
    UserDetails findByUsername(String username);
}
