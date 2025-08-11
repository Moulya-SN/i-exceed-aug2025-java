class Sample extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("Child\t" + i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Child thread interrupted: " + e.getMessage());
            }
        }
    }
}

public class Thread2 {

    public static void main(String[] args) {
        Sample obj = new Sample();
        obj.start();

        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("Main\t" + i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Main thread interrupted: " + e.getMessage());
            }
        }
    }
}
