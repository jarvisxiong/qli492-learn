1. 桥阶层和对应的实现jar是不能共存的，比如log4j-over-slf4j和slf4j-log4j，jul-to-slf4j和slf4j-jdk14，这个很好理解，会有死循环，启动也会报错。
这种想象也就是说jar之前有互斥性，怎么使用maven有效解决“全局排除”会在以后的博文中讲解。
jcl-over-slf4j是把对jcl的调用桥接到slf4j上，前文说到它和jcl是互斥的。图中的红线就表示互斥关系。
当然slf4j也提供了可以把对slf4j的调用桥接到JCL上的工程包——slf4j-jcl，可以看出slf4j的设计者考虑非常周到，想想这样的情况：
遗留系统使用的是JCL+log4j，因为系统功能演进，依赖了其他业务线的库，恰好那个库依赖了slf4j-api，并且应用需要关心这个库的日志，
那么就需要转接日志到JCL上即可。细心的你可能一经发现，slf4j-jcl和jcl-over-slf4j也是互斥的，太多互斥的了:(。
对于log4j2的加入，也很简单，和logback是很相似的，如下图：