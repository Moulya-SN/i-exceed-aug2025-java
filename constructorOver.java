public class ConstructOver {
    String name;
    int age;
    ConstructOver() {
        System.out.println("Default constructor called");
    }
    ConstructOver(String n) {
        name = n;
        System.out.println("Name: " + name);
    }
    ConstructOver(String n, int a) {
        name = n;
        age = a;
        System.out.println("Name: " + name + ", Age: " + age);
    }

    public static void main(String[] args) {
      
        ConstructOver obj1 = new ConstructOver();               
        ConstructOver obj2 = new ConstructOver("Alice");         
        ConstructOver obj3 = new ConstructOver("Bob", 22);       
    }
}
