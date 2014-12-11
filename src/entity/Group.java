package entity;

/**
 * Created by Reaktor on 10.12.2014.
 */
public class Group {
    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (groupName != null ? !groupName.equals(group.groupName) : group.groupName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return groupName != null ? groupName.hashCode() : 0;
    }

    @Override
    public String toString() {
        return groupName;
    }
}