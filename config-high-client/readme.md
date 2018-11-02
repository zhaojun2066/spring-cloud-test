### 刷新
       加入监控模块
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-actuator</artifactId>
       </dependency>
       当git 更改之后，需要发送post
       通过POST请求发送到http://localhost:7002/refresh
       来更新本地的值