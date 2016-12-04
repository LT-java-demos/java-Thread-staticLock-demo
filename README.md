#java-Thread-staticLock-demo


- 关于同步方法需要注意的重要问题

线程获取一个静态同步方法的同步锁时，相当于同时获取了该类中所有静态同步方法的锁；

线程获取一个对象同步方法时，相当于同时获取了该对象中所有同步方法的锁。

换句话说，如果类中有两个静态同步方法，这两个方法不会被两个线程同时执行；如果对象中有两个同步方法，这两个方法不会被两个线程同时执行。

##Demo
```java
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
```


###运行结果

```shell
当前进入的线程是 ： Thread-0
当前进入的线程是 ： Thread-2
当前进入的线程是 ： Thread-0
当前进入的线程是 ： Thread-2
当前进入的线程是 ： Thread-0
当前进入的线程是 ： Thread-2
当前进入的线程是 ： Thread-0
当前进入的线程是 ： Thread-2
当前进入的线程是 ： Thread-0
当前进入的线程是 ： Thread-2
当前进入的线程是 ： Thread-1
当前进入的线程是 ： Thread-1
当前进入的线程是 ： Thread-1
当前进入的线程是 ： Thread-1
当前进入的线程是 ： Thread-1
```