package camp.model;

public class ScoreDatail {
  private int round;
  private int score;
  private char grade;

  public ScoreDatail(int round, int score, String subjectType) {
    this.round = round;
    this.score = score;
    this.grade = changeGrade(subjectType, score);
  }

  public void setRound(int round) { this.round = round; }

  public void setScore(String subjectType, int score) {
    this.score = score;
    this.grade = changeGrade(subjectType, score);
  }

  public int getRound() { return round; }

  public int getScore() { return score; }

  public char getGrade() { return grade; }

  public static char changeGrade(String type, double Score) {
    if(type.equals("MANDATORY")) {
      if(Score>=95) return 'A';
      else if(Score>=90) return 'B';
      else if(Score>=80) return 'C';
      else if(Score>=70) return 'D';
      else if(Score>=60) return 'F';
      else return 'N';
    }
    else {
      if(Score>=90) return 'A';
      else if(Score>=80) return 'B';
      else if(Score>=70) return 'C';
      else if(Score>=60) return 'D';
      else if(Score>=50) return 'F';
      else return 'N';
    }
  }
}
