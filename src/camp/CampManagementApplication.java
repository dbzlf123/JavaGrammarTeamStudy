package camp;

import camp.CRUDOperations.Create;
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
    public static String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    public static String SUBJECT_TYPE_CHOICE = "CHOICE";

    // index 관리 필드
    public static int studentIndex;
    public static final String INDEX_TYPE_STUDENT = "ST";
    public static int subjectIndex;
    public static final String INDEX_TYPE_SUBJECT = "SU";
    public static int scoreIndex;
    public static final String INDEX_TYPE_SCORE = "SC";

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
    public static void setInitData() {
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
    public static String sequence(String type) {
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
                case 1 -> Create.createStudent(); // 수강생 등록
                case 2 -> Inquire.inquireStudent(studentStore); // 수강생 목록 조회
                case 3 -> Inquire.inquireStudentInfo(studentStore); //수강생 정보 조회
                case 4 -> Edit.updateStudent(); // 수강생 정보 수정
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
            System.out.println("6. 특정 상태 수강생들의 필수 과목 평균 등급 조회");
            System.out.println("7. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = 0;
            try {    //nextInt에 문자열이 입력됬을때를 대비
                input = sc.nextInt();
            } catch (Exception e) {
                System.out.println("숫자로 입력해주세요");
                sc.nextLine();
            }

            switch (input) {

                case 1 -> Create.createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> Edit.updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> Inquire.inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> Inquire.inquireAverageGrade(subjectStore); // 수강생의 특정 과목 평균등급 조회
                case 5 -> Edit.roundScoreList();  //특정 과목 특정 회차 점수 순위
                case 6 -> Inquire.inquireAverageGradeByStatus(studentStore, subjectStore, scoreStore); // 특정 상태 수강생들의 필수과목 평균등급 조회
                case 7 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }
}
