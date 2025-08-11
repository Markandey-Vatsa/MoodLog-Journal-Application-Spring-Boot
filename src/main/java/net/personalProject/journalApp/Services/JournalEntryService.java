package net.personalProject.journalApp.Services;

import net.personalProject.journalApp.entity.JournalEntry;
import net.personalProject.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class JournalEntryService {


    @Autowired
    private net.personalProject.journalApp.Repository.JournalEntryRepository JournalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveJournalEntry(JournalEntry JournalEntry, String username){

        try {
            User user = userService.findByUserName(username);
            JournalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = JournalEntryRepository.save(JournalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUserEntry(user);

        }catch(Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occurred while saving the entry.");
        }
    }



    public void saveJournalEntry(JournalEntry JournalEntry){
        JournalEntryRepository.save(JournalEntry);
    }

    public List<JournalEntry> getAllEntries(){
        return JournalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){  // -> optional -> isme ya to ho sakta hai ya nhi bhi ho sakta hai
        return JournalEntryRepository.findById(id);

    }


    @Transactional
    public boolean deleteById(ObjectId myid,String username){
        boolean removed = false;
        try {
            User user = userService.findByUserName(username);
            removed = user.getJournalEntries().removeIf(je -> je.getId().equals(myid));
            if(removed) {
                userService.saveUserEntry(user);
                JournalEntryRepository.deleteById(myid);
            }
        }catch(Exception e){
            System.out.println("Exception encountered: "+e);
        }

        return removed;
    }





}
