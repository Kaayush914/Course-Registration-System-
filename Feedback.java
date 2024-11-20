public class Feedback<T> {
    private String Student_Email;
    private T data_feedback;

    public Feedback(String studentEmail, T feedbackData) {
        this.Student_Email = studentEmail;
        this.data_feedback = feedbackData;
    }

    public String getStudent_Email() {
        return Student_Email;
    }

    public T getData_feedback() {
        return data_feedback;
    }

    @Override
    public String toString() {
        return "Feedback from " + Student_Email + ": " + data_feedback.toString();
    }
}
