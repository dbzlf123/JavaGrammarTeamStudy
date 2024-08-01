package camp.model;

public class Student {
    private static String studentId;
    private static String studentName;

    public Student(String seq, String studentName) {
        this.studentId = seq;
        this.studentName = studentName;
    }

    // Getter
    public static String getStudentId() {
        return studentId;
    }

    public static String getStudentName() {
        return studentName;
    }

}
