import java.io.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class StudentManager {
    private Set<String> studentIds;
    private List<Student> students;

    public StudentManager() {
        studentIds = new HashSet<>();
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        if (studentIds.add(student.getId())) {
            students.add(student);
        } else {
            System.out.println("Student ID already exists.");
        }
    }

    public Student searchStudent(String id) {
        return students.stream()
                .filter(student -> student.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    public void deleteStudent(String id) {
        Student student = searchStudent(id);
        if (student != null) {
            students.remove(student);
            studentIds.remove(id);
        } else {
            System.out.println("Student not found.");
        }
    }

    public void calculateResults() {
        for (Student student : students) {
            CompletableFuture.runAsync(() -> {
                int total = student.getTotalMarks();
                double average = student.getAverageMarks();
                System.out.println("Calculated for " + student.getName() + ": Total = " + total + ", Average = " + String.format("%.2f", average));
            }).join(); // Wait for the calculation to complete
        }
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(students);
            System.out.println("Data saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            students = (List<Student>) ois.readObject();
            studentIds.clear();
            for (Student student : students) {
                studentIds.add(student.getId());
            }
            System.out.println("Data loaded from " + filename);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
