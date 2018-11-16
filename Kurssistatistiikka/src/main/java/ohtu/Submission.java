package ohtu;

import java.util.Arrays;

public class Submission {
    private int week;
    private int hours;
    private int[] exercises;
    private String course;

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

      public void setHours(int hrs) {
        this.hours = hrs;
    }

    public int getHours() {
        return hours;
    }  

      public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    public int[] getExercises() {
        return exercises;
    }
    
      public void setCourse(String course) {
        this.course = course;
    }

    public String getCourse() {
        return course;
    }
    
    @Override
    public String toString() {
        String tuloste = course+", viikko "+week+", tehtäviä tehty "+exercises.length+", tunnit "+hours+", tehdyt tehtävät ";
        String jatko = Arrays.toString(exercises);
        return tuloste+jatko.substring(1,jatko.length()-1);
    }
    
}