import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Student {
    private String name;
    private Map<String, Double> subjectMarks;
    private double totalMarks;
    private double average;
    private String grade;

    public Student(String name) {
        this.name = name;
        this.subjectMarks = new LinkedHashMap<>();
    }

    public void addMark(String subject, double mark) {
        subjectMarks.put(subject, mark);
    }

    public void calculateResults() {
        totalMarks = subjectMarks.values().stream().mapToDouble(Double::doubleValue).sum();
        average = totalMarks / subjectMarks.size();
        grade = determineGrade(average);
    }

    private String determineGrade(double avg) {
        if (avg >= 90) return "A+";
        else if (avg >= 80) return "A";
        else if (avg >= 70) return "B";
        else if (avg >= 60) return "C";
        else if (avg >= 50) return "D";
        else return "F";
    }

    public void printReport() {
        System.out.println("\n--- Student Report ---");
        System.out.println("Name: " + name);
        for (Map.Entry<String, Double> entry : subjectMarks.entrySet()) {
            System.out.printf("%s: %.2f\n", entry.getKey(), entry.getValue());
        }
        System.out.printf("Total Marks: %.2f\n", totalMarks);
        System.out.printf("Average: %.2f\n", average);
        System.out.println("Grade: " + grade);
    }
}

public class GradeCalculatorApp {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter number of students: ");
        int numStudents = getValidInt();

        List<Student> students = new ArrayList<>();

        for (int i = 0; i < numStudents; i++) {
            System.out.printf("\n--- Enter data for Student %d ---\n", i + 1);

            System.out.print("Enter student name: ");
            String name = scanner.nextLine();
            Student student = new Student(name);

            System.out.print("Enter number of subjects: ");
            int numSubjects = getValidInt();

            for (int j = 0; j < numSubjects; j++) {
                System.out.printf("Enter name of subject %d: ", j + 1);
                String subject = scanner.nextLine();

                System.out.printf("Enter marks for %s: ", subject);
                double mark = getValidMark();
                student.addMark(subject, mark);
            }

            student.calculateResults();
            students.add(student);
        }

        // Display reports
        for (Student s : students) {
            s.printReport();
        }
    }

    // --- Utility Methods ---

    private static int getValidInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter a valid number: ");
            scanner.next();
        }
        int val = scanner.nextInt();
        scanner.nextLine(); // clear buffer
        return val;
    }

    private static double getValidMark() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input. Enter a numeric mark: ");
            scanner.next();
        }
        double mark = scanner.nextDouble();
        scanner.nextLine(); // clear buffer
        if (mark < 0 || mark > 100) {
            System.out.print("Mark must be between 0 and 100. Re-enter: ");
            return getValidMark();
        }
        return mark;
    }
}
