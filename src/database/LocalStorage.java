package database;

import entity.Group;
import entity.Rating;
import entity.Student;
import entity.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Reaktor on 10.12.2014.
 */
public class LocalStorage {
    private int idGroup = 0;
    private int idStudent = 0;
    private int idSubject = 0;
    private int idRating = 0;
    private List<Group> groupsTable = new ArrayList<>();
    private List<Student> studentsTable = new ArrayList<>();
    private List<Subject> subjectsTable = new ArrayList<>();
    private List<Rating> ratingsTable = new ArrayList<>();

    public int getIdGroup() {
        return idGroup;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public int getIdSubject() {
        return idSubject;
    }

    public int getIdRating() {
        return idRating;
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
        idGroup++;
    }

    public void incrementIdStudent() {
        idStudent++;
    }

    public void incrementIdSubject() {
        idSubject++;
    }

    public void incrementIdRating() {
        idRating++;
    }
}
