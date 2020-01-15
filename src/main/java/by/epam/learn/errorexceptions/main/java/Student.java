package by.epam.learn.errorexceptions.main.java;

import by.epam.learn.errorexceptions.main.java.exceptions.NoSubjectForStudentException;
import by.epam.learn.errorexceptions.main.java.structure.SubjectName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
    private static int countId = 0;
    private int studentId;
    private String name;
    private List<SubjectName> listOfSubjects;

    public Student(String name) {
        this.studentId = ++countId;
        this.name = name;
        this.listOfSubjects = new ArrayList<>();
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<SubjectName> getListOfSubjects() throws NoSubjectForStudentException{
        if (listOfSubjects.isEmpty()) {
            throw new NoSubjectForStudentException("Отсутсвие предметов у студента");
        }
        return listOfSubjects;
    }

    public void addSubjectToStudent(SubjectName subjectName) {
        listOfSubjects.add(subjectName);
    }


    @Override
    public String toString() {
        return "\n    Student{" +
                "Id=" + studentId +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return (studentId == student.studentId)
                && (name.equals(student.name));
    }

    @Override
    public int hashCode() {
        return 31 * studentId + name.hashCode();
    }
}
