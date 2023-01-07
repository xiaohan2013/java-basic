基础性技术
===

* Netty

* Thrift

* Protobuf
  - RPC
  - 阻塞与非阻塞
```
对于 protobuf-rpc-pro应用优化建议：
1.配置JVM options

-server -Xms2048m -Xmx2048m -XX:+UseParallelGC -XX:+AggressiveOpts -XX:+UseFastAccessorMethods

2.如果不是客户端和服务端双向调用，将ServerBootstrap中的使用相同的ThreadExecutor

3.关闭日志

// RPC payloads are uncompressed when logged - so reduce logging
 CategoryPerServiceLogger logger = new CategoryPerServiceLogger();
 logger.setLogRequestProto(false);
 logger.setLogResponseProto(false);
 factory.setRpcLogger(logger);

```

* Leader Election
- ZooKeeper-based leader election

