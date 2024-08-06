package camp.CRUDOperations;

import camp.model.*;

import java.util.*;

import camp.CampManagementApplication;
import camp.model.Student;

public class Inquire extends CampManagementApplication {

    // studentStore 리스트에서 학생 정보 받아서 목록 출력 + 순서대로 번호 부여
    public static void getStudentInfo() {
        String studentId = Helper.getStudentId(); // 학생 id 입력
        Student studentInfo = null;
        for(
                int i = 0; i<studentStore.size(); i++) {
        if (studentId.equals(studentStore.get(i).getStudentId())) { //입력받은 id가 스튜던트 스토어에 같은값이 있다면 진행
            studentInfo = studentStore.get(i); //진행 오류 확인용
            System.out.println("고유번호: "); //과목 이름 받아오는 메서드가 세로로 나열해서
            //비슷하게 맞추기 위한 세로 정렬
            System.out.println(" " + studentStore.get(i).getStudentId());
            System.out.println("이름: ");
            System.out.println(" " + studentStore.get(i).getStudentName());
            System.out.println("상태: ");
            System.out.println(" " + studentStore.get(i).getStatus());
            System.out.println("과목: ");
            getSubjectNameListByStudentId(studentId); //해당학생이 선택한 과목 리스트 불러오기
            break;
        }
    }
        if(studentInfo ==null)
    {
        System.out.println("해당 학생을 찾을 수 없습니다.");
        return; //오류시 끝내기
    }
}

    // 과목 타입
    private static String SUBJECT_TYPE_MANDATORY = "MANDATORY";

    private static final Scanner sc = new Scanner(System.in);

    // 수강생 목록 조회
    public static void inquireStudent(List<Student> studentStore) {
        System.out.println("\n수강생 목록을 조회합니다...");

        // studentStore 리스트에서 학생 정보 받아서 목록 출력 + 순서대로 번호 부여
        for (int i = 0; i < studentStore.size(); i++) {
            System.out.println((i + 1) + ". 고유번호: " + studentStore.get(i).getStudentId() + ", 이름: " + studentStore.get(i).getStudentName());
        }
        System.out.println("\n수강생 목록 조회 성공!");
    }

    public static void inquireStudentInfo(List<Student> studentStore) {
        System.out.println("\n수강생 목록을 조회합니다...");
        String studentId = getStudentId(); // 학생 id 입력
        System.out.println();
        Student studentInfo = null;
        // studentStore 리스트에서 학생 정보 받아서 목록 출력 + 순서대로 번호 부여
        for (int i = 0; i < studentStore.size(); i++) {
            if (studentId.equals(studentStore.get(i).getStudentId())) { //입력받은 id가 스튜던트 스토어에 같은값이 있다면 진행
                studentInfo = studentStore.get(i); //진행 오류 확인용
                System.out.println("고유번호: "); //과목 이름 받아오는 메서드가 세로로 나열해서
                //비슷하게 맞추기 위한 세로 정렬
                System.out.println(" " + studentStore.get(i).getStudentId());
                System.out.println("이름: ");
                System.out.println(" " + studentStore.get(i).getStudentName());
                System.out.println("상태: ");
                System.out.println(" " + studentStore.get(i).getStatus());
                System.out.println("과목: ");
                Helper.getSubjectNameListByStudentId(studentId); //해당학생이 선택한 과목 리스트 불러오기
                break;
            }
        }
        if (studentInfo == null) {
            System.out.println("해당 학생을 찾을 수 없습니다.");
            return; //오류시 끝내기
        }
        System.out.println("\n수강생 목록 조회 성공!");
    }


    // 수강생의 특정 과목 회차별 등급 조회
    public static void inquireRoundGradeBySubject() {
        // 조회할 특정 수강생 입력
        // String studentId = getStudentId(); // 관리할 수강생 고유 번호
        String studentId = Helper.getStudentIdByName();  //이름으로 수강생 고유번호 입력
        // 조회할 특정 과목 입력
        // String subjectId = getSubjectId(); // 관리할 과목 고유 번호
        String subjectId = Helper.getSubjectIdByName();  //이름으로 과목 고유번호 입력
        // 기능 구현
        Helper.getSubjectNameListByStudentId(studentId);
        System.out.println("회차별 등급을 조회합니다...");
        //해당 학생아이디와 과목아이디를 가진 점수를 찾아라
        Optional<Score> selectScore = Helper.GetScoreByStudentIdAndSubjectId(studentId, subjectId);

        //만약 있다면
        if (selectScore.isPresent()) {
            List<ScoreDetail> scoreList = selectScore.get().getScoreList();

            //점수가 등록된 경우 점수 출력
            if (!scoreList.isEmpty()) {
                for (ScoreDetail score : scoreList) System.out.println(score.getRound() + "회차 : " + score.getGrade());

                System.out.println("\n등급 조회 성공!");
            }
            //점수가 등록되지 않는 경우
            else {
                System.out.println("\n 해당과목에 등록된 점수가 없습니다");
            }
        } else {
            System.out.println("\n등급 조회 실패! 다시 시도해주세요.");
        }
    }


