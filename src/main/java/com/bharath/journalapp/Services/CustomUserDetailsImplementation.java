package com.bharath.journalapp.Services;

import com.bharath.journalapp.Entities.User;
import com.bharath.journalapp.Repositories.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;



@Component
public class CustomUserDetailsImplementation implements UserDetailsService {

    @Autowired
    UserJpaRepository userJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUsername = userJpaRepository.findByUsername(username);
        if(byUsername==null){
            throw new UsernameNotFoundException("User not Found with name: " + username);
        }
        else {

            return org.springframework.security.core.userdetails.User.builder()
                    .username(byUsername.getUsername())
                    .password(byUsername.getPassword())
                    .roles(byUsername.getRoles().toArray(new String[0]))
                    .build();
            //Please dont forget to change the fetch type to Eager in the roles attribute, cuz its annotation @ElementCollection is
            // Lazily initialised by default, change it to fetch-FETCH.EAGER
//            The roles collection in your User entity is defined with @ElementCollection, which is by default lazily loaded.
//                When Spring Security attempts to access the roles collection during authentication, it encounters the lazy
//                initialization exception because the collection is being accessed outside of an active Hibernate session.
        }

    }


}
