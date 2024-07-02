package com.bharath.journalapp.Services;
import com.bharath.journalapp.Entities.Journal;
import com.bharath.journalapp.Entities.User;
import com.bharath.journalapp.Repositories.JournalJpaRepository;
import com.bharath.journalapp.Repositories.UserJpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
@Transactional
public class JournalServices {
    @Autowired
    private JournalJpaRepository journalJpaRepository;


    @Autowired
    private UserJpaRepository userJpaRepository;




    public List<Journal> getAllJournals(String username){
        return userJpaRepository.findByUsername(username).getJournal();
    }

    public boolean addJournalToUser(Journal journal,String username){
        User byUsername = userJpaRepository.findByUsername(username);
        try {
            journal.setPublishDate(LocalDate.now());
            journal.setUser(byUsername);
            journalJpaRepository.save(journal);
            return true;
           }catch (Exception e) {
            return false;
        }
    }

    public Journal findJournalByID(long id,String username){
        User byUsername = userJpaRepository.findByUsername(username);
        List<Journal> collect = byUsername.getJournal().stream().filter(x -> x.getId() == id).collect(Collectors.toList());
        if(!collect.isEmpty()) {
            return collect.get(0);
        }
        else return null;

        //You can implement this in two ways, one way is what I have done, find the user by his username and then get the journal of the user
        //which match the id given in the request, as there can only be one journal id, as it is a primary key you will only get one record in
        //the list so, you can return it back, but you can try another way, that is, the same until the if statement, but if the list is not empty
        // then you are sure that,journal id belongs to the user so,you can directly get from the journalRepository using the id.
    }

    public boolean updateJournal(Journal newJ,long id,String username){
        User byUsername = userJpaRepository.findByUsername(username);
        Optional<Journal> optOld = byUsername.getJournal().stream().filter(x -> x.getId() == id).findFirst();
        if(!optOld.isEmpty()) {
            Journal oldJournal = optOld.get();
            try {
                oldJournal.setJournal_name(newJ.getJournal_name() != null && !newJ.getJournal_name().equals("") ? newJ.getJournal_name() : oldJournal.getJournal_name());
                oldJournal.setDescription(newJ.getDescription() != null && !newJ.getDescription().equals("") ? newJ.getDescription() : oldJournal.getDescription());
                journalJpaRepository.save(oldJournal);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        else return false;
    }

    public boolean DeleteJournal(long id, String username) {
        User byUsername = userJpaRepository.findByUsername(username);
//        Optional<Journal> journalToRemove = byUsername.getJournal().stream()
//                .filter(x -> x.getId() == id)
//                .findFirst();
//
//        if (!journalToRemove.isPresent()) {
//            System.out.println("Journal with ID " + id + " not found for user " + username);
//            return false;
//        } else {
//            boolean removed = byUsername.getJournal().remove(journalToRemove.get());
//            if (!removed) {
//                System.out.println("Journal not removed from user's journal list");
//                return false;
//            } This is another method
        boolean removed = byUsername.getJournal().removeIf(ob->ob.getId()==id);
        if (removed) {
            userJpaRepository.save(byUsername);
            return true;
        }
        else {
            return false;
        }
        }
    }

