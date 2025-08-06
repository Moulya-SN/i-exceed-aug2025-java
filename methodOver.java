public class methodOver {
    int add(int a, int b) {
        return a + b;
    }
    int add(int a, int b, int c) {
        return a + b + c;
    }
    double add(double a, double b) {
        return a + b;
    }

    public static void main(String[] args) {
        MethodOver calc = new MethodOver();
        System.out.println(calc.add(5, 10));          
        System.out.println(calc.add(1, 2, 3));        
        System.out.println(calc.add(2.5, 3.7));       
    }
}
