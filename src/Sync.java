class ThreadStatic1 extends Thread {
    @Override
    public void run() {
        try {
            Sync.staticLock1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadStatic2 extends Thread {
    @Override
    public void run() {
        try {
            Sync.staticLock2();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadStatic3 extends Thread {
    @Override
    public void run() {
        try {
            OtherClass.staticLock3();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class OtherClass {
    public synchronized static void staticLock3() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            System.out.println("当前进入的线程是 ： " + Thread.currentThread().getName());
            Thread.sleep(200);
        }
    }


}

public class Sync {
    public synchronized static void staticLock1() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            System.out.println("当前进入的线程是 ： " + Thread.currentThread().getName());
            Thread.sleep(200);
        }

    }

    public synchronized static void staticLock2() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            System.out.println("当前进入的线程是 ： " + Thread.currentThread().getName());
            Thread.sleep(200);
        }

    }

    public static void main(String[] args) {
        new ThreadStatic1().start();
        new ThreadStatic2().start();
        new ThreadStatic3().start();
    }
}
