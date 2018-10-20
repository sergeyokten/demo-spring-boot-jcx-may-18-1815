package oktenweb.demospringbootjcxmay181815start1.controllers;

import oktenweb.demospringbootjcxmay181815start1.dao.ContactDao;
import oktenweb.demospringbootjcxmay181815start1.models.Contact;
import oktenweb.demospringbootjcxmay181815start1.models.User;
import oktenweb.demospringbootjcxmay181815start1.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class MainController {


    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private ContactDao contactDao;

    @GetMapping("/")
    public String index() {
        //......
        return "index";
    }


    @GetMapping("/asd")
    public String asd() {

        return "index";
    }

    @PostMapping("/saveContact")
    public String myName(
            Contact contact,
            @RequestParam MultipartFile image
    ) throws IOException {

        String path = System.getProperty("user.home")
                + File.separator
                + "images"
                + File.separator
                + image.getOriginalFilename();

        image.transferTo(new File(path));

        contact.setAvatar(image.getOriginalFilename());
        contactDao.save(contact);
        return "redirect:/";
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


    @GetMapping("/showAllContacts")
    public String showAllContacts(Model model) {
        model.addAttribute("allContacts", contactDao.findAll());
        return "allContactsPage";
    }


    @GetMapping("/rest")
    public @ResponseBody
    List<Contact> rest() {
        return contactDao.findAll();
    }

    @PostMapping("/saveContactRest")

    public @ResponseBody
    List<Contact> saveContactRest(@RequestBody Contact contact) {

        contactDao.save(contact);
        System.out.println(contact);
        List<Contact> responseList = contactDao.findAll();
        return responseList;
    }


    @PostMapping("/upload")
    public @ResponseBody
    void upload(@RequestBody MultipartFile uploadfile) throws IOException {

        uploadfile.transferTo(new File(
                System.getProperty("user.home") + File.separator + "images" + File.separator + uploadfile.getOriginalFilename()
        ));
        System.out.println("done");

    }

    @PostMapping("/successURL")
    public String successURL() {

        return "index";
    }


    @Autowired
    private UserService userService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/saveUser")
    public String saveUser(User user) {
        logger.info("user" + " " + user + " saved----------------------------------------------");
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        userService.save(user);
        return "redirect:/login";
    }
}


