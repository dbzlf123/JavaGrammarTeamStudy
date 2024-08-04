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
                        Status.valueOf("Green"),
                        new ArrayList<>(Arrays.asList(getSubjectByName("Java"),
                                getSubjectByName("객체지향"),
                                getSubjectByName("Spring"),
                                getSubjectByName("MongoDB"),
                                getSubjectByName("Spring Security")))
                ),
                new Student(
                        sequence(INDEX_TYPE_STUDENT),
                        "YuHari",
                        Status.valueOf("Red"),
                        new ArrayList<>(Arrays.asList(getSubjectByName("Java"),
                                getSubjectByName("객체지향"),
                                getSubjectByName("Spring"),
                                getSubjectByName("Redis"),
                                getSubjectByName("Spring Security")))
                ),
                new Student(
                        sequence(INDEX_TYPE_STUDENT),
                        "HongGilDong",
                        Status.valueOf("Red"),
                        new ArrayList<>(Arrays.asList(getSubjectByName("Java"),
                                getSubjectByName("MySQL"),
                                getSubjectByName("JPA"),
                                getSubjectByName("디자인 패턴"),
                                getSubjectByName("Spring Security")))
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
                        ))
                ))
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
            System.out.println("3. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> flag = false; // 메인 화면 이동
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

                    //데이터 넣어주기.
                    Subject subject = new Subject(subjectStore.get(i).getSubjectId(),
                            subjectStore.get(i).getSubjectName(),
                            subjectStore.get(i).getSubjectType());
                    tempSubjectStore.add(subject);
//scoreStore.add(new Score(sequence(),학생아이디, 과목 아이디))
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

        Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName, Status.valueOf("Green"), tempSubjectStore);
        studentStore.add(student);

        System.out.println("수강생 등록 성공!\n");
    }

    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");

        // studentStore 리스트에서 학생 정보 받아서 목록 출력 + 순서대로 번호 부여
        for (int i = 0; i < studentStore.size(); i++) {
            System.out.println((i + 1) + ". 고유번호: " + studentStore.get(i).getStudentId() + ", 이름: " + studentStore.get(i).getStudentName());
        }
        System.out.println("\n수강생 목록 조회 성공!");
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
            System.out.println("5. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> inquireAverageGrade(); // 수강생의 특정 과목 평균등급 조회
                case 5 -> flag = false; // 메인 화면 이동
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

        while (true) {
            // 필수/선택 과목타입 선택
            System.out.print("필수/선택 과목인지 선택하세요.(필수 : 1, 선택 : 2을 입력하세요)\n입력 : ");
            int subType = sc.nextInt();
            // 필수 과목 조건문
            if (subType == 1) {
                for (int i = 0; i < subjectStore.size(); i++) {
                    Subject subject = subjectStore.get(i);
                    if (subject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY)) {
                        System.out.println("필수과목 List");
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
                        if (round > 0 && round < 11) {
                            System.out.println("과목점수를 입력하세요.");
                            int scoreInput = sc.nextInt();
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
                for (int i = 0; i < subjectStore.size(); i++) {
                    Subject subject = subjectStore.get(i);
                    if (subject.getSubjectType().equals(SUBJECT_TYPE_CHOICE)) {
                        System.out.println("선택과목 List");
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
                        if (round > 0 && round < 11) {
                            System.out.println("과목점수를 입력하세요.");
                            int scoreInput = sc.nextInt();
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


    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (수정할 과목 및 회차, 점수)
        System.out.println("시험 점수를 수정합니다...");
        // 기능 구현
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
            for (ScoreDatail score : scoreList) System.out.println(score.getRound() + "회차 : " + score.getGrade());
            System.out.println("\n등급 조회 성공!");
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
}
