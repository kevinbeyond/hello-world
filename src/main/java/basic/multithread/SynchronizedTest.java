package basic.multithread;

/**
 * Created by Leo on 2017/10/24.
 */
public class SynchronizedTest implements Runnable {

    private static int count;

    public SynchronizedTest (){
        count = 0;
    }

    /**
     * 实现Runable的run方法
     */
    public void run() {
        synchronized (this) {
            for (int i=0;i<5;i++){
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        SynchronizedTest test = new SynchronizedTest();
//        Thread thread1 = new Thread(test, "syncThread1");
//        Thread thread2 = new Thread(test, "syncThread2");

        Thread thread1 = new Thread(new SynchronizedTest(), "syncThread1");
        Thread thread2 = new Thread(new SynchronizedTest(), "syncThread2");

        thread1.start();
        thread2.start();
    }
}
