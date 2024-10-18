import java.util.Scanner;

public class StudentManagement {
    private static Student[] students = new Student[100];
    private static int studentCount = 0;
    private static StudentStack editHistory = new StudentStack(100);
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Sort Students by Marks (Merge Sort)");
            System.out.println("6. Sort Students by Marks (Bubble Sort)");
            System.out.println("7. Undo Last Edit");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    editStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    displayAllStudents();
                    break;
                case 5:
                    sortStudentsByMarksMergeSort();
                    break;
                case 6:
                    sortStudentsByMarksBubbleSort();
                    break;
                case 7:
                    undoLastEdit();
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student marks: ");
        double marks = scanner.nextDouble();
        scanner.nextLine();  // Consume newline
        students[studentCount++] = new Student(id, name, marks);
        System.out.println("Student added successfully.");
    }

    private static void editStudent() {
        System.out.print("Enter student ID to edit: ");
        String id = scanner.nextLine();
        Student student = findStudentByID(id);
        if (student != null) {
            // Push current student state to history stack before editing
            editHistory.push(new Student(student.getStudentID(), student.getName(), student.getMarks()));

            System.out.print("Enter new name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new marks: ");
            double marks = scanner.nextDouble();
            scanner.nextLine();  // Consume newline

            student.setName(name);
            student.setMarks(marks);
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        String id = scanner.nextLine();
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getStudentID().equals(id)) {
                students[i] = students[--studentCount];  // Replace with last student
                System.out.println("Student deleted successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    private static void displayAllStudents() {
        if (studentCount == 0) {
            System.out.println("No students found.");
        } else {
            for (int i = 0; i < studentCount; i++) {
                System.out.println(students[i]);
            }
        }
    }

    private static Student findStudentByID(String id) {
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getStudentID().equals(id)) {
                return students[i];
            }
        }
        return null;
    }

    private static void undoLastEdit() {
        if (!editHistory.isEmpty()) {
            Student previousState = editHistory.pop();
            Student currentStudent = findStudentByID(previousState.getStudentID());
            if (currentStudent != null) {
                currentStudent.setName(previousState.getName());
                currentStudent.setMarks(previousState.getMarks());
                System.out.println("Undo successful: " + currentStudent);
            } else {
                students[studentCount++] = previousState;
                System.out.println("Undo successful: Student restored.");
            }
        } else {
            System.out.println("No edits to undo.");
        }
    }

    // Merge Sort for Students by Marks
    private static void sortStudentsByMarksMergeSort() {
        if (studentCount > 1) {
            mergeSort(students, 0, studentCount - 1);
            System.out.println("Students sorted by marks using Merge Sort.");
        }
    }

    private static void mergeSort(Student[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;

            // Sort first and second halves
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);

            // Merge the sorted halves
            merge(array, left, middle, right);
        }
    }

    private static void merge(Student[] array, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        Student[] leftArray = new Student[n1];
        Student[] rightArray = new Student[n2];

        for (int i = 0; i < n1; i++)
            leftArray[i] = array[left + i];
        for (int j = 0; j < n2; j++)
            rightArray[j] = array[middle + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i].getMarks() <= rightArray[j].getMarks()) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    // Bubble Sort for Students by Marks
    private static void sortStudentsByMarksBubbleSort() {
        for (int i = 0; i < studentCount - 1; i++) {
            for (int j = 0; j < studentCount - i - 1; j++) {
                if (students[j].getMarks() > students[j + 1].getMarks()) {
                    // Swap students[j] and students[j+1]
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
        System.out.println("Students sorted by marks using Bubble Sort.");
    }
}