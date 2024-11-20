import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student extends User {
    private int currentSemester;
    private int totalCredits;
    private List<Course> registered_Courses;
    private List<Course> completed_Courses; // To track completed courses
    List<Complaint> complaints;

    public Student(String email, String password, int currentSemester) {
        super(email, password);
        this.currentSemester = currentSemester;
        this.totalCredits = 0;
        this.registered_Courses = new ArrayList<>();
        this.completed_Courses = new ArrayList<>();
        this.complaints = new ArrayList<>();
    }

    // Display student menu
    @Override
    public void displayMenu() {
        System.out.println("Student Menu:");
        System.out.println("1. View Available Courses");
        System.out.println("2. Register for a Course");
        System.out.println("3. View Registered Courses");
        System.out.println("4. View Schedule");
        System.out.println("5. Results");
        System.out.println("6. Course Drop");
        System.out.println("7. Submit Complaints");
        System.out.println("8. View Complaints");
        System.out.println("9. Submit Feedback");
        System.out.println("0. Logout");
    }

    @Override
    public void performAction(int choice) {
        // Implementation for general action if needed
    }

    @Override
    public void performAction(int choice, List<Course> courseCatalog, List<Student> students) {
        // Overloaded method implementation if needed
    }

    // Perform actions based on student input
    @Override
    public void performAction(int choice, List<Course> availableCourses) {
        Scanner scanner = new Scanner(System.in);
        switch (choice) {
            case 1 -> viewAvailableCourses(availableCourses);
            case 2 -> registerForCourse(availableCourses);
            case 3 -> viewRegisteredCourses();
            case 4 -> viewSchedule();
            case 5 -> Result();
            case 6 -> dropCourse(scanner);
            case 7 -> submitComplaint(scanner);
            case 8 -> viewComplaints();
            case 9 -> submitFeedback(scanner, availableCourses);
            case 0 -> System.out.println("Logging out...");
            default -> System.out.println("Invalid choice.");
        }
    }

    public void Result() {
        double sgpa = calculateSGPA();
        double cgpa = calculate_CGPA();
        System.out.println("SGPA for current semester: " + sgpa);
        System.out.println("CGPA for all semesters: " + cgpa);
    }

    // Calculating SGPA
    private double calculateSGPA() {
        int totalCreditsForSemester = 0;
        int totalPointsForSemester = 0;
        for (Course course : registered_Courses) {
            if (course.getGrade() != null) { // Only include courses with assigned grades
                int sgpa = course.getGradePoints();
                if (sgpa != -1) { // Grade is valid
                    totalCreditsForSemester += course.getCredits();
                    totalPointsForSemester += course.getCredits() * sgpa;
                }
            }
        }
        return (totalCreditsForSemester > 0) ? (double) totalPointsForSemester / totalCreditsForSemester : 0;
    }

    // Calculating CGPA
    private double calculate_CGPA() {
        int totalCreditsAllSemesters = 0;
        int totalPointsAllSemesters = 0;
        for (Course course : completed_Courses) {
            int cgpa = course.getGradePoints();
            if (cgpa != -1) { // Grade is valid
                totalCreditsAllSemesters += course.getCredits();
                totalPointsAllSemesters += course.getCredits() * cgpa;
            }
        }
        return (totalCreditsAllSemesters > 0) ? (double) totalPointsAllSemesters / totalCreditsAllSemesters : 0;
    }

    public void completeSemester() {
        Scanner scanner = new Scanner(System.in);
        for (Course course : registered_Courses) {
            System.out.println("Enter grade for the course " + course.getTitle() + ": ");
            String grade = scanner.nextLine().toUpperCase();
            course.setGrade(grade);
        }
        // Move registered courses to completed courses
        completed_Courses.addAll(registered_Courses);
        registered_Courses.clear();
        System.out.println("Semester completed. Grades assigned.");
    }

    // View available courses
    public void viewAvailableCourses(List<Course> availableCourses) {
        if (availableCourses.isEmpty()) {
            System.out.println("No available courses.");
        } else {
            System.out.println("Available Courses:");
            for (Course course : availableCourses) {
                course.printCourseDetails();
            }
        }
    }

    // Select and register for a course
    public void registerForCourse(List<Course> availableCourses) {
        if (availableCourses.isEmpty()) {
            System.out.println("No available courses to register for.");
        } else {
            System.out.println("Available Courses:");
            for (int i = 0; i < availableCourses.size(); i++) {
                Course course = availableCourses.get(i);
                System.out.println((i + 1) + ". " + course.getTitle() + " (" + course.getCourseCode() + ")");
            }
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the number of the course you want to register for:");
            int courseChoice = -1;

            // Validate input for index
            if (scanner.hasNextInt()) {
                courseChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume invalid input
                return; // Exit the method
            }

            // Check if the input is valid
            if (courseChoice >= 1 && courseChoice <= availableCourses.size()) {
                Course selectedCourse = availableCourses.get(courseChoice - 1);
                registerForCourses(selectedCourse);
            } else {
                System.out.println("Invalid course selection.");
            }
        }
    }

    // Register for a course
    public void registerForCourses(Course course) {
        if (!registered_Courses.contains(course)) {
            if (totalCredits + course.getCredits() <= 20) { // Ensure the credit limit is not exceeded
                registered_Courses.add(course);
                totalCredits += course.getCredits();
                course.enrollStudent(this);
                System.out.println("Successfully registered for " + course.getTitle());
            } else {
                System.out.println("Cannot register, total credits exceed the 20 credit limit.");
            }
        } else {
            System.out.println("You are already registered for this course.");
        }
    }

    // View registered courses
    public void viewRegisteredCourses() {
        if (registered_Courses.isEmpty()) {
            System.out.println("You are not registered for any courses.");
        } else {
            System.out.println("Registered Courses:");
            for (Course course : registered_Courses) {
                System.out.println(course.getTitle() + " (" + course.getCourseCode() + ")");
            }
        }
    }

    // View schedule
    public void viewSchedule() {
        if (registered_Courses.isEmpty()) {
            System.out.println("You have no registered courses to display a schedule.");
        } else {
            System.out.println("Your Schedule:");
            for (Course course : registered_Courses) {
                System.out.println(course.getTitle() + " - " + course.getTimings());
            }
        }
    }

    // Drop a course
    public void dropCourse(Scanner scanner) {
        if (registered_Courses.isEmpty()) {
            System.out.println("You are not registered for any courses.");
        } else {
            // Display registered courses
            System.out.println("Registered Courses:");
            for (int i = 0; i < registered_Courses.size(); i++) {
                Course course = registered_Courses.get(i);
                System.out.println((i + 1) + ". " + course.getTitle());
            }

            // Choose a course to drop
            System.out.println("Enter the number of the course you want to drop:");
            int dropChoice = scanner.nextInt();
            if (dropChoice >= 1 && dropChoice <= registered_Courses.size()) {
                Course selectedCourse = registered_Courses.get(dropChoice - 1);
                registered_Courses.remove(selectedCourse);
                totalCredits -= selectedCourse.getCredits();
                selectedCourse.dropStudent(this);
                System.out.println("Successfully dropped " + selectedCourse.getTitle());
            } else {
                System.out.println("Invalid selection.");
            }
        }
    }

    // Submit a complaint
    public void submitComplaint(Scanner scanner) {
        System.out.println("Enter your complaint:");
        String description = scanner.nextLine();
        Complaint complaint = new Complaint(description);
        complaints.add(complaint);
        System.out.println("Complaint submitted.");
    }

    // View complaints
    public void viewComplaints() {
        if (complaints.isEmpty()) {
            System.out.println("You have no complaints.");
        } else {
            System.out.println("Your Complaints:");
            for (Complaint complaint : complaints) {
                complaint.printComplaint();
            }
        }
    }

    // Submit feedback
    public void submitFeedback(Scanner scanner, List<Course> courseCatalog) {
        if (registered_Courses.isEmpty()) {
            System.out.println("Haven't completed any course to provide feedback.");
            return;
        }

        System.out.println("Select a course to provide feedback:");
        for (int i = 0; i < registered_Courses.size(); i++) {
            Course course = registered_Courses.get(i);
            System.out.println((i + 1) + ". " + course.getTitle() + " (" + course.getCourseCode() + ")");
        }

        System.out.println("Enter the number of the course:");
        int courseChoice = scanner.nextInt();
        scanner.nextLine();

        if (courseChoice >= 1 && courseChoice <= registered_Courses.size()) {
            Course selectedCourse = registered_Courses.get(courseChoice - 1);
            giveFeedback(scanner, selectedCourse);
        } else {
            System.out.println("Invalid selection. Please try again.");
        }
    }

    // Method to provide feedback
    public void giveFeedback(Scanner scanner, Course course) {
        System.out.println("Provide feedback for the course: " + course.getTitle());
        System.out.println("Press 1 for rating the course (1-5)");
        System.out.println("Press 2 for textual feedback");
        System.out.println("Press 3 for both");
        int feedbackType = scanner.nextInt();
        scanner.nextLine();

        switch (feedbackType) {
            case 1 -> {
                System.out.println("Enter Rating (1-5): ");
                int rating = scanner.nextInt();
                scanner.nextLine();
                if (rating < 1 || rating > 5) {
                    System.out.println("Invalid... Please try again");
                } else {
                    Feedback<Integer> numericFeedback = new Feedback<>(this.getEmail(), rating);
                    course.addFeedback(numericFeedback);
                    System.out.println("Rated Successfully.");
                }
            }
            case 2 -> {
                System.out.println("Feedback: ");
                String textFeedback = scanner.nextLine();
                Feedback<String> textualFeedback = new Feedback<>(this.getEmail(), textFeedback);
                course.addFeedback(textualFeedback);
                System.out.println("Feedback Given Successfully.");
            }
            case 3 -> {
                System.out.println("Enter Rating (1-5): ");
                int rating = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (rating < 1 || rating > 5) {
                    System.out.println("Invalid... Please try again");
                    break;
                }
                System.out.println("Feedback: ");
                String textFeedback = scanner.nextLine();
                Feedback<Integer> numericFeedback = new Feedback<>(this.getEmail(), rating);
                Feedback<String> textualFeedback = new Feedback<>(this.getEmail(), textFeedback);
                course.addFeedback(numericFeedback);
                course.addFeedback(textualFeedback);
                System.out.println("Rated and Feedback Given Successfully.");
            }
            default -> System.out.println("Invalid...");
        }
    }
}
