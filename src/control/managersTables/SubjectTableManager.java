package control.managersTables;

import database.LocalStorage;
import entity.Rating;
import entity.Subject;
import userInterface.KeyboardInput;
import userInterface.Validation;

/**
 * Created by sdovhtc on 12/11/2014.
 */
public class SubjectTableManager {
    private LocalStorage localStorage;

    public SubjectTableManager(LocalStorage localStorage) {
        this.localStorage = localStorage;
    }

    private void addSubject(Subject subject) {
        localStorage.getSubjectsTable().add(subject);
    }

    public void addSubject(String subjectName) {
        Subject newSubject = new Subject();
        newSubject.setSubjectName(subjectName);
        if (!localStorage.getSubjectsTable().contains(newSubject)) {
            localStorage.incrementIdSubject();
            newSubject.setIdSubject(localStorage.getIdSubjectCounter());
            addSubject(newSubject);
        }
    }

    public void addSubjects() {
        System.out.println("Input subjects :");
        while (true) {
            String inputQuery = "subject name";
            String SubjectName = KeyboardInput.inputValidQueryData(inputQuery, Validation.SUBJECT_PATTERN);
            if (SubjectName.equals("!e")) break;
            addSubject(SubjectName);
            System.out.println("Subject added");
        }
    }

    public int selectIdSubject() {
        String inputQuery = "subject";
        while (true) {
            outTableSubjects();
            int selectedId = KeyboardInput.selectId(inputQuery);
            if (isContainId(selectedId)) {
                return selectedId;
            } else {
                System.out.println("Subject ID=" + selectedId + ", not found.");
            }
        }
    }

    public void outTableSubjects() {
        for (Subject subject : localStorage.getSubjectsTable()) {
            System.out.println(subject);
        }
    }

    private boolean isContainId(int idSubject) {
        for (Subject subject : localStorage.getSubjectsTable()) {
            if (subject.getIdSubject() == idSubject) {
                return true;
            }
        }
        return false;
    }

    public String getSubjectNameById(int idSubject) {
        if (isContainId(idSubject)) {
            for (Subject subject : localStorage.getSubjectsTable()) {
                if (subject.getIdSubject() == idSubject) {
                    return subject.getSubjectName();
                }
            }
        }
        return "";
    }
}
