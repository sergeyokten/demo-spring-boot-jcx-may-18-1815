package oktenweb.demospringbootjcxmay181815start1.services;

import oktenweb.demospringbootjcxmay181815start1.models.User;

import java.util.List;

public interface UserService {
    void save(User user);

    List<User> findAll();

    User findOneById(int id);
    //.......
}
