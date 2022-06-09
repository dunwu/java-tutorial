package io.github.dunwu.javatech.java.samples;

/**
 * WebSocket 消息类型
 */
public enum WebSocketMsgType {
    /** 连接 */
    CONNECT,
    /** 关闭 */
    CLOSE,
    /** 信息 */
    INFO,
    /** 错误 */
    ERROR
}
