package sba.sms.kbahib;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyTest {
//
//    private final MyClass myClass = new MyClass();
//
//    @BeforeAll
//    public void beforeAll() {
//        System.out.println("beforeAll/beforeClass Finished ");
//    }
//
//    @Test
//    public void additionTest() {
//        assertEquals(2, myClass.add(1, 1));
//        System.out.println("additionTest Finished ");
//    }


  //  public class CalculatorTests {

        private final MyClass calculator = new MyClass();

        @Test
        void additionTest() {
            System.out.println("Test started");
            assertEquals(57, calculator.add(1, 1));
            System.out.println("Test Finished");
        }
   // }
}