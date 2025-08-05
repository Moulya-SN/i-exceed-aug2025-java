public class Student {
    String name;
    int age;

    Student(String studentName, int studentAge) {
        name = studentName;
        age = studentAge;
    }


    public void display() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }

        public static void main(String[] args) {
        Student s1 = new Student("Moulya", 22);
        s1.display();
    }
}
