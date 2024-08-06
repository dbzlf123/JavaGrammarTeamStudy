package camp;

import camp.CRUDOperations.Helper;
import camp.CRUDOperations.Inquire;
import camp.model.*;
import camp.CRUDOperations.Edit;

import java.util.*;

/**
 * Notification
 * Java, 객체지향이 아직 익숙하지 않은 분들은 위한 소스코드 틀입니다.
 * main 메서드를 실행하면 프로그램이 실행됩니다.
 * model 의 클래스들과 아래 (// 기능 구현...) 주석 부분을 완성해주세요!
 * 프로젝트 구조를 변경하거나 기능을 추가해도 괜찮습니다!
 * 구현에 도움을 주기위한 Base 프로젝트입니다. 자유롭게 이용해주세요!
 */
public class CampManagementApplication {
    // 데이터 저장소
    public static List<Student> studentStore;
    public static List<Subject> subjectStore;
    public static List<Score> scoreStore;

    // 과목 타입
    private static String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static String SUBJECT_TYPE_CHOICE = "CHOICE";

    // index 관리 필드
    private static int studentIndex;
    private static final String INDEX_TYPE_STUDENT = "ST";
    private static int subjectIndex;
    private static final String INDEX_TYPE_SUBJECT = "SU";
    private static int scoreIndex;
    private static final String INDEX_TYPE_SCORE = "SC";

    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        setInitData();
        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }

    // 초기 데이터 생성
    private static void setInitData() {
        subjectStore = List.of(
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Java",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "객체지향",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "JPA",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MySQL",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "디자인 패턴",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring Security",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Redis",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MongoDB",
                        SUBJECT_TYPE_CHOICE
                )
        );
//        studentStore = new ArrayList<>();
        //조회를 위해 학생리스트를 임의로 생성
        studentStore = new ArrayList<>(Arrays.asList(
                new Student(
                        sequence(INDEX_TYPE_STUDENT),
                        "HongGilDong",
                        Status.valueOf("Green")
                ),
                new Student(
                        sequence(INDEX_TYPE_STUDENT),
                        "YuHari",
                        Status.valueOf("Red")

                ),
                new Student(
                        sequence(INDEX_TYPE_STUDENT),
                        "HongGilDong",
                        Status.valueOf("Red")
                ))
        );
