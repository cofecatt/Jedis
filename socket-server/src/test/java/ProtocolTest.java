import factory.ThreadPoolFactory;
import protocol.MyProtocol;
import socket.save.BgSave;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: HLJ
 * @Date: 2022/6/29 22:36
 */
public class ProtocolTest {
    public static void main(String[] args) throws Exception {
//        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
//        objectObjectHashMap.put("2", "null");
//        BgSave.bgSave(objectObjectHashMap);
       // ExecutorService executorService = ThreadPoolFactory.bgsaveThreadPool();
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        // 参数：1、任务体 2、首次执行的延时时间
        //      3、任务执行间隔 4、间隔时间单位
        service.scheduleAtFixedRate(()->
                System.out.println("task ScheduledExecutorService "+new Date())
                , 0, 1, TimeUnit.SECONDS);

        Map<String, Object> reload = BgSave.reload();
        for (String s : reload.keySet()) {
            System.out.println(s);
            System.out.println(reload.get(s));
        }
    }
}
