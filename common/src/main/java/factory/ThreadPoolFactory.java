package factory;

import java.util.concurrent.*;

/**
 * @Author: HLJ
 * @Date: 2022/5/21 0:27
 */
public class ThreadPoolFactory {
    public static ThreadPoolExecutor getDefault() {
        //使用线程池
        return new ThreadPoolExecutor(20,
                40,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
    }

    public static ExecutorService bgSaveThreadPool() {
        return Executors.newSingleThreadScheduledExecutor();
    }
}
