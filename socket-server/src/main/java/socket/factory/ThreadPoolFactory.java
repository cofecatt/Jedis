package socket.factory;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: HLJ
 * @Date: 2022/5/21 0:27
 */
public class ThreadPoolFactory {
    public static ThreadPoolExecutor getDefault() {
        //使用线程池
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(20,
                40,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        return executorService;
    }
}
