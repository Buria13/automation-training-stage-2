package by.epam.learn.errorexceptions.main.java.hierarchy;

import by.epam.learn.errorexceptions.main.java.exceptions.NoStudentsInGroupException;

import java.util.List;

public class Group {
    private int groupId;
    private List<Student> students;

    public Group(int groupId, List<Student> students) {
        this.groupId = groupId;
        this.students = students;
    }

    public int getGroupId() {
        return groupId;
    }

    public Student getStudentById(int studentId) {
        for (Student student : students) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }
        return null;
    }

    public Student getStudentByName(String studentName) {
        for (Student student : students) {
            if (student.getName().contains(studentName)) {
                return student;
            }
        }
        return null;
    }

    public List<Student> getStudents() throws NoStudentsInGroupException{
        if (students.isEmpty()) {
            throw new NoStudentsInGroupException("Отсутствие студентов в группе");
        }
        return students;
    }


    @Override
    public String toString() {
        return "\n Group{" +
                "groupId=" + groupId +
                ", students=" + students +
                '}';
    }
}
