"# spring-cloud-test"

## Hystrix
### HystrixCommand annotation
    fallbackMethod : 容错的方法名称
                    .以下四种情况将触发getFallback调用：
                     1.)run()方法抛出非HystrixBadRequestException异常。
                     2.)run()方法调用超时
                     3.)熔断器开启拦截调用
                     4.)线程池/队列/信号量是是否跑满
                        实现getFallback()后,执行命令时遇到以上4中情况将被fallback接管,
                        不会抛出异常或其他。
    groupKey： 命令分组key 当对同一业务依赖做隔离时使用CommandGroup做区分,
               虽然在业务上都是相同的组，但是需要在资源上做隔离时，
               	可以使用HystrixThreadPoolKey区分.
    commandKey： 命令key
    threadPoolKey：HystrixThreadPoolKey

### HystrixCommand commandProperties threadPoolProperties 属性列表
       demo：
        @HystrixCommand(commandProperties = {
                  @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
                  @HystrixProperty(name = "execution.timeout.enabled", value = "false")},
                  threadPoolProperties = {
                                    @HystrixProperty(name = "coreSize", value = "2"),
                                    @HystrixProperty(name = "maxQueueSize", value = "2")},
                  fallbackMethod = "findByIdFallback")
          public User findById(@PathVariable Long id) {
            return this.restTemplate.getForObject("http://microservice-provider-user/simple/" + id, User.class);
          }


       hystrix.command.default和hystrix.threadpool.default中的default为默认CommandKey，default 是所有实例的默认值

       Command Properties
       Execution相关的属性的配置：
       hystrix.command.default.execution.isolation.strategy 隔离策略，默认是Thread, 可选Thread｜Semaphore

       hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds 命令执行超时时间，默认1000ms

       hystrix.command.default.execution.timeout.enabled 执行是否启用超时，默认启用true
       hystrix.command.default.execution.isolation.thread.interruptOnTimeout 发生超时是是否中断，默认true
       hystrix.command.default.execution.isolation.semaphore.maxConcurrentRequests 最大并发请求数，默认10，该参数当使用ExecutionIsolationStrategy.SEMAPHORE策略时才有效。如果达到最大并发请求数，请求会被拒绝。理论上选择semaphore size的原则和选择thread size一致，但选用semaphore时每次执行的单元要比较小且执行速度快（ms级别），否则的话应该用thread。
       semaphore应该占整个容器（tomcat）的线程池的一小部分。
       Fallback相关的属性
       这些参数可以应用于Hystrix的THREAD和SEMAPHORE策略

       hystrix.command.default.fallback.isolation.semaphore.maxConcurrentRequests 如果并发数达到该设置值，请求会被拒绝和抛出异常并且fallback不会被调用。默认10
       hystrix.command.default.fallback.enabled 当执行失败或者请求被拒绝，是否会尝试调用hystrixCommand.getFallback() 。默认true
       Circuit Breaker相关的属性
       hystrix.command.default.circuitBreaker.enabled 用来跟踪circuit的健康性，如果未达标则让request短路。默认true
       hystrix.command.default.circuitBreaker.requestVolumeThreshold 一个rolling window内最小的请求数。如果设为20，那么当一个rolling window的时间内（比如说1个rolling window是10秒）收到19个请求，即使19个请求都失败，也不会触发circuit break。默认20
       hystrix.command.default.circuitBreaker.sleepWindowInMilliseconds 触发短路的时间值，当该值设为5000时，则当触发circuit break后的5000毫秒内都会拒绝request，也就是5000毫秒后才会关闭circuit。默认5000
       hystrix.command.default.circuitBreaker.errorThresholdPercentage错误比率阀值，如果错误率>=该值，circuit会被打开，并短路所有请求触发fallback。默认50
       hystrix.command.default.circuitBreaker.forceOpen 强制打开熔断器，如果打开这个开关，那么拒绝所有request，默认false
       hystrix.command.default.circuitBreaker.forceClosed 强制关闭熔断器 如果这个开关打开，circuit将一直关闭且忽略circuitBreaker.errorThresholdPercentage
       Metrics相关参数
       hystrix.command.default.metrics.rollingStats.timeInMilliseconds 设置统计的时间窗口值的，毫秒值，circuit break 的打开会根据1个rolling window的统计来计算。若rolling window被设为10000毫秒，则rolling window会被分成n个buckets，每个bucket包含success，failure，timeout，rejection的次数的统计信息。默认10000
       hystrix.command.default.metrics.rollingStats.numBuckets 设置一个rolling window被划分的数量，若numBuckets＝10，rolling window＝10000，那么一个bucket的时间即1秒。必须符合rolling window % numberBuckets == 0。默认10
       hystrix.command.default.metrics.rollingPercentile.enabled 执行时是否enable指标的计算和跟踪，默认true
       hystrix.command.default.metrics.rollingPercentile.timeInMilliseconds 设置rolling percentile window的时间，默认60000
       hystrix.command.default.metrics.rollingPercentile.numBuckets 设置rolling percentile window的numberBuckets。逻辑同上。默认6
       hystrix.command.default.metrics.rollingPercentile.bucketSize 如果bucket size＝100，window＝10s，若这10s里有500次执行，只有最后100次执行会被统计到bucket里去。增加该值会增加内存开销以及排序的开销。默认100
       hystrix.command.default.metrics.healthSnapshot.intervalInMilliseconds 记录health 快照（用来统计成功和错误绿）的间隔，默认500ms
       Request Context 相关参数
       hystrix.command.default.requestCache.enabled 默认true，需要重载getCacheKey()，返回null时不缓存
       hystrix.command.default.requestLog.enabled 记录日志到HystrixRequestLog，默认true

       Collapser Properties 相关参数
       hystrix.collapser.default.maxRequestsInBatch 单次批处理的最大请求数，达到该数量触发批处理，默认Integer.MAX_VALUE
       hystrix.collapser.default.timerDelayInMilliseconds 触发批处理的延迟，也可以为创建批处理的时间＋该值，默认10
       hystrix.collapser.default.requestCache.enabled 是否对HystrixCollapser.execute() and HystrixCollapser.queue()的cache，默认true

       ThreadPool 相关参数
       线程数默认值10适用于大部分情况（有时可以设置得更小），如果需要设置得更大，那有个基本得公式可以follow：
       requests per second at peak when healthy × 99th percentile latency in seconds + some breathing room
       每秒最大支撑的请求数 (99%平均响应时间 + 缓存值)
       比如：每秒能处理1000个请求，99%的请求响应时间是60ms，那么公式是：
       （0.060+0.012）

       基本得原则时保持线程池尽可能小，他主要是为了释放压力，防止资源被阻塞。
       当一切都是正常的时候，线程池一般仅会有1到2个线程激活来提供服务

       hystrix.threadpool.default.coreSize 并发执行的最大线程数，默认10
       hystrix.threadpool.default.maxQueueSize BlockingQueue的最大队列数，当设为－1，会使用SynchronousQueue，值为正时使用LinkedBlcokingQueue。该设置只会在初始化时有效，之后不能修改threadpool的queue size，除非reinitialising thread executor。默认－1。
       hystrix.threadpool.default.queueSizeRejectionThreshold 即使maxQueueSize没有达到，达到queueSizeRejectionThreshold该值后，请求也会被拒绝。因为maxQueueSize不能被动态修改，这个参数将允许我们动态设置该值。if maxQueueSize == -1，该字段将不起作用
       hystrix.threadpool.default.keepAliveTimeMinutes 如果corePoolSize和maxPoolSize设成一样（默认实现）该设置无效。如果通过plugin（https://github.com/Netflix/Hystrix/wiki/Plugins）使用自定义实现，该设置才有用，默认1.
       hystrix.threadpool.default.metrics.rollingStats.timeInMilliseconds 线程池统计指标的时间，默认10000
       hystrix.threadpool.default.metrics.rollingStats.numBuckets 将rolling window划分为n个buckets，默认10
