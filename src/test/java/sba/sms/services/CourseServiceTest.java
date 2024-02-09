package sba.sms.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseServiceTest {
    static CourseService courseService = new CourseService();

    @Test
    public void readCourse() {
        assertEquals(null, courseService.getCourseById(100000));

    }

}
