package by.epam.learn.errorexceptions.main.java;

import by.epam.learn.errorexceptions.main.java.exceptions.*;
import by.epam.learn.errorexceptions.main.java.hierarchy.*;
import by.epam.learn.errorexceptions.main.java.structure.SubjectName;

import java.util.List;
import java.util.Set;

public class University {
    private List<Faculty> faculties;
    private List<Subject> subjectsList;

    public University(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    public University(List<Faculty> faculties, List<Subject> listOfSubjects) {
        this.faculties = faculties;
        this.subjectsList = listOfSubjects;
    }

    public List<Subject> getSubjectsList() {
        return subjectsList;
    }

    public Subject getSubject(SubjectName subjectName) {
        for (Subject subject : subjectsList) {
            if (subject.getSubjectName() == subjectName) {
                return subject;
            }
        }
        return null;
    }

    public List<Faculty> getFaculties() throws NoFacultyInUniversityException {
        if (faculties.isEmpty()) {
            throw new NoFacultyInUniversityException("Отсутствие факультетов в университете");
        }
        return faculties;
    }

    public Group getGroupById(int groupId) {
        for (Faculty faculty : faculties) {

            try {
                List<Group> groups = faculty.getGroups();
                for (Group group : groups) {
                    if (group.getGroupId() == groupId) {
                        return group;
                    }
                }
            } catch (NoGroupInFacultyException e) {
                System.out.println(e.getMessage());
            }
        }

        return null;
    }

    public Student getStudentById(int studentId) {
        for (Faculty faculty : faculties) {
            Student student = faculty.getStudentById(studentId);
            if (student != null) return student;
        }
        return null;
    }

    public Student getStudentByName(String studentName) {
        for (Faculty faculty : faculties) {
            Student student = faculty.getStudentByName(studentName);
            if (student != null) return student;

        }
        return null;
    }

    public double getAverageGradeOfStudentInAllSubjects(Student student) {
        double averageGrade = 0;

        try {
            List<SubjectName> subjectsList = student.getListOfSubjects();
            for (SubjectName subjectName : subjectsList) {
                averageGrade += getSubject(subjectName).getAverageGradeOfStudent(student);
            }
            averageGrade = averageGrade / subjectsList.size();
        } catch (NoSubjectForStudentException e) {
            System.out.println(e);
        }

        return round(averageGrade);
    }

    public double getAverageGradeOfFacultyInSpecificSubject(Faculty faculty, Subject subject) {
        double averageGrade = 0;

        try {
            List<Group> groupList = faculty.getGroups();
            for (Group group : groupList) {
                averageGrade += getAverageGradeOfGroupInSpecificSubject(group, subject);
            }
            averageGrade = averageGrade / groupList.size();
        } catch (NoGroupInFacultyException e) {
            e.printStackTrace();
        }

        return round(averageGrade);
    }

    public double getAverageGradeOfGroupInSpecificSubject(Group group, Subject subject) {
        double averageGrade = 0;

        try {
            List<Student> studentList = group.getStudents();
            for (Student student : studentList) {
                averageGrade += subject.getAverageGradeOfStudent(student);
            }
            averageGrade = averageGrade / studentList.size();
        } catch (NoStudentsInGroupException e) {
            e.printStackTrace();
        }

        return round(averageGrade);
    }

    public double getAverageGradeOfUniversityInSpecificSubject(Subject subject) {
        double averageGrades = 0;
        Set<Student> studentList = subject.getStudentsGradesMap().keySet();

        for (Student student : studentList) {
            averageGrades += subject.getAverageGradeOfStudent(student);
        }

        return round((averageGrades / studentList.size()));
    }

    private double round(double number) {
        return (double) Math.round(number * 10) / 10;
    }


    @Override
    public String toString() {
        return "University{" +
                "\nfaculties=" + faculties +
                '}';
    }
}
