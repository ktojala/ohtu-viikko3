package ohtu;

import java.util.Arrays;

public class CourseInfo {

    private String name;
    private String fullName;
    private int[] exercises;

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    public int[] getExercises() {
        return exercises;
    }
    
    public void setFullName(String longname) {
        this.fullName = longname;
    }

    public String getFullName() {
        return fullName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int tehtSum() {
        return Arrays.stream(exercises).sum();
    }

}