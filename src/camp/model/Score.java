package camp.model;

import java.util.ArrayList;

public class Score {
    private String scoreId;
    private String studentId;
    private String subjectId;
    private ArrayList<Integer> round;
    private ArrayList<Integer> score;
    private ArrayList<Character> grade;


    public Score(String seq, String studentId, String subjectId)
    {
        this.scoreId = seq;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.round = new ArrayList<>();
        this.score = new ArrayList<>();
        this.grade = new ArrayList<>();
    }
    public Score(String seq, String studentId, String subjectId, ArrayList<Integer> round,ArrayList<Integer> score,ArrayList<Character> grade){
        this.scoreId = seq;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.round = round;
        this.score = score;
        this.grade = grade;
    }

    // Getter
    public String getScoreId() {return scoreId;}

    public String getStudentId() {return studentId;}

    public String getSubjectId() {return subjectId;}

    public ArrayList<Integer> getRound() {return round;}

    public ArrayList<Integer> getScore() {return score;}

    public ArrayList<Character> getGrade() {return grade;}
}

