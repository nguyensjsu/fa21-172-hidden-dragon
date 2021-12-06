package com.example.hiddendragon;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.StringArraySerializer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.example.springcybersource.*;
import com.example.hiddendragon.*;

@Slf4j
@Controller
@RequestMapping("/")
public class MainController {


  //for payments start
  @Value("${cybersource.apihost}") String apiHost;
  @Value("${cybersource.merchantkeyid}") String merchantKeyId;
  @Value("${cybersource.merchantsecretkey}") String merchantSecretKey;
  @Value("${cybersource.merchantid}") String merchantId ;

  private CyberSourceAPI api = new CyberSourceAPI();

  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private DSRepository repo;
  @Autowired
  private RabbitMqSender sender;

  @Getter
  @Setter
  class  Message{
      private String msg;
      public Message(String m){
          msg = m;
      }
  }
  class  ErrorMessages{
      private ArrayList<Message>  messages = new ArrayList<Message>();
      public void add (String msg){
          messages.add(new Message(msg));
      }
      public ArrayList<Message> getMessages() {return messages;
      }
      public void print(){
          for(Message m : messages){
              System.out.println(m.msg);
          }
      }
  }
  private  static Map<String,String> months = new HashMap<>(); static{
    months.put("January", "01");
    months.put("February", "02");
    months.put("March", "03");
    months.put("April", "04");
    months.put("May", "05");
    months.put("June", "06");
    months.put("July", "07");
    months.put("August", "08");
    months.put("September", "09");
    months.put("October", "10");
    months.put("November", "11");
    months.put("December", "12");
}
private  static Map<String,String> states = new HashMap<>(); static{
    states.put("AK",  "Alabama");
    states.put("AL",  "Alaska");
    states.put("AR",  "Arizona");
    states.put("AZ",  "Arkansas");
    states.put("CA",  "California");
    states.put("CO",  "Colorado");
    states.put("CT",  "Conneticut");
    states.put("DE",  "Delaware");
    states.put("FL",  "Florida");
    states.put("GA",  "Georgia");
    states.put("HI",  "Hawaii");
    states.put("ID",  "Idaho");
    states.put("IL",  "Illinois");
    states.put("IN",  "Indiana");
    states.put("IA",  "Iowa");
    states.put("KS",  "Kansas");
    states.put("KY",  "Kentucky");
    states.put("LA",  "Louisiana");
    states.put("ME",  "Maine");
    states.put("MD",  "Maryland");
    states.put("MA",  "Massachusetts");
    states.put("MI",  "Michigan");
    states.put("MN",  "Minnesota");
    states.put("MS",  "Mississippi");
    states.put("MO",  "Missouri");
    states.put("MT",  "Montana");
    states.put("NE",  "Nebraska");
    states.put("NV",  "Nevada");
    states.put("NH",  "New Hampshire");
    states.put("NJ",  "New Jersey");
    states.put("NM",  "New Mexico");
    states.put("NY",  "New York");
    states.put("NC",  "North Carolina");
    states.put("ND",  "North Dakota");
    states.put("OH",  "Ohio");
    states.put("OK",  "Oklahoma");
    states.put("OR",  "Oregon");
    states.put("PA",  "Pennsylvania");
    states.put("RI",  "Rhode Island");
    states.put("SC",  "South Carolina");
    states.put("SD",  "South Dakota");
    states.put("TN",  "Tennessee");
    states.put("TX",  "Texas");
    states.put("UT",  "Utah");
    states.put("VT",  "Vermont");
    states.put("VA",  "Virginia");
    states.put("WA",  "Washington");
    states.put("WV",  "West Virgnia");
    states.put("WI",  "Wisconsin");
    states.put("WY",  "Wyoming");
}
 //for payments end


    //run on docker
    private String USERS_URI = "http://localhost:8080/users";
    private String CARTS_URI = "http://localhost:8080/carts";
    private String ITEM_URI = "http://localhost:8080/items";
    private String PAYMENT_URI = "http://localhost:8080/payments";


  @GetMapping
  public String home(@ModelAttribute User user, Model model) {
      System.out.println("Redirecting to home");
      
      return "home";
  }

  //login
  @PostMapping("/login")
  public String login(@ModelAttribute User user, Model model ){
    log.info("User " + user.getUsername() + " attemped login");
    System.out.println(user.getUsername() + " / " + user.getPassword());

    
    try {
      ResponseEntity<User> response = restTemplate.postForEntity(USERS_URI + "/login?username=" + user.getUsername() + "&password=" + user.getPassword(), user, User.class);
      return "redirect:/store";
    } catch(Exception e) {
      return "wrongUsePass"; 
    }

  }


  //register
  @PostMapping("/register")
  public String register(@ModelAttribute User user ){
    log.info("User " + user.getUsername() + " / " + user.getPassword() + " attemped register");
    System.out.println(user.getUsername() + " / " + user.getPassword());

    try {
      ResponseEntity<User> response = restTemplate.postForEntity(USERS_URI + "/register?username=" + user.getUsername() + "&password=" + user.getPassword(), user, User.class);
      
      return "regSuc";
    } catch(Exception e) {
      return "wrongDupAcc";
    }
    
  }

