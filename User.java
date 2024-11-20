import java.util.List;

abstract class User {
    protected String Email;
    protected String Password;

    public User(String email, String password) {
        this.Email = email;
        this.Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    // Abstract methods that will be implemented by subclasses (Student, Professor, Admin)
    public abstract void displayMenu();
    public abstract void performAction(int choice);

    public abstract void performAction(int choice, List<Course> courseCatalog, List<Student> students);

    public abstract void performAction(int choice, List<Course> availableCourses);
}
