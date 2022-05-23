import socket.basic.Domain;
import socket.basic.Response;
import socket.core.LejClient;
import socket.core.ResourceLoader;

/**
 * @Author: HLJ
 * @Date: 2022/5/20 19:53
 */
public class Test {
    public static void main(String[] args) {
//        LejClient.execute("set", "a", "b");
        Response execute = LejClient.execute("get", "a", null);
    }
}
