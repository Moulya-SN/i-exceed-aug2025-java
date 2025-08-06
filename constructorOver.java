public class Person {
    String name;
    int age;
    public Person() {
        this.name = "Unknown";
        this.age = 0;
    }
    public Person(String name) {
        this.name = name;
        this.age = 0;
    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + age);
    }

    public static void main(String[] args) {
        Person p1 = new Person();
        Person p2 = new Person("Alice");
        Person p3 = new Person("Bob", 30);

        p1.displayInfo();  
        p2.displayInfo();  
        p3.displayInfo();  
    }
}

        
