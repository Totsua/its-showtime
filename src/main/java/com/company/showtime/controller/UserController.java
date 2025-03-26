package com.company.showtime.controller;

import com.company.showtime.exceptions.CustomException;
import com.company.showtime.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *       *******Currently under construction*******
 *       Will be the controller that dictates the
 *       save/delete cinema from User's List, or
 *       Update cinema name.
 */
@Controller
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/saveCinema")
    public String saveUserCinema(HttpServletRequest request, RedirectAttributes redirectAttributes) throws CustomException {
        try {
            service.saveUserCinema(request.getParameter("cinemaId"));
            redirectAttributes.addFlashAttribute("message", "Cinema saved successfully");
        }catch (CustomException e){
            redirectAttributes.addFlashAttribute("error", "Error saving cinema");
        }

        return "redirect:/cinemas";
    }


}
