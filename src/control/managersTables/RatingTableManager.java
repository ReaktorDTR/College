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
        localStorage.getRatingsTable().add(rating);
    }

    public void addRating(int idStudent, int idSubject, int mark) {
        boolean isUpdated = false;
        Rating newRating = new Rating();
        newRating.setIdStudent(idStudent);
        newRating.setIdSubject(idSubject);
        newRating.setMark(mark);
        if (!localStorage.getRatingsTable().contains(newRating)) {
            for (Rating rating : localStorage.getRatingsTable()) {
                if (rating.getIdStudent() == idStudent && rating.getIdSubject() == idSubject) {
                    rating.setMark(mark);
                    isUpdated = true;
                    break;
                }
            }
            if (!isUpdated) {
                localStorage.incrementIdRating();
                newRating.setIdRating(localStorage.getIdRating());
                addRating(newRating);
            }
        }
    }

    public void addRatingsToGroup() {
        if (!localStorage.getSubjectsTable().isEmpty()) {
            int idSubject = subjectTableManager.selectIdSubject();
            if (!localStorage.getGroupsTable().isEmpty()) {
                int idGroup = groupTableManager.selectIdGroup();
                System.out.println("Input ratings: ");
                for (Student student : localStorage.getStudentsTable()) {
                    if (student.getIdGroup() == idGroup) {
                        System.out.println("Student='" + student.getFirstName() + " " + student.getLastName() + "' Group=" + groupTableManager.getGroupNameById(idGroup));
                        String input = KeyboardInput.inputValidQueryData("mark for " + subjectTableManager.getSubjectNameById(idSubject), Validation.MARK_PATTERN);
                        if (input.equals("!e")) break;
                        int mark = Integer.parseInt(input);
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

    public void addRatingsToStudent() {
        if (!localStorage.getSubjectsTable().isEmpty()) {
            int idSubject = subjectTableManager.selectIdSubject();
            if (!localStorage.getGroupsTable().isEmpty()) {
                int idGroup = groupTableManager.selectIdGroup();
                int idStudent = studentTableManager.selectIdStudent(idGroup);
                Student student = studentTableManager.getStudentById(idStudent);
                System.out.println("Input rating: ");
                if (student.getIdGroup() == idGroup) {
                    System.out.println("Student='" + student.getFirstName() + " " + student.getLastName() + "' Group=" + groupTableManager.getGroupNameById(idGroup));
                    String input = KeyboardInput.inputValidQueryData("mark for " + subjectTableManager.getSubjectNameById(idSubject), Validation.MARK_PATTERN);
                    if (!input.equals("!e")) {
                        int mark = Integer.parseInt(input);
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
        if (isStudentContainRating(student)) {
            System.out.println("Student='" + studentTableManager.getStudentFullNameById(student.getIdStudent()) + "' Group=" + studentTableManager.getStudentGroupNameById(student.getIdStudent()));
            for (Rating rating : localStorage.getRatingsTable()) {
                if (rating.getIdStudent() == student.getIdStudent()) {
                    System.out.println("ID=" + rating.getIdRating() + " Subject=" + subjectTableManager.getSubjectNameById(rating.getIdSubject()) + " Mark=" + rating.getMark());
                }
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

    public void outTableRating() {
        System.out.println("Table rating (a - All, g - Group, s - Student, !e - Exit)");
        while (true) {
            System.out.print("key table rating: ");
            String input = KeyboardInput.input();
            switch (input) {
                case "a": {
                    for (Group group : localStorage.getGroupsTable()) {
                        outTableRating(group);
                    }
                    break;
                }
                case "g": {
                    int idGroup = groupTableManager.selectIdGroup();
                    outTableRating(groupTableManager.getGroupById(idGroup));
                    break;
                }
                case "s": {
                    int idStudent = studentTableManager.selectIdStudent();
                    outTableRating(studentTableManager.getStudentById(idStudent));
                }
            }
            if (input.equals("!e")) break;
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

    private boolean isStudentContainRating(Student student) {
        for (Rating rating : localStorage.getRatingsTable()) {
            if (rating.getIdStudent() == student.getIdStudent()) {
                return true;
            }
        }
        return false;
    }
}
