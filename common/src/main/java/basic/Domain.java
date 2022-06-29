package basic;

/**
 * @Author: HLJ
 * @Date: 2022/5/20 19:43
 */
public class Domain {
    private String ip;
    private String port;
    private int minMapSize;

    public int getMinMapSize() {
        return minMapSize;
    }

    public void setMinMapSize(int minMapSize) {
        this.minMapSize = minMapSize;
    }

    public String getIp() {
        return ip;
    }

    @Override
    public String toString() {
        return "Domain{" +
                "ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                '}';
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
