import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager studentManager = new StudentManager();

        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. Search Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Calculate Results");
            System.out.println("5. Save Data");
            System.out.println("6. Load Data");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice;

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); 
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); 
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    Student student = new Student(id, name);
                    while (true) {
                        System.out.print("Enter Subject (or 'done' to finish): ");
                        String subject = scanner.nextLine();
                        if (subject.equalsIgnoreCase("done")) {
                            break;
                        }
                        System.out.print("Enter Marks (0-100): ");
                        int marks;
                        try {
                            marks = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            student.addSubjectMark(subject, marks);
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a number between 0 and 100.");
                            scanner.nextLine(); // Clear the invalid input
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    studentManager.addStudent(student);
                    break;

                case 2:
                    System.out.print("Enter Student ID to search: ");
                    String searchId = scanner.nextLine();
                    Student foundStudent = studentManager.searchStudent(searchId);
                    if (foundStudent != null) {
                        System.out.println(foundStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Student ID to delete: ");
                    String deleteId = scanner.nextLine();
                    studentManager.deleteStudent(deleteId);
                    break;

                case 4:
                    studentManager.calculateResults();
                    break;

                case 5:
                    System.out.print("Enter filename to save data: ");
                    String saveFilename = scanner.nextLine();
                    studentManager.saveToFile(saveFilename + ".dat");
                    break;

                case 6:
                    System.out.print("Enter filename to load data: ");
                    String loadFilename = scanner.nextLine();
                    studentManager.loadFromFile(loadFilename + ".dat");
                    break;

                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
