package com.example.controller;

import com.example.model.*;
import com.example.repository.*;
import com.example.security.UserValidation;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Controller
public class MainController {
    private final Logger logger = LogManager.getLogger(MainController.class);
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    LoginRepository loginRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    ForumPostRepository forumPostRepository;

    @Autowired
    VaccineAppointmentRepository vaccineAppointmentRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    private UserValidation userValidation;

    boolean initBookingPage = true;

    @GetMapping("/viewUserData")
    public String viewUserData(Model model){

        List<User> userRepo = usersRepository.findAll();
        model.addAttribute("users", userRepo);
        return "viewUserData";
    }

    @PostMapping("/viewUserDataChangeVacc/{pps}")
    public String viewUserDataDelete(@PathVariable("pps") String pps, @RequestParam int newVaccStatus, @RequestParam String vaccineType){
        User oldUsr = usersRepository.findById(pps).get();

        User newUsr = new User(oldUsr.getEmail(), oldUsr.getName(), oldUsr.getSurname(), oldUsr.getDateOfBirth(), oldUsr.getPPS(), oldUsr.getAddress(), oldUsr.getPhoneNumber(), oldUsr.getNationality(), oldUsr.getGender());
        newUsr.setVaccinationStage(newVaccStatus);
        newUsr.setVaccineType(vaccineType);
        logger.info(newUsr.getEmail() + " Vaccination status updated : " + newVaccStatus + " , Vaccine type : " +vaccineType);
        usersRepository.deleteById(pps);
        usersRepository.save(newUsr);
        return "redirect:/viewUserData";
    }

    @RequestMapping("/stats")
    public String stats(Model model){
        List<User> users = usersRepository.findAll();
        HashMap<String, Integer> gender = new HashMap<>();
        gender.put("M", 0);
        gender.put("F", 0);
        gender.put("O", 0);
        for(String n : gender.keySet()){
            int count = 0;
            for(User user : users){
               if(user.getVaccinationStage() != 0 && user.getGender().equals(n)){
                    count++;
               }
            }
            gender.replace(n, count);
        }

        model.addAttribute("Male", gender.get("M"));
        model.addAttribute("Female", gender.get("F"));
        model.addAttribute("Other", gender.get("O"));

        for(String n : gender.keySet()){
            int count = 0;
            for(User user : users){
                if(user.getVaccinationStage() == 0 && user.getGender().equals(n)){
                    count++;
                }
            }
            gender.replace(n, count);
        }

        model.addAttribute("MaleNoVac", gender.get("M"));
        model.addAttribute("FemaleNoVac", gender.get("F"));
        model.addAttribute("OtherNoVac", gender.get("O"));

        ArrayList<Tuple> listNonVaccinated =  new ArrayList<>();
        ArrayList<Tuple> listVaccinated =  new ArrayList<>();
        HashMap<String, Integer> nationMap = new HashMap<>();
        users.forEach(n -> nationMap.put(n.getNationality(), 0));

        for(String n : nationMap.keySet()){
            int count = 0;
            int i = 0;
            for(User user : users){
                if(user.getVaccinationStage() == 0 && user.getNationality().equals(n)){
                    count++;
                }else if(user.getVaccinationStage() != 0 && user.getNationality().equals(n)){
                    i++;
                }
            }
            listNonVaccinated.add(new Tuple(n, count));
            listVaccinated.add(new Tuple(n, i));
        }

        String str = "[";
        for(Tuple tuple : listNonVaccinated){
            str = str.concat(tuple.toString());
        }

        str= str.substring(0, str.length() - 1);
        str = str.concat("]");
        model.addAttribute("nationData", str);

        String str2 = "[";
        for(Tuple tuple : listVaccinated){
            str2 = str2.concat(tuple.toString());
        }

        str2 = str2.substring(0, str2.length() - 1);
        str2 = str2.concat("]");
        model.addAttribute("nationData2", str2);

        return "stats";
    }
    @Data
    private class Tuple{
        String data;
        int num;

        public Tuple(String n, int count) {
            this.data = n;
            this.num = count;
        }

        public String toString(){
            return "[\"" + data +"\", " + num + "],";
        }

    }

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
    public String activity(Model model, HttpServletRequest servletRequest){
        ArrayList<VaccineAppointment> vaccineAppointments = new ArrayList<>();
        List<VaccineAppointment> repoList = vaccineAppointmentRepository.findAll();
        String username = (String) servletRequest.getSession().getAttribute("username");

        Login login = loginRepository.getById(username);
        String PPS = login.getPPS();
        int vaccinationStage = usersRepository.getById(PPS).getVaccinationStage();
        String vaccineType = usersRepository.getById(PPS).getVaccineType();

        for(VaccineAppointment apt : repoList){
            if(Objects.equals(apt.username, username)) {
                System.out.println("in if");
                vaccineAppointments.add(apt);
            }
        }

        model.addAttribute("vaccineAppointments", vaccineAppointments);
        model.addAttribute("vaccinationStage", vaccinationStage);
        model.addAttribute("vaccineType", vaccineType);
        return "activity";
    }

