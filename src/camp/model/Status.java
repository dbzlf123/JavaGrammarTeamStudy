package camp.model;

public enum Status {
    Green("Green"),
    Yellow("Yellow"),
    Red("Red");

    private final String status;

    Status(String status){
        this.status = status;
    }

    public static Status checkType(String status){
        for(Status st : Status.values()){
            if(st.status.equals(status)){
                return st;
            }
        }
        return null;
    }
}


