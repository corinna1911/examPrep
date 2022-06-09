package com.example.examprep.controller;

import com.example.examprep.events.OnRegistrationCompleteEvent;
import com.example.examprep.models.User;
import com.example.examprep.models.VerificationToken;
import com.example.examprep.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Calendar;
import java.util.Locale;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    ApplicationEventPublisher eventPublisher;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String register(@ModelAttribute User user) {
        User registered = userService.registerUser(user.getName(), user.getEmail(), user.getPassword());
        /*String appUrl = request.getContextPath();
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered,
                request.getLocale(), appUrl));*/
        //return "redirect:/login";
        return null;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String login(@ModelAttribute User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User savedUser = userService.findByUsername(user.getName());
        if (savedUser != null) {
            if (passwordEncoder.matches(user.getPassword(), userService.findByUsername(user.getName()).getPassword())) {
                session.setAttribute("username", user.getName());
                return "redirect:/index_cards.html";
            }
        }
        return "redirect:/log_in.html";

    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Principal userPrincipal = request.getUserPrincipal();
        session.removeAttribute(userPrincipal.getName());
        return "redirect:/log_in.html";

    }


    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping(path = "/delete")
    public String deleteUser(@RequestBody User user, HttpServletRequest request) {
        HttpSession session = request.getSession();

        userService.delete(user.getId());
        session.removeAttribute(user.getName());
        return "Deleted";
    }

    @GetMapping("/regitrationConfirm")
    public String confirmRegistration
            (WebRequest request, @RequestParam("token") String token) {

        Locale locale = request.getLocale();

        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }

        user.setEnabled(true);
        userService.confirmUser(user);
        return "redirect:/login.html?lang=" + request.getLocale().getLanguage();
    }



}
