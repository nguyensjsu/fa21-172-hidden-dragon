// package com.example.hiddendragon;


// import javax.validation.Valid;
// import javax.servlet.http.HttpServletRequest;
// import java.net.InetAddress;
// import java.util.*;
// import java.time.*; 


// import javax.crypto.Mac;
// import javax.crypto.spec.SecretKeySpec;
// import java.security.InvalidKeyException;
// import java.security.NoSuchAlgorithmException;


// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.validation.Errors;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.validation.BindingResult;
// import lombok.extern.slf4j.Slf4j;
// import lombok.Getter;
// import lombok.Setter;

// import com.example.springcybersource.*;
// import org.springframework.beans.factory.annotation.Value;
// import com.example.hiddendragon.*;



// @Slf4j
// @Controller
// @RequestMapping("/checkout")
// public class DSController {  
//     @Value("${cybersource.apihost}") String apiHost;
//     @Value("${cybersource.merchantkeyid}") String merchantKeyId;
//     @Value("${cybersource.merchantsecretkey}") String merchantSecretKey;
//     @Value("${cybersource.merchantid}") String merchantId ;

//     private CyberSourceAPI api = new CyberSourceAPI();

//     @Autowired
//     private DSRepository repo;
    
//     @Getter
//     @Setter
//     class  Message{
//         private String msg;
//         public Message(String m){
//             msg = m;
//         }
//     }
//     class  ErrorMessages{
//         private ArrayList<Message>  messages = new ArrayList<Message>();
//         public void add (String msg){
//             messages.add(new Message(msg));
//         }
//         public ArrayList<Message> getMessages() {return messages;
//         }
//         public void print(){
//             for(Message m : messages){
//                 System.out.println(m.msg);
//             }
//         }
//     }
   


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
//     public String postAction(@Valid @ModelAttribute("checkout") DSCommand command,  
//     @RequestParam(value="action", required=true) String action,
//     Errors errors, Model model, HttpServletRequest request
// ) {
//     log.info("Command: " + command);
//     CyberSourceAPI.setHost(apiHost);
//     CyberSourceAPI.setKey(merchantKeyId);
//     CyberSourceAPI.setSecret(merchantSecretKey);
//     CyberSourceAPI.setMerchant(merchantId);
//     CyberSourceAPI.debugConfig(); 

//     ErrorMessages msgs = new ErrorMessages();
//     int min = 1239871;
//     int max = 9999999;
//     int random = (int)Math.floor(Math.random()*(max-min+1)+min);
//     String orderNum = String.valueOf(random);
//     boolean hasErrors = false;
//     if(command.firstname().equals("")){hasErrors = true; msgs.add("First Name Required.");}
//     if(command.lastname().equals("")){hasErrors = true; msgs.add("Last Name Required.");}
//     if(command.address().equals("")){hasErrors = true; msgs.add("Address Required.");}
//     if(command.city() .equals("")){hasErrors = true; msgs.add("City Required.");}
//     if(command.state() .equals("")){hasErrors = true; msgs.add("State Required.");}
//     if(command.zip() .equals("")){hasErrors = true; msgs.add("Zip Required.");}
//     if(command.phone() .equals("")){hasErrors = true; msgs.add("Phone Required.");}
//     if(command.cardnum() .equals("")){hasErrors = true; msgs.add("Card Number Required.");}
//     if(command.cardexpmon() .equals("")){hasErrors = true; msgs.add("Card Expiration Month Required.");}
//     if(command.cardexpyear() .equals("")){hasErrors = true; msgs.add("Card Expiration Year Required.");}
//     if(command.cardcvv().equals("")){hasErrors = true; msgs.add("Card CVV Required.");}
//     if(command.email() .equals("")){hasErrors = true; msgs.add("Email Required.");}
//     if(command.zip().matches("\\d{4}")){hasErrors = true; msgs.add("Invalid zip.");}
//     if(command.phone().matches("[(]\\d{3}[)] \\d{3}-\\d{4}")) {hasErrors = true; msgs.add("Invalid phone.");}
//     if(command.cardnum().matches("\\d{3}-\\d{4}-\\d{4}-\\d{4}")){hasErrors = true; msgs.add("Invalid card.");}
//     if(command.cardcvv().matches("\\d{2}"))  {hasErrors = true; msgs.add("Invalid CVV.");}

//     if(hasErrors){
//         msgs.print();
//         model.addAttribute("messages", msgs.getMessages());
//         return "creditcards";
//     }

//     model.addAttribute( "message", "Thank You for Your Payment! Your order number is" + orderNum ) ;

//     AuthRequest req = new AuthRequest();
//         req.reference = orderNum;
//         req.billToFirstName = command.firstname();
//         req.billToLastName = command.lastname() ;
//         req.billToAddress = command.address() ;
//         req.billToCity = command.city() ;
//         req.billToState = command.state() ;
//         req.billToZipCode = command.zip() ;
//         req.billToPhone = command.phone() ;
//         req.billToEmail = command.email() ;
//         req.transactionAmount = "30.00" ;
//         req.transactionCurrency = "USD" ;
//         req.cardNumber = command.cardnum() ;
//         req.cardExpMonth = command.cardexpmon() ;
//         req.cardExpYear = command.cardexpyear() ;
//         req.cardCVV = command.cardcvv() ;
//         //req.cardType = CyberSourceAPI.getCardType(req.cardNumber);
//         boolean auth = false;
//         AuthResponse   authRes = new AuthResponse();
//         System.out.println("\n\nAuthRequest: " + authRes.toJson());
//         authRes = api.authorize(req);
//         auth = true;
//         boolean captureValid = false;
//         CaptureRequest capture = new CaptureRequest();
//         CaptureResponse captureresponse = new CaptureResponse();
//         if(auth){
//             capture.paymentId = authRes.id;
//             capture.transactionAmount = "30.00";
//             capture.transactionCurrency = "USD";
//             captureresponse  = api.capture(capture);
//             captureValid = true;
//         }

//         // if(auth && captureValid){
//         //     command.setOrderNumber(orderNum);
//         //     command.setTransactionAmount("30.00");
//         //     command.setTransactionCurrency("USD");
//         //     command.setAuthId (authRes.id);
//         //     command.setAuthStatus(authRes.status);
//         //     command.setCaptureId (captureresponse.id);
//         //     command.setCaptureStatus (captureresponse.status);
     
//         // }
//         return "checkout";
//     }

// }

// // @ModelAttribute("command") DSCommand command, 
// // Model model

