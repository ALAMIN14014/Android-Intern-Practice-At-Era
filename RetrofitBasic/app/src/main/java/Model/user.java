package Model;


public class user {

    public String name;
    public String university;

    /**
     * No args constructor for use in serialization
     *
     */
    public user() {
    }

    /**
     *
     * @param university
     * @param name
     */
    public user(String name, String university) {
        super();
        this.name = name;
        this.university = university;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

}
