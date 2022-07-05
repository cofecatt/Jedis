package socket.save;

import factory.ThreadPoolFactory;
import socket.basic.LocalMap;
import socket.loader.CustomLoader;

import java.io.*;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @Author: HLJ
 * @Date: 2022/7/5 11:31
 */
public class BgSave {
    private final static Logger logger = Logger.getLogger("BgSave");
    public static final String SAVEFILENAME = "savefile";
    public static final ScheduledExecutorService executorService =
            (ScheduledExecutorService) ThreadPoolFactory.bgSaveThreadPool();
    public static String resource;
    static {
        resource = CustomLoader.getResource(SAVEFILENAME);
        executorService.scheduleAtFixedRate(()->
                        bgSave(LocalMap.getInstance())
                , 0, 1, TimeUnit.SECONDS);
    }

    public static boolean bgSave(Map<String, Object> map) {
        try(FileOutputStream fileOutputStream =
                    new FileOutputStream(Objects.requireNonNull(resource));
            ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream)) {

            for (String key : map.keySet()) {
                oos.writeObject(key);
                oos.writeObject(map.get(key));
                oos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        logger.info("保存数据成功");
        return true;
    }


    public static Map<String, Object> reload() {
        ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>();
        try(FileInputStream fileInputStream =
                    new FileInputStream(Objects.requireNonNull(resource));
            ObjectInputStream ois = new ObjectInputStream(fileInputStream)) {
            while (true) {
                String k;
                Object v;
                try {
                    k = (String) ois.readObject();
                    v = ois.readObject();
                } catch (EOFException e) {
                    break;
                }
                if(k != null && v != null) {
                    concurrentHashMap.put(k, v);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("加载数据成功");
        return concurrentHashMap;
    }
}
