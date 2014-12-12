package control.managersTables;

import database.LocalStorage;
import entity.Group;
import entity.Rating;
import entity.Student;
import userInterface.KeyboardInput;
import userInterface.Validation;

/**
 * Created by sdovhtc on 12/11/2014.
 */
public class StudentTableManager {
    private LocalStorage localStorage;
    GroupTableManager groupTableManager;

    public StudentTableManager(LocalStorage localStorage) {
        this.localStorage = localStorage;
        this.groupTableManager = new GroupTableManager(localStorage);
    }

    private void addStudent(Student student) {
        localStorage.getStudentsTable().add(student);
    }

    public void addStudent(String firstName, String lastName, int idGroup) {
        Student newStudent = new Student();
        newStudent.setFirstName(firstName);
        newStudent.setLastName(lastName);
        newStudent.setIdGroup(idGroup);
        if (!localStorage.getStudentsTable().contains(newStudent)) {
            localStorage.incrementIdStudent();
            newStudent.setIdStudent(localStorage.getIdStudentCounter());
            addStudent(newStudent);
        }
    }

    public void addStudents() {
        System.out.println("Input students :");
        if (!localStorage.getGroupsTable().isEmpty()) {
            int idGroup = groupTableManager.selectIdGroup();
            System.out.println("Input students: ");
            while (true) {
                String inputQuery = "student first name";
                String firstName = KeyboardInput.inputValidQueryData(inputQuery, Validation.FIRST_NAME_PATTERN);
                if (firstName.equals("!e")) break;

                inputQuery = "student last name";
                String lastName = KeyboardInput.inputValidQueryData(inputQuery, Validation.LAST_NAME_PATTERN);
                if (lastName.equals("!e")) break;
                addStudent(firstName, lastName, idGroup);
                System.out.println("Student added");
            }
        } else {
            System.out.println("Add some groups");
            groupTableManager.addGroups();
            addStudents();
        }
    }

    public int selectIdStudent() {
        int idGroup = groupTableManager.selectIdGroup();
        return selectIdStudent(idGroup);
    }

    public int selectIdStudent(int idGroup) {
        String inputQuery = "student";
        while (true) {
            outTableStudents(idGroup);
            int selectedId = KeyboardInput.selectId(inputQuery);
            if (isContainId(selectedId, idGroup)) {
                return selectedId;
            } else {
                System.out.println("Student ID=" + selectedId + ", not found.");
            }
        }
    }

    public void outTableStudents() {
        for (Student student : localStorage.getStudentsTable()) {
            System.out.println(student + " Group=" + groupTableManager.getGroupNameById(student.getIdGroup()));
        }
    }

    public void outTableStudents(int idGroup) {
        for (Student student : localStorage.getStudentsTable()) {
            if (student.getIdGroup() == idGroup) {
                System.out.println(student + " Group=" + groupTableManager.getGroupNameById(student.getIdGroup()));
            }
        }
    }

    private boolean isContainId(int idStudent, int idGroup) {
        for (Student student : localStorage.getStudentsTable()) {
            if (student.getIdStudent() == idStudent && student.getIdGroup() == idGroup) {
                return true;
            }
        }
        return false;
    }

    private boolean isContainId(int idStudent) {
        for (Student student : localStorage.getStudentsTable()) {
            if (student.getIdStudent() == idStudent) {
                return true;
            }
        }
        return false;
    }

    public String getStudentFullNameById(int idStudent) {
        Student student = getStudentById(idStudent);
        return student.getFirstName() + " " + student.getLastName();
    }

    public String getStudentGroupNameById(int idStudent) {
        return groupTableManager.getGroupNameById(getStudentById(idStudent).getIdGroup());
    }

    public Student getStudentById(int idStudent) {
        if (isContainId(idStudent)) {
            for (Student student : localStorage.getStudentsTable()) {
                if (student.getIdStudent() == idStudent) {
                    return student;
                }
            }
        }
        return null;
    }
}
