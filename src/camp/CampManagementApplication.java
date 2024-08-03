package camp;

import camp.model.*;

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
                        Status.valueOf("Yellow")
                ),
                new Student(
                        sequence(INDEX_TYPE_STUDENT),
                        "Test",
                        Status.valueOf("Green")
                )
        ));
        //조회를 위해 점수리스트를 임의로 생성
        scoreStore = new ArrayList<>(Arrays.asList(
            new Score(
                sequence(INDEX_TYPE_SCORE),
                "ST1",
                "SU1",
                new ArrayList<ScoreDatail>(Arrays.asList(
                    new ScoreDatail(1,78, getSubjectTypeByName("Java")),
                    new ScoreDatail(2,98, getSubjectTypeByName("Java")),
                    new ScoreDatail(3,86, getSubjectTypeByName("Java"))
                ))
            ),
            new Score(
                sequence(INDEX_TYPE_SCORE),
                "ST1",
                "SU2",
                new ArrayList<ScoreDatail>(Arrays.asList(
                    new ScoreDatail(1,96, getSubjectTypeByName("객체지향")),
                    new ScoreDatail(2,98, getSubjectTypeByName("객체지향")),
                    new ScoreDatail(3,92, getSubjectTypeByName("객체지향"))
                ))
            ),
            new Score(
                sequence(INDEX_TYPE_SCORE),
                "ST2",
                "SU1",
                new ArrayList<ScoreDatail>(Arrays.asList(
                    new ScoreDatail(1,78, getSubjectTypeByName("Java")),
                    new ScoreDatail(2,68, getSubjectTypeByName("Java")),
                    new ScoreDatail(3,86, getSubjectTypeByName("Java"))
                ))
            ),
            new Score(
                sequence(INDEX_TYPE_SCORE),
                "ST2",
                "SU2",
                new ArrayList<ScoreDatail>(Arrays.asList(
                    new ScoreDatail(1,78, getSubjectTypeByName("객체지향")),
                    new ScoreDatail(2,98, getSubjectTypeByName("객체지향")),
                    new ScoreDatail(3,86, getSubjectTypeByName("객체지향"))
                ))
            ),
                new Score(
                        sequence(INDEX_TYPE_SCORE),
                        "ST4",
                        "SU2",
                        new ArrayList<ScoreDatail>(Arrays.asList(
                                new ScoreDatail(1,78, getSubjectTypeByName("객체지향")),
                                new ScoreDatail(2,98, getSubjectTypeByName("객체지향")),
                                new ScoreDatail(3,86, getSubjectTypeByName("객체지향"))
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
            System.out.println("3. 수강생 정보 수정");
            System.out.println("7. 수강생 삭제");
            System.out.println("8. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> updateStudent(); // 수강생 정보 수정
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
        SelectSubjectLoop: // 이미 선택했다면 흐름제어를 통해 돌아오려고 만든 라벨
        while (true) {
            System.out.print("필수 과목 3개 이상, 선택 과목 2개 이상을 선택해주세요. 과목 이름으로 적어주세요.");
            String subjectName = sc.nextLine();
            boolean bFindSubject = false; // 엉뚱한 입력했을 수도 있으니 Try Catch 로 바꿀 필요성

            for (int i = 0; i < subjectStore.size(); i++) {
                if (subjectStore.get(i).getSubjectName().equals(subjectName)) {
                    //고른 필수 과목, 선택 과목 수 늘려주기
                    if (subjectStore.get(i).getSubjectType().equals(SUBJECT_TYPE_MANDATORY)) mandatoryNums++;
                    else choiceNums++;

                    for(int j = 0; j < tempSubjectStore.size(); j++){
                        if(tempSubjectStore.get(j).getSubjectName().equals(subjectName)){
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
        for(int i = 0; i < tempSubjectStore.size(); i++){
            //IndexScore 를 제외하고 학생 ID 는 이미 sequence 를 이용해서 올라갔을테니 따로 안올려주고 조합해서 보내준다.
            scoreStore.add(new Score(sequence(INDEX_TYPE_SCORE), INDEX_TYPE_STUDENT + studentIndex, subjectStore.get(i).getSubjectId()));
        }

        System.out.println("수강생 등록 성공!\n");
    }


    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        // studentStore 리스트에서 학생 정보 받아서 목록 출력 + 순서대로 번호 부여
        for(int i = 0; i < studentStore.size(); i++) {
            System.out.println((i+1) +". 고유번호: "+ studentStore.get(i).getStudentId() + ", 이름: " +studentStore.get(i).getStudentName()
                    + ", 상태: " + studentStore.get(i).getStatus());
        }
        System.out.println("\n수강생 목록 조회 성공!");
    }


    // 수강생 정보 수정
    private static void updateStudent() {
        System.out.println("\n수강생 정보를 수정합니다...");
        // 수강생의 고유번호를 받아 이름, 상태를 수정
        while(true) {
            System.out.print("수강생의 고유번호 입력 : ");
            String insertNumber = sc.next();
            boolean bFindName = false;
            // studentStore 에 입력한 고유번호이랑 같은 고유번호가 있다면 고유번호, 이름, 상태를 출력
            for (int i = 0; i < studentStore.size(); i++) {
                if (insertNumber.equals(studentStore.get(i).getStudentId())) {
                    System.out.println("고유번호: " + studentStore.get(i).getStudentId() + ", 이름: " + studentStore.get(i).getStudentName()
                            + ", 상태: " + studentStore.get(i).getStatus());
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

            if (updateNumber == 1) { //이름 변경
                System.out.print("무엇으로 이름을 변경하시겠습니까? : ");
                String updateName = sc.next();

                //studentStore 에서 입력한 이름값이랑 같은 것을 updateName 이름으로 바꿔줘!
                for (int i = 0; i < studentStore.size(); i++) {
                    if (insertNumber.equals(studentStore.get(i).getStudentId())) {
                        studentStore.get(i).setStudentName(updateName);
                    }
                }
                //잘 변경되나 확인 프린트
                for (int i = 0; i < studentStore.size(); i++) {
                    if (updateName.equals(studentStore.get(i).getStudentName())) {
                        System.out.println("고유번호: " + studentStore.get(i).getStudentId() + ", 이름: " + studentStore.get(i).getStudentName()
                                + ", 상태: " + studentStore.get(i).getStatus());
                    }
                }

                System.out.println("\n수강생 정보 수정 성공!");
                break;

            } else if (updateNumber == 2) { //상태 변경
                System.out.print("무엇으로 상태를 변경하시겠습니까? : ");
                String updateStatus = sc.next();

                for (int i = 0; i < studentStore.size(); i++) {
                    if (insertNumber.equals(studentStore.get(i).getStudentId())) {
                        studentStore.get(i).setStatus(Status.valueOf(updateStatus));
                    }
                }

                System.out.println("\n수강생 정보 수정 성공!");
                break;

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
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        System.out.println("시험 점수를 등록합니다...");
        // 기능 구현
        //score.add(new ScoreDetail())
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
            .filter((Score score)->score.getStudentId().equals(studentId))
            .filter((Score score)->score.getSubjectId().equals(subjectId))
            .findFirst();
        //만약 있다면
        if(selectScore.isPresent()) {
            List<ScoreDatail> scoreList = selectScore.get().getScoreList();
            for (ScoreDatail score : scoreList) System.out.println(score.getRound()+"회차 : "+score.getGrade());
            System.out.println("\n등급 조회 성공!");
        }else {
            System.out.println("\n등급 조회 실패! 다시 시도해주세요.");
        }
    }
    //수강생 이름으로 아이디 찾기
    //이름이 같을 경우도 고려해야함
    private static String getStudentIdByName(){
        System.out.print("\n관리할 수강생의 이름을 입력하시오...");
        String studentName = sc.next();
        String subjectId = "";
        //해당 이름을 가진 수강생 데이터 리스트
        List<Student> selectStudent = studentStore.stream()
            .filter((Student student)->student.getStudentName().equals(studentName))
            .toList();
        //만약 동명이인 있다면
        if(selectStudent.size()>1) {
            //목록을 보여줄테니
            for(Student eachStudent: selectStudent) {
                System.out.println("이름 : "+eachStudent.getStudentName()+"\t 아이디:"+eachStudent.getStudentId());
            }
            //이중에 누구인지 골라라
            System.out.println("조회할 수강생의 아이디 입력");
            return sc.next();
        }
        //이름이 같은 사람이 없다면 해당 수강생의 아이디 출력
        else if(selectStudent.size()==1) return selectStudent.get(0).getStudentId();
        //해당 이름을 가진 수강생이 없다면 NoName 출력
        else return "NoName";
    }
    //과목 이름으로 아이디 찾기
    private static String getSubjectIdByName(){
        System.out.print("\n관리할 과목의 이름을 입력하시오...");
        String subjectName = sc.next();
        //입력값에 해당하는 subject 데이터 찾기
        Optional<Subject> selectSubject = subjectStore.stream()
            .filter((Subject subject)->subject.getSubjectName().equals(subjectName))
            .findFirst();
        //있으면 해당 과목의 아이디 출력
        if(selectSubject.isPresent()) return selectSubject.get().getSubjectId();
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
            .filter((Score score)->score.getStudentId().equals(studentId))
            .filter((Score score)->score.getSubjectId().equals(subjectId))
            .findFirst();
        if(selectScore.isPresent()) {
            Score score = selectScore.get();
            //해당하는 과목의 subjectType 구하기("필수 or 선택)
            String subjectType = subjectStore.stream()
                .filter((Subject subject) -> subject.getSubjectId().equals(subjectId))
                .findFirst().get().getSubjectType();
            //점수의 평균값 얻기
            List<ScoreDatail> scoreList = score.getScoreList();
            int sum = 0;
            for(ScoreDatail scoreDetail:scoreList) {
                sum+=scoreDetail.getScore();
            }
            double avgScore= sum/scoreList.size();
            // 평균 점수를 등급으로 바꿔줌
            System.out.println("이 과목의 평균등급은 "+ ScoreDatail.changeGrade(subjectType,avgScore)+"입니다.");

            System.out.println("\n등급 조회 성공!");
        }else {
            System.out.println("\n등급 조회 실패! 다시 시도해주세요.");
        }
    }

    //초기값 생성에서 수강생의 과목 리스트를 쉽게 만들기 위한 함수 -> 나중에 지울 예정이라 신경안써도 됨
    public static  Subject getSubjectByName(String subjectName){
      Optional<Subject> selectSubject = subjectStore.stream()
            .filter((Subject subejct)->subejct.getSubjectName().equals(subjectName))
            .findFirst();
      if(selectSubject.isPresent()){
          Subject subject = selectSubject.get();
          return subject;
      }
      else return new Subject("SU0","NoSubject","MANDATORY");
    }
    //초기값 생성에서 과목 이름으로 과목타입을 구하는 함수
    public static String getSubjectTypeByName(String subjectName) {
        Optional<Subject> selectSubject = subjectStore.stream()
            .filter((Subject subejct)->subejct.getSubjectName().equals(subjectName))
            .findFirst();
        if(selectSubject.isPresent()) return selectSubject.get().getSubjectType();
        else return "NoSubject";
    }


    //특정 상태 수강생들의 필수 과목 평균 등급 조회
    public static void inquireAverageGradeByStatus(){
        System.out.println("찾으실 수강생들의 상태를 입력하세요");
        String inputStatus = sc.next();
        Status status = Status.Green;

        switch (inputStatus){
            case "Green":
                status = Status.Green;
                break;
            case "Yellow" :
                status = Status.Yellow;
                break;
            case "Red" :
                status = Status.Red;
                break;
            default:
                System.out.println("해당하는 상태값은 없습니다.");
                return;
        }

        //클래스화 시킬때 Stream 화 시키기
        boolean found = false;
        for(Student student : studentStore){ //학생들 다 뒤져야하니 전부 찾기
            if(student.getStatus() == status){ //등록받은 상태값이 같다면
                int tempScore = 0;
                int totalScoreNums = 0;
                for(Score score : scoreStore){ //점수 저장공간도 탐색해준다.
                    if(!score.getStudentId().equals(student.getStudentId())) continue; //만약 학생 ID 가 일치하지 않으면 탐색할필요없으니 넘어가주고..
                    for(Subject subject : subjectStore){ // 일치한다면 이제 과제 저장 목록들하고 비교하면서 필수과목인지, 등록된 과목하고 같은지 비교해준다.
                        if(subject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY) &&
                                subject.getSubjectId().equals(score.getSubjectId())){
                            if(score.getScoreList().isEmpty()) break; //처음 만들었을 때 생성되는 회차, 점수 없는 학생 예외처리

                            for(ScoreDatail scoreDatail : score.getScoreList()){ //회차 있으니 탐색해준다.
                                found = true;
                                tempScore += scoreDatail.getScore();
                                totalScoreNums++;
                            }
                        }
                    }
                }
                if(found && totalScoreNums != 0) { //회차 돌았는데 0점 맞은 학생도 있을수있으니 나누기 연산 터지는거 대비해서 미리 0 체크
                    tempScore /= totalScoreNums;
                    System.out.println(student.getStudentName() + " 의 필수과목 평균 등급은 " + ScoreDatail.changeGrade("MANDATORY", tempScore)  + " 입니다.");
                }
            }
        }
        if(!found) System.out.println("해당 상태의 학생이 존재하지 않습니다.");
    }


    //수강생 지우기
    public static void removeStudent(){
        System.out.println("삭제할 수강생의 고유 번호를 입력하세요");
        String studentId = sc.next();

        //지운게 성공한적 체크를 위해 boolean 형 지역변수 선언
        boolean successRemove = false;
        for(Student student : studentStore){
            if(student.getStudentId().equals(studentId)){
                studentStore.remove(student);
                //지우기 성공, 어차피 학생 ID 는 유일하니 바로 Break;
                successRemove = true;
                break;
            }
        }

        //점수 저장 공간에서도 삭제
        //그냥 Index 0 으로 하고 지워주면 삭제하면서 ArrayList 배열 땡겨져서 안됨, 뒤에서부터 지워야 배열 안변함
        for(int i = scoreStore.size() - 1; i >= 0; i--){
            if(scoreStore.get(i).getStudentId().equals(studentId)){
                scoreStore.remove(i);
            }
        }

        if(successRemove) System.out.println("성공적으로 삭제 되었습니다.");
        else System.out.println("그런 수강생 없습니다");
    }

}
