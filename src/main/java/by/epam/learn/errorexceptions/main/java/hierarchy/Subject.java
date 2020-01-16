package by.epam.learn.errorexceptions.main.java.hierarchy;

import by.epam.learn.errorexceptions.main.java.exceptions.IllegalGradeException;
import by.epam.learn.errorexceptions.main.java.exceptions.NoGroupInFacultyException;
import by.epam.learn.errorexceptions.main.java.exceptions.NoStudentsInGroupException;
import by.epam.learn.errorexceptions.main.java.structure.SubjectName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Subject {
    private SubjectName subjectName;
    private Map<Student, List<Integer>> studentsGradesMap;

    public Subject(SubjectName subjectName) {
        this.subjectName = subjectName;
        studentsGradesMap = new HashMap<>();
    }

    public SubjectName getSubjectName() {
        return subjectName;
    }

    public Map<Student, List<Integer>> getStudentsGradesMap() {
        return studentsGradesMap;
    }

    public List<Integer> getGradesOfSpecificStudent(Student student) {
        return studentsGradesMap.get(student);
    }

    public void addSubjectToFaculty(Faculty faculty) {
        try {
            for (Group group : faculty.getGroups()) {
                addSubjectToGroup(group);
            }
        } catch (NoGroupInFacultyException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addSubjectToGroup(Group group) {
        try {
            for (Student student : group.getStudents()) {
                studentsGradesMap.put(student, new ArrayList<>());
                student.addSubjectToStudent(subjectName);
            }
        } catch (NoStudentsInGroupException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addGradeToStudent(Student student, int grade) throws IllegalGradeException {
        if (grade < 1 || grade > 10) {
            throw new IllegalGradeException("Оценка должна быть в границах от 1 до 10");
        }
        studentsGradesMap.get(student).add(grade);
    }

    public double getAverageGradeOfStudent(Student student) {
        double averageGrade = 0;
        List<Integer> grades = studentsGradesMap.get(student);

        averageGrade += (double) (grades.stream()
                .reduce((o1, o2) -> o1 + o2)
                .orElse(0))
                / grades.size();

        return averageGrade;
    }

}
