package socket.core;

import socket.basic.Domain;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author: HLJ
 * @Date: 2022/5/20 19:39
 */
public class ResourceLoader {
    private final static Logger logger = Logger.getLogger("Client-ResourceLoader");
    public static Domain getResource() {

        Domain domain = new Domain();
        Properties properties = new Properties();
        try {
            InputStream inputStream = ResourceLoader.class.getResourceAsStream("/boot.properties");
            properties.load(inputStream);
            Iterator<String> it = properties.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                if("ip".equals(key)) {
                    domain.setIp(properties.getProperty(key));
                }
                if("port".equals(key)) {
                    domain.setPort(properties.getProperty(key));
                }
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(Objects.nonNull(domain.getIp()) && Objects.nonNull(domain.getPort())) {
            logger.log(Level.INFO, "ip="+ domain.getIp()+" port="+domain.getPort());
        } else {
            logger.log(Level.FINE, "boot.properties配置出错");
        }
        return domain;
    }

}