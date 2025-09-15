class Student {
    String name;
    int age;
    String course;

    void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Course: " + course);
    }
}

public class SimpleStudentClass {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.name = "Alice";
        s1.age = 20;
        s1.course = "Computer Science";
        s1.displayInfo();
    }
}


