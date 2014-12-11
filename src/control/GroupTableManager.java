package control;

import database.LocalStorage;
import entity.Group;
import entity.Student;

import java.util.List;

/**
 * Created by sdovhtc on 12/11/2014.
 */

public class GroupTableManager {
    private LocalStorage localStorage;

    public GroupTableManager(LocalStorage localStorage) {
        this.localStorage = localStorage;
    }

    private void addGroup(Group group) {
        localStorage.getGroupsTable().add(group);
    }

    public void addGroup(String groupName) {
        Group newGroup = new Group();
        newGroup.setGroupName(groupName);
        if (!localStorage.getGroupsTable().contains(newGroup)) {
            localStorage.incrementIdGroup();
            newGroup.setIdGroup(localStorage.getIdGroup());
            addGroup(newGroup);
        }
    }

    public void addGroups() {
        System.out.println("Input groups :");
        while (true) {
            String inputQuery = "group name";
            String groupName = KeyboardInput.inputValidQueryData(inputQuery, Validation.GROUP_PATTERN);
            if (groupName.equals("!e")) break;
            addGroup(groupName);
            System.out.println("Group added");
        }
    }

    public int selectIdGroup() {
        String inputQuery = "group";
        while (true) {
            outTableGroups();
            int selectedId = KeyboardInput.selectId(inputQuery);
            if (isContainId(localStorage.getGroupsTable(),selectedId)) {
                return selectedId;
            } else {
                System.out.println("Group ID=" + selectedId + ", not found.");
            }
        }
    }

    public void outTableGroups() {
        for (Group group : localStorage.getGroupsTable()) {
            System.out.println(group);
        }
    }

    private boolean isContainId(List<Group> list, int id) {
        for (Group group : list) {
            if (group.getIdGroup() == id) {
                return true;
            }
        }
        return false;
    }

    private boolean isGroupEmpty(int idGroup) {
        for (Student student : localStorage.getStudentsTable()) {
            
        }
        return true;
    }
}
