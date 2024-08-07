package camp.CRUDOperations;

import camp.CampManagementApplication;
import camp.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Create {
    private static Scanner sc = new Scanner(System.in);


    // 수강생 등록
    public static void createStudent() {
        System.out.println("\n수강생을 등록합니다...");
        System.out.println("수강생 이름 입력: ");
        String studentName = sc.next();
        sc.nextLine();

        int mandatoryNums = 0; //필수과목 선택 횟수
        int choiceNums = 0; //선택과목 선택 횟수
        List<Subject> tempSubjectStore = new ArrayList<>();

        //과목 목록 출력, 과목 이름과 과목 타입(필수인지 선택인지)
        for (int i = 0; i < CampManagementApplication.subjectStore.size(); i++) {
            System.out.println(CampManagementApplication.subjectStore.get(i).getSubjectName() + " "
                    + CampManagementApplication.subjectStore.get(i).getSubjectType());
        }

        //필수 과목 3개 이상, 선택 과목 2개 이상 선택할때까지
        SelectSubjectLoop:
        // 이미 선택했다면 흐름제어를 통해 돌아오려고 만든 라벨
        while (true) {
            System.out.print("필수 과목 3개 이상, 선택 과목 2개 이상을 선택해주세요. 과목 이름으로 적어주세요.");
            String subjectName = sc.nextLine();
            boolean bFindSubject = false; // 엉뚱한 입력했을 수도 있으니 Try Catch 로 바꿀 필요성

            for (int i = 0; i < CampManagementApplication.subjectStore.size(); i++) {
                if (CampManagementApplication.subjectStore.get(i).getSubjectName().equals(subjectName)) {
                    //고른 필수 과목, 선택 과목 수 늘려주기
                    if (CampManagementApplication.subjectStore.get(i).getSubjectType().equals(CampManagementApplication.SUBJECT_TYPE_MANDATORY))
                        mandatoryNums++;
                    else choiceNums++;

                    for (int j = 0; j < tempSubjectStore.size(); j++) {
                        if (tempSubjectStore.get(j).getSubjectName().equals(subjectName)) {
                            System.out.println("이미 등록한 과목 입니다. 다시 선택해 주세요.");
                            continue SelectSubjectLoop;
                        }
                    }

                    //데이터 넣어주고 이놈은 그냥 중복자료 있나 확인용 임시 subject
                    Subject subject = new Subject(CampManagementApplication.subjectStore.get(i).getSubjectId(),
                            CampManagementApplication.subjectStore.get(i).getSubjectName(),
                            CampManagementApplication.subjectStore.get(i).getSubjectType());
                    tempSubjectStore.add(subject);

                    bFindSubject = true;
                    System.out.print("선택 완료\n");
                    break;
                }
            }
            if (!bFindSubject) { //존재하지 않는 이름
                System.out.print("\n존재하지 않는 과목 입니다. 다시 입력해주세요\n");
            }
            if (mandatoryNums >= 3 && choiceNums >= 2) { //최소 선택 수 만족
                System.out.print("충분히 선택하셨습니다. 더 선택하시겠습니까? Yes or No\n");
                String choice = sc.nextLine();
                if (choice.equals("No")) break;
            }
        }
        Student student = new Student(CampManagementApplication.sequence(CampManagementApplication.INDEX_TYPE_STUDENT), studentName, Status.valueOf("Green"));

        CampManagementApplication.studentStore.add(student);

        //실제 자료 넣기 -> 일단 점수 데이터는 빈 ArrayList 가 들어간다.
        for (int i = 0; i < tempSubjectStore.size(); i++) {
            //IndexScore 를 제외하고 학생 ID 는 이미 sequence 를 이용해서 올라갔을테니 따로 안올려주고 조합해서 보내준다.
            CampManagementApplication.scoreStore.add(new Score(CampManagementApplication.sequence(CampManagementApplication.INDEX_TYPE_SCORE),
                    CampManagementApplication.INDEX_TYPE_STUDENT + CampManagementApplication.studentIndex, tempSubjectStore.get(i).getSubjectId()));
        }

        System.out.println("수강생 등록 성공!\n");
    }


    // 수강생의 과목별 시험 회차 및 점수 등록
    public static void createScore() {
        String studentId = Helper.getStudentIdByName();
        if (studentId.equals("NoName")) {
            System.out.println("해당 학생이 없습니다.");
        } else {
            System.out.println("시험 점수를 등록합니다...");
            Helper.getSubjectNameListByStudentId(studentId);
            String subjectId = Helper.getSubjectIdByName();
            Score score = Helper.GetScoreByStudentIdAndSubjectId(studentId, subjectId);

            if (!score.getSubjectId().equals("SU0")) {
                ScoreDetail roundFound = Helper.GetScoreDetailByRound(score);

                if (roundFound.getScore() != 0) { // 해당 라운드가 있을 때
                    System.out.println("이미 등록된 회차입니다. 다른 회차를 선택해주세요.");
                } else { // 해당 라운드가 없을 때
                    System.out.println("과목점수를 입력하세요.");
                    int scoreInput = sc.nextInt();
                    sc.nextLine(); // 값 넘어가는 부분 수정
                    if (scoreInput >= 0 && scoreInput < 101) {
                        roundFound.setScore(Helper.getSubjectTypeById(subjectId), scoreInput);
                        score.addScore(roundFound);
                    } else {
                        System.out.println("잘못된 입력값입니다. (1~100까지의 점수만 입력가능)");
                    }
                }
            } else {
                System.out.println("선택하지 않은 과목입니다.");
            }
        }
    }
}
