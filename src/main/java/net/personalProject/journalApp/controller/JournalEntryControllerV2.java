package net.personalProject.journalApp.controller;
import net.personalProject.journalApp.Services.JournalEntryService;
import net.personalProject.journalApp.Services.UserService;
import net.personalProject.journalApp.entity.JournalEntry;
import net.personalProject.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    JournalEntryService journalEntryService;

    @Autowired
    UserService userService;
        @GetMapping()
        public ResponseEntity<?> getAllJournalEntriesOfUser(){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
              User user = userService.findByUserName(userName);
              List<JournalEntry> all = user.getJournalEntries();
            if(all != null && !all.isEmpty()){
                return new ResponseEntity<>(all, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        @PostMapping()
           public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
            try {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String username = authentication.getName();
//                myEntry.setDate(LocalDateTime.now());
                journalEntryService.saveJournalEntry(myEntry, username);
                return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
            }catch(Exception e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        @GetMapping("/get-by-id/{myId}")
        public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User user = userService.findByUserName(username);
            List<JournalEntry> collect = user.getJournalEntries().stream().filter(x->x.getId().equals(myId)).collect(Collectors.toList());
            if(!collect.isEmpty()) {
                Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
                if (journalEntry.isPresent()) {
                    return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
                }
            }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        @DeleteMapping("/delete/{myid}")
        public ResponseEntity<?> deleteJournalById(@PathVariable ObjectId myid){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
                boolean removed = journalEntryService.deleteById(myid,username);
                if(removed) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }else{
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
        }

        @PutMapping("/id/{myid}")
       public ResponseEntity<JournalEntry> updateJournalById(@PathVariable ObjectId myid, @RequestBody JournalEntry newEntry){

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User user = userService.findByUserName(username);
            List<JournalEntry> collect = user.getJournalEntries().stream().filter(x->x.getId().equals(myid)).collect(Collectors.toList());

            if(!collect.isEmpty()) {
                Optional<JournalEntry> journalEntry = journalEntryService.findById(myid);
                if (journalEntry.isPresent()) {
                    JournalEntry old = journalEntry.get();
                    old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
                    old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
                    journalEntryService.saveJournalEntry(old);
                    return new ResponseEntity<>(old, HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

}