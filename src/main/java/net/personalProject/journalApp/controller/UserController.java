package net.personalProject.journalApp.controller;

import net.personalProject.journalApp.Repository.UserEntryRepository;
import net.personalProject.journalApp.Services.QuoteService;
//import net.personalProject.journalApp.Services.SentimentAnalysisService;
import net.personalProject.journalApp.Services.UserService;
import net.personalProject.journalApp.Services.WeatherService;
import net.personalProject.journalApp.apiResponse.QuoteResponse;
import net.personalProject.journalApp.apiResponse.WeatherResponse;
import net.personalProject.journalApp.entity.JournalEntry;
import net.personalProject.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    WeatherService weather;

    @Autowired
    QuoteService quoteService;

    @Autowired
    UserEntryRepository userEntryRepository;
//    -> To update password, if username is given.


//    @Autowired
//    private SentimentAnalysisService sentimentAnalysisService;



//    Below method assumes that the user is already authenticated and it is changing the password/username.
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDB = userService.findByUserName(username);
            userInDB.setUserName(user.getUserName());
            userInDB.setPassword(user.getPassword());
          userService.saveUserEntry(userInDB);
            return new ResponseEntity<>(HttpStatus.OK);
    }



    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userEntryRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // WeatherResponse weatherResponse = weather.getWeather("New Delhi");
        WeatherResponse weatherResponse = null;
        QuoteResponse quoteResponse = quoteService.quoteOfTheMoment();
        String quote = quoteResponse.getQuote();
        String greetings;
        if(weatherResponse!=null) {
             greetings = "Hi " + authentication.getName()+", today feels like "+weatherResponse.getCurrent().getFeelsLike();
        }else{
            greetings = "Hi " + authentication.getName();
        }
        return new ResponseEntity<>(greetings+"\n, QUOTE OF THE MOMENT: "+quote, HttpStatus.OK);
    }


//    @GetMapping("/get-sentiment")
//    public ResponseEntity<?> getSentiment(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//
//        User user = userService.findByUserName(username);
//
//        JournalEntry journal = user.getJournalEntries().get(0);
//        String content = journal.getContent();
//
//        Double v = sentimentAnalysisService.analyzeSentiment(user.getJournalEntries().get(0).getContent());
//
//        return new ResponseEntity<>("Score"+v,HttpStatus.OK);
//
//    }


}
