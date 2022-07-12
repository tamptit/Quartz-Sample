package job;

public class ThreadTest {

    public static void main(String[] args) {
        Thread thread = new Thread("thread1");
        Thread thread2 = new Thread("thread2");
        thread.run();
        thread2.run();
    }
}
