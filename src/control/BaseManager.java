package control;

import database.LocalStorage;
import entity.Group;
import entity.Rating;
import entity.Student;
import entity.Subject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;

/**
 * Created by Reaktor on 10.12.2014.
 */
//todo Рознести по підкласам методи роботи з різнимим таблицяими
public class BaseManager {
    private LocalStorage localStorage = new LocalStorage();


/*




    private void addRating(Rating rating) {
        localStorage.getRatingsTable().contains(rating);
    }

    public void addRating(int idStudent, int idSubject, int mark) {
        Rating newRating = new Rating();
        newRating.setIdStudent(idStudent);
        newRating.setIdSubject(idSubject);
        newRating.setMark(mark);
        if (!localStorage.getRatingsTable().contains(newRating)) {
            idRating = idRating + 1;
            newRating.setIdRating(idRating);
            addRating(newRating);
        }
    }

    public void addRatingToGroup() {
        int idSubject = selectIdSubject();
        if (!localStorage.getGroupsTable().isEmpty()) {
            int idGroup = selectIdGroup();
            if (!isGroupEmpty(idGroup)) {
                for (Entry<Integer, Student> entry : localStorage.getStudentsTable().entrySet()) {
                    Integer key = entry.getKey();
                    Student value = entry.getValue();
                    if (value.getIdGroup() == idGroup) {
                        System.out.println("ID=" + key + " Student='" + value.getFirstName() + " " + value.getLastName() + "\' Group=" + localStorage.getGroupsTable().get(idGroup));
                        int mark = Integer.parseInt(input("mark for " + localStorage.getSubjectsTable().get(idSubject),Validation.MARK_PATTERN));
                        addRating(key, idSubject, mark);
                    }
                }
            } else {
                System.out.println("Add some students");
                addStudents();
                addRatingToGroup();
            }
        } else {
            System.out.println("Add some groups");
            addGroups();
            addStudents();
        }
    }

   public void outTableRating(int idStudent) {
        for (Entry<Integer, Rating> entry : localStorage.getRatingsTable().entrySet()) {
            Integer key = entry.getKey();
            Rating value = entry.getValue();
            if (value.getIdStudent() == idStudent) {
                System.out.println("ID=" + key + localStorage.getStudentsTable().get(value.getIdStudent()) + " " + value.getLastName() + "\' Group=" + localStorage.getGroupsTable().get(idGroup));
            }
        }
    }

    private boolean isGroupEmpty(int idGroup) {
        for (Entry<Integer, Student> entry : localStorage.getStudentsTable().entrySet()) {
            Integer key = entry.getKey();
            Student value = entry.getValue();
            if (value.getIdGroup() == idGroup) {
                return false;
            }
        }
        return true;
    }



    private boolean isContainId(List<Student> list, int id) {
        for (Student student : list) {
            if (student.getIdStudent() == id) {
                return true;
            }
        }
        return false;
    }



    private boolean isContainId(List<Rating> list, int id) {
        for (Rating rating : list) {
            if (rating.getIdRating() == id) {
                return true;
            }
        }
        return false;
    }*/

}
