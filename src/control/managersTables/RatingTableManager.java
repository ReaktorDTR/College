package control.managersTables;

import database.LocalStorage;
import entity.Group;
import entity.Rating;
import entity.Student;
import userInterface.KeyboardInput;
import userInterface.Validation;

/**
 * Created by Reaktor on 11.12.2014.
 */
public class RatingTableManager {
    private LocalStorage localStorage;
    private SubjectTableManager subjectTableManager = new SubjectTableManager(localStorage);
    private StudentTableManager studentTableManager = new StudentTableManager(localStorage);
    private GroupTableManager groupTableManager = new GroupTableManager(localStorage);

    public RatingTableManager(LocalStorage localStorage) {
        this.localStorage = localStorage;
        this.subjectTableManager = new SubjectTableManager(localStorage);
        this.studentTableManager = new StudentTableManager(localStorage);
        this.groupTableManager = new GroupTableManager(localStorage);
    }

    private void addRating(Rating rating) {
        localStorage.getRatingsTable().contains(rating);
    }

    public void addRating(int idStudent, int idSubject, int mark) {
        Rating newRating = new Rating();
        newRating.setIdStudent(idStudent);
        newRating.setIdSubject(idSubject);
        newRating.setMark(mark);
        if (!localStorage.getRatingsTable().contains(newRating)) {
            localStorage.incrementIdRating();
            newRating.setIdRating(localStorage.getIdRating());
            addRating(newRating);
        }
    }

    public void addRatingsToGroup() {
        if (!localStorage.getSubjectsTable().isEmpty()) {
            int idSubject = subjectTableManager.selectIdSubject();
            if (!localStorage.getGroupsTable().isEmpty()) {
                int idGroup = groupTableManager.selectIdGroup();
                System.out.println("Input ratings :");
                for (Student student : localStorage.getStudentsTable()) {
                    if (student.getIdGroup() == idGroup) {
                        System.out.println("Student='" + student.getFirstName() + " " + student.getLastName() + "' Group=" + groupTableManager.getGroupNameById(idGroup));
                        int mark = Integer.parseInt(KeyboardInput.inputValidQueryData("mark for " + subjectTableManager.getSubjectNameById(idSubject), Validation.MARK_PATTERN));
                        addRating(student.getIdStudent(), idSubject, mark);
                    }
                }
            } else {
                System.out.println("Add some groups");
                groupTableManager.addGroups();
                studentTableManager.addStudents();
                addRatingsToGroup();
            }
        } else {
            System.out.println("Add some subjects");
            subjectTableManager.addSubjects();
        }
    }

    public void outTableRating(Student student) {
        System.out.println("Student='" + studentTableManager.getStudentFullNameById(student.getIdStudent()) + "' Group=" + studentTableManager.getStudentGroupNameById(student.getIdStudent()));
        for (Rating rating : localStorage.getRatingsTable()) {
            if (rating.getIdStudent() == student.getIdStudent()) {
                System.out.println("ID=" + rating.getIdRating() + " Subject=" + subjectTableManager.getSubjectNameById(rating.getIdSubject()) + " Mark=" + rating.getMark());
            }
        }
    }

    public void outTableRating(Group group) {
        for (Student student : localStorage.getStudentsTable()) {
            if (student.getIdGroup() == group.getIdGroup()) {
                outTableRating(student);
            }
        }
    }

    private boolean isContainId(int idRating) {
        for (Rating rating : localStorage.getRatingsTable()) {
            if (rating.getIdRating() == idRating) {
                return true;
            }
        }
        return false;
    }
}
