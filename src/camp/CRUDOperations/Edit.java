package camp.CRUDOperations;

import camp.CampManagementApplication;
import camp.model.*;

import java.util.*;

public class Edit {

    public static Scanner sc = new Scanner(System.in);

    // 수강생 정보 수정
    public static void updateStudent() {
        System.out.println("\n수강생 정보를 수정합니다...");
        // 수강생의 고유번호를 받아 이름, 상태를 수정
        while (true) {
            String insertNumber = Helper.getStudentIdByName();
            boolean bFindName = false;
            // studentStore 에 입력한 고유번호이랑 같은 고유번호가 있다면 고유번호, 이름, 상태를 출력
            for (int i = 0; i < CampManagementApplication.studentStore.size(); i++) {
                if (insertNumber.equals(CampManagementApplication.studentStore.get(i).getStudentId())) {
                    System.out.println("고유번호: " + CampManagementApplication.studentStore.get(i).getStudentId() + ", 이름: " + CampManagementApplication.studentStore.get(i).getStudentName()
                            + ", 상태: " + CampManagementApplication.studentStore.get(i).getStatus());
                    System.out.println();
                    bFindName = true;
                    break;
                }
            }
            if (!bFindName) { //존재하지 않는 이름이라면
                System.out.println("입력한 이름의 수강생은 없습니다.\n되돌아갑니다!");
                break;
            }
            System.out.println("무엇을 변경하시겠습니까?");
            System.out.print("번호를 입력해주세요 1.이름 2.상태 : ");
            int updateNumber = 0;
            try {
                updateNumber = sc.nextInt();
            } catch (Exception e) {
                System.out.println("숫자로 입력해주세요");
                sc.nextLine();
            }
            // 1. 이름 변경 선택
            if (updateNumber == 1) {
                System.out.print("무엇으로 이름을 변경하시겠습니까? : ");
                String updateName = sc.next();

                //입력한 고유번호(insertNumber)의 수강생의 이름을 setStudentName 메서드 이용해 updateName 으로 바꿔줘!
                for (int i = 0; i < CampManagementApplication.studentStore.size(); i++) {
                    if (insertNumber.equals(CampManagementApplication.studentStore.get(i).getStudentId())) {
                        //입력한 이름이 기존 이름과 같을 때
                        if (updateName.equals(CampManagementApplication.studentStore.get(i).getStudentName())) {
                            System.out.println("기존의 이름과 동일합니다. \n되돌아갑니다!");
                            break;
                        }
                        CampManagementApplication.studentStore.get(i).setStudentName(updateName);

                        //변경된 수강생 내역 출력
                        System.out.println("고유번호: " + CampManagementApplication.studentStore.get(i).getStudentId() + ", 이름: " + CampManagementApplication.studentStore.get(i).getStudentName()
                                + ", 상태: " + CampManagementApplication.studentStore.get(i).getStatus());
                        System.out.println("\n수강생 정보 수정 성공!");
                    }
                }
                break;

                // 2. 상태 변경 선택
            } else if (updateNumber == 2) {
                System.out.print("무엇으로 상태를 변경하시겠습니까? (Green/Yellow/Red) : ");
                String updateStatus = sc.next();
                //updateStatus 이 Green/Yellow/Red 이외의 것을 입력했을 때
                if (!(updateStatus.equals("Green") || updateStatus.equals("Yellow") || updateStatus.equals("Red"))) {
                    System.out.println("입력하신 상태값은 없습니다. \n되돌아갑니다!");
                    break;
                }
                //입력한 고유번호(insertNumber)의 수강생의 상태를 setStatus 메서드 이용해 updateStatus 으로 바꿔줘!
                for (int i = 0; i < CampManagementApplication.studentStore.size(); i++) {
                    if (insertNumber.equals(CampManagementApplication.studentStore.get(i).getStudentId())) {
                        //입력한 상태가 기존 상태와 같을 때
                        if (updateStatus.equals(CampManagementApplication.studentStore.get(i).getStatus().name())) {
                            System.out.println("기존의 상태와 동일합니다. \n되돌아갑니다!");
                            break;
                        }
                        CampManagementApplication.studentStore.get(i).setStatus(Status.valueOf(updateStatus));

                        //변경한 수강생 내역 출력
                        System.out.println("고유번호: " + CampManagementApplication.studentStore.get(i).getStudentId() + ", 이름: " + CampManagementApplication.studentStore.get(i).getStudentName()
                                + ", 상태: " + CampManagementApplication.studentStore.get(i).getStatus());
                        System.out.println("\n수강생 정보 수정 성공!");
                    }
                }
                break;

                // 1 이름, 2 상태 이외 선택 시
            } else {
                System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                break;
            }
        }
    }

