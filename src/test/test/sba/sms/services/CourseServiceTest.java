package sba.sms.services;

import org.junit.jupiter.api.Test;
import sba.sms.utils.CommandLine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CourseServiceTest {
    static CourseService courseService = new CourseService();

    // CommandLine.addData();
    @Test
    public void readCourse() {
        assertNull(courseService.getCourseById(10000));

    }

}
