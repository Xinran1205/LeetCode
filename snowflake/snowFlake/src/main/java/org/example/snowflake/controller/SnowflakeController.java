package org.example.snowflake.controller;

import org.example.snowflake.util.SnowflakeIdGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 提供生成唯一ID的测试接口
 */
@RestController
@RequestMapping("/api/snowflake")
public class SnowflakeController {

    private final SnowflakeIdGenerator idGenerator;

    public SnowflakeController(SnowflakeIdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    /**
     * GET 请求生成一个唯一ID
     *
     * @return 唯一的64位ID
     */
    @GetMapping("/nextId")
    public ResponseEntity<Long> getNextId() {
        long id = idGenerator.nextId();
        return ResponseEntity.ok(id);
    }
}
