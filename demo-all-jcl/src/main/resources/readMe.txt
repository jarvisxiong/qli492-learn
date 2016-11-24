1. 严格的说，commons-logging不是一个日志控件，没有日志功能，它只是统一了JDK Logging与Log4j的API，
并把日志功能交给JDK Loggings或者是log4j。对于不能确定日志方式的系统，commons-logging是一个不错的选择，
Spring，Hibernate，Struts等使用的都是commons-logging

2.slf4j:Simple Logging Facade for Java 为java提供的简单日志Facade。Facade：门面，更底层一点说就是接口。
他允许用户以自己的喜好，在工程中通过slf4j接入不同的日志系统。
更直观一点，slf4j是个数据线，一端嵌入程序，另一端链接日志系统，从而实现将程序中的信息导入到日志系统并记录。

slf4j入口就是众多接口的集合，他不负责具体的日志实现，只在编译时负责寻找合适的日志系统进行绑定。具体有哪些接口，全部都定义在slf4j-api中。查看slf4j-api源码就可以发现，里面除了public final class LoggerFactory类之外，都是接口定义。因此，slf4j-api本质就是一个接口定义。


Log 是一个接口声明。LogFactory 的内部会去装载具体的日志系统，并获得实现该Log 接口的实现类。
而内部有一个Log4JLogger 实现类对Log 接口同时内部提供了对log4j logger 的代理。
LogFactory 内部装载日志系统流程：
1.   首先，寻找org.apache.commons.logging.LogFactory 属性配置
2.   否则，利用JDK1.3 开始提供的service 发现机制，会扫描classpah 下的META-INF/services/org.apache.commons.logging.LogFactory 文件，若找到则装载里面的配置，使用里面的配置。
3.   否则，从Classpath 里寻找commons-logging.properties ，找到则根据里面的配置加载。
4.   否则，使用默认的配置：如果能找到Log4j 则默认使用log4j 实现，如果没有则使用JDK14Logger 实现，再没有则使用commons-logging 内部提供的SimpleLog 实现。
从上述加载流程来看，如果没有做任何配置，只要引入了log4j 并在classpath 配置了log4j.xml ，则commons-logging 就会使log4j 使用正常，而代码里不需要依赖任何log4j 的代码。