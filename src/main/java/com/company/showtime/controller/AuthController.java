package com.company.showtime.controller;

import com.company.showtime.entities.User;
import com.company.showtime.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }


    /**
     * Method meant to redirect to the homepage.
     * Doesn't seem to be working at the moment.
     * "href:"/"" will suffice for now.
     * @return
     */
    /*// handler method to handle home page request
    @GetMapping("/index")
    public String home(){
        return "index";
    }*/

    /**
     * GetMapping for url "/login" for the login page.
     *
     * The SpringSecurity intercepts the login form
     * and takes care of the login functionality.
     *
     * @return the "login" to the login page.
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * GetMapping for the register page.
     *
     * The model takes in the user's inputs and
     * verifies them in the "/register/save" PostMapping.
     *
     * @param model - used to send data to the frontend
     * @return to the register page
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    /**
     * The PostMapping for the registration form.
     *
     * The method verifies the inputted information
     * and returns to the register page if there is an
     * error with the information.
     *
     * Otherwise, it will succeed in registering a user
     * and return to the success page.
     *
     * @param user - the user object created from the user's inputs. (Is validated for the entity parameters,SEE User)
     * @param result - holds the result of validation and contains any errors
     * @param model - used to send data to the frontend
     * @return to the register page with the success or failure of a register attempt.
     */
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") User user,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByUsername(user.getUsername());

        if(existingUser != null && user.getUsername() != null && !existingUser.getUsername().isEmpty()){
            result.rejectValue("username", null,
                    "There is already an account registered with the same username");
        }

        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "/register";
        }

        userService.saveUser(user);
        return "redirect:/register?success";
    }



}
