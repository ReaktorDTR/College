package control;

import control.managersTables.GroupTableManager;
import control.managersTables.RatingTableManager;
import control.managersTables.StudentTableManager;
import control.managersTables.SubjectTableManager;
import database.LocalStorage;

/**
 * Created by sdovhtc on 12/11/2014.
 */
public class StorageManager {
    private LocalStorage localStorage;
    public GroupTableManager groupTableManager;
    public SubjectTableManager subjectTableManager;
    public StudentTableManager studentTableManager;
    public RatingTableManager ratingTableManager;

    public StorageManager(LocalStorage localStorage) {
        this.localStorage = localStorage;
        this.groupTableManager = new GroupTableManager(localStorage);
        this.subjectTableManager = new SubjectTableManager(localStorage);
        this.studentTableManager = new StudentTableManager(localStorage);
        this.ratingTableManager = new RatingTableManager(localStorage);
    }

    public LocalStorage getLocalStorage() {
        return localStorage;
    }

    public void setLocalStorage(LocalStorage localStorage) {
        this.localStorage = localStorage;
        this.groupTableManager = new GroupTableManager(localStorage);
        this.subjectTableManager = new SubjectTableManager(localStorage);
        this.studentTableManager = new StudentTableManager(localStorage);
        this.ratingTableManager = new RatingTableManager(localStorage);
    }
}
