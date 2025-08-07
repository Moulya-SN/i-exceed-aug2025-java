class Company {
    String nameofcom;
    String city;
    Company(String nameofcom, String city) {
        this.nameofcom = nameofcom;
        this.city = city;
    }
    void displayDetails() {
        System.out.println("Company Name: " + nameofcom);
        System.out.println("City: " + city);
    }
}
class HR extends Company {
    int numOfHRs;
    HR(String nameofcom, String city, int numOfHRs) {
        super(nameofcom, city);  // Call to parent class constructor
        this.numOfHRs = numOfHRs;
    }
    void showHRInfo() {
        displayDetails();
        System.out.println("Number of HR Employees: " + numOfHRs);
    }
}

class Finance extends Company {
    double budget;
    Finance(String nameofcom, String city, double budget) {
        super(nameofcom, city); 
        this.budget = budget;
    }
    void showFinanceInfo() {
        displayDetails();
        System.out.println("Annual Budget: ₹" + budget);
    }
}
class Inheritance {
    public static void main(String[] args) {
        HR hrDept = new HR("TechSoft", "Chennai", 5);
        Finance financeDept = new Finance("I-Exceed", "Bangalore", 1500000.00);

        System.out.println("--- HR Department ---");
        hrDept.showHRInfo();

        System.out.println("\n--- Finance Department ---");
        financeDept.showFinanceInfo();
    }
}
