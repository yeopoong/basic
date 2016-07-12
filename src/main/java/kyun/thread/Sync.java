package kyun.thread;

public class Sync implements Runnable {

    @Override
    public void run() {
        System.out.println(">>>" + Thread.currentThread().getName() + ": started");
        if (Thread.currentThread().getName().equals("one")) {
            stepA();
        } else {
            stepB();
        }
    }

    private synchronized void stepA() {
        System.out.println("started A");
        System.out.println("Do something");
        System.out.println("end A");
    }

    private synchronized void stepB() {
        System.out.println("started B");
        System.out.println("Do something");
        System.out.println("end B");
    }

    public static void main(String[] args) {
        Sync sync = new Sync();

        Thread t1 = new Thread(sync, "one");
        Thread t2 = new Thread(sync, "two");

        t1.start();
        t2.start();
    }
}
