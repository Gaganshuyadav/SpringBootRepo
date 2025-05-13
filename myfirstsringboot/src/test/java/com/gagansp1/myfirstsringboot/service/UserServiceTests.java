package com.gagansp1.myfirstsringboot.service;

import com.gagansp1.myfirstsringboot.entity.User;
import com.gagansp1.myfirstsringboot.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {


    @Autowired
    private UserRepository userRepository;

    @Disabled
    @Test
    public void simpleTest1(){
        assertEquals(4,4);
        assertNotNull(userRepository.findByUserName("ironman1"));
        // assertNotNull(userRepository.findByUserName("ironma"));
    }

    @Test
    public void testFindByUserName2(){
        User user = userRepository.findByUserName("ironman1");
        assertTrue(user.getJournalEnteries().isEmpty());
    }


    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,9",
            "2,4,4"
    })
    public void testParamsVise3(int a, int b, int expected){
        assertEquals( expected, a+b);
    }


    @ParameterizedTest
//    @ValueSource(strings = {
//            "ironman",
//            "ironman1",
//            "ironman2",
//            "ironman3",
//            "ironman4",
//            "captain",
//            "captain america"
//    })
    @CsvSource({
            "ironman",
            "ironman1",
            "ironman2",
            "ironman3",
            "ironman4",
            "captain",
            "captain america"
    })
    public void testFindByUserName4(String name){
        assertNotNull(userRepository.findByUserName(name),"failed for "+name);
    }

    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testFindByUserName5(User user){
        assertNotNull(userRepository.findByUserName(user.getUserName()));
    }

/*
    @BeforeEach
    void setUp(){};

    @BeforeAll
    void setUp(){};

    @AfterAll(){};
    void setUp(){};

    @AfterEach(){};
    void setUp(){};

  */





}













