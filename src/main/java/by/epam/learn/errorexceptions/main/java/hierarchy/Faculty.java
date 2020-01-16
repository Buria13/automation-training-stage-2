package by.epam.learn.errorexceptions.main.java.hierarchy;

import by.epam.learn.errorexceptions.main.java.exceptions.NoGroupInFacultyException;
import by.epam.learn.errorexceptions.main.java.structure.FacultyName;

import java.util.ArrayList;
import java.util.List;

public class Faculty {
    private FacultyName facultyName;
    private List<Group> groups;

    public Faculty(FacultyName facultyName, List<Group> groups) {
        this.facultyName = facultyName;
        this.groups = groups;
    }

    public Faculty(FacultyName facultyName, Group group) {
        this.facultyName = facultyName;
        groups = new ArrayList<>();
        groups.add(group);
    }

    public List<Group> getGroups() throws NoGroupInFacultyException {
        if (groups.isEmpty()) {
            throw new NoGroupInFacultyException("Отсутствие групп на факультете");
        }
        return groups;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public Group getGroupById(int groupId) {
        for (Group group : groups) {
            if (group.getGroupId() == groupId) {
                return group;
            }
        }
        return null;
    }

    public Student getStudentById(int studentId) {
        for (Group group : groups) {
            Student student = group.getStudentById(studentId);
            if (student != null) return student;
        }
        return null;
    }

    public Student getStudentByName(String studentName) {
        for (Group group : groups) {
            Student student = group.getStudentByName(studentName);
            if (student != null) return student;
        }
        return null;
    }


    @Override
    public String toString() {
        return "\nFaculty{" + facultyName +
                "\ngroups=" + groups +
                '}';
    }
}
