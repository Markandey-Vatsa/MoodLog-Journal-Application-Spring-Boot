package net.personalProject.journalApp.Services;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserService userService;

    @Test
    public void testFindByUserName(){
    assertNotNull(userService.findByUserName("ram"));
    }



    @ParameterizedTest
    @CsvSource({
            "2, 2, 4",
            "4, 4, 8",
            "3, 3, 6"
    })
    public void testParameterized(int a, int b, int expected){
        assertEquals(expected,a+b);
    }





    public int prod(int a, int b){
        return a*b;
    }

    @ParameterizedTest
    @CsvSource({
            "2,3,6",
            "4,5,20",
            "6,7,42",
            "8,9,72"
    })
    public void testPramProd(int a, int b, int expected){
        assertEquals(expected,prod(a,b));
    }
}
