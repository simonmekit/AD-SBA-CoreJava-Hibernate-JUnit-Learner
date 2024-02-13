package sba.sms.services;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StudentServiceTest {


    static StudentService studentService = new StudentService();


    private StudentServiceTest() {
    }


    @Test
    public void studEval() {
        //Negative testing
        assertFalse(studentService.validateStudent("example@gmail.com", "wrong-password"));

    }

}