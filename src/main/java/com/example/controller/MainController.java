package com.example.controller;

import com.example.exception.BookNotFoundException;
import com.example.model.Book;
import com.example.model.Login;
import com.example.model.User;
import com.example.model.VaccineAppointment;
import com.example.repository.BookRepository;
import com.example.repository.LoginRepository;
import com.example.repository.UsersRepository;
import com.example.repository.VaccineAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    VaccineAppointmentRepository vaccineAppointmentRepository;

    @RequestMapping("/vaccine_register")
    public String vaccineRegister(){
        return "vaccine_register";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/";
    }

    @GetMapping("/booking")
    public String booking(Model model){
        vaccineAppointmentRepository.save(new VaccineAppointment(123, "Kealans house", "13:00", "24th Feb", false));
        vaccineAppointmentRepository.save(new VaccineAppointment(123, "Lukaszs house", "14:00", "21st Feb", false));
        model.addAttribute("vaccineAppointments", vaccineAppointmentRepository.findAll());
        return "booking";
    }

    @RequestMapping("/account_register")
    public String accountRegister(){
        return "account_register";
    }


    @PostMapping("/loginData")
    public String loginCheck(@RequestParam String username, @RequestParam String password, HttpServletRequest request){
        if(loginRepository.existsById(username)){
            if(loginRepository.findById(username).get().getPassword().equals(password)){
                request.getSession().setAttribute("login", true);
                request.getSession().setAttribute("username", username);
                return "redirect:/";
            }

        }
        if(username.contains("add")){
            Login login = new Login();
            login.setUsername(username);
            login.setPassword(password);
            loginRepository.save(login);
        }
        request.getSession().setAttribute("login", false);
        return "redirect:/login";
    }

    @PostMapping("/userData")
    public String createUser(@RequestParam String name, @RequestParam String surname, @RequestParam String email, @RequestParam String PPS, @RequestParam String dateOfBirth, @RequestParam String address, @RequestParam int phoneNumber, @RequestParam String nationality, HttpServletRequest request){
        if(usersRepository.existsById(PPS)){
            request.getSession().setAttribute("already_registered", true);
            return "redirect:/account_register";
        }

        LocalDate currentDate = LocalDate.now();
        String[] result = dateOfBirth.split("-");
        LocalDate birthday = LocalDate.of(Integer.parseInt(result[0]), Integer.parseInt(result[1]), Integer.parseInt(result[2]));

        long timeBetween = ChronoUnit.DAYS.between(birthday, currentDate);

        if(timeBetween < 6575){
            request.getSession().setAttribute("failed_registered", false);
            return "redirect:/account_register";
        }

        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPPS(PPS);
        user.setDateOfBirth(dateOfBirth);
        user.setAddress(address);
        user.setPhoneNumber(phoneNumber);
        user.setNationality(nationality);

        return "redirect:/";
    }

    // See All Books on Homepage
    @RequestMapping({"/", "/list"})
    public String viewHomePage(Model model){
        List<Book> listBooks = bookRepository.findAll();
        model.addAttribute("listBooks", listBooks);
        return "homepage";
    }

    // Delete a Book
    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable(value = "id") Long bookId, Model model) throws BookNotFoundException{
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        bookRepository.delete(book);
        return viewHomePage(model);
    }

    // Create a Book
    @RequestMapping("/new")
    public String createBook(){
        return "bookform";
    }
    // Save Created Book
    @PostMapping("/books")
    public String saveCreatedBook(@ModelAttribute("book") Book book, Model model){
        bookRepository.save(book);
        return viewHomePage(model);
    }

    // Update a Book
    // Get Book By ID and open the editform
    @GetMapping("/books/{id}")
    public String getBookById(@PathVariable(value="id") Long bookId, Model model) throws BookNotFoundException{
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        model.addAttribute("book", book);
        return "editform";
    }
    // Save Updated Details
    @RequestMapping(value="/books/save", method=RequestMethod.POST)
    public String updateBook(@ModelAttribute("book") Book book, Model model){
        bookRepository.save(book);
        return viewHomePage(model);
    }
}