    @PostMapping("/activityDelete/{id}")
    public String activityDelete(@PathVariable("id") String id){
        int aptId = Integer.parseInt(id);
        vaccineAppointmentRepository.deleteById(aptId);

        return "redirect:/activity";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/";
    }

    @GetMapping("/booking")
    public String booking(Model model){
        if(initBookingPage){
            vaccineAppointmentRepository.deleteAll();
            vaccineAppointmentRepository.save(new VaccineAppointment("Churchtown Pharmacy", "13:00", "24-02-2022", "true"));
            vaccineAppointmentRepository.save(new VaccineAppointment("Churchtown Pharmacy", "14:00", "24-02-2022", "false"));
            vaccineAppointmentRepository.save(new VaccineAppointment("Churchtown Pharmacy", "15:00", "24-02-2022", "false"));
            vaccineAppointmentRepository.save(new VaccineAppointment("Churchtown Pharmacy", "16:00", "24-02-2022", "false"));
            vaccineAppointmentRepository.save(new VaccineAppointment("Churchtown Pharmacy", "17:00", "24-02-2022", "true"));
            vaccineAppointmentRepository.save(new VaccineAppointment("Dundrum Pharmacy", "13:00", "25-02-2022", "false"));
            vaccineAppointmentRepository.save(new VaccineAppointment("Dundrum Pharmacy", "14:00", "25-02-2022", "false"));
            vaccineAppointmentRepository.save(new VaccineAppointment("Dundrum Pharmacy", "15:00", "25-02-2022", "false"));
            initBookingPage = false;
        }
        model.addAttribute("vaccineAppointments", vaccineAppointmentRepository.findAll());
        return "booking";
    }

    @GetMapping("/forum")
    public String forum(Model model){
        model.addAttribute("forumposts", forumPostRepository.findAll());
        model.addAttribute("comments", commentRepository.findAll());
        return "forum";
    }

    @PostMapping("/addForumPost")
    public String addForumPost(HttpServletRequest request, @RequestParam String title, @RequestParam String content){
        ForumPost post = new ForumPost(title, content, (String) request.getSession().getAttribute("username"));
        logger.info("New forum post titled : " + title);
        forumPostRepository.save(post);
        return "redirect:/forum";
    }

    @PostMapping("/addForumComment/{id}")
    public String addForumComment(@PathVariable Long id, HttpServletRequest request, @RequestParam String comment){
        if(forumPostRepository.findById(id).isPresent()){
            Comment forumComment = new Comment(comment, (String) request.getSession().getAttribute("username"));
            forumComment.setForumPostId(id);
            commentRepository.save(forumComment);
            logger.info("New forum comment : " + forumComment.getId());
        }
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


    @PostMapping("/bookRequest/{id}")
    public String bookRequest(HttpServletRequest servletRequest, @PathVariable("id") String id){
        String username = (String) servletRequest.getSession().getAttribute("username");
        int appointmentID = Integer.parseInt(id);

        VaccineAppointment oldApt =  vaccineAppointmentRepository.getById(appointmentID);
        VaccineAppointment newApt = new VaccineAppointment(appointmentID, oldApt.getCentre(), oldApt.getTime(), oldApt.getDate(), "true", username);
        String[] dateArr = oldApt.getDate().split("-");
        LocalDate secondVaccDate = LocalDate.of(Integer.parseInt(dateArr[2]), Integer.parseInt(dateArr[1]), Integer.parseInt(dateArr[0]));
        String secondVaccDateString = secondVaccDate.plusDays(21L).toString();

        vaccineAppointmentRepository.deleteById(appointmentID);
        vaccineAppointmentRepository.save(newApt);

        // Second vacc date
        vaccineAppointmentRepository.save(new VaccineAppointment(oldApt.getCentre(), oldApt.getTime(), secondVaccDateString, "true", username));
        return "redirect:/booking";
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

                //PASSWORD VALIDATE HERE
                if(!(userValidation.isPasswordStrong(password))){
                    request.getSession().setAttribute("PasswordWeak", "true");
                    return "redirect:/newUserLogin";
                }

                Login login = new Login();
                login.setUsername(username);
                login.setPassword(passwordEncoder.encode(password));

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
            if(loginRepository.findById(username).get().getPassword().equals(passwordEncoder.encode(password))){
                request.getSession().setAttribute("login", "true");
                request.getSession().setAttribute("username", username);
                String PPS = loginRepository.findById(username).get().getPPS();
                if(usersRepository.existsById(PPS)){
                    if(usersRepository.findById(PPS).get().getVaccinationStage() > 1){
                        request.getSession().setAttribute("vaccinated", "true");
                    }
                }
                return "redirect:/";
            }
        }
        request.getSession().setAttribute("login", "false");
        return "redirect:/login";
    }

