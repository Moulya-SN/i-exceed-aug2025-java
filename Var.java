class Sample {
    public void iterate() {
        System.out.println("Iterating from 0 to 9:");
        for (var i = 0; i < 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println("\n");
    }
    public void displayNames() {
        var names = new String[]{"Arun", "Bala", "Cathy", "Dinesh"};

        System.out.println("Names in the list:");
        for (var name : names) {
            System.out.println("- " + name);
        }
        System.out.println();
    }
}

class Var {
    public static void main(String[] args) {
        var sampleObj = new Sample();
        sampleObj.iterate();
        sampleObj.displayNames();
        var x = 10;
        var price = 32.43;
        var name = "Sathishisivam";
        var discount = 5.0;
        System.out.println("Integer value (x): " + x);
        System.out.println("Price: ₹" + price);
        System.out.println("Name: " + name);
        System.out.println("Discount: ₹" + discount);
        var finalPrice = price - discount;
        System.out.println("Final price after discount: ₹" + finalPrice);
    }
}
