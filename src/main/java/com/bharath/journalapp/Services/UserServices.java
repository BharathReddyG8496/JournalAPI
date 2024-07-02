package com.bharath.journalapp.Services;


import com.bharath.journalapp.Entities.User;
import com.bharath.journalapp.Repositories.UserJpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UserServices {

    @Autowired
    private UserJpaRepository userJpaRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public List<User> returnUsers(){
        List<User> all = userJpaRepository.findAll();
        if(all.isEmpty())
            return null;
        else
            return all;
    }

    public User findUserById(long id){
            Optional<User> byId = userJpaRepository.findById(id);
            if (byId.isEmpty()){
                return null;
            }
            else{
                return byId.get();
            }

    }

//    public User addUser(User user){
//        try{
//            return userJpaRepository.save(user);
//        }catch (Exception e){
//            return null;
//        }
//    }

    public User addNewUser(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            return userJpaRepository.save(user);
        }catch (Exception e){
            return null;
        }
    }

    public User addNewAdmin(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER","ADMIN"));
            return userJpaRepository.save(user);
        }catch (Exception e){
            return null;
        }
    }


    public boolean updateUser(User user,String username){
        User updt=userJpaRepository.findByUsername(username);
            try{
                updt.setUsername(user.getUsername()==null && user.getUsername().equals("")? updt.getUsername() : user.getUsername());
                updt.setPassword(user.getPassword()==null && user.getPassword().equals("")? updt.getPassword() : user.getPassword());
                userJpaRepository.save(updt);
                return true;
            }catch (Exception e){
                return false;
            }
        }



    public void deleteUser(String username){
       userJpaRepository.deleteByUsername(username);
    }

}