### 断路器
       断路器的三个重要参数：快照时间窗、请求总数下限、错误百分比下限。这个参数的作用分别是：

       快照时间窗：断路器确定是否打开需要统计一些请求和错误数据，而统计的时间范围就是快照时间窗，默认为最近的10秒。
       请求总数下限：在快照时间窗内，必须满足请求总数下限才有资格根据熔断。默认为20，意味着在10秒内，如果该hystrix命令的调用此时不足20次，即时所有的请求都超时或其他原因失败，断路器都不会打开。
       错误百分比下限：当请求总数在快照时间窗内超过了下限，比如发生了30次调用，如果在这30次调用中，有16次发生了超时异常，也就是超过50%的错误百分比，在默认设定50%下限情况下，这时候就会将断路器打开。

       那么当断路器打开之后会发生什么呢？我们先来说说断路器未打开之前，对于之前那个示例的情况就是每个请求都会在当hystrix超时之后返回fallback，每个请求时间延迟就是近似hystrix的超时时间，如果设置为5秒，
       那么每个请求就都要延迟5秒才会返回。当熔断器在10秒内发现请求总数超过20，并且错误百分比超过50%，这个时候熔断器打开。打开之后，再有请求调用的时候，将不会调用主逻辑，而是直接调用降级逻辑，这个时候就
       不会等待5秒之后才返回fallback。通过断路器，实现了自动地发现错误并将降级逻辑切换为主逻辑，减少响应延迟的效果。