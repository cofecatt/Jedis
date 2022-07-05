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

/**
 * @Author: HLJ
 * @Date: 2022/7/5 11:31
 */
public class BgSave {
    public static final String SAVEFILENAME = "savefile";
    public static final ScheduledExecutorService executorService =
            (ScheduledExecutorService) ThreadPoolFactory.bgSaveThreadPool();

    static {
        executorService.scheduleAtFixedRate(()->
                        bgSave(LocalMap.getInstance())
                , 0, 1, TimeUnit.SECONDS);
    }

    public static boolean bgSave(Map<String, Object> map) {
        try(FileOutputStream fileOutputStream =
                    new FileOutputStream(Objects.requireNonNull(CustomLoader.getResource(SAVEFILENAME)));
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
        return true;
    }


    public static Map<String, Object> reload() {
        ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>();
        try(FileInputStream fileInputStream =
                    new FileInputStream(Objects.requireNonNull(CustomLoader.getResource(SAVEFILENAME)));
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
        return concurrentHashMap;
    }
}
