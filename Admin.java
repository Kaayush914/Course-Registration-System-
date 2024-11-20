import java.util.List;
import java.util.Scanner;

class Admin extends User {

    public Admin(String email, String password) {
        super(email, password);
    }

    public void manageCourseCatalog(List<Course> courseCatalog) {
        System.out.println("Course Catalog:");
        for (Course course : courseCatalog) {
            course.printCourseDetails();
        }
    }

    public void addCourse(List<Course> courseCatalog) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Course Code:");
        String courseCode = scanner.nextLine();
        System.out.println("Enter Course Title:");
        String title = scanner.nextLine();
        System.out.println("Enter Professor Email:");
        String professorEmail = scanner.nextLine();
        Professor professor = findProfessorByEmail(professorEmail); // Assume this method exists to find a professor
        System.out.println("Enter Course Credits:");
        int credits = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter Course Timings:");
        String timings = scanner.nextLine();

        Course newCourse = new Course(courseCode, title, professor, credits, timings);
        courseCatalog.add(newCourse);
        System.out.println("Course added: " + newCourse.getTitle());
    }

    public void deleteCourse(List<Course> courseCatalog) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Course Code to delete:");
        String courseCode = scanner.nextLine();

        Course courseToRemove = null;
        for (Course course : courseCatalog) {
            if (course.getCourseCode().equals(courseCode)) {
                courseToRemove = course;
                break;
            }
        }

        if (courseToRemove != null) {
            courseCatalog.remove(courseToRemove);
            System.out.println("Course deleted: " + courseToRemove.getTitle());
        } else {
            System.out.println("Course with code " + courseCode + " not found.");
        }
    }

    public void assignProfessorToCourse(List<Course> courseCatalog) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Course Code to assign professor:");
        String courseCode = scanner.nextLine();
        System.out.println("Enter Professor Email:");
        String professorEmail = scanner.nextLine();
        Professor professor = findProfessorByEmail(professorEmail); // Assume this method exists to find a professor

        if (professor != null) {
            for (Course course : courseCatalog) {
                if (course.getCourseCode().equals(courseCode)) {
                    course.setProfessor(professor);
                    System.out.println("Professor " + professor.getEmail() + " assigned to course " + course.getTitle());
                    return;
                }
            }
            System.out.println("Course with code " + courseCode + " not found.");
        } else {
            System.out.println("Professor with email " + professorEmail + " not found.");
        }
    }

    public void handleComplaints(List<Student> students) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Student Email to handle complaints:");
        String studentEmail = scanner.nextLine();

        Student student = findStudentByEmail(studentEmail); // Assume this method exists to find a student

        if (student != null) {
            student.viewComplaints();
            if (!student.complaints.isEmpty()) {
                Complaint complaint = student.complaints.get(0);
                complaint.resolve();
                System.out.println("Resolved complaint: " + complaint.getDscptn());
            } else {
                System.out.println("No complaints for student " + studentEmail);
            }
        } else {
            System.out.println("Student with email " + studentEmail + " not found.");
        }
    }

    @Override
    public void displayMenu() {
        System.out.println("Admin Menu: ");
        System.out.println("1. Manage Course Catalog");
        System.out.println("2. Add Course");
        System.out.println("3. Delete Course");
        System.out.println("4. Assign Professor to Course");
        System.out.println("5. Handle Complaints");
        System.out.println("0. Logout");
    }

    @Override
    public void performAction(int choice) {
        // This method is not used in this context
    }

    @Override
    public void performAction(int choice, List<Course> courseCatalog, List<Student> students) {
        Scanner scanner = new Scanner(System.in);
        switch (choice) {
            case 1 -> manageCourseCatalog(courseCatalog);
            case 2 -> addCourse(courseCatalog);
            case 3 -> deleteCourse(courseCatalog);
            case 4 -> assignProfessorToCourse(courseCatalog);
            case 5 -> handleComplaints(students);
            case 0 -> System.out.println("Logging out...");
            default -> System.out.println("Invalid choice.");
        }
    }

    @Override
    public void performAction(int choice, List<Course> availableCourses) {
        // This method is not used in this context
    }

    // Helper methods to find professor or student by email
    private Professor findProfessorByEmail(String email) {
        for (Professor professor : CourseRegistrationSystem.professors) {
            if (professor.getEmail().equals(email)) {
                return professor;
            }
        }
        return null;
    }

    private Student findStudentByEmail(String email) {
        for (Student student : CourseRegistrationSystem.students) {
            if (student.getEmail().equals(email)) {
                return student;
            }
        }
        return null;
    }
}
