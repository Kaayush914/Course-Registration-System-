import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseRegistrationSystem {

    static List<Course> courseCatalog = new ArrayList<>();
    static List<Student> students = new ArrayList<>();
    static List<Professor> professors = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to ERP Portal");

        Professor BN = new Professor("bnjain@iiitd.ac.in", "BN");
        professors.add(BN);
        Professor Pravesh = new Professor("pravesh@iiitd.ac.in", "pravesh");
        professors.add(Pravesh);
        Professor Subhajit = new Professor("subhajit@iiitd.ac.in", "subhajit");
        professors.add(Subhajit);
        Professor Rajiv = new Professor("rajiv@iiitd.ac.in", "rajiv");
        professors.add(Rajiv);
        Professor Payal = new Professor("payal@iiitd.ac.in", "payal");
        professors.add(Payal);
        Professor Bapi = new Professor("bapi@iiitd.ac.in", "bapi");
        professors.add(Bapi);
        Professor Arun = new Professor("arun@iiitd.ac.in", "arun");
        professors.add(Arun);
        Professor Vivek = new Professor("vivek@iiitd.ac.in", "vivek");
        professors.add(Vivek);
        Professor Satish = new Professor("satish@iiitd.ac.in", "satish");
        professors.add(Satish);
        Professor Deepak = new Professor("deepak@iiitd.ac.in", "deepak");
        professors.add(Deepak);

//      Semester 1
        Course course1 = new Course("CSE101", "Introduction to Programming", BN, 4, "MWF 9:30-11:00AM");
        courseCatalog.add(course1);

        Course course2 = new Course("ECE111", "Digital Electronics", Pravesh, 4, "TTF 11:00-12:30PM");
        courseCatalog.add(course2);

        Course course3 = new Course("MTH100", "Linear Algebra", Subhajit, 4, "MWF 11:00-12:30PM");
        courseCatalog.add(course3);

        Course course4 = new Course("DES102", "Introduction to HCI", Rajiv, 4, "TTF 9:30-11:00AM");
        courseCatalog.add(course4);

        Course course5 = new Course("COM101", "Communication SKills", Rajiv, 4, "F 3:00-6:00PM");
        courseCatalog.add(course5);

//        Semester 3
        Course course6 = new Course("CSE121", "Discrete Maths", Bapi, 4, "MWF 9:30-11:00AM");
        courseCatalog.add(course6);

        Course course7 = new Course("CSE201", "Advanced Programming", Arun, 4, "MW 3:00-4:30PM");
        courseCatalog.add(course7);

        Course course8 = new Course("CSE231", "Operating System",Vivek, 4, "TT 3:00-4:30PM");
        courseCatalog.add(course8);

        Course course9 = new Course("MTH203", "Maths-III", Satish, 4, "TT 4:30-6:00PM");
        courseCatalog.add(course9);

        Course course10 = new Course("SOC216", "Political Antropology", Deepak, 4, "MW 4:30-6:00PM");
        courseCatalog.add(course10);


        boolean running = true;
        while (running) {
            System.out.println("Login as: ");
            System.out.println("1. Student");
            System.out.println("2. Professor");
            System.out.println("3. Admin");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> Student_Login(scanner);
                case 2 -> Prof_Login(scanner);
                case 3 -> Admin_Login(scanner);
                case 0 -> running = false;
                default -> System.out.println("Invalid Input,Try again.");
            }
        }

        System.out.println("Have a Nice Day...");
        scanner.close();
    }

    private static void Student_Login(Scanner scanner) {
        System.out.println("Enter Email: ");
        String email = scanner.nextLine();
        System.out.println("Enter Password: ");
        String password = scanner.nextLine();

        Student student = new Student(email, password, 1);
        students.add(student);

        while (true) {
            student.displayMenu();
            int choice = scanner.nextInt();
            student.performAction(choice, courseCatalog);

            if (choice == 0) {
                break;
            }
        }
    }

    private static void Prof_Login(Scanner scanner) {
        System.out.println("Enter Email: ");
        String email = scanner.nextLine();
        System.out.println("Enter Password: ");
        String password = scanner.nextLine();

        Professor professor = professors.get(0);

        while (true) {
            professor.displayMenu();
            int choice = scanner.nextInt();
            professor.performAction(choice);

            if (choice == 0) {
                break; // Exit the loop if the user chooses 0
            }
        }

    }

    private static void Admin_Login(Scanner scanner) {
        System.out.println("Enter Email: ");
        String email = scanner.nextLine();
        System.out.println("Enter Password: ");
        String password = scanner.nextLine();

        if (password.equals("IIITDELHI")) {
            Admin admin = new Admin(email, password);

            while (true) {
                admin.displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                admin.performAction(choice, courseCatalog, students);

                if (choice == 0) {
                    break;
                }
            }
        } else {
            System.out.println("Invalid Password!");
        }
    }
}
