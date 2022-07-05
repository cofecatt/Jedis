package protocol;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生成全局递增的消息序列号
 * @author HLJ
 */
public abstract class SequenceIdGenerator {
    private static final AtomicInteger id = new AtomicInteger();

    public static int nextId() {
        return id.incrementAndGet();
    }
}
