package camp.model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentId;
    private String studentName;
    private Status status;

    private List<Subject> subjectStore;
    private List<Score> scoreStore = new ArrayList<>();

    public Student(String studentId, String studentName, Status status, List<Subject> subjectStore) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.status = status;
        this.subjectStore = subjectStore;
    }

    // Getter
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }
    public List<Subject> getSubjectStore() {
        return subjectStore;
    }

}
