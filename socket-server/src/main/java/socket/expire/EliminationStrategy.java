package socket.expire;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @Author: HLJ
 * @Date: 2022/7/6 20:54
 */
public interface EliminationStrategy {
    /**
     * 淘汰跳跃表的节点
     * @param skipList
     */
    void eliminate(ConcurrentSkipListMap skipList);

    /**
     * 淘汰map的节点
     * @param map
     */
    void eliminate(Map<String, Object> map);

}