    @PostMapping("/adminLoginCheck")
    public String adminLoginCheck(@RequestParam String username, @RequestParam String password, HttpServletRequest request){

        if(adminRepository.existsById(username)){
            if(adminRepository.findById(username).get().getPassword().equals(passwordEncoder.encode(password))){
                request.getSession().setAttribute("admin_login", "true");
                request.getSession().setAttribute("username", username);

                if(adminRepository.findById(username).get().getPrivilege().equals("HSE")){
                    request.getSession().setAttribute("privilege", "HSE");
                }

                if(adminRepository.findById(username).get().getPrivilege().equals("Admin")){
                    request.getSession().setAttribute("privilege", "Admin");
                }

                return "redirect:/admin_homepage";
            }
        }

        request.getSession().setAttribute("admin_login", "false");
        return "redirect:/admin_login";
    }

    @PostMapping("/userData")
    public String createUser(@RequestParam String name, @RequestParam String surname, @RequestParam String email, @RequestParam String PPS, @RequestParam String dateOfBirth, @RequestParam String address, @RequestParam String phoneNumber, @RequestParam String nationality, @RequestParam String gender, HttpServletRequest request){
        if(usersRepository.existsById(PPS)){
            request.getSession().setAttribute("already_registered", true);
            return "redirect:/account_register";
        }

        LocalDate currentDate = LocalDate.now();
        String[] result = dateOfBirth.split("-");
        LocalDate birthday = LocalDate.of(Integer.parseInt(result[0]), Integer.parseInt(result[1]), Integer.parseInt(result[2]));

        long timeBetween = ChronoUnit.DAYS.between(birthday, currentDate);

        if(!(userValidation.isDateCorrectFormat(timeBetween))){
            request.getSession().setAttribute("failed_registered", false);
            System.out.print("Date failed");
            return "redirect:/account_register";
        }

        if(!(userValidation.isEmailCorrectFormat(email))){
            request.getSession().setAttribute("failed_registered", false);
            System.out.print("Email failed");
            return "redirect:/account_register";
        }

        PPS = PPS.toUpperCase();

        if(!(userValidation.isPPSCorrectFormat(PPS))){
            request.getSession().setAttribute("failed_registered", false);
            System.out.print("PPS failed");
            return "redirect:/account_register";
        }

        if(!(userValidation.isStringCorrectFormat(name)) || !(userValidation.isStringCorrectFormat(surname))){
            request.getSession().setAttribute("failed_registered", false);
            System.out.print("Name failed");
            return "redirect:/account_register";
        }

        if(!(userValidation.isPhoneNumberCorrectFormat(phoneNumber))){
            request.getSession().setAttribute("failed_registered", false);
            System.out.print("Phone failed");
            return "redirect:/account_register";
        }

        if(!(userValidation.isAddressCorrectFormat(address))){
            request.getSession().setAttribute("failed_registered", false);
            System.out.print("Address failed");
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
        user.setGender(gender);
        user.setVaccinationStage(0);
        request.getSession().setAttribute("PPS", PPS);
        request.getSession().setAttribute("NewlyRegistered", "true");
        usersRepository.save(user);

        return "redirect:/newUserLogin";
    }

    @PostMapping("/createNewHSEUser")
    public String createNewHSEUser(@RequestParam String email, @RequestParam String username, @RequestParam String password, @RequestParam String privilege, HttpServletRequest request){
        if(adminRepository.existsById(username)){
            request.getSession().setAttribute("already_registered", true);
            return "redirect:/registerNewUser";
        }

        Admin admin = new Admin();
        admin.setEmail(email);
        admin.setUsername(username);
        admin.setPassword(passwordEncoder.encode(password));
        admin.setPrivilege(privilege);
        adminRepository.save(admin);

        return "redirect:/admin_homepage";
    }

    // See All Books on Homepage
    @RequestMapping({"/"})
    public String viewHomePage(){
        if(usersRepository.findAll().isEmpty()){
            createData();
        }

        return "homepage";
    }

    @GetMapping("/error")
    public String viewErrorPage(){
        return "errorPage";
    }

    public void createData(){
        String[] nation = new String[]{"Irish", "American", "Italian", "Polish"};
        String[] gender = new String[]{"M", "F", "O"};
        for(int i = 0; i < 50; i++){
            User newBoi = new User("Ahdw231" + i, nation[i % 4], gender[i % 3], i%3);
            usersRepository.save(newBoi);
        }

        forumPostRepository.save(new ForumPost("How do I vaccine", "How do i get vaccine fast", "Someguy_2"));
        forumPostRepository.save(new ForumPost("Best vaccine?", "Which is the best vaccine to get", "UserGuy"));
        forumPostRepository.save(new ForumPost("Where can I complain", "This site looks like something the government would make, straight out of the 1980s", "FunVccineTia"));

        adminRepository.save(new Admin("ads@gmail.com", "mainAdmin", "superSecurePassword123", "Admin"));
        loginRepository.save(new Login("bingus", "bingus", "Ahdw2310"));

    }

}
