package control.managersTables;

import database.LocalStorage;
import entity.Group;
import entity.Rating;
import entity.Student;
import entity.Subject;
import userInterface.KeyboardInput;
import userInterface.Validation;

import java.util.Iterator;

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
                newRating.setIdRating(localStorage.getIdRatingCounter());
                addRating(newRating);
            }
        }
    }

    public void addRatingsToGroup() {
        int idGroup = groupTableManager.selectIdGroup();
        if (idGroup != -1) {
            if (studentTableManager.isGroupContainStudents(idGroup)) {
                if (!localStorage.getSubjectsTable().isEmpty()) {
                    int idSubject = subjectTableManager.selectIdSubject();
                    if (idSubject != -1) {
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
                    }
                } else {
                    System.out.println("Add some subjects");
                    subjectTableManager.addSubjects();
                    addRatingsToGroup();
                }
            } else {
                System.out.println("Add some students");
                studentTableManager.addStudents();
                addRatingsToGroup();
            }
        }
    }

    public void addRatingsToStudent() {
        int idStudent = studentTableManager.selectIdStudent();
        if (idStudent != -1) {
            if (!localStorage.getSubjectsTable().isEmpty()) {
                int idSubject = subjectTableManager.selectIdSubject();
                if (idSubject != -1) {
                    Student student = studentTableManager.getStudentById(idStudent);
                    System.out.println("Input rating: ");
                    System.out.println("Student='" + student.getFirstName() + " " + student.getLastName() + "' Group=" + groupTableManager.getGroupNameById(student.getIdGroup()));
                    String input = KeyboardInput.inputValidQueryData("mark for " + subjectTableManager.getSubjectNameById(idSubject), Validation.MARK_PATTERN);
                    if (!input.equals("!e")) {
                        int mark = Integer.parseInt(input);
                        addRating(student.getIdStudent(), idSubject, mark);
                    }
                }
            } else {
                System.out.println("Add some subjects");
                subjectTableManager.addSubjects();
                addRatingsToGroup();
            }
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
        } else {
            System.out.println("Student='" + studentTableManager.getStudentFullNameById(student.getIdStudent()) + "' Group=" + studentTableManager.getStudentGroupNameById(student.getIdStudent()));
            System.out.println("Student doesn't have any rank");
        }
    }

    public void outTableRating(Group group) {
        if (isGroupContainRating(group)) {
            for (Student student : localStorage.getStudentsTable()) {
                if (student.getIdGroup() == group.getIdGroup()) {
                    outTableRating(student);
                }
            }
        } else {
            System.out.println();
            System.out.println("Group='" + group.getGroupName() + "' doesn't have any rank");
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

    public void updateRating(int idRating, int idStudent, int idSubject, int mark) {
        Rating rating = getRatingById(idRating);
        rating.setIdStudent(idStudent);
        rating.setIdSubject(idSubject);
        rating.setMark(mark);
    }

    private boolean isContainId(int idRating) {
        for (Rating rating : localStorage.getRatingsTable()) {
            if (rating.getIdRating() == idRating) {
                return true;
            }
        }
        return false;
    }

    private boolean isContainId(int idRating, int idStudent) {
        for (Rating rating : localStorage.getRatingsTable()) {
            if (rating.getIdRating() == idRating && rating.getIdStudent() == idStudent) {
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

    private boolean isGroupContainRating(Group group) {
        for (Student student : localStorage.getStudentsTable()) {
            if (student.getIdGroup() == group.getIdGroup()) {
                if (isStudentContainRating(student)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int selectIdRating() {
        int idStudent = studentTableManager.selectIdStudent();
        return selectIdRating(idStudent);
    }

    public int selectIdRating(int idStudent) {
        String inputQuery = "rating";
        if (isStudentContainRating(studentTableManager.getStudentById(idStudent))) {
            while (true) {
                outTableRating(studentTableManager.getStudentById(idStudent));
                int selectedId = KeyboardInput.selectId(inputQuery);
                if (selectedId != -1) {
                    if (isContainId(selectedId, idStudent)) {
                        return selectedId;
                    } else {
                        System.out.println("Rating ID=" + selectedId + ", not found.");
                    }
                } else return -1;
            }
        }
        return -1;
    }

    public Rating getRatingById(int idRating) {
        if (isContainId(idRating)) {
            for (Rating rating : localStorage.getRatingsTable()) {
                if (rating.getIdRating() == idRating) {
                    return rating;
                }
            }
        }
        return null;
    }

    public void removeRating() {
        int idRating = selectIdRating();
        if (idRating != -1) {
            removeRating(idRating);
        }
    }

    public void removeRating(int idRating) {
        Iterator<Rating> iteratorRating = localStorage.getRatingsTable().iterator();
        while (iteratorRating.hasNext()) {
            Rating rating = iteratorRating.next();
            if (rating.getIdRating() == idRating) {
                iteratorRating.remove();
            }
        }
    }

    public void removeRating(Student student) {
        Iterator<Rating> iteratorRating = localStorage.getRatingsTable().iterator();
        while (iteratorRating.hasNext()) {
            Rating rating = iteratorRating.next();
            if (rating.getIdStudent() == student.getIdStudent()) {
                iteratorRating.remove();
            }
        }
    }

    public void removeRating(Subject subject) {
        Iterator<Rating> iteratorRating = localStorage.getRatingsTable().iterator();
        while (iteratorRating.hasNext()) {
            Rating rating = iteratorRating.next();
            if (rating.getIdSubject() == subject.getIdSubject()) {
                iteratorRating.remove();
            }
        }
    }

    public void removeSubject() {
        int idSubject = subjectTableManager.selectIdSubject();
        if (idSubject != -1) {
            removeSubject(idSubject);
        }
    }

    public void removeSubject(int idSubject) {
        Iterator<Subject> iteratorSubject = localStorage.getSubjectsTable().iterator();
        while (iteratorSubject.hasNext()) {
            Subject subject = iteratorSubject.next();
            if (subject.getIdSubject() == idSubject) {
                removeRating(subject);
                iteratorSubject.remove();
            }
        }
    }

    public void removeStudent() {
        int idStudent = studentTableManager.selectIdStudent();
        if (idStudent != -1) {
            removeStudent(idStudent);
        }
    }

    public void removeStudent(int idStudent) {
        Iterator<Student> iteratorStudent = localStorage.getStudentsTable().iterator();
        while (iteratorStudent.hasNext()) {
            Student student = iteratorStudent.next();
            if (student.getIdStudent() == idStudent) {
                removeRating(student);
                iteratorStudent.remove();
            }
        }
    }

    public void removeStudent(Group group) {
        Iterator<Student> iteratorStudent = localStorage.getStudentsTable().iterator();
        while (iteratorStudent.hasNext()) {
            Student student = iteratorStudent.next();
            if (student.getIdGroup() == group.getIdGroup()) {
                removeRating(student);
                iteratorStudent.remove();
            }
        }
    }

    public void removeGroup() {
        int idGroup = groupTableManager.selectIdGroup();
        if (idGroup != -1) {
            removeGroup(idGroup);
        }
    }

    public void removeGroup(int idGroup) {
        Iterator<Group> iteratorGroup = localStorage.getGroupsTable().iterator();
        while (iteratorGroup.hasNext()) {
            Group group = iteratorGroup.next();
            if (group.getIdGroup() == idGroup) {
                removeStudent(group);
                iteratorGroup.remove();
            }
        }
    }
}
