package sba.sms.services;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentServiceTest {


    static StudentService studentService = new StudentService();


    private StudentServiceTest() {
    }


    @Test
    public void studEval() {
        //Negative testing
        assertEquals(true, studentService.validateStudent("example@gmail.com", "wrong-password"));

    }


}