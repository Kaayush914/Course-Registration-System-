import java.util.ArrayList;
import java.util.List;

class Course {
    private String courseCode;
    private String title;
    private Professor professor;
    private int credits;
    private String timings;
    private List<Student> enrolledStudents;
    private String Grade;
    private List<Feedback<?>> Feetback_List;

    // Constructor
    public Course(String courseCode, String title, Professor professor, int credits, String timings) {
        this.courseCode = courseCode;
        this.title = title;
        this.professor = professor;
        this.credits = credits;
        this.timings = timings;
        this.enrolledStudents = new ArrayList<>();
        this.Grade = null;
        this.Feetback_List = new ArrayList<>();
    }

    // Getters and setters for grade
    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        this.Grade = grade;
    }

    public int getGradePoints() {
        return switch (Grade) {
            case "A+" -> 10;
            case "A" -> 9;
            case "B+" -> 8;
            case "B" -> 7;
            case "C+" -> 6;
            case "C" -> 5;
            case "D" -> 4;
            case "F" -> 0; // Fail
            default -> -1; // Grade not yet assigned
        };
    }

    // Getter for course code
    public String getCourseCode() {
        return courseCode;
    }

    // Getter for course title
    public String getTitle() {
        return title;
    }

    // Getter for course professor
    public Professor getProfessor() {
        return professor;
    }

    // Getter for course credits
    public int getCredits() {
        return credits;
    }

    // Getter for course timings
    public String getTimings() {
        return timings;
    }

    // Enroll a student in the course
    public void enrollStudent(Student student) {
        enrolledStudents.add(student);
    }

    // Drop a student from the course
    public void dropStudent(Student student) {
        enrolledStudents.remove(student);
    }

    // Get the list of enrolled students
    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    // Print course details
    public void printCourseDetails() {
        System.out.println("Course Code: " + courseCode);
        System.out.println("Title: " + title);
        if (professor != null) {
            System.out.println("Professor: " + professor.getEmail());
        } else {
            System.out.println("Professor: Not assigned");
        }
        System.out.println("Credits: " + credits);
        System.out.println("Timings: " + timings);
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void addFeedback(Feedback<?> feedback) {
        Feetback_List.add(feedback);
    }

    public List<Feedback<?>> getFeedbackList() {
        return Feetback_List;
    }
}
