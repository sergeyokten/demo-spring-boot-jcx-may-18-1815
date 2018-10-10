package oktenweb.demospringbootjcxmay181815start1.dao;

import oktenweb.demospringbootjcxmay181815start1.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactDao extends JpaRepository<Contact, Integer> {


}