  //reset
  @PostMapping("/reset")
  public String reset(@RequestParam("username") String username, @RequestParam("existingPassword") String existingPassword, @RequestParam("newPassword") String newPassword, Model model ){
    System.out.println("Resetting password for" + username + " " + existingPassword + " new password: " + newPassword);
    try {
      ResponseEntity<User> response = restTemplate.postForEntity(USERS_URI + "/reset?username=" + username + "&existingPassword=" + existingPassword + "&newPassword=" + newPassword, null, User.class);
      System.out.println(response.toString());
      return "regSuc";
    } catch(Exception e) {
      return "error";
    }
    
  }
  
//store products
  @GetMapping("/store")
  public String getStore(Model model){
    System.out.println("Accessing page store");
    sender.send("test message - accessing store");
    try {
      ArrayList<Item> items = new ArrayList<Item>();
      ResponseEntity<ArrayList> response = restTemplate.getForEntity(ITEM_URI, ArrayList.class);
      ObjectMapper mapper = new ObjectMapper();
      System.out.println(response.getBody().toString());
      for(Object item: response.getBody()){
        Item storeItem = new Item();

        storeItem = mapper.convertValue(item, Item.class);
        items.add(storeItem);
        System.out.println(storeItem);
      }
      model.addAttribute("user", 1);
      model.addAttribute("items", items);
      return "store";
    } catch(Exception e)  {
      return "error";
    }
    

  }

  //error page
  @GetMapping("/error")
  public String getErrorpage(){
    return "error";

  }
  //checkout page
  @GetMapping("/checkout")
  public String getCheckout(@ModelAttribute("checkout")DSCommand command, Model model){
    ResponseEntity<Integer> response = restTemplate.getForEntity(CARTS_URI + "/total?userId=1", Integer.class);
    model.addAttribute("total", response.getBody());
    return "checkout";
  }

  // cart page
  @GetMapping("/cart")
  public String getCart(Model model){
    Integer id = 1;

    try {
      ArrayList<CartItem> items = new ArrayList<CartItem>();
      ResponseEntity<ArrayList> response = restTemplate.getForEntity(CARTS_URI, ArrayList.class, id);
      ResponseEntity<Integer> totalResponse = restTemplate.getForEntity(CARTS_URI + "/total?=" + id, Integer.class, id);
      ObjectMapper mapper = new ObjectMapper();

      for(Object item: response.getBody()){
        CartItem cartItem = new CartItem();

        cartItem = mapper.convertValue(cartItem, CartItem.class);
        items.add(cartItem);
        System.out.println(cartItem);
      }

      model.addAttribute("items", items);
      model.addAttribute("total", totalResponse.getBody());

      return "cart";
    } catch(Exception e)  {
      return "error";
    }

  }

