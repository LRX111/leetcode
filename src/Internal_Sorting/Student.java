package Internal_Sorting;

import java.util.Comparator;

public class Student implements Comparable<Student> {
    private String Name;
    private static int studentsCount = 0;
    private int studentID = 0;
    private double mathScore;
    private double physicsScore;

    public Student(String name, double mathScore, double physicsScore) {
        this.studentID = studentsCount++;
        Name = name;
        this.mathScore = mathScore;
        this.physicsScore = physicsScore;
    }

    public void setMathScore(double mathScore) {
        this.mathScore = mathScore;
    }

    public void setPhysicsScore(double physicsScore) {
        this.physicsScore = physicsScore;
    }

    public void setName(String name) {

        Name = name;
    }

    public String getName() {

        return Name;
    }

    public static int getStudentsCount() {
        return studentsCount;
    }

    public int getStudentID() {
        return studentID;
    }

    public double getMathScore() {
        return mathScore;
    }

    public double getPhysicsScore() {
        return physicsScore;
    }

    @Override
    public int compareTo(Student o) {
        if ((this.mathScore + this.physicsScore) == (o.getMathScore() + o.getPhysicsScore()))
            return 0;
        else return (this.mathScore + this.physicsScore) < (o.getMathScore() + o.getPhysicsScore()) ?
                -1 : 1;
    }

    @Override
    public String toString() {
        return "Student{" +
                "Name='" + Name + '\'' +
                ", studentID=" + studentID +
                ", mathScore=" + mathScore +
                ", physicsScore=" + physicsScore +
                '}';
    }
}

class StudentMathComparator implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        if (o1.getMathScore() == o2.getMathScore())
            return 0;
        else return o1.getMathScore() < o2.getMathScore() ? -1 : 1;
    }
}