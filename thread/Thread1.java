class Sample extends Thread {

    Sample(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getName() +
                           " is running with ID: " + Thread.currentThread().getId());

        try {
            for (int i = 1; i <= 3; i++) {
                System.out.println("Thread " + getName() + " - Count: " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + getName() + " was interrupted.");
        }

        System.out.println("Thread " + getName() + " has finished.");
    }
}

public class Thread1 {
    public static void main(String[] args) {
        Sample thread1 = new Sample("Worker-1");
        Sample thread2 = new Sample("Worker-2");

        thread1.start();
        thread2.start();
    }
}