//        scoreStore = new ArrayList<>();

        //조회를 위해 점수리스트를 임의로 생성
        scoreStore = new ArrayList<>(Arrays.asList(
                new Score(
                        sequence(INDEX_TYPE_SCORE),
                        "ST1",
                        "SU1",
                        new ArrayList<ScoreDetail>(Arrays.asList(
                                new ScoreDetail(1, 78, Helper.getSubjectTypeByName("Java")),
                                new ScoreDetail(2, 98, Helper.getSubjectTypeByName("Java")),
                                new ScoreDetail(3, 86, Helper.getSubjectTypeByName("Java"))
                        ))
                ),
                new Score(
                        sequence(INDEX_TYPE_SCORE),
                        "ST1",
                        "SU2",
                        new ArrayList<ScoreDetail>(Arrays.asList(
                                new ScoreDetail(1, 96, Helper.getSubjectTypeByName("객체지향")),
                                new ScoreDetail(2, 98, Helper.getSubjectTypeByName("객체지향")),
                                new ScoreDetail(3, 92, Helper.getSubjectTypeByName("객체지향"))
                        ))
                ),
                new Score(
                        sequence(INDEX_TYPE_SCORE),
                        "ST2",
                        "SU1",
                        new ArrayList<ScoreDetail>(Arrays.asList(
                                new ScoreDetail(1, 78, Helper.getSubjectTypeByName("Java")),
                                new ScoreDetail(2, 68, Helper.getSubjectTypeByName("Java")),
                                new ScoreDetail(3, 86, Helper.getSubjectTypeByName("Java"))
                        ))
                ),
                new Score(
                        sequence(INDEX_TYPE_SCORE),
                        "ST2",
                        "SU2",
                        new ArrayList<ScoreDetail>(Arrays.asList(
                                new ScoreDetail(1, 78, Helper.getSubjectTypeByName("객체지향")),
                                new ScoreDetail(2, 98, Helper.getSubjectTypeByName("객체지향")),
                                new ScoreDetail(3, 86, Helper.getSubjectTypeByName("객체지향"))
                        ))
                ),
                new Score(
                        sequence(INDEX_TYPE_SCORE),
                        "ST4",
                        "SU2",
                        new ArrayList<ScoreDetail>(Arrays.asList(
                                new ScoreDetail(1, 78, Helper.getSubjectTypeByName("객체지향")),
                                new ScoreDetail(2, 98, Helper.getSubjectTypeByName("객체지향")),
                                new ScoreDetail(3, 86, Helper.getSubjectTypeByName("객체지향"))
                        ))
                ),
                new Score(
                        sequence(INDEX_TYPE_SCORE),
                        "ST3",
                        "SU1",
                        new ArrayList<ScoreDetail>(Arrays.asList(
                                new ScoreDetail(1, 98, Helper.getSubjectTypeByName("Java")),
                                new ScoreDetail(2, 99, Helper.getSubjectTypeByName("Java")),
                                new ScoreDetail(3, 97, Helper.getSubjectTypeByName("Java"))
                        ))
                ),
                new Score(
                        sequence(INDEX_TYPE_SCORE),
                        "ST3",
                        "SU2",
                        new ArrayList<ScoreDetail>(Arrays.asList(
                                new ScoreDetail(1, 76, Helper.getSubjectTypeByName("객체지향")),
                                new ScoreDetail(2, 82, Helper.getSubjectTypeByName("객체지향")),
                                new ScoreDetail(3, 85, Helper.getSubjectTypeByName("객체지향"))
                        ))
                )
        )
        );
    }

    // index 자동 증가
    private static String sequence(String type) {
        switch (type) {
            case INDEX_TYPE_STUDENT -> {
                studentIndex++;
                return INDEX_TYPE_STUDENT + studentIndex;
            }
            case INDEX_TYPE_SUBJECT -> {
                subjectIndex++;
                return INDEX_TYPE_SUBJECT + subjectIndex;
            }
            default -> {
                scoreIndex++;
                return INDEX_TYPE_SCORE + scoreIndex;
            }
        }
    }

    private static void displayMainView() throws InterruptedException {
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요...");
            int input = 0;
            try {    //nextInt에 문자열이 입력됬을때를 대비
                input = sc.nextInt();
            } catch (Exception e) {
                System.out.println("숫자로 입력해주세요");
                sc.nextLine();
            }
            switch (input) {
                case 1 -> displayStudentView(); // 수강생 관리
                case 2 -> displayScoreView(); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                    Thread.sleep(2000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private static void displayStudentView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 수강생 정보 조회");
            System.out.println("4. 수강생 정보 수정");
            System.out.println("5. 상태별 수강생 목록 조회");
            System.out.println("6. 수강생 삭제");
            System.out.println("7. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");

            int input = 0;
            try {   //nextInt에 문자열이 입력됬을때를 대비
                input = sc.nextInt();
            } catch (Exception e) {
                System.out.println("숫자로 입력해주세요");
                sc.nextLine();
            }

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> Inquire.inquireStudent(studentStore); // 수강생 목록 조회
                case 3 -> Inquire.inquireStudentInfo(studentStore); //수강생 정보 조회
                case 4 -> updateStudent(); // 수강생 정보 수정
                case 5 -> Inquire.inquireStudentStatus(studentStore); // 상태별 수강생목록 조회
                case 6 -> Edit.removeStudent(); // 수강생 삭제
                case 7 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생 등록
    private static void createStudent() {
        System.out.println("\n수강생을 등록합니다...");
        System.out.println("수강생 이름 입력: ");
        String studentName = sc.next();
        sc.nextLine();

        int mandatoryNums = 0; //필수과목 선택 횟수
        int choiceNums = 0; //선택과목 선택 횟수
        List<Subject> tempSubjectStore = new ArrayList<>();

        //과목 목록 출력, 과목 이름과 과목 타입(필수인지 선택인지)
        for (int i = 0; i < subjectStore.size(); i++) {
            System.out.println(subjectStore.get(i).getSubjectName() + " " + subjectStore.get(i).getSubjectType());
        }

        //필수 과목 3개 이상, 선택 과목 2개 이상 선택할때까지
        SelectSubjectLoop:
        // 이미 선택했다면 흐름제어를 통해 돌아오려고 만든 라벨
        while (true) {
            System.out.print("필수 과목 3개 이상, 선택 과목 2개 이상을 선택해주세요. 과목 이름으로 적어주세요.");
            String subjectName = sc.nextLine();
            boolean bFindSubject = false; // 엉뚱한 입력했을 수도 있으니 Try Catch 로 바꿀 필요성

            for (int i = 0; i < subjectStore.size(); i++) {
                if (subjectStore.get(i).getSubjectName().equals(subjectName)) {
                    //고른 필수 과목, 선택 과목 수 늘려주기
                    if (subjectStore.get(i).getSubjectType().equals(SUBJECT_TYPE_MANDATORY)) mandatoryNums++;
                    else choiceNums++;

                    for (int j = 0; j < tempSubjectStore.size(); j++) {
                        if (tempSubjectStore.get(j).getSubjectName().equals(subjectName)) {
                            System.out.println("이미 등록한 과목 입니다. 다시 선택해 주세요.");
                            continue SelectSubjectLoop;
                        }
                    }

                    //데이터 넣어주고 이놈은 그냥 중복자료 있나 확인용 임시 subject
                    Subject subject = new Subject(subjectStore.get(i).getSubjectId(),
                            subjectStore.get(i).getSubjectName(),
                            subjectStore.get(i).getSubjectType());
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


        Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName, Status.valueOf("Green"));

        studentStore.add(student);

        //실제 자료 넣기 -> 일단 점수 데이터는 빈 ArrayList 가 들어간다.
        for (int i = 0; i < tempSubjectStore.size(); i++) {
            //IndexScore 를 제외하고 학생 ID 는 이미 sequence 를 이용해서 올라갔을테니 따로 안올려주고 조합해서 보내준다.
            scoreStore.add(new Score(sequence(INDEX_TYPE_SCORE), INDEX_TYPE_STUDENT + studentIndex, tempSubjectStore.get(i).getSubjectId()));
        }

        System.out.println("수강생 등록 성공!\n");
    }

    // 수강생 정보 수정
    private static void updateStudent() {
        System.out.println("\n수강생 정보를 수정합니다...");
        // 수강생의 고유번호를 받아 이름, 상태를 수정
        while (true) {
            System.out.print("수강생의 고유번호 입력 : ");
            String insertNumber = sc.next();
            boolean bFindName = false;
            // studentStore 에 입력한 고유번호이랑 같은 고유번호가 있다면 고유번호, 이름, 상태를 출력
            for (int i = 0; i < studentStore.size(); i++) {
                if (insertNumber.equals(studentStore.get(i).getStudentId())) {
                    System.out.println("고유번호: " + studentStore.get(i).getStudentId() + ", 이름: " + studentStore.get(i).getStudentName()
                            + ", 상태: " + studentStore.get(i).getStatus());
                    System.out.println();
                    bFindName = true;
                    break;
                }
            }
            if (!bFindName) { //존재하지 않는 고유번호라면
                System.out.println("입력한 고유번호의 수강생은 없습니다.\n되돌아갑니다!");
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
                for (int i = 0; i < studentStore.size(); i++) {
                    if (insertNumber.equals(studentStore.get(i).getStudentId())) {
                        //입력한 이름이 기존 이름과 같을 때
                        if (updateName.equals(studentStore.get(i).getStudentName())) {
                            System.out.println("기존의 이름과 동일합니다. \n되돌아갑니다!");
                            break;
                        }
                        studentStore.get(i).setStudentName(updateName);

                        //변경된 수강생 내역 출력
                        System.out.println("고유번호: " + studentStore.get(i).getStudentId() + ", 이름: " + studentStore.get(i).getStudentName()
                                + ", 상태: " + studentStore.get(i).getStatus());
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
                for (int i = 0; i < studentStore.size(); i++) {
                    if (insertNumber.equals(studentStore.get(i).getStudentId())) {
                        //입력한 상태가 기존 상태와 같을 때
                        if (updateStatus.equals(studentStore.get(i).getStatus().name())) {
                            System.out.println("기존의 상태와 동일합니다. \n되돌아갑니다!");
                            break;
                        }
                        studentStore.get(i).setStatus(Status.valueOf(updateStatus));

                        //변경한 수강생 내역 출력
                        System.out.println("고유번호: " + studentStore.get(i).getStudentId() + ", 이름: " + studentStore.get(i).getStudentName()
                                + ", 상태: " + studentStore.get(i).getStatus());
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

    private static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 수강생의 특정 과목 평균등급 조회");
            System.out.println("5. 특정 과목 회차 점수 순위");
            System.out.println("7. 특정 상태 수강생들의 필수 과목 평균 등급 조회");
            System.out.println("8. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = 0;
            try {    //nextInt에 문자열이 입력됬을때를 대비
                input = sc.nextInt();
            } catch (Exception e) {
                System.out.println("숫자로 입력해주세요");
                sc.nextLine();
            }

            switch (input) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> Edit.updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> Inquire.inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> Inquire.inquireAverageGrade(subjectStore); // 수강생의 특정 과목 평균등급 조회
                case 5 -> Edit.roundScoreList();  //특정 과목 특정 회차 점수 순위
                case 7 -> Inquire.inquireAverageGradeByStatus(studentStore, subjectStore, scoreStore); // 수강생의 특정 과목 평균등급 조회
                case 8 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    private static void createScore() {
        String studentId = Helper.getStudentId();
        // 학생이름 출력
        boolean studentFound = false; // 미등록학생 확인용 선언.
        for (int i = 0; i < studentStore.size(); i++) {
            Student student = studentStore.get(i);
            if (student.getStudentId().equals(studentId)) {
                System.out.println("학생 이름 : " + student.getStudentName()); //studentId로 Name출력
                studentFound = true;
                break;
            }
        }
        if (!studentFound) {
            System.out.println("등록되지 않은 학생입니다.");
            return;
        }
        System.out.println("시험 점수를 등록합니다...");

        // 필수/선택 과목타입 선택
        while (true) {
            System.out.print("필수/선택 과목인지 선택하세요.(필수 : 1, 선택 : 2을 입력하세요)\n입력 : ");
            int subType = sc.nextInt();
            sc.nextLine(); // 값 넘어가는 부분 수정
            // 필수 과목 조건문
            if (subType == 1) {
                System.out.println("필수과목 List");
                for (int i = 0; i < subjectStore.size(); i++) {
                    Subject subject = subjectStore.get(i);
                    if (subject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY)) {
                        System.out.println(subject.getSubjectName());
                    }
                }
                System.out.print("과목 이름을 입력하세요: ");
                String subjectName = sc.nextLine();
                boolean subjectFound = false; // 과목 확인용 선언.
                for (int i = 0; i < subjectStore.size(); i++) {
                    Subject subject = subjectStore.get(i);
                    if (subject.getSubjectName().equals(subjectName)) {
                        String subjectId = subject.getSubjectId();
                        subjectFound = true;
                        System.out.println("점수를 등록할 시험의 회차를 선택하세요...(1~10 입력)");
                        int round = sc.nextInt();
                        sc.nextLine(); // 값 넘어가는 부분 수정

                        boolean roundFound = false;
                        for (int j = 0; j < scoreStore.size(); j++) {
                            Score score = scoreStore.get(j);
                            if (score.getStudentId().equals(studentId) && score.getSubjectId().equals(subjectId)) {
                                for (ScoreDetail scoreDetail : score.getScoreList()) {
                                    if (scoreDetail.getRound() == round) {
                                        roundFound = true;
                                        break;
                                    }
                                }
                            }
                            if (roundFound) break;
                        }
                        if (roundFound) {
                            System.out.println("이미 등록된 회차입니다. 다른 회차를 선택해주세요.");
                        } else {
                            if (round > 0 && round < 11) {
                                System.out.println("과목점수를 입력하세요.");
                                int scoreInput = sc.nextInt();
                                sc.nextLine(); // 값 넘어가는 부분 수정
                                if (scoreInput >= 0 && scoreInput < 101) {
                                    for (Score score : scoreStore) {
                                        if (score.getStudentId().equals(studentId) && score.getSubjectId().equals(subjectId)) {
                                            score.addScore(new ScoreDetail(round, scoreInput, Helper.getSubjectTypeByName(subjectName)));
                                            break;
                                        }
                                    }
                                } else {
                                    System.out.println("잘못된 입력값입니다. (1~100까지의 점수만 입력가능)");
                                }
                            } else {
                                System.out.println("잘못된 입력값입니다.(1~10까지의 회차만 입력가능)");
                            }
                        }
                        break;
                    }
                }
                if (!subjectFound) {
                    System.out.println("잘못된 과목 이름입니다. 다시 입력해주세요.");
                }
            }
            // 선택과목 조건문
            else if (subType == 2) {
                System.out.println("선택과목 List");
                for (int i = 0; i < subjectStore.size(); i++) {
                    Subject subject = subjectStore.get(i);
                    if (subject.getSubjectType().equals(SUBJECT_TYPE_CHOICE)) {
                        System.out.println(subject.getSubjectName());
                    }
                }
                System.out.print("과목 이름을 입력하세요: ");
                String subjectName = sc.nextLine();
                boolean subjectFound = false;
                for (int i = 0; i < subjectStore.size(); i++) {
                    Subject subject = subjectStore.get(i);
                    if (subject.getSubjectName().equals(subjectName)) {
                        String subjectId = subject.getSubjectId();
                        subjectFound = true;
                        System.out.println("점수를 등록할 시험의 회차를 선택하세요...(1~10 입력)");
                        int round = sc.nextInt();
                        sc.nextLine(); // 값 넘어가는 부분 수정

                        boolean roundFound = false;
                        for (int j = 0; j < scoreStore.size(); j++) {
                            Score score = scoreStore.get(j);
                            if (score.getStudentId().equals(studentId) && score.getSubjectId().equals(subjectId)) {
                                for (ScoreDetail scoreDetail : score.getScoreList()) {
                                    if (scoreDetail.getRound() == round) {
                                        roundFound = true;
                                        break;
                                    }
                                }
                            }
                            if (roundFound) break;
                        }
                        if (roundFound) {
                            System.out.println("이미 등록된 회차입니다. 다른 회차를 선택해주세요.");
                        } else {
                            if (round > 0 && round < 11) {
                                System.out.println("과목점수를 입력하세요.");
                                int scoreInput = sc.nextInt();
                                sc.nextLine(); // 값 넘어가는 부분 수정
                                if (scoreInput >= 0 && scoreInput < 101) {
                                    for (Score score : scoreStore) {
                                        if (score.getStudentId().equals(studentId) && score.getSubjectId().equals(subjectId)) {
                                            score.addScore(new ScoreDetail(round, scoreInput, Helper.getSubjectTypeByName(subjectName)));
                                            break;
                                        }
                                    }
                                } else {
                                    System.out.println("잘못된 입력값입니다. (1~100까지의 점수만 입력가능)");
                                }
                            } else {
                                System.out.println("잘못된 입력값입니다.(1~10까지의 회차만 입력가능)");
                            }
                        }
                        break;
                    }
                }
                if (!subjectFound) {
                    System.out.println("잘못된 과목 이름입니다. 다시 입력해주세요.");
                }
            } else {
                System.out.println("잘못된 입력입니다. 1 또는 2를 입력해주세요.");
            }

            System.out.print("계속 점수를 등록하시겠습니까? Yes or No\n입력 : ");
            String choice = sc.nextLine();
            if (choice.equalsIgnoreCase("No")) break;
        }
        System.out.println("\n점수 등록 성공!");
    }

}
