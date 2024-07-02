package com.bharath.journalapp.Services;

import com.bharath.journalapp.Entities.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest//This annotation is used to start the applicationContext i.e., the application cuz if you
//run the test application, the application doesnt run so the beans that are supposed to be created and
//autowired arent done, so this annotation starts the application.
public class UserServicesTests {


    @Autowired
    UserServices userservice;
    @Autowired
    JournalServices journalservice;

      @Test
    public void testadd(){
      assertEquals(10,5+5);
      assertNotEquals(10,5+6);
//      There are various methods that are related to equals, just type assert and then choose the method you want
         //and take into consideration that all the tests inside a method annotated as test are treated as a single test or unit
         // so even if one test inside the method fails then the whole test fails.
    }

    @Test
    @Disabled//This annotation is used if you dont want to run this test.
    public void testFindByUserId(){
        assertNotNull(userservice.findUserById(152));

//        assertTrue( journalservice.DeleteJournal(652,"Reddy"));
    }

    @ParameterizedTest
    @CsvSource({
            "10,10,20",
            "20,20,40",
            "11,12,23"
    })
    //The parameterisedTest allows for testing of multiple values instead writing a method for each by hardcoding for each value
    //The CSVSource is a set of comma seperated values that you give in the annotation and there is an annotation called
    //@CSVSourcefile where you can give the address and that csv file will be processed for inputs.
    public void paramTest(int a, int b, int expected){
          assertEquals(expected,a+b,"failed for "+ expected);
          //The message that is displayed of a test case fails
    }

    @ParameterizedTest
    @ArgumentsSource(UserArgumentProvider.class)
    public void userServiceAddUser(User user){
       assertNotNull(userservice.addNewUser(user));
        //For all about the ArgumentsSource, check the UserArgumentProvider class comments
    }



    //There are four annotations,
//    @BeforeAll: used to initialise or do something before running all the tests
//    @BeforeEach:used to initialise or do something before running each one of the tests


//    @AfterAll:used to initialise or do something after running all the tests
//    @AfterEach:used to initialise or do something before after each one of the tests


//    @ValueSource(strings={
//"Bharath","Sharath","mohan"
//    }) can be used if u give single values of a single type,can also use ints etc
    //You can use enumSource annotation as well and also instead of CSVSource.
}
//To check and generate percentages report of the methods tested, run the test using run as more and select run using coverage
// and you will get the results and then use the generate report to get an html file that contains the report in gui form
//can also be done using mavenwrapper but is not frequently used
