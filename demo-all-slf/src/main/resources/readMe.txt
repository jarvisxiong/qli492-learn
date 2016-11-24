1. 桥阶层和对应的实现jar是不能共存的，比如log4j-over-slf4j和slf4j-log4j，jul-to-slf4j和slf4j-jdk14，这个很好理解，会有死循环，启动也会报错。
这种想象也就是说jar之前有互斥性，怎么使用maven有效解决“全局排除”会在以后的博文中讲解。
jcl-over-slf4j是把对jcl的调用桥接到slf4j上，前文说到它和jcl是互斥的。图中的红线就表示互斥关系。
当然slf4j也提供了可以把对slf4j的调用桥接到JCL上的工程包——slf4j-jcl，可以看出slf4j的设计者考虑非常周到，想想这样的情况：
遗留系统使用的是JCL+log4j，因为系统功能演进，依赖了其他业务线的库，恰好那个库依赖了slf4j-api，并且应用需要关心这个库的日志，
那么就需要转接日志到JCL上即可。细心的你可能一经发现，slf4j-jcl和jcl-over-slf4j也是互斥的，太多互斥的了:(。
对于log4j2的加入，也很简单，和logback是很相似的，如下图：


2.Commons-logging+log4j+slf4j
如果在原有commons-logging 系统里，如果要迁移到slf4j, 使用slf4j 替换commons-logging ，也是可以做到的。原理使用到了上述commons-logging 加载的第二点。需要引入Org.slf4j.jcl-over-slf4j-1.5.6.jar 。这个jar 包提供了一个桥接，让底层实现是基于slf4j 。原理是在该jar 包里存放了配置META-INF/services/org.apache.commons.logging.LogFactory =org.apache.commons.logging.impl.SLF4JLogFactory ，而commons-logging 在初始化的时候会找到这个serviceId ，并把它作为LogFactory 。
完成桥接后，那么那么简单日志门面SLF4J 内部又是如何来装载合适的log 呢？
原理是SLF4J 会在编译时会绑定import org.slf4j.impl.StaticLoggerBinder; 该类里面实现对具体日志方案的绑定接入。任何一种基于slf4j 的实现都要有一个这个类。如：
org.slf4j.slf4j-log4j12-1.5.6: 提供对 log4j 的一种适配实现。
Org.slf4j.slf4j-simple-1.5.6: 是一种 simple 实现，会将 log 直接打到控制台。
……
那么这个地方就要注意了：如果有任意两个实现slf4j 的包同时出现，那就有可能酿就悲剧，你可能会发现日志不见了、或都打到控制台了。原因是这两个jar 包里都有各自的org.slf4j.impl.StaticLoggerBinder ，编译时候绑定的是哪个是不确定的。这个地方要特别注意！！出现过几次因为这个导致日志错乱的问题。