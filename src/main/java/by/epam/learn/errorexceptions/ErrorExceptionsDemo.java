package by.epam.learn.errorexceptions;

import by.epam.learn.errorexceptions.main.java.*;
import by.epam.learn.errorexceptions.main.java.exceptions.*;
import by.epam.learn.errorexceptions.main.java.structure.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ErrorExceptionsDemo {

    private static List<Student> listOfStudents = Arrays.asList(
            new Student("Siarhei Burets"),
            new Student("Ivan Ivanov"),
            new Student("Petr Petrov"),
            new Student("Al Pacino"),
            new Student("Tom Cruise"),
            new Student("Robert De Niro"),
            new Student("Bill Gates"),
            new Student("Elon Musk"),
            new Student("Mark Zuckerberg"),
            new Student("Leo Messi"),
            new Student("Cristiano Ronaldo"),
            new Student("Andriy Shevchenko")
    );


    public static void main(String[] args) {

        Faculty fitu = new Faculty(FacultyName.FITiU, Arrays.asList(
                new Group(120404, listOfStudents.subList(0, 3)),
                new Group(120403, listOfStudents.subList(3, 6))
        ));

        Faculty fksis = new Faculty(FacultyName.FKSiS, new Group(110601, listOfStudents.subList(6, 9)));
        fksis.addGroup(new Group(110602, listOfStudents.subList(9, 12)));

        List<Subject> listOfSubjects = new ArrayList<>(Arrays.asList(
                new Subject(SubjectName.MATHEMATICS),
                new Subject(SubjectName.PHYSICS),
                new Subject(SubjectName.PROGRAMMING),
                new Subject(SubjectName.PHILOSOPHY)
        ));

        University bguir = new University(Arrays.asList(
                fitu,
                fksis
        ), listOfSubjects);

        // Добавляем предметы на факультеты и ставим случайные оценки студентам
        for (Subject subject : listOfSubjects) {
            try {
                for (Faculty faculty : bguir.getFaculties()) {
                    subject.addSubjectToFaculty(faculty);
                    setRandomGradesToStudents(faculty, subject);
                }
            } catch (NoFacultyInUniversityException e) {
                System.out.println(e.getMessage());
            }
        }

        Group group = bguir.getGroupById(120404);
        Student student = bguir.getStudentByName("Burets");
        Subject subject = bguir.getSubject(SubjectName.MATHEMATICS);

        System.out.println("Вывод для примера студент и список его оценок по предметам: "
                + student);
        try {
            for (SubjectName subjectName : student.getListOfSubjects()) {
                System.out.println(subjectName + " : "
                         + bguir.getSubject(subjectName).getGradesOfSpecificStudent(student));
            }
        } catch (NoSubjectForStudentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nСредний балл студента по всем предметам: "
                + bguir.getAverageGradeOfStudentInAllSubjects(student));

        System.out.println("\nСредний балл по конкретному предмету в конкретной группе: "
                + bguir.getAverageGradeOfGroupInSpecificSubject(group, subject));

        System.out.println("\nСредний балл по конкретному предмету на конкретном факультете: "
                + bguir.getAverageGradeOfFacultyInSpecificSubject(fitu, subject));

        System.out.println("\nСредний балл по предмету для всего университета: "
                + bguir.getAverageGradeOfUniversityInSpecificSubject(subject));

    }


    private static void setRandomGradesToStudents(Faculty faculty, Subject subject) {

        try {
            for (Group group : faculty.getGroups()) {
                for (Student student : group.getStudents()) {
                    for (int i = 0; i < 10; i++) {
                        int randomGrade = (int) (Math.random() * 9) + 2;

                        try {
                            subject.addGradeToStudent(student, randomGrade);
                        } catch (IllegalGradeException e) {
                            System.out.println("Невозможно добавить оценку. " + e.getMessage());
                        }
                    }
                }
            }
        } catch (NoGroupInFacultyException | NoStudentsInGroupException e) {
            System.out.println(e.getMessage());
        }
    }

}
