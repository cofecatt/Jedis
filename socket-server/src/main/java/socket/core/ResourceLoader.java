package socket.core;

import socket.basic.Domain;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author: HLJ
 * @Date: 2022/5/20 19:39
 */
public class ResourceLoader {
    private final static Logger logger = Logger.getLogger("Server-ResourceLoader");

    /**
     * 防止控制台重复打印端口信息
     */
    private static int cnt = 0;

    public static Domain getResource() {

        Domain domain = new Domain();
        Properties properties = new Properties();
        try {
            InputStream inputStream = ResourceLoader.class.getResourceAsStream("/boot.properties");
            properties.load(inputStream);
            Iterator<String> it = properties.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                if("port".equals(key)) {
                    domain.setPort(properties.getProperty(key));
                }
                if("min-map-size".equals(key)) {
                    domain.setMinMapSize(Integer.valueOf(properties.getProperty(key)));
                }
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(Objects.nonNull(domain.getPort()) && cnt == 0) {
            logger.log(Level.INFO, "port="+domain.getPort());
            cnt++;
        } else {
            logger.log(Level.FINE, "boot.properties配置出错");
        }
        return domain;
    }

}