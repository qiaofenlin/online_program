# 单体应用 与 微服务
微服务：架构风格（服务维纳化）
一个应用事一组小型服务;可以通过HTTP的方式进行互通。
每一个功能元素最终都是一个可替换和独立的软件单元

# springBoot Data 
```text
springBoot 默认支持jdbc baseDataSource 和自定义数据源（利用反射）

执行创建表和数据
```

```text
druid 数据源 1.1.8 alibaba
```

```text
mybaits ：
```

# config 自动配置原理

# 日志

```text
日志门面(抽象)  日志实现(实现)
jcl             log4j()
[slf4j (***)](https://www.slf4j.org/manual.html)     logback(***)
jboos-logging   
```
> 开发中，日志记录方法的调用，应该调用日志抽象层里的方法，不应该直接调用实现类。

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {
  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger(HelloWorld.class);
    logger.info("Hello World");
  }
}
```
#遗留问题：
统一到slf4j,1.将系统气体日志框架排除出去
2.用中间包来替换原有日志框架3.用slf4j实现。


































