public class Student {
    private String studentID;
    private String name;
    private double marks;

    public Student(String studentID, String name, double marks) {
        this.studentID = studentID;
        this.name = name;
        this.marks = marks;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public String getRank() {
        if (marks >= 0 && marks <= 5.0) {
            return "Fail";
        } else if (marks > 5.0 && marks <= 6.5) {
            return "Medium";
        } else if (marks > 6.5 && marks <= 7.5) {
            return "Good";
        } else if (marks > 7.5 && marks <= 9.0) {
            return "Very Good";
        } else {
            return "Excellent";
        }
    }

    @Override
    public String toString() {
        return "ID: " + studentID + ", Name: " + name + ", Marks: " + marks + ", Rank: " + getRank();
    }
}

