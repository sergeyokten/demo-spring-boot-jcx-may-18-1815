package oktenweb.demospringbootjcxmay181815start1.controllers;

import oktenweb.demospringbootjcxmay181815start1.dao.ContactDao;
import oktenweb.demospringbootjcxmay181815start1.models.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ContactDao contactDao;

    @GetMapping("/")
    public String index() {
        return "index";
    }


    @GetMapping("/asd")
    public String asd() {
        return "index";
    }

    @GetMapping("/saveContact")
    public String myName(
            @RequestParam("imya") String name,
            @RequestParam String email,
            @RequestParam String number,
            Model model
    ) {

        Contact contact = new Contact(name, email, number);
        System.out.println(contact);
        contactDao.save(contact);
        System.out.println(contact);
        model.addAttribute("contact", contact);
        List<Contact> contactList = contactDao.findAll();
        model.addAttribute("allContacts", contactList);


        return "greeting";
    }


    @GetMapping("/contact/{idOfContact}")
    public String getSingleContact(
            @PathVariable int idOfContact,
            Model model
    ) {
        Contact contact = contactDao.findById(idOfContact).get();
        model.addAttribute("singleContact", contact);

        return "singlContactPage";
    }


    @PostMapping("/updateContact")
    public String updateContact(
            Contact contact,
            Model model
    ) {
        System.out.println(contact);
        contactDao.save(contact);
        model.addAttribute("singleContact", contact);
        return "singlContactPage";
    }


}