    // 상태별 수강생 목록 조회
    public static void inquireStudentStatus(List<Student> studentStore) {
        System.out.println("찾으실 수강생들의 상태를 입력하세요 (Green, Yellow, Red 중 입력)");
        String inputStatus = sc.next();
        Status status;

        switch (inputStatus) {
            case "Green":
                status = Status.Green;
                break;
            case "Yellow":
                status = Status.Yellow;
                break;
            case "Red":
                status = Status.Red;
                break;
            default:
                System.out.println("해당하는 상태 값은 없습니다.");
                return;
        }
        boolean studentFound = false;
        for (Student student : studentStore) {
            if (student.getStatus() == status) {
                System.out.println(student.getStudentName());
                studentFound = true;
            }
        }
        if (!studentFound) {
            System.out.println("해당 상태의 학생이 없습니다.");
        }
    }

    //한 과목의 평균 점수/등급 조회
    public static void inquireAverageGrade(List<Subject> subjectStore) {
        // 조회할 특정 수강생 입력
        String studentId = Helper.getStudentIdByName(); //이름으로 수강생 고유번호 입력
        // 조회할 특정 과목 입력
        String subjectId = Helper.getSubjectIdByName();  //이름으로 과목 고유번호 입력
        //기능 규현
        System.out.println("평균 등급을 조회합니다...");
        //해당 학생과 과목이 일치하는 점수 얻기
        Optional<Score> selectScore = Helper.GetScoreByStudentIdAndSubjectId(studentId, subjectId);
        if (selectScore.isPresent()) {
            Score score = selectScore.get();

            //점수가 등록된 경우
            if (!score.getScoreList().isEmpty()) {
                //해당하는 과목의 subjectType 구하기("필수 or 선택)
                String subjectType = subjectStore.stream()
                        .filter((Subject subject) -> subject.getSubjectId().equals(subjectId))
                        .findFirst().get().getSubjectType();
                //점수의 평균값 얻기
                List<ScoreDetail> scoreList = score.getScoreList();
                int sum = 0;
                for (ScoreDetail scoreDetail : scoreList) {
                    sum += scoreDetail.getScore();
                }
                double avgScore = sum / scoreList.size();
                // 평균 점수를 등급으로 바꿔줌
                System.out.println("이 과목의 평균등급은 " + ScoreDetail.changeGrade(subjectType, avgScore) + "입니다.");

                System.out.println("\n등급 조회 성공!");
                //점수가 등록되지 않은 경우
            } else {
                System.out.println("\n 해당과목에 등록된 점수가 없습니다");
            }
            //아얘 score값이 없는 경우
        } else {

            System.out.println("\n등급 조회 실패! 다시 시도해주세요.");
        }
    }

    //특정 상태 수강생들의 필수 과목 평균 등급 조회
    public static void inquireAverageGradeByStatus(List<Student> studentStore ,List<Subject> subjectStore, List<Score> scoreStore) {
        System.out.println("찾으실 수강생들의 상태를 입력하세요");
        String inputStatus = sc.next();
        Status status = Status.Green;

        switch (inputStatus) {
            case "Green":
                status = Status.Green;
                break;
            case "Yellow":
                status = Status.Yellow;
                break;
            case "Red":
                status = Status.Red;
                break;
            default:
                System.out.println("해당하는 상태값은 없습니다.");
                return;
        }

        //클래스화 시킬때 Stream 화 시키기
        boolean found = false;
        for (Student student : studentStore) { //학생들 다 뒤져야하니 전부 찾기
            if (student.getStatus() == status) { //등록받은 상태값이 같다면
                int tempScore = 0;
                int totalScoreNums = 0;

                for (Score score : scoreStore) { //점수 저장공간도 탐색해준다.
                    if (!score.getStudentId().equals(student.getStudentId()))
                        continue; //만약 학생 ID 가 일치하지 않으면 탐색할필요없으니 넘어가주고..
                    for (Subject subject : subjectStore) { // 일치한다면 이제 과제 저장 목록들하고 비교하면서 필수과목인지, 등록된 과목하고 같은지 비교해준다.
                        if (subject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY) &&
                                subject.getSubjectId().equals(score.getSubjectId())) {
                            if (score.getScoreList().isEmpty()) break; //처음 만들었을 때 생성되는 회차, 점수 없는 학생 예외처리

                            for (ScoreDetail scoreDetail : score.getScoreList()) { //회차 있으니 탐색해준다.
                                found = true;
                                tempScore += scoreDetail.getScore();
                                totalScoreNums++;
                            }
                        }
                    }
                }
                if (found && totalScoreNums != 0) { //회차 돌았는데 한번도 체크 못한 경우 예외처리
                    tempScore /= totalScoreNums;
                    System.out.println(student.getStudentName() + " 의 필수과목 평균 등급은 " + ScoreDetail.changeGrade("MANDATORY", tempScore) + " 입니다.");

                }
            }
        }
        if (!found) System.out.println("해당 상태의 학생이 존재하지 않습니다.");
    }







    private static String getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.next();
    }

    private static String getSubjectId() {
        System.out.print("\n관리할 과목의 번호를 입력하시오...");
        return sc.next();
    }

}
