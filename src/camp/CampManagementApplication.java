package camp;

import camp.model.*;

import javax.swing.*;
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
    private static List<Student> studentStore;
    private static List<Subject> subjectStore;
    private static List<Score> scoreStore;

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
                        new ArrayList<ScoreDatail>(Arrays.asList(
                                new ScoreDatail(1, 78, getSubjectTypeByName("Java")),
                                new ScoreDatail(2, 98, getSubjectTypeByName("Java")),
                                new ScoreDatail(3, 86, getSubjectTypeByName("Java"))
                        ))
                ),
                new Score(
                        sequence(INDEX_TYPE_SCORE),
                        "ST1",
                        "SU2",
                        new ArrayList<ScoreDatail>(Arrays.asList(
                                new ScoreDatail(1, 96, getSubjectTypeByName("객체지향")),
                                new ScoreDatail(2, 98, getSubjectTypeByName("객체지향")),
                                new ScoreDatail(3, 92, getSubjectTypeByName("객체지향"))
                        ))
                ),
                new Score(
                        sequence(INDEX_TYPE_SCORE),
                        "ST2",
                        "SU1",
                        new ArrayList<ScoreDatail>(Arrays.asList(
                                new ScoreDatail(1, 78, getSubjectTypeByName("Java")),
                                new ScoreDatail(2, 68, getSubjectTypeByName("Java")),
                                new ScoreDatail(3, 86, getSubjectTypeByName("Java"))
                        ))
                ),
                new Score(
                        sequence(INDEX_TYPE_SCORE),
                        "ST2",
                        "SU2",
                        new ArrayList<ScoreDatail>(Arrays.asList(
                                new ScoreDatail(1, 78, getSubjectTypeByName("객체지향")),
                                new ScoreDatail(2, 98, getSubjectTypeByName("객체지향")),
                                new ScoreDatail(3, 86, getSubjectTypeByName("객체지향"))
                        )
                        )
                ),
                new Score(
                        sequence(INDEX_TYPE_SCORE),
                        "ST2",
                        "SU2",
                        new ArrayList<ScoreDatail>(Arrays.asList(
                                new ScoreDatail(1, 78, getSubjectTypeByName("객체지향")),
                                new ScoreDatail(2, 98, getSubjectTypeByName("객체지향")),
                                new ScoreDatail(3, 86, getSubjectTypeByName("객체지향"))
                        ))
                ),
                new Score(
                        sequence(INDEX_TYPE_SCORE),
                        "ST4",
                        "SU2",
                        new ArrayList<ScoreDatail>(Arrays.asList(
                                new ScoreDatail(1, 78, getSubjectTypeByName("객체지향")),
                                new ScoreDatail(2, 98, getSubjectTypeByName("객체지향")),
                                new ScoreDatail(3, 86, getSubjectTypeByName("객체지향"))
                        ))
                )

        ));

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
            int input = sc.nextInt();

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
            System.out.println("5. 수강생 정보 수정");
            System.out.println("7. 수강생 삭제");
            System.out.println("8. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 5 -> updateStudent(); // 수강생 정보 수정
                case 7 -> removeStudent(); // 수강생 삭제
                case 8 -> flag = false; // 메인 화면 이동
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
            scoreStore.add(new Score(sequence(INDEX_TYPE_SCORE), INDEX_TYPE_STUDENT + studentIndex, subjectStore.get(i).getSubjectId()));
        }

        System.out.println("수강생 등록 성공!\n");
    }


    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");

        // studentStore 리스트에서 학생 정보 받아서 목록 출력 + 순서대로 번호 부여
        for (int i = 0; i < studentStore.size(); i++) {
            System.out.println((i + 1) + ". 고유번호: " + studentStore.get(i).getStudentId() + ", 이름: " + studentStore.get(i).getStudentName()
                    + ", 상태: " + studentStore.get(i).getStatus());
        }
        System.out.println("\n수강생 목록 조회 성공!");
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
            int updateNumber = sc.nextInt();

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
            System.out.println("7. 특정 상태 수강생들의 필수 과목 평균 등급 조회");
            System.out.println("8. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> inquireAverageGrade(); // 수강생의 특정 과목 평균등급 조회
                case 7 -> inquireAverageGradeByStatus(); // 수강생의 특정 과목 평균등급 조회
                case 8 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    private static String getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.next();
    }

    private static String getSubjectId() {
        System.out.print("\n관리할 과목의 번호를 입력하시오...");
        return sc.next();
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    private static void createScore() {
        // scoredetail 리스트 선언.
        List<ScoreDatail> scoreList = new ArrayList<>();
        String studentId = getStudentId();
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
                        if (round > 0 && round < 11) {
                            System.out.println("과목점수를 입력하세요.");
                            int scoreInput = sc.nextInt();
                            sc.nextLine(); // 값 넘어가는 부분 수정
                            // char grade = ScoreDatail.changeGrade(SUBJECT_TYPE_MANDATORY, scoreInput);
                            scoreList.add(new ScoreDatail(round, scoreInput, SUBJECT_TYPE_MANDATORY));
                            Score score = new Score(sequence(INDEX_TYPE_SCORE), studentId, subjectId, scoreList);
                            scoreStore.add(score);
                        } else {
                            System.out.println("잘못된 입력값입니다.(1~10까지의 회차만 입력가능)");
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
                        if (round > 0 && round < 11) {
                            System.out.println("과목점수를 입력하세요.");
                            int scoreInput = sc.nextInt();
                            sc.nextLine(); // 값 넘어가는 부분 수정
                            // char grade = ScoreDatail.changeGrade(SUBJECT_TYPE_CHOICE, scoreInput);
                            scoreList.add(new ScoreDatail(round, scoreInput, SUBJECT_TYPE_CHOICE));
                            Score score = new Score(sequence(INDEX_TYPE_SCORE), studentId, subjectId, scoreList);
                            scoreStore.add(score);
                        } else {
                            System.out.println("잘못된 입력값입니다.(1~10까지의 회차만 입력가능)");
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


    // 수강생의 과목별 회차 점수 수정//1. 해당학생 조회하고 없는 번호면 오류 텍스트 출력하기
    //        //2. 해당학생의 수정할 과목 입력하고 조회 후 없으면 오류 텍스트 출력하기
    //        //3. 수정할 회차 입력하고 조회 후 없으면 오류 텍스트 출력하기
    //        //4. 점수 수정하는 기능 구현 점수가 없으면 바로 추가해주는것도 넣기
    private static void updateRoundScoreBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        Student SI = null;
        for (int i = 0; i < studentStore.size(); i++) {
            if (studentId.equals(studentStore.get(i).getStudentId())) { //학생 아이디값 같으면 진행
                SI = studentStore.get(i); //나중에 scoreDetail에 넣을 si 값을 찾은 id값을 대입
                break;
            }
        }
        if (SI == null) {
            System.out.println("해당 학생을 찾을 수 없습니다.");
            return; //오류시 끝내기
        }

        System.out.println("수정할 과목 이름을 입력하세요.");
        String inputSubjectId = sc.next(); //해당 과목 입력
        Subject SJ = null;
        for (int i = 0; i < subjectStore.size(); i++) {
            if (inputSubjectId.equals(subjectStore.get(i).getSubjectId())) {
                //입력된 과목과 불러온 과목 이름 같은지 대조
                SJ = subjectStore.get(i);
                //맞으면 해당 과목의 값을 넣고 다음으로 넘어감
                break;
            }
        }
        if (SJ == null) {
            System.out.println("해당 과목을 찾을 수 없습니다.");
            return; //오류
        }


        while (true) {
            System.out.println("수정할 회차를 입력해 주세요 ");
            int inputRound = sc.nextInt();
            if (inputRound > 0 && inputRound < 10) {
                System.out.println("선택한 회차 : " + inputRound + "회차 입니다");
                break;
            } else {
                System.out.println("잘못된 회차 입니다. (1 ~ 10)회차 까지 있습니다.");
            }
        }

        System.out.println("새로운 점수를 입력해 주세요 "); // 점수입력 - 범위벗어나는 숫자 입력시 오류 문자 내는 기능 넣기
        int updatedScore = sc.nextInt();
        ScoreDatail scoreDetail;
//        if(scoreDetail == null) {
//            scoreDetail = new ScoreDatail(round,updatedScore,inputSubjectId);
//        }else{
//            //있는값을 없애고 추가하는방법
//            //그냥 대체하는방법?
//            scoreStore.get()
//            scoreDetail.setScore(inputSubjectId,updatedScore);
//        }
////            score.getScoreList().set(get(scoreIndex,scoreDetail))
        System.out.println("시험 점수를 수정합니다...");
        System.out.println("\n점수 수정 성공!");
    }

    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {
        // 조회할 특정 수강생 입력
//        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        String studentId = getStudentIdByName();  //이름으로 수강생 고유번호 입력
        // 조회할 특정 과목 입력
//        String subjectId = getSubjectId(); // 관리할 과목 고유 번호
        String subjectId = getSubjectIdByName();  //이름으로 과목 고유번호 입력
        // 기능 구현
        System.out.println("회차별 등급을 조회합니다...");
        //해당 학생아이디와 과목아이디를 가진 점수를 찾아라
        Optional<Score> selectScore = scoreStore.stream()
                .filter((Score score) -> score.getStudentId().equals(studentId))
                .filter((Score score) -> score.getSubjectId().equals(subjectId))
                .findFirst();
        //만약 있다면
        if (selectScore.isPresent()) {
            List<ScoreDatail> scoreList = selectScore.get().getScoreList();

            //점수가 등록된 경우 점수 출력
            if (!scoreList.isEmpty()) {
                for (ScoreDatail score : scoreList) System.out.println(score.getRound() + "회차 : " + score.getGrade());
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

    //수강생 이름으로 아이디 찾기
//이름이 같을 경우도 고려해야함
    private static String getStudentIdByName() {
        System.out.print("\n관리할 수강생의 이름을 입력하시오...");
        String studentName = sc.next();
        String subjectId = "";
        //해당 이름을 가진 수강생 데이터 리스트
        List<Student> selectStudent = studentStore.stream()
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
    private static String getSubjectIdByName() {
        System.out.print("\n관리할 과목의 이름을 입력하시오...");
        String subjectName = sc.next();
        //입력값에 해당하는 subject 데이터 찾기
        Optional<Subject> selectSubject = subjectStore.stream()
                .filter((Subject subject) -> subject.getSubjectName().equals(subjectName))
                .findFirst();
        //있으면 해당 과목의 아이디 출력
        if (selectSubject.isPresent()) return selectSubject.get().getSubjectId();
            //없으면 NoName 출력
        else return "NoName";
    }

    //한 과목의 평균 점수/등급 조회
    private static void inquireAverageGrade() {
        // 조회할 특정 수강생 입력
        String studentId = getStudentIdByName(); //이름으로 수강생 고유번호 입력
        // 조회할 특정 과목 입력
        String subjectId = getSubjectIdByName();  //이름으로 과목 고유번호 입력
        //기능 규현
        System.out.println("평균 등급을 조회합니다...");
        //해당 학생과 과목이 일치하는 점수 얻기
        Optional<Score> selectScore = scoreStore.stream()
                .filter((Score score) -> score.getStudentId().equals(studentId))
                .filter((Score score) -> score.getSubjectId().equals(subjectId))
                .findFirst();
        if (selectScore.isPresent()) {
            Score score = selectScore.get();

            //점수가 등록된 경우
            if (!score.getScoreList().isEmpty()) {
                //해당하는 과목의 subjectType 구하기("필수 or 선택)
                String subjectType = subjectStore.stream()
                        .filter((Subject subject) -> subject.getSubjectId().equals(subjectId))
                        .findFirst().get().getSubjectType();
                //점수의 평균값 얻기
                List<ScoreDatail> scoreList = score.getScoreList();
                int sum = 0;
                for (ScoreDatail scoreDetail : scoreList) {
                    sum += scoreDetail.getScore();
                }
                double avgScore = sum / scoreList.size();
                // 평균 점수를 등급으로 바꿔줌
                System.out.println("이 과목의 평균등급은 " + ScoreDatail.changeGrade(subjectType, avgScore) + "입니다.");

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

    //초기값 생성에서 수강생의 과목 리스트를 쉽게 만들기 위한 함수 -> 나중에 지울 예정이라 신경안써도 됨
    public static Subject getSubjectByName(String subjectName) {
        Optional<Subject> selectSubject = subjectStore.stream()
                .filter((Subject subejct) -> subejct.getSubjectName().equals(subjectName))
                .findFirst();
        if (selectSubject.isPresent()) {
            Subject subject = selectSubject.get();
            return subject;
        } else return new Subject("SU0", "NoSubject", "MANDATORY");
    }

    //초기값 생성에서 과목 이름으로 과목타입을 구하는 함수
    public static String getSubjectTypeByName(String subjectName) {
        Optional<Subject> selectSubject = subjectStore.stream()
                .filter((Subject subejct) -> subejct.getSubjectName().equals(subjectName))
                .findFirst();
        if (selectSubject.isPresent()) return selectSubject.get().getSubjectType();
        else return "NoSubject";
    }


    //특정 상태 수강생들의 필수 과목 평균 등급 조회
    public static void inquireAverageGradeByStatus() {
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

                            for (ScoreDatail scoreDatail : score.getScoreList()) { //회차 있으니 탐색해준다.
                                found = true;
                                tempScore += scoreDatail.getScore();
                                totalScoreNums++;
                            }
                        }
                    }
                }
                if (found && totalScoreNums != 0) { //회차 돌았는데 0점 맞은 학생도 있을수있으니 나누기 연산 터지는거 대비해서 미리 0 체크
                    tempScore /= totalScoreNums;
                    System.out.println(student.getStudentName() + " 의 필수과목 평균 등급은 " + ScoreDatail.changeGrade("MANDATORY", tempScore) + " 입니다.");
                }
            }
        }
        if (!found) System.out.println("해당 상태의 학생이 존재하지 않습니다.");
    }


    //수강생 지우기
    public static void removeStudent() {
        System.out.println("삭제할 수강생의 고유 번호를 입력하세요");
        String studentId = sc.next();

        //지운게 성공한적 체크를 위해 boolean 형 지역변수 선언
        boolean successRemove = false;
        for (Student student : studentStore) {
            if (student.getStudentId().equals(studentId)) {
                studentStore.remove(student);
                //지우기 성공, 어차피 학생 ID 는 유일하니 바로 Break;
                successRemove = true;
                break;
            }
        }

        //점수 저장 공간에서도 삭제
        //그냥 Index 0 으로 하고 지워주면 삭제하면서 ArrayList 배열 땡겨져서 안됨, 뒤에서부터 지워야 배열 안변함
        for (int i = scoreStore.size() - 1; i >= 0; i--) {
            if (scoreStore.get(i).getStudentId().equals(studentId)) {
                scoreStore.remove(i);
            }
        }

        if (successRemove) System.out.println("성공적으로 삭제 되었습니다.");
        else System.out.println("그런 수강생 없습니다");
    }

}
