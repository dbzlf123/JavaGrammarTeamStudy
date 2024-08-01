package camp.model;

import java.util.List;

public class Student {
    private String studentId;
    private String studentName;
    private List<Subject> subjectStore;

    public Student(String studentId, String studentName, List<Subject> subjectStore) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.subjectStore = subjectStore;
    }

    // Getter
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public List<Subject> getSubjectStore() {
        return subjectStore;
    }
}
