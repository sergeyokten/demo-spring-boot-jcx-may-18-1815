package oktenweb.demospringbootjcxmay181815start1.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data

public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String number;
    private String avatar;


    public Contact() {
    }

    public Contact(String name, String email, String number) {
        this.name = name;
        this.email = email;
        this.number = number;
    }
}
