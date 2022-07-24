import lombok.SneakyThrows;
import net.sf.json.JSON;
import net.sf.json.util.JSONUtils;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

public class testReadProperty {

    public static Properties properties=new Properties();
    static {
        classPathSourceRead();
    }
    private static void classPathSourceRead(){
        try {
            properties.load(testReadProperty.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) throws InterruptedException {
//        int times=20;
//        //testReadProperty t=new testReadProperty();
//        System.out.println(testReadProperty.properties.toString());
//        //count – the number of times countDown must be invoked before threads can pass through await
//        final CountDownLatch countDownLatch=new CountDownLatch(times);
//        for (int i = 0; i < times; i++) {
//            int finalIndex=i+1;
//            new Thread(new Runnable() {
//                @SneakyThrows
//                @Override
//                public void run() {
//                    //Thread.sleep(finalIndex*1000);
//                    System.out.println("第"+finalIndex+"个线程结束了... ...");
//                    System.out.println("countDownLatch还需  " + (countDownLatch.getCount()-1)+"次可打开");
//                    countDownLatch.countDown();
//                }
//            }).start();
//        }
//        countDownLatch.await();
//        System.out.println("countDownLatch打开了！！！");
//    }
//=============================================================================================
//    private static volatile int cnt;
//    public static void main(String[] args) {
//
//        int times=20;
//        final Object lock=new Object();
//
//        for (int i = 0; i < times; i++) {
//            int finalIndex=i+1;
//            new Thread(()->{
//
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                synchronized (lock){
//                    cnt++;
//                    System.out.println("第"+finalIndex+"个线程结束了... ..."+cnt);
//                }
//            }).start();
//        }
//        while(true){
//            if (cnt==10){
//                System.out.println("=================="+cnt+"个任务结束了！");
//                break;
//            }
//        }
//    }
//=============================================================================================
//    public static void main(String[] args) throws InterruptedException {
//        Object lock=new Object();
//        Thread threadB = new Thread(new Runnable() {
//            @SneakyThrows
//            @Override
//            public void run() {
//                synchronized (lock) {
//                    System.out.println("waiting...");
//                    lock.wait();
//                    System.out.println("threadB back to alive");
//                }
//            }
//        });
//
//        Thread threadA = new Thread(new Runnable() {
//            @SneakyThrows
//            @Override
//            public void run() {
//                synchronized (lock) {
//                    Thread.sleep(3000);
//                    System.out.println("threadA waking other threads... ...");
//                    lock.notify();
//                }
//            }
//        });
//        threadB.start();
//        Thread.sleep(1000);
//        threadA.start();
//    }
//=============================================================================================
        public static void main(String[] args) throws InterruptedException {

        Thread threadB = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                    System.out.println("waiting...");
                    LockSupport.park();
                    System.out.println("threadB back to alive");
            }
        });

        Thread threadA = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                    Thread.sleep(3000);
                    System.out.println("threadA waking other threads... ...");
                    LockSupport.unpark(threadB);
            }
        });
        threadB.start();
        threadA.start();
    }
}
