package com.example.hiddendragon;


import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.*;
import java.time.*; 


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;
import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;

import com.example.springcybersource.*;
import org.springframework.beans.factory.annotation.Value;
import com.example.hiddendragon.*;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
@Controller
@RequestMapping("/")
public class DSController {
      
//     @Value("${cybersource.apihost}") String apiHost;
//     @Value("${cybersource.merchantkeyid}") String merchantKeyId;
//     @Value("${cybersource.merchantsecretkey}") String merchantSecretKey;
//     @Value("${cybersource.merchantid}") String merchantId ;
//     private CyberSourceAPI api = new CyberSourceAPI();
//     @Autowired
//     private DSRepository repo;
    
//     @GetMapping
//     public String getAction( @ModelAttribute("testCommand") DSCommand command, 
//     Model model) {
    

//         return "drugStore";

//     }
//    private DSRepository dsRepository;
//    private  static Map<String,String> months = new HashMap<>(); static{
//     months.put("January", "01");
//     months.put("February", "02");
//     months.put("March", "03");
//     months.put("April", "04");
//     months.put("May", "05");
//     months.put("June", "06");
//     months.put("July", "07");
//     months.put("August", "08");
//     months.put("September", "09");
//     months.put("October", "10");
//     months.put("November", "11");
//     months.put("December", "12");
// }
// private  static Map<String,String> states = new HashMap<>(); static{
//     states.put("AK",  "Alabama");
//     states.put("AL",  "Alaska");
//     states.put("AR",  "Arizona");
//     states.put("AZ",  "Arkansas");
//     states.put("CA",  "California");
//     states.put("CO",  "Colorado");
//     states.put("CT",  "Conneticut");
//     states.put("DE",  "Delaware");
//     states.put("FL",  "Florida");
//     states.put("GA",  "Georgia");
//     states.put("HI",  "Hawaii");
//     states.put("ID",  "Idaho");
//     states.put("IL",  "Illinois");
//     states.put("IN",  "Indiana");
//     states.put("IA",  "Iowa");
//     states.put("KS",  "Kansas");
//     states.put("KY",  "Kentucky");
//     states.put("LA",  "Louisiana");
//     states.put("ME",  "Maine");
//     states.put("MD",  "Maryland");
//     states.put("MA",  "Massachusetts");
//     states.put("MI",  "Michigan");
//     states.put("MN",  "Minnesota");
//     states.put("MS",  "Mississippi");
//     states.put("MO",  "Missouri");
//     states.put("MT",  "Montana");
//     states.put("NE",  "Nebraska");
//     states.put("NV",  "Nevada");
//     states.put("NH",  "New Hampshire");
//     states.put("NJ",  "New Jersey");
//     states.put("NM",  "New Mexico");
//     states.put("NY",  "New York");
//     states.put("NC",  "North Carolina");
//     states.put("ND",  "North Dakota");
//     states.put("OH",  "Ohio");
//     states.put("OK",  "Oklahoma");
//     states.put("OR",  "Oregon");
//     states.put("PA",  "Pennsylvania");
//     states.put("RI",  "Rhode Island");
//     states.put("SC",  "South Carolina");
//     states.put("SD",  "South Dakota");
//     states.put("TN",  "Tennessee");
//     states.put("TX",  "Texas");
//     states.put("UT",  "Utah");
//     states.put("VT",  "Vermont");
//     states.put("VA",  "Virginia");
//     states.put("WA",  "Washington");
//     states.put("WV",  "West Virgnia");
//     states.put("WI",  "Wisconsin");
//     states.put("WY",  "Wyoming");
// }
//     @PostMapping
//     public String postAction(@Valid @ModelAttribute("command") DSCommand command,  
//     @RequestParam(value="action", required=true) String action,
//     Errors errors, Model model, HttpServletRequest request
// ) {
//     // CyberSourceAPI.setHost(apiHost);
//     // CyberSourceAPI.setKey(merchantKeyId);
//     // CyberSourceAPI.setSecret(merchantSecretKey);
//     // CyberSourceAPI.setMerchant(merchantId);
//     // CyberSourceAPI.debugConfig(); 

//         return "drugStore";
//     }

}

// @ModelAttribute("command") DSCommand command, 
// Model model

