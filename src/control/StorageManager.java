package control;

import database.LocalStorage;
import entity.Subject;

/**
 * Created by sdovhtc on 12/11/2014.
 */
public class StorageManager {
    private LocalStorage localStorage;
    public GroupTableManager groupTableManager = new GroupTableManager(localStorage);
    public SubjectTableManager subjectTableManager = new SubjectTableManager(localStorage);

    public StorageManager(LocalStorage localStorage) {
        this.localStorage = localStorage;
    }
}
