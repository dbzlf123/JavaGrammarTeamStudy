package camp.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Score {
    private String scoreId;
    private String studentId;
    private String subjectId;
    private List<ScoreDetail> scoreList;


    public Score(String seq, String studentId, String subjectId) {
        this.scoreId = seq;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.scoreList = new ArrayList<>();
    }
    public Score(String seq, String studentId, String subjectId, List<ScoreDetail> scoreList) {
        this.scoreId = seq;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.scoreList = scoreList;
    }

    // Getter
    public String getScoreId() {
        return scoreId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void addScore(ScoreDetail score) {
        this.scoreList.add(score);
        //새로운 회차가 들어오면 회차순으로 정렬
        this.scoreList.sort(Comparator.comparingInt(scoreDetail -> scoreDetail.getRound()));
    }

    public List<ScoreDetail> getScoreList() {
        return scoreList;
    }
}