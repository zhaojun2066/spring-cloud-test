package com.jufeng.springcloud.gateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: zhaojun(JUENG)
 * \* Date: 2018/11/9
 * \* Time: 10:23
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class AccessFilter extends ZuulFilter {
    private static Logger Logger = LoggerFactory.getLogger(AccessFilter.class);

    /**
     * PRE：请求被路由之前被调用，身份验证
     * ROUTING：将请求路由到微服务。
     * POST：路由到微服务之后执行
     * ERROR：在其他阶段发生错误时执行。
     * @return
     * 返回过滤器的类型：pre、route、post、error
     */
    //过滤器的类型，它决定过滤器在请求的哪个生命周期中执行。这里定义为pre，代表会在请求被路由之前执行
    @Override
    public String filterType() {
        return "pre";
    }


    //过滤器的执行顺序。当请求在一个阶段中存在多个过滤器时，需要根据该方法返回的值来依次执行。
    //数字越小，优先级越高
    @Override
    public int filterOrder() {
        return 1;
    }


    //判断该过滤器是否需要被执行。这里我们直接返回了true，因此该过滤器对所有请求都会生效。实际运用中我们可以利用该函数来指定过滤器的有效范围
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑。这里我们通过ctx.setSendZuulResponse(false)令zuul过滤该请求，不对其进行路由，
     * 然后通过ctx.setResponseStatusCode(401)设置了其返回的错误码，
     * 当然我们也可以进一步优化我们的返回，比如，通过ctx.setResponseBody(body)对返回body内容进行编辑等
     * @return
     */
    @Override
    public Object run() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        String host = request.getRemoteHost();
        Logger.info("<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Logger.info("<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Logger.info("<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Logger.info("                    请求的host:{}                          ", host);
        Logger.info("<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Logger.info("<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Logger.info("<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return null;
    }
}