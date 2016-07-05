package lk.ac.pdn.co328.studentSystem;
import org.junit.*;
import java.util.ArrayList;

public class StudentRegisterTest {
    StudentRegister register;

    @Before
    public void setupTest() {
        System.out.println("A new test is starting.");
        register = new StudentRegister();
    }

    @After
    public void finishTest() {
        System.out.println("Test finished");
    }

    @BeforeClass
    public  static void beforeClass() {
        System.out.println("Evaluating test cases in StudentRegisterTest.java");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("All tests are done");
    }

   @Test
    public void testAddStudent() {
       try {
           register.addStudent(new Student(2, "nimal", "kumara"));
           register.addStudent(new Student(5, "fawzan", "mohomad"));
       }
       catch (Exception ex) {
           Assert.fail("Adding student failed");
       }
       System.out.println("Testing add student method");

       Student student = register.findStudent(2);
       Assert.assertEquals("Student Id is wrong",2,student.getId());
    }

   @Test(expected=Exception.class)
    public void testAddStudentTwice() throws Exception {
       // Implement your test code here. Adding a student with same registration number twice should generate an exception.

       register.addStudent(new Student(1, "namal", "sameera"));
       register.addStudent(new Student(1, "sanath", "ranjan"));

       System.out.println("Testing add twice student");
    }

    @Test
    public void testRemoveStudent() {
        try {
            register.addStudent(new Student(2, "nimal", "kumara"));
            register.addStudent(new Student(1, "ruwan", "tharaka"));
            register.addStudent(new Student(5, "gayan", "chamara"));
        }
        catch (Exception ex) {
            Assert.fail("Add student failed");
        }
        register.removeStudent(1);
        Student student = register.findStudent(1);
        Assert.assertNull("student was not removed",student);
    }

    @Test
    public void testGetRegNumbers() {
        try {
            register.addStudent(new Student(1, "ruwan", "tharaka"));
            register.addStudent(new Student(2, "nimal", "kumara"));
            register.addStudent(new Student(5, "gayan", "chamara"));
        }
        catch (Exception ex) {
            Assert.fail("Adding student failed");
        }
        ArrayList<Integer> numbers = register.getAllRegistrationNumbers();
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(1);
        expected.add(2);
        expected.add(5);
        Assert.assertTrue(numbers.equals(expected));
    }

    @Test
    public void testFindStudentsByName() {
        try {
            register.addStudent(new Student(1, "ruwan", "tharaka"));
            register.addStudent(new Student(2, "nimal", "ruwan"));
            register.addStudent(new Student(5, "gayan", "chamara"));
        }
        catch (Exception ex) {
            Assert.fail("Adding student failed");
        }

        ArrayList<Student> expected = new ArrayList<Student>();
        expected.add(new Student(1, "ruwan", "tharaka"));
        expected.add(new Student(2, "nimal", "ruwan"));

        System.out.println("Testing Find Students By Name");
        ArrayList<Student> actual = register.findStudentsByName("ruwan");

        int index = 0; boolean flag = true;
        for (Student s : actual) {
            if (!s.getFirstName().equals(expected.get(index).getFirstName())) {
                flag = false;
                break;
            } else if (!s.getLastName().equals(expected.get(index++).getLastName())) {
                flag = false;
                break;
            }
        }

        Assert.assertTrue(flag);
    }
}
