package org.example.snowflake.util;

import org.example.snowflake.config.SnowflakeProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 企业级雪花算法实现（64 位ID生成器）
 *
 * 结构说明：
 * 1 位   符号位（固定为0）
 * 41 位  时间戳（毫秒，记录相对于自定义纪元的时间差）
 * 10 位  机器标识（分布式部署中保证唯一性，0~1023）
 * 12 位  序列号（同一毫秒内的计数器，0~4095）
 */
@Component
public class SnowflakeIdGenerator {

    // 自定义纪元（一般取一个过去的时间戳）
    private final long twepoch = 1288834974657L;

    // 各部分所占位数
    private final long machineIdBits = 10L;
    private final long sequenceBits = 12L;

    // 各部分最大值
    private final long maxMachineId = ~(-1L << machineIdBits); // 1023
    private final long maxSequence = ~(-1L << sequenceBits);     // 4095

    // 各部分左移位数
    private final long machineIdShift = sequenceBits;
    private final long timestampShift = sequenceBits + machineIdBits;

    // 当前节点的机器ID
    private long machineId;

    // 序列号和上一次时间戳
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    // 注入配置属性
    private final SnowflakeProperties properties;

    public SnowflakeIdGenerator(SnowflakeProperties properties) {
        this.properties = properties;
    }

    @PostConstruct
    public void init() {
        this.machineId = properties.getMachineId();
        if (machineId < 0 || machineId > maxMachineId) {
            throw new IllegalArgumentException(
                    String.format("machineId 必须在 0 和 %d 之间", maxMachineId));
        }
    }

    /**
     * 生成下一个唯一ID（线程安全）
     *
     * @return 全局唯一且有序的 64 位ID
     */
    public synchronized long nextId() {
        long currentTimestamp = timeGen();

        if (currentTimestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("系统时钟回拨异常：当前时间 %d 小于上次时间 %d", currentTimestamp, lastTimestamp));
        }

        if (currentTimestamp == lastTimestamp) {
            // 同一毫秒内，序列号递增
            sequence = (sequence + 1) & maxSequence;
            if (sequence == 0) {
                // 同一毫秒内序列号用完，等待下一毫秒
                currentTimestamp = waitNextMillis(lastTimestamp);
            }
        } else {
            // 不同毫秒内，序列号重置
            sequence = 0L;
        }
        lastTimestamp = currentTimestamp;

        return ((currentTimestamp - twepoch) << timestampShift)
                | (machineId << machineIdShift)
                | sequence;
    }

    /**
     * 等待直到下一个毫秒
     */
    private long waitNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 获取当前时间（毫秒）
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }
}


//3.1 作用
//注解 @PostConstruct：
//这个方法会在 Spring 完成依赖注入后自动执行。目的是进行一些初始化工作，比如从配置属性中读取机器 ID，并对其合法性进行验证。
// 3.2 为什么不放在构造器中？
//依赖注入顺序：
//
//在 Spring 的生命周期中，构造器执行时，有时依赖的属性可能还没有完全注入。
//使用 @PostConstruct 方法，可以保证所有依赖（例如通过 @ConfigurationProperties 注入的 SnowflakeProperties）都已经设置完毕。
//分离职责：
//
//将初始化逻辑放到 @PostConstruct 方法中，可以让构造器只负责依赖注入，而初始化（包括配置验证）则在依赖注入完成后统一处理，使代码更清晰。
//灵活性：
//
//如果后续需要增加更多的初始化逻辑，@PostConstruct 方法提供了一个统一的入口，而不需要在构造器中写太多逻辑。