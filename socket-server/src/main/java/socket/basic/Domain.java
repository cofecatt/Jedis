package socket.basic;

/**
 * @Author: HLJ
 * @Date: 2022/5/20 19:43
 */
public class Domain {
    private String ip;
    private String port;
    private Integer minMapSize;

    public String getIp() {
        return ip;
    }

    @Override
    public String toString() {
        return "Domain{" +
                "ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", minMapSize=" + minMapSize +
                '}';
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getMinMapSize() {
        return minMapSize;
    }

    public void setMinMapSize(Integer minMapSize) {
        this.minMapSize = minMapSize;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
