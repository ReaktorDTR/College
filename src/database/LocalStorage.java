package database;

import entity.Group;
import entity.Rating;
import entity.Student;
import entity.Subject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Reaktor on 10.12.2014.
 */
public class LocalStorage {
    private Map<Integer,Group> groupsTable = new LinkedHashMap<>();
    private Map<Integer,Student> studentsTable = new LinkedHashMap<>();
    private Map<Integer,Subject> subjectsTable = new LinkedHashMap<>();
    private Map<Integer,Rating> ratingsTable = new LinkedHashMap<>();

    public Map<Integer, Group> getGroupsTable() {
        return groupsTable;
    }

    public void setGroupsTable(Map<Integer, Group> groupsTable) {
        this.groupsTable = groupsTable;
    }

    public Map<Integer, Student> getStudentsTable() {
        return studentsTable;
    }

    public void setStudentsTable(Map<Integer, Student> studentsTable) {
        this.studentsTable = studentsTable;
    }

    public Map<Integer, Subject> getSubjectsTable() {
        return subjectsTable;
    }

    public void setSubjectsTable(Map<Integer, Subject> subjectsTable) {
        this.subjectsTable = subjectsTable;
    }

    public Map<Integer, Rating> getRatingsTable() {
        return ratingsTable;
    }

    public void setRatingsTable(Map<Integer, Rating> ratingsTable) {
        this.ratingsTable = ratingsTable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocalStorage that = (LocalStorage) o;

        if (groupsTable != null ? !groupsTable.equals(that.groupsTable) : that.groupsTable != null) return false;
        if (ratingsTable != null ? !ratingsTable.equals(that.ratingsTable) : that.ratingsTable != null) return false;
        if (studentsTable != null ? !studentsTable.equals(that.studentsTable) : that.studentsTable != null)
            return false;
        if (subjectsTable != null ? !subjectsTable.equals(that.subjectsTable) : that.subjectsTable != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = groupsTable != null ? groupsTable.hashCode() : 0;
        result = 31 * result + (studentsTable != null ? studentsTable.hashCode() : 0);
        result = 31 * result + (subjectsTable != null ? subjectsTable.hashCode() : 0);
        result = 31 * result + (ratingsTable != null ? ratingsTable.hashCode() : 0);
        return result;
    }
}
