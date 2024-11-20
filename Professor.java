import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Professor extends User {
    private List<Course> courses;

    public Professor(String email, String password) {
        super(email, password);
        this.courses = new ArrayList<>();
    }

    public void manageCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses assigned yet.");
        } else {
            System.out.println("Manage Courses:");
            for (Course course : courses) {
                course.printCourseDetails();
            }
        }
    }

    public void viewEnrolledStudents(Course course) {
        System.out.println("Students enrolled in " + course.getTitle() + ":");
        for (Student student : course.getEnrolledStudents()) {
            System.out.println(student.getEmail());
        }
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    @Override
    public void displayMenu() {
        System.out.println("Professor Menu: ");
        System.out.println("1. Manage Courses");
        System.out.println("2. View Enrolled Students");
        System.out.println("3. View Course Feedback");
        System.out.println("0. Logout");
    }

    public void Course_Feedback(Course course) {
        System.out.println("Feedback for course: " + course.getTitle());
        List<Feedback<?>> feedbackList = course.getFeedbackList();
        if (feedbackList.isEmpty()) {
            System.out.println("No Feedback.");
        } else {
            for (Feedback<?> feedback : feedbackList) {
                System.out.println(feedback);
                System.out.println("--------------------------");
            }
        }
    }


    @Override
    public void performAction(int choice) {
        Scanner scanner = new Scanner(System.in);
        switch (choice) {
            case 1 -> manageCourses();
            case 2 -> {
                if (courses.isEmpty()) {
                    System.out.println("No courses assigned.");
                } else {
                    System.out.println("Select course to view Enrolled Students:");
                    for (int i = 0; i < courses.size(); i++) {
                        Course course = courses.get(i);
                        System.out.println((i + 1) + ". " + course.getTitle() + " (" + course.getCourseCode() + ")");
                    }
                    int courseChoice = scanner.nextInt();
                    scanner.nextLine();
                    if (courseChoice >= 1 && courseChoice <= courses.size()) {
                        Course selectedCourse = courses.get(courseChoice - 1);
                        viewEnrolledStudents(selectedCourse);
                    } else {
                        System.out.println("Invalid course selection.");
                    }
                }
            }
            case 3 -> {
                if (courses.isEmpty()) {
                    System.out.println("No courses assigned.");
                } else {
                    System.out.println("Select course to view Feetback:");
                    for (int i = 0; i < courses.size(); i++) {
                        Course course = courses.get(i);
                        System.out.println((i + 1) + ". " + course.getTitle() + " (" + course.getCourseCode() + ")");
                    }
                    int courseChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if (courseChoice >= 1 && courseChoice <= courses.size()) {
                        Course selectedCourse = courses.get(courseChoice - 1);
                        Course_Feedback(selectedCourse);
                    } else {
                        System.out.println("Invalid Course, try again");
                    }
                }
            }
            case 0 -> System.out.println("Logging out...");
            default -> System.out.println("Invalid...");
        }
    }

    @Override
    public void performAction(int choice, List<Course> courseCatalog, List<Student> students) {

    }

    @Override
    public void performAction(int choice, List<Course> availableCourses) {

    }
}
