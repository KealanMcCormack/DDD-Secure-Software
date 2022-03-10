package com.example.controller;

import com.example.exception.BookNotFoundException;
import com.example.model.*;
import com.example.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Controller
public class MainController {

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AdminLoginRepository adminLoginRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    ForumPostRepository forumPostRepository;

    @Autowired
    VaccineAppointmentRepository vaccineAppointmentRepository;

    @RequestMapping("/vaccine_register")
    public String vaccineRegister(){
        return "vaccine_register";
    }

    @RequestMapping("/registerNewUser")
    public String registerNewUser(){
        return "registerNewUser";
    }

    @RequestMapping("/admin_homepage")
    public String adminHomepage(){
        return "admin_homepage";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/admin_login")
    public String adminLogin(){
        return "admin_login";
    }

    @RequestMapping("/activity")
    public String activity(){
        return "activity";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/";
    }

    @GetMapping("/booking")
    //TODO(gerard): Figure out why this isnt working
    public String booking(Model model){
        vaccineAppointmentRepository.deleteAll();
        vaccineAppointmentRepository.save(new VaccineAppointment(123, "Kealans house", "13:00", "24th Feb", "true"));
        vaccineAppointmentRepository.save(new VaccineAppointment(123, "Lukaszs house", "14:00", "21st Feb", "false"));
        model.addAttribute("vaccineAppointments", vaccineAppointmentRepository.findAll());
        return "booking";
    }

    @GetMapping("/forum")
    public String forum(Model model){
        model.addAttribute("forumposts", forumPostRepository.findAll());
        return "forum";
    }

    @PostMapping("/addForumPost")
    public String addForumPost(HttpServletRequest request, @RequestParam String title, @RequestParam String content){
        ForumPost post = new ForumPost(title, content, (String) request.getSession().getAttribute("username"));
        Set<Comment> comments =  new HashSet<>();
        post.setComments(comments);
        forumPostRepository.save(post);
        return "redirect:/forum";
    }

    @PostMapping("/addForumComment/{id}")
    public String addForumComment(@PathVariable int id, HttpServletRequest request, @RequestParam String comment){
        //ForumPost post = new ForumPost(title, content, (String) request.getSession().getAttribute("username"));
        Set<Comment> comments =  new HashSet<>();
        //post.setComments(comments);
        //forumPostRepository.save(post);
        return "redirect:/forum";
    }

    @RequestMapping("/newForumPost")
    public String newForumPost(){
        return "newForumPosts";
    }

    @RequestMapping("/newForumComment/{id}")
    public String newForumComment(@PathVariable int id){
        return "newForumPosts";
    }


    @RequestMapping("/account_register")
    public String accountRegister(){
        return "account_register";
    }

    @RequestMapping("/newUserLogin")
    public String newLoginRegister(){
        return "newUserLogin";
    }

    @PostMapping("/addLoginDetails")
    public String addLoginDetails(@RequestParam String username, @RequestParam String password, HttpServletRequest request){

        if(request.getSession().getAttribute("NewlyRegistered").equals("true")){
            //Check if username taken
            if(loginRepository.existsById(username)){
                request.getSession().setAttribute("UsernameTaken", "true");
                return "redirect:/newUserLogin";
            } else{
                Login login = new Login();
                login.setUsername(username);
                login.setPassword(password);

                String PPS = (String) request.getSession().getAttribute("PPS");

                login.setPPS(PPS);
                loginRepository.save(login);
                request.getSession().setAttribute("login", "true");
                request.getSession().setAttribute("username", username);
                request.getSession().setAttribute("NewlyRegistered", "false");
                return "redirect:/";
            }
        }
        return "redirect:/";

    }

    @PostMapping("/loginCheck")
    public String loginCheck(@RequestParam String username, @RequestParam String password, HttpServletRequest request){
        if(loginRepository.existsById(username)){
            if(loginRepository.findById(username).get().getPassword().equals(password)){
                request.getSession().setAttribute("login", "true");
                request.getSession().setAttribute("username", username);
                return "redirect:/";
            }
        }
        request.getSession().setAttribute("login", "false");
        return "redirect:/login";
    }

    @PostMapping("/adminLoginCheck")
    public String adminLoginCheck(@RequestParam String username, @RequestParam String password, HttpServletRequest request){
        if(adminLoginRepository.existsById(username)){
            if(adminLoginRepository.findById(username).get().getPassword().equals(password)){
                request.getSession().setAttribute("admin_login", "true");
                request.getSession().setAttribute("username", username);
                return "redirect:/admin_homepage";
            }
        }

        request.getSession().setAttribute("admin_login", "false");
        return "redirect:/admin_login";
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
        request.getSession().setAttribute("PPS", PPS);
        request.getSession().setAttribute("NewlyRegistered", "true");
        usersRepository.save(user);

        return "redirect:/newUserLogin";
    }

    @PostMapping("/createNewHSEUser")
    public String createNewHSEUser(@RequestParam String email, @RequestParam String username, @RequestParam String password, @RequestParam String privilege, HttpServletRequest request){
        if(adminRepository.existsById(email)){
            request.getSession().setAttribute("already_registered", true);
            return "redirect:/registerNewUser";
        }

        Admin admin = new Admin();
        admin.setEmail(email);
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setPrivilege(privilege);
        adminRepository.save(admin);

        return "redirect:/admin_homepage";
    }

    // See All Books on Homepage
    @RequestMapping({"/"})
    public String viewHomePage(){
        return "homepage";
    }

}
