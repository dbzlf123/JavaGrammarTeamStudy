package camp.model;

import java.util.ArrayList;

public class Score {
    private String scoreId;
    private String studentId;
    private String subjectId;
    private ArrayList<Integer> round;
    private ArrayList<Integer> score;
    private ArrayList<Character> grade;


    public Score(String seq) {
        this.scoreId = seq;
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

    public static char changeGrade(String type, double avgScore) {
        if(type.equals("MANDATORY")) {
            if(avgScore>=95) return 'A';
            else if(avgScore>=90) return 'B';
            else if(avgScore>=80) return 'C';
            else if(avgScore>=70) return 'D';
            else if(avgScore>=60) return 'F';
            else return 'N';
        }
        else if(type.equals("CHOICE")){
            if(avgScore>=90) return 'A';
            else if(avgScore>=80) return 'B';
            else if(avgScore>=70) return 'C';
            else if(avgScore>=60) return 'D';
            else if(avgScore>=50) return 'F';
            else return 'N';
        }
        else return 'N';
    }

    public void addScore(int round, int score, int subtype) {
        this.round.add(round);
        this.score.add(score);
        if(subtype == 1) this.grade.add(changeGrade("MANDATORY",score));
        else if(subtype == 2) this.grade.add(changeGrade("CHOICE",score));
        else this.grade.add('N');  //이미 이전에 1,2가 아니면 들어오지 못하므로 안쓰는 코드
    }
}

