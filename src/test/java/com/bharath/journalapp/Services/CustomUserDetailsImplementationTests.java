package com.bharath.journalapp.Services;

import com.bharath.journalapp.Entities.User;
import com.bharath.journalapp.Repositories.UserJpaRepository;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomUserDetailsImplementationTests {

    @Autowired
    CustomUserDetailsImplementation customUserDetailsImplementation;

    @MockBean
    UserJpaRepository userJpaRepository;
    @Test
    public void test(){
        when(userJpaRepository.findByUsername(ArgumentMatchers.anyString())).thenReturn(User.builder().username("Bharath").password("sndn").roles(new ArrayList<>()).build());
        //So what we are doing here is that, when testing it takes time to establish connection and get the object, so what we do is that
        // whenever this findByUsername is called, we return a dummy user or object and then test the further code, instead of getting from the database
        UserDetails userDetails = customUserDetailsImplementation.loadUserByUsername("Bharath");
        assertNotNull(userDetails);
    }
}

//There are two ways you can do mock testing,
//1. You can make use of the application context,i.e., run the application and then use the @MockBean annotation, because
//you want to create a mock bean to inject to the customUserImplementation bean and can create mock objects
//whenever the repository is to be made use of.

//2.If you dont want to run the application and want to directly run the tests without creating a context and beans, then remove the
//@SpringBootTest and then add the annotation on the local variable of the class instance you want to test add the @InjectMocks
//annotation(it creates an instance of it also) and then add the @Mock on the repository instances that the test uses and then add a method with @BeforeAll annotation
// and inside that call the init mocks to initialise the mocks that are to be injected to the testing.
