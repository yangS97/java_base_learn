package cn.ys.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池测试
 */
public class ThreadPoolExecutorTest {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3), Executors.defaultThreadFactory(),
                // 满了 还有人进来 不处理这个人，抛出异常
                new ThreadPoolExecutor.AbortPolicy());
        try {
            // 最大承载：Deque + MaxSize
            // 超过了最大承载，就报错 拒绝策略异常
            // java.util.concurrent.RejectedExecutionException
            for (int i = 0; i < 1000; i++) {
                executor.execute(() -> {
                    System.out.println("Demo01.main " + Thread.currentThread().getName());
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
        // 线程池用完，程序结束，关闭线程池

    }
}