  @PostMapping("/checkout")
    public String postAction(@Valid @ModelAttribute("checkout") DSCommand command,  
    @RequestParam(value="action", required=true) String action,
    Errors errors, Model model, HttpServletRequest request
) {
    log.info("Command: " + command);
    CyberSourceAPI.setHost(apiHost);
    CyberSourceAPI.setKey(merchantKeyId);
    CyberSourceAPI.setSecret(merchantSecretKey);
    CyberSourceAPI.setMerchant(merchantId);
    CyberSourceAPI.debugConfig(); 

    ResponseEntity<String> response = restTemplate.getForEntity(CARTS_URI + "/total?userId=1", String.class); //for total
    command.setTransactionAmount(response.getBody());
    
    ErrorMessages msgs = new ErrorMessages();
    int min = 1239871;
    int max = 9999999;
    int random = (int)Math.floor(Math.random()*(max-min+1)+min);
    String orderNum = String.valueOf(random);
    command.setOrderNumber(orderNum);
    boolean hasErrors = false;
    if(command.firstname().equals("")){hasErrors = true; msgs.add("First Name Required.");}
    if(command.lastname().equals("")){hasErrors = true; msgs.add("Last Name Required.");}
    if(command.address().equals("")){hasErrors = true; msgs.add("Address Required.");}
    if(command.city() .equals("")){hasErrors = true; msgs.add("City Required.");}
    if(command.state() .equals("")){hasErrors = true; msgs.add("State Required.");}
    if(command.zip() .equals("")){hasErrors = true; msgs.add("Zip Required.");}
    if(command.phone() .equals("")){hasErrors = true; msgs.add("Phone Required.");}
    if(command.cardnum() .equals("")){hasErrors = true; msgs.add("Card Number Required.");}
    if(command.cardexpmon() .equals("")){hasErrors = true; msgs.add("Card Expiration Month Required.");}
    if(command.cardexpyear() .equals("")){hasErrors = true; msgs.add("Card Expiration Year Required.");}
    if(command.cardcvv().equals("")){hasErrors = true; msgs.add("Card CVV Required.");}
    if(command.email() .equals("")){hasErrors = true; msgs.add("Email Required.");}
    if(command.zip().matches("\\d{4}")){hasErrors = true; msgs.add("Invalid zip.");}
    if(command.phone().matches("[(]\\d{3}[)] \\d{3}-\\d{4}")) {hasErrors = true; msgs.add("Invalid phone.");}
    if(command.cardnum().matches("\\d{3}-\\d{4}-\\d{4}-\\d{4}")){hasErrors = true; msgs.add("Invalid card.");}
    if(command.cardcvv().matches("\\d{2}"))  {hasErrors = true; msgs.add("Invalid CVV.");}

    if(hasErrors){
        msgs.print();
        model.addAttribute("messages", msgs.getMessages());
        return "creditcards";
    }
    String cardNumInput = command.cardnum();
    int cardTypeInput = Integer.parseInt(cardNumInput.substring(0, 1));
    if (cardTypeInput == 4) // visa
    {
        command.setCardType("001");
    } else if (cardTypeInput == 5) // mastercard
    {
        command.setCardType("002");
    } else if (cardTypeInput == 6) // discover
    {
        command.setCardType("004");
    }
    model.addAttribute( "message", "Thank You for Your Payment! Your order number is: " + orderNum ) ;

    AuthRequest req = new AuthRequest();
        req.reference = orderNum;
        req.billToFirstName = command.firstname();
        req.billToLastName = command.lastname() ;
        req.billToAddress = command.address() ;
        req.billToCity = command.city() ;
        req.billToState = command.state() ;
        req.billToZipCode = command.zip() ;
        req.billToPhone = command.phone() ;
        req.billToEmail = command.email() ;
        req.transactionAmount = command.transactionamount();
        req.transactionCurrency = "USD" ;
        req.cardNumber = command.cardnum() ;
        req.cardExpMonth = command.cardexpmon() ;
        req.cardExpYear = command.cardexpyear() ;
        req.cardCVV = command.cardcvv() ;
        req.cardType = command.getCardType();
        //req.cardType = CyberSourceAPI.getCardType(req.cardNumber);
        boolean auth = false;
        AuthResponse   authRes = new AuthResponse();
        System.out.println("\n\nAuthRequest: " + authRes.toJson());
        authRes = api.authorize(req);
        auth = true;
        boolean captureValid = false;
        CaptureRequest capture = new CaptureRequest();
        CaptureResponse captureresponse = new CaptureResponse();
        if(auth){
            sender.send("payment confirm");
            capture.paymentId = authRes.id;
            capture.transactionAmount = command.transactionamount();
            capture.transactionCurrency = "USD";
            captureresponse  = api.capture(capture);
            captureValid = true;
        }

        // if(auth && captureValid){
        //     command.setOrderNumber(orderNum);
        //     command.setTransactionAmount("30.00");
        //     command.setTransactionCurrency("USD");
        //     command.setAuthId (authRes.id);
        //     command.setAuthStatus(authRes.status);
        //     command.setCaptureId (captureresponse.id);
        //     command.setCaptureStatus (captureresponse.status);
     
        // }
        return "orderSuc";

        }


  @PostMapping("/cart")
  public String initiateCheckout(@ModelAttribute Item item, @RequestParam("userId") Integer id, @RequestParam("quantity") Integer quantity, Model model){
    System.out.println("Adding item to cart" + item.getName() + item.getId() + "/" + id + "/" + quantity);
    System.out.println(CARTS_URI + "/add?userId="+ id + "&quantity=" + quantity + "&name=" + item.getName() + "&price=" + item.getPrice() + "&stock=" + item.getStock() + "&id=" + item.getId());
    ResponseEntity<CartItem> response = restTemplate.postForEntity(CARTS_URI + "/add?userId="+ id + "&quantity=" + quantity + "&name=" + item.getName() + "&price=" + item.getPrice() + "&stock=" + item.getStock() + "&id=" + item.getId(), item, CartItem.class);
    System.out.println(response.getBody());
    return "checkout";
  }

  @GetMapping("/cart")
  public String getCart(@RequestParam("userId") Integer id, Model model ){
    System.out.println("User " + id + "accessing cart");

    try {
      ArrayList<CartItem> items = new ArrayList<CartItem>();
      ResponseEntity<ArrayList> response = restTemplate.getForEntity(CARTS_URI, ArrayList.class, id);
      ResponseEntity<Integer> totalResponse = restTemplate.getForEntity(CARTS_URI + "/total?=" + id, Integer.class, id);
      ObjectMapper mapper = new ObjectMapper();

      for(Object item: response.getBody()){
        CartItem cartItem = new CartItem();

        cartItem = mapper.convertValue(cartItem, CartItem.class);
        items.add(cartItem);
        System.out.println(cartItem);
      }

      model.addAttribute("items", items);
      model.addAttribute("total", totalResponse.getBody());

      return "checkout";
    } catch(Exception e)  {
      return "error";

    }


  }

  // @GetMapping("/thankYou")
  // public String getOrderNum(@ModelAttribute("thankYou")DSCommand command, Model model){
  //   model.addAttribute("orderNum", command.getOrdernumber());
  //   return "thankYou";
  // }
}

