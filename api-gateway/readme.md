默认链接到哪个注册中心 eureka ，eureka下面所有的服务都会被代理
转发规则
转发到eureka-client服务的请求规则为：/eureka-client/**
转发到eureka-consumer服务的请求规则为：/eureka-consumer/**
比如访问：http://localhost:1101/eureka-client/dc ，该请求将最终被路由到eureka-client的/dc接口上。