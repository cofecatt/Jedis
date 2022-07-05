package socket.loader;

import basic.Domain;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author: HLJ
 * @Date: 2022/7/5 11:52
 */
public class CustomLoader {
    private final static Logger logger = Logger.getLogger("CustomLoader");

    public static String getResource(String key) {

        Properties properties = new Properties();
        try {
            InputStream inputStream = DomainLoader.class.getResourceAsStream("/boot.properties");
            properties.load(inputStream);
            Iterator<String> it = properties.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String k = it.next();
                if(k.equals(key)) {
                    logger.info(key+":"+properties.getProperty(k));
                    return properties.getProperty(k);
                }
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.log(Level.WARNING,"没有找到"+key+"的属性！");
        return null;
    }
}