    // 수강생 과목 회차 점수 수정
    public static void updateRoundScoreBySubject() {
        String studentId = Helper.getStudentIdByName(); // 관리할 수강생 고유 번호
        String studentName = Helper.getStudentNameById(studentId);
        if (!studentName.equals("NoName")) {
            Helper.getSubjectNameListByStudentId(studentId);
        } else {
            System.out.println("해당 학생을 찾을 수 없습니다.");
            return; //오류시 끝내기
        }
        String inputSubjectId = Helper.getSubjectIdByName(); //해당 과목 id 입력
        Score score = Helper.GetScoreByStudentIdAndSubjectId(studentId, inputSubjectId);
        if (!score.getSubjectId().equals("SU0")) {
            ScoreDetail scoreDetail = Helper.GetScoreDetailByRound(score);
            int updatedScore = Helper.getScore(); //점수 입력 받고 해당 점수 범위에 맞는 지 확인
            if (scoreDetail.getScore() != 0) { // 라운드에 값이 있으면 해당 scoreDetail에 셋스코어 해서 입력받은 점수 수정
                scoreDetail.setScore(Helper.getSubjectTypeById(inputSubjectId), updatedScore);
            } else {// 라운드에 값이 없으면 새로운 scoreDetail 만들어주고 입력받은 값을 넣는다.
                scoreDetail.setScore(Helper.getSubjectTypeById(inputSubjectId), updatedScore);
                score.addScore(scoreDetail);
            }
        } else {
            System.out.println("해당 과목을 찾을 수 없습니다.");
            return; //오류
        }
        System.out.println("시험 점수를 수정합니다...");
        System.out.println("\n점수 수정 성공!");
    }

    //수강생 지우기
    public static void removeStudent() {
        System.out.println("삭제할 수강생의 고유 번호를 입력하세요");
        String studentId = sc.next();

        //지운게 성공한적 체크를 위해 boolean 형 지역변수 선언
        boolean successRemove = false;
        for (Student student : CampManagementApplication.studentStore) {
            if (student.getStudentId().equals(studentId)) {
                CampManagementApplication.studentStore.remove(student);
                //지우기 성공, 어차피 학생 ID 는 유일하니 바로 Break;
                successRemove = true;
                break;
            }
        }

        //점수 저장 공간에서도 삭제
        //그냥 Index 0 으로 하고 지워주면 삭제하면서 ArrayList 배열 땡겨져서 안됨, 뒤에서부터 지워야 배열 안변함
        for (int i = CampManagementApplication.scoreStore.size() - 1; i >= 0; i--) {
            if (CampManagementApplication.scoreStore.get(i).getStudentId().equals(studentId)) {
                CampManagementApplication.scoreStore.remove(i);
            }
        }

        if (successRemove) System.out.println("성공적으로 삭제 되었습니다.");
        else System.out.println("그런 수강생 없습니다");
    }

    //특정 과목 특정 회차 점수 순위
    public static void roundScoreList() {
        String subjectId = Helper.getSubjectIdByName();
        List<Score> selectScore = CampManagementApplication.scoreStore.stream()
                .filter((Score score) -> score.getSubjectId().equals(subjectId))
                .toList();
        //만약 해당값이 있다면
        if (!selectScore.isEmpty()) {
            System.out.println("조회할 회차를 입력하세요");
            int round = sc.nextInt();
            HashMap<String, Integer> map = new HashMap<>();
            for (Score score : selectScore) {
                String studentId = score.getStudentId();
                List<ScoreDetail> scorelist = score.getScoreList();
                for (ScoreDetail scoreDetail : scorelist) {
                    if (scoreDetail.getRound() == round) {
                        map.put(studentId, scoreDetail.getScore());
                    }
                }
            }
            List<Map.Entry<String, Integer>> entryList = new LinkedList<>(map.entrySet());
            if (!entryList.isEmpty()) {
                entryList.sort(new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                        return o2.getValue() - o1.getValue();
                    }
                });
                for (Map.Entry<String, Integer> entry : entryList) {
                    System.out.println("이름 : " + Helper.getStudentNameById(entry.getKey()) + ", 점수 : " + entry.getValue());
                }
            } else {
                System.out.println("등록된 점수가 없습니다.");
            }
        } else {
            System.out.println("\n등급 조회 실패! 다시 시도해주세요.");
        }
    }

}