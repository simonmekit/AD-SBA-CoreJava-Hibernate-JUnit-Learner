package sba.sms;

import lombok.extern.java.Log;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import sba.sms.models.Course;
import sba.sms.models.Employee;
import sba.sms.models.Student;
import sba.sms.services.CourseService;
import sba.sms.services.EmployeeService;
import sba.sms.services.StudentService;
import sba.sms.utils.CommandLine;
import sba.sms.utils.HibernateUtil;

import java.util.List;
import java.util.Scanner;


/**
 * SBA Core Java Hibernate/Junit
 * Business Requirement:
 * The task is to create a basic School Management System
 * where students can register for courses, and view the course assigned to them.
 * <br />
 * App uses <br />
 * Initialize dummy data: {@link CommandLine#addData()} <br />
 * Two models: {@link Student} & {@link Course} <br />
 * Two services: {@link StudentService} & {@link CourseService}
 *
 *
 * <b style="color:red">WARNING! </b>
 * <b>DO NOT MODIFY THIS CODE</b>
 *
 * @author Jafer Alhaboubi & LaTonya Lewis
 * @since sba-core-java-hibernate-junit 1.0
 */

@Log
public class App {
    static final StudentService studentService = new StudentService();
    static final CourseService courseService = new CourseService();
    static final EmployeeService employeeService = new EmployeeService();

    public static void main(String[] args) {

//        Thread createDatabase = new Thread(CommandLine::addData);
//        createDatabase.start();
//        try {
//            createDatabase.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        SessionFactory factory = HibernateUtil.getSessionFactory();
//
//        try {
//            Session session = factory.openSession();
//            //Begin transaction
//            session.beginTransaction();
//            //create database and insert initial data
        CommandLine.addData();
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        mainTest();

    }

    private static void mainTest() {
        // SessionFactory factory = HibernateUtil.getSessionFactory();
        Scanner input = new Scanner(System.in);
        int userInput;
        String strInput;
        do {
            System.out.printf("Select # from menu:%n1.Employee%n2.Student%n0.Quit%n");
            userInput = input.nextInt();
            if (userInput == 0) {
                System.exit(0);
            } else if (userInput == 1) {

                System.out.print("Enter Employee email: ");
                String email = input.next();
                System.out.printf("Enter %s's password: ", email.substring(0, email.indexOf("@")));
                String password = input.next();
                if (employeeService.validateEmployee(email, password)) {
                    System.out.printf("%nWelcome %s! Choose options from menu:%n",
                            employeeService.getEmployeeByEmail(email).getEmpName());
                    do {
                        System.out.printf("1. Add Employee %n2. Add Student %n3. Add Course %n4. Logout%n");
                        userInput = input.nextInt();

                        if (userInput == 4) {
                            System.exit(0);
                        } else if (userInput == 1) {
                            System.out.println("Enter employee name:");
                            String empName = input.next();
                            System.out.println("Enter employee email:");
                            String emplEmail = input.next();
                            System.out.println("Enter password");
                            String emplPassword = input.next();
                            Employee employee = new Employee();
                            employee.setEmpName(empName);
                            employee.setEmail(emplEmail);
                            employee.setPassword(emplPassword);
                            employeeService.createEmployee(employee);

                        } else if (userInput == 2) {
                            System.out.println("Enter student name:");
                            String name = input.next();
                            System.out.println("Enter student email:");
                            String stEmail = input.next();
                            System.out.println("Enter student password:");
                            String passwd = input.next();
                            studentService.createStudent(new Student(stEmail, name, passwd));

                        } else if (userInput == 3) {
                            System.out.println("Enter course name:");
                            String course = input.next();
                            System.out.println("Enter instructor name:");
                            String instructor = input.next();
                            courseService.createCourse(new Course(course, instructor));

                        } else {
                            System.out.println("Invalid option!");
                        }
                        // System.out.printf("session ended!%n");
                    } while (userInput != 4);

                } else {
                    System.out.printf("Incorrect username or password%n");
                }
            } else if (userInput == 2) {
                System.out.print("Enter student email: ");
                String email = input.next();
                System.out.printf("Enter %s's password: ", email.substring(0, email.indexOf("@")));
                String password = input.next();
                if (studentService.validateStudent(email, password)) {
                    System.out.printf("%nWelcome %s! Choose options from menu:%n", studentService.getStudentByEmail(email).getName());

                    do {
                        System.out.printf("1. Register to class%n2. View Course%n3. Logout%n");
                        userInput = input.nextInt();

                        if (userInput == 3) {
                            System.exit(0);
                        } else if (userInput == 1) {
                            List<Course> courseList = courseService.getAllCourses();
                            System.out.printf("All courses:%n-----------------------------%n");
                            System.out.printf("%-2s | %-20s | %s%n", "ID", "Course", "Instructor");
                            if (courseList.isEmpty()) System.out.printf("No courses to view%n");
                            for (Course course : courseList) {
                                System.out.printf("%-2d | %-20s | %s%n", course.getId(), course.getName(), course.getInstructor());
                            }
                            System.out.print("select course #: ");
                            int courseId = input.nextInt();
                            if (courseId > 0 && courseId <= courseList.size()) {
                                studentService.registerStudentToCourse(email, (courseId));
                                System.out.printf("successfully registered %s to %s%n", studentService.getStudentByEmail(email).getName(),
                                        courseService.getCourseById(courseId).getName());
                                printStudentCourses(email);
                            } else {
                                System.out.printf("course id not found!%n");
                            }
                        } else if (userInput == 2) {
                            printStudentCourses(email);
                        } else {
                            System.out.println("Invalid option!");
                        }
                        // System.out.printf("session ended!%n");
                    } while (userInput != 3);

                } else {
                    System.out.printf("Incorrect username or password%n");
                }
            } else {
                System.out.println("Invalid option!");
            }
        } while (userInput != 0);
        input.close();
    }

    private static void printStudentCourses(String email) {
        System.out.printf("%s's courses:%n-----------------------------%n", studentService.getStudentByEmail(email).getName());
        System.out.printf("%-2s | %-20s | %s%n", "ID", "Course", "Instructor");
        List<Course> userCourses = studentService.getStudentCourses(email);
        if (userCourses.isEmpty()) System.out.printf("No courses to view%n");
        for (Course course : userCourses) {
            System.out.printf("%-2d | %-20s | %s%n", course.getId(), course.getName(), course.getInstructor());
        }
    }

//    private static void registerStudent(String email, String userName, String password) {
//        StudentService studentService = new StudentService();
//        studentService.createStudent(new Student(email, userName, password));
//    }
//
//    private static void addCourse(String course, String instructor) {
//        CourseService courseService = new CourseService();
//        courseService.createCourse(new Course(course, instructor));
//    }
}