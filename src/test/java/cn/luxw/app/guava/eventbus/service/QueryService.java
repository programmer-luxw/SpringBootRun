package cn.luxw.app.guava.eventbus.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import cn.luxw.app.guava.eventbus.events.Request;
import cn.luxw.app.guava.eventbus.events.Response;

/***************************************
 * @author:Alex Wang
 * @Date:2017/10/19
 * 532500648
 ***************************************/
public class QueryService
{

    private final static Logger LOGGER = LoggerFactory.getLogger(QueryService.class);

    private final EventBus eventBus;

    public QueryService(EventBus eventBus)
    {
        this.eventBus = eventBus;
        this.eventBus.register(this);
    }

    public void query(String orderNo)
    {
        LOGGER.info("Received the orderNo [{}]", orderNo);
        this.eventBus.post(new Request(orderNo));
    }

    @Subscribe
    public void handleResponse(Response response)
    {
        LOGGER.info("{}", response);
    }
}
