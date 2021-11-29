package com.example.hiddendragon;


//import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.Optional;
import java.time.*; 
import java.util.List;
import java.sql.Connection;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64.Encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
@RequestMapping("/")
public class DSController {  
    
    @GetMapping
    public String getAction() {
        // String hn = "";
        // try{
        // InetAddress ip = InetAddress.getLocalHost();
        // hn = ip.getHostName();
        // }
        // catch(Exception e) {}
        // /* Render View */
        // model.addAttribute( "hostName", hn ) ;

        return "drugStore";

    }
    //private DSRepository dsRepository;
    @PostMapping
    public String postAction() {

        return "drugStore";
    }

}

// @ModelAttribute("command") PaymentsCommand command, 
// Model model

// @Valid @ModelAttribute("command") PaymentsCommand command,  
//                             @RequestParam(value="action", required=true) String action,
//                             Errors errors, Model model, HttpServletRequest request
