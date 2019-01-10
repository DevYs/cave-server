package devy.cave.server.search.model;

public class Person {

    private String peopleNm;
    private String repRoleNm;

    public String getPeopleNm() {
        return peopleNm;
    }

    public void setPeopleNm(String peopleNm) {
        this.peopleNm = peopleNm;
    }

    public String getRepRoleNm() {
        return repRoleNm;
    }

    public void setRepRoleNm(String repRoleNm) {
        this.repRoleNm = repRoleNm;
    }

    @Override
    public String toString() {
        return "Person{" +
                "peopleNm='" + peopleNm + '\'' +
                ", repRoleNm='" + repRoleNm + '\'' +
                '}';
    }
}
