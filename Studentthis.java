public class Student {
    int id;
    String name;
    Student() {
        this(101, "John Doe"); 
        System.out.println("Default constructor called\n");
    }
    Student(int id, String name) {
        this.id = id;     
        this.name = name;
        System.out.println("Parameterized constructor called");
    }
    void printDetails() {
        System.out.println("ID: " + this.id + ", Name: " + this.name);
        this.displayDetails(this);  // passing current object
    }

    void displayDetails(Student s) {
        System.out.println("Displaying from another method:");
        System.out.println("Student ID: " + s.id + ", Name: " + s.name);
    }
    Student updateName(String name) {
        this.name = name;
        return this; 
    }

    Student updateId(int id) {
        this.id = id;
        return this;
    }
    public static void main(String[] args) {
        Student s = new Student();
        s.updateId(202).updateName("Alice");
        s.printDetails();
    }
}
