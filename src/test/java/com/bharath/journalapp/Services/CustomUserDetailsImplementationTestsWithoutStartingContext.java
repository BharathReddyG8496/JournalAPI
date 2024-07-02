package com.bharath.journalapp.Services;

import com.bharath.journalapp.Entities.User;
import com.bharath.journalapp.Repositories.UserJpaRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsImplementationTestsWithoutStartingContext {

    @InjectMocks
    CustomUserDetailsImplementation customUserDetailsImplementation;

    @Mock
    UserJpaRepository userJpaRepository;

//    @BeforeEach
//    void test(){
//        MockitoAnnotations.initMocks(this);
//    }This is depricated, so use the annotation above Using MockitoExtension: This approach leverages JUnit 5's extension model to
//    automatically initialize mocks and inject them into the test class. It is cleaner and removes the need for manual setup and teardown.
    @Test
    public void test2(){
        when(userJpaRepository.findByUsername(ArgumentMatchers.anyString())).thenReturn(User.builder().username("Bharath").password("sndn").roles(new ArrayList<>()).build());
        UserDetails userDetails = customUserDetailsImplementation.loadUserByUsername("Bharath");
        System.out.println(userDetails);
        assertNotNull(userDetails);
    }
}



//2.If you dont want to run the application and want to directly run the tests without creating a context and beans, then remove the
//@SpringBootTest and then add the annotation on the local variable of the class instance you want to test add the @InjectMocks
//annotation(it creates an instance of it also) and then add the @Mock on the repository instances that the test uses and then add a method with @BeforeAll annotation
// and inside that call the init mocks to initialise the mocks that are to be injected to the testing.
