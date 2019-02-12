package com.lpcoder.agile.base.forj.memorydb;

/**
 * @author liurenpeng
 * @date Created in 19-2-12
 */
public class MemoryDBException extends RuntimeException {
    public MemoryDBException(String message) {
        super(message);
    }

    public MemoryDBException(String message, Throwable cause) {
        super(message, cause);
    }
}
