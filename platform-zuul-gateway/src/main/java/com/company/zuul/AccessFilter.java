package com.company.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * User: zjt
 * DateTime: 2017/6/24 14:16
 */
public class AccessFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    /**
     * 过滤器的类型， 它决定过滤器在请求的哪个生命周期中执行。 pre, 代表会在请求被路由之前执行
     * pre: 可以在请求被路由之前调用。
     * routing: 在路由请求时被调用。
     * post: 在 routing 和 error 过滤器之后被调用。
     * error: 处理请求时发生错误时被调用。
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 执行顺序：通过 int 值来定义过滤器的执行顺序， 数值越小优先级越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 执行条件：返回一个 boolean 值来判断该过滤器是否要执行。 我们可以通过此方法来指定过滤器的有效范围
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑
     * @return
     */
    @Override
    public Object run() {

        //获取上下文、request对象
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        //判断是否存在accessToken
        Object accessToken = request.getParameter("accessToken");


        //不存在则过滤,不进行请求路由,并返回403
        if(accessToken == null){
            logger.info("access token is empty");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(403);
            return null;
        }

        context.setSendZuulResponse(true);      // 对该请求进行路由
        context.setResponseStatusCode(200);

        logger.info("access token ok");

        return null;
    }

}