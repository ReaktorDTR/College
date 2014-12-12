package control.managersTables;

import control.StorageManager;
import database.LocalStorage;
import entity.Group;
import entity.Student;
import userInterface.KeyboardInput;
import userInterface.Validation;

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
            newGroup.setIdGroup(localStorage.getIdGroupCounter());
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
            if (isContainId(selectedId)) {
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

    private boolean isContainId(int id) {
        for (Group group : localStorage.getGroupsTable()) {
            if (group.getIdGroup() == id) {
                return true;
            }
        }
        return false;
    }

    public String getGroupNameById(int idGroup) {
        return getGroupById(idGroup).getGroupName();
    }

    public Group getGroupById(int idGroup) {
        if (isContainId(idGroup)) {
            for (Group group : localStorage.getGroupsTable()) {
                if (group.getIdGroup() == idGroup) {
                    return group;
                }
            }
        }
        return null;
    }
}
