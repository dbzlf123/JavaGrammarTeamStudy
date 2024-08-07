package camp.CRUDOperations;

import camp.CampManagementApplication;
import camp.model.Score;
import camp.model.ScoreDetail;
import camp.model.Student;
import camp.model.Subject;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Helper {
    private static Scanner sc = new Scanner(System.in);

    //수강생의 아이디 입력
    public static String getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.next();
    }

    //과목의 아이디 입력
    public static String getSubjectId() {
        System.out.print("\n관리할 과목의 번호를 입력하시오...");
        return sc.next();
    }

    //수강생 이름으로 아이디 찾기
    //이름이 같을 경우도 아이디로 한번 더 입력
    public static String getStudentIdByName() {
        System.out.print("\n관리할 수강생의 이름을 입력하시오...");
        String studentName = sc.next();
        String subjectId = "";
        //해당 이름을 가진 수강생 데이터 리스트
        List<Student> selectStudent = CampManagementApplication.studentStore.stream()
            .filter((Student student) -> student.getStudentName().equals(studentName))
            .toList();
        //만약 동명이인 있다면
        if (selectStudent.size() > 1) {
            //목록을 보여줄테니
            for (Student eachStudent : selectStudent) {
                System.out.println("이름 : " + eachStudent.getStudentName() + "\t 아이디:" + eachStudent.getStudentId());
            }
            //이중에 누구인지 골라라
            System.out.println("조회할 수강생의 아이디 입력");
            return sc.next();
        }
        //이름이 같은 사람이 없다면 해당 수강생의 아이디 출력

        else if (selectStudent.size() == 1) return selectStudent.get(0).getStudentId();
            //해당 이름을 가진 수강생이 없다면 NoName 출력
        else return "NoName";
    }

    //과목 이름으로 아이디 찾기
    public static String getSubjectIdByName() {
        System.out.print("\n관리할 과목의 이름을 입력하시오...");
        String subjectName = sc.next();
        //입력값에 해당하는 subject 데이터 찾기
        Optional<Subject> selectSubject = CampManagementApplication.subjectStore.stream()
            .filter((Subject subject) -> subject.getSubjectName().equals(subjectName))
            .findFirst();
        //있으면 해당 과목의 아이디 출력
        if (selectSubject.isPresent()) return selectSubject.get().getSubjectId();
            //없으면 NoName 출력
        else return "NoName";
    }

    //수강생 아이디로 이름 찾기
    public static String getStudentNameById(String studentId) {
        //해당 id을 가진 수강생
        Optional<Student> selectStudent = CampManagementApplication.studentStore.stream()
            .filter((Student student) -> student.getStudentId().equals(studentId))
            .findFirst();
        //만약 해당 id를 가진 수강생이 존재한다면
        if (selectStudent.isPresent()) {
            return selectStudent.get().getStudentName();
        }
        //존재하지 않는다면
        else return "NoName";
    }

    //과목 아이디로 이름 찾기
    public static String getSubjectNameById(String subjectId) {
        //해당 id을 가진 수강생
        Optional<Subject> selectSubject = CampManagementApplication.subjectStore.stream()
            .filter((Subject subject) -> subject.getSubjectId().equals(subjectId))
            .findFirst();
        //만약 해당 id를 가진 수강생이 존재한다면
        if (selectSubject.isPresent()) {
            return selectSubject.get().getSubjectName();
        }
        //존재하지 않는다면
        else return "NoName";
    }

    //초기값 생성에서 수강생의 과목 리스트를 쉽게 만들기 위한 함수 -> 나중에 지울 예정이라 신경안써도 됨
    public static Subject getSubjectByName(String subjectName) {
        Optional<Subject> selectSubject = CampManagementApplication.subjectStore.stream()
            .filter((Subject subejct) -> subejct.getSubjectName().equals(subjectName))
            .findFirst();
        if (selectSubject.isPresent()) {
            Subject subject = selectSubject.get();
            return subject;
        } else return new Subject("SU0", "NoSubject", "MANDATORY");
    }

    //초기값 생성에서 과목 이름으로 과목타입을 구하는 함수
    public static String getSubjectTypeByName(String subjectName) {
        Optional<Subject> selectSubject = CampManagementApplication.subjectStore.stream()
            .filter((Subject subejct) -> subejct.getSubjectName().equals(subjectName))
            .findFirst();
        if (selectSubject.isPresent()) return selectSubject.get().getSubjectType();
        else return "NoSubject";
    }

    //초기값 생성에서 과목 아이디로 과목타입을 구하는 함수
    public static String getSubjectTypeById(String subjectId) {
        Optional<Subject> selectSubject = CampManagementApplication.subjectStore.stream()
            .filter((Subject subejct) -> subejct.getSubjectId().equals(subjectId))
            .findFirst();
        if (selectSubject.isPresent()) return selectSubject.get().getSubjectType();
        else return "NoSubject";
    }

    //해당 수강생의 수업과목 이름 목록  출력
    public static void getSubjectNameListByStudentId(String studentId) {
        List<String> selectScore = CampManagementApplication.scoreStore.stream()
            .filter((Score score) -> score.getStudentId().equals(studentId))
            .map(it -> it.getSubjectId()).toList();
        if (selectScore.isEmpty()) {
            System.out.println("해당 학생이 없습니다");
        } else {
            for (String subjectId : selectScore) {
                System.out.println(getSubjectNameById(subjectId));
            }
        }
    }

    //해당 수강생의 수업점수목록
    public static List<Score> getSubjectListByStudentId(String studentId) {
        return CampManagementApplication.scoreStore.stream()
            .filter((Score score) -> score.getStudentId().equals(studentId))
            .toList();
    }

    //수강생아이디& 과목 아이디로 해당 score 찾기
    public static Score GetScoreByStudentIdAndSubjectId(String studentId, String subjectId) {
        Optional<Score> selectScore = CampManagementApplication.scoreStore.stream()
            .filter((Score score) -> score.getStudentId().equals(studentId))
            .filter((Score score) -> score.getSubjectId().equals(subjectId))
            .findFirst();
        return selectScore.orElseGet(() -> new Score("SC0", "ST0", "SU0"));
        //아래와 같음
//        if(selectScore.isPresent()) return selectScore.get();
//        else return new Score("SC0","ST0","SU0");
    }
    public static ScoreDetail GetScoreDetailByRound(Score score) {
        int round = getRound();
        Optional<ScoreDetail> selectDetail = score.getScoreList().stream()
            .filter((ScoreDetail e) -> e.getRound()==round)
            .findFirst();
        return selectDetail.orElseGet(() -> new ScoreDetail(round, 0, Helper.getSubjectTypeById(score.getSubjectId())));
    }

    private static int getRound() {
        while (true) {
            System.out.println("회차를 입력해 주세요 ");
            int inputRound = sc.nextInt();
            if (inputRound > 0 && inputRound < 11) {
                System.out.println("선택한 회차 : " + inputRound + "회차 입니다");
                return inputRound;
            } else {
                System.out.println("잘못된 회차 입니다. (1 ~ 10)회차 까지 있습니다.");
            }
        }
    }
}
