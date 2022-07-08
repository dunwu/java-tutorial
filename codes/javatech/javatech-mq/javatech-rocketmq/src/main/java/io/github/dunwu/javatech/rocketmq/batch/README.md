# Rocket 官方示例之 Batch Example

批量发送消息提高了传递小消息的性能。

同一批次的消息应该具有：相同的主题、相同的 waitStoreMsgOK 并且不支持调度。

此外，一批消息的总大小不应超过 1MiB。

## 参考资料

- [Batch Example](https://rocketmq.apache.org/docs/batch-example/)
