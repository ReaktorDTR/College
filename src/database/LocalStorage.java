package database;

import entity.Group;
import entity.Rating;
import entity.Student;
import entity.Subject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Reaktor on 10.12.2014.
 */
public class LocalStorage implements Serializable {
    private int idGroupCounter = 0;
    private int idStudentCounter = 0;
    private int idSubjectCounter = 0;
    private int idRatingCounter = 0;
    private List<Group> groupsTable = new ArrayList<>();
    private List<Student> studentsTable = new ArrayList<>();
    private List<Subject> subjectsTable = new ArrayList<>();
    private List<Rating> ratingsTable = new ArrayList<>();

    public int getIdGroupCounter() {
        return idGroupCounter;
    }

    public int getIdStudentCounter() {
        return idStudentCounter;
    }

    public int getIdSubjectCounter() {
        return idSubjectCounter;
    }

    public int getIdRatingCounter() {
        return idRatingCounter;
    }

    public void setIdGroupCounter(int idGroupCounter) {
        this.idGroupCounter = idGroupCounter;
    }

    public void setIdStudentCounter(int idStudentCounter) {
        this.idStudentCounter = idStudentCounter;
    }

    public void setIdSubjectCounter(int idSubjectCounter) {
        this.idSubjectCounter = idSubjectCounter;
    }

    public void setIdRatingCounter(int idRatingCounter) {
        this.idRatingCounter = idRatingCounter;
    }

    public List<Group> getGroupsTable() {
        return groupsTable;
    }

    public void setGroupsTable(List<Group> groupsTable) {
        this.groupsTable = groupsTable;
    }

    public List<Student> getStudentsTable() {
        return studentsTable;
    }

    public void setStudentsTable(List<Student> studentsTable) {
        this.studentsTable = studentsTable;
    }

    public List<Subject> getSubjectsTable() {
        return subjectsTable;
    }

    public void setSubjectsTable(List<Subject> subjectsTable) {
        this.subjectsTable = subjectsTable;
    }

    public List<Rating> getRatingsTable() {
        return ratingsTable;
    }

    public void setRatingsTable(List<Rating> ratingsTable) {
        this.ratingsTable = ratingsTable;
    }

    public void incrementIdGroup() {
        idGroupCounter++;
    }

    public void incrementIdStudent() {
        idStudentCounter++;
    }

    public void incrementIdSubject() {
        idSubjectCounter++;
    }

    public void incrementIdRating() {
        idRatingCounter++;
    }
}
