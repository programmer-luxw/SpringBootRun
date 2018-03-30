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
public class RequestQueryHandler
{

    private final static Logger LOGGER = LoggerFactory.getLogger(RequestQueryHandler.class);

    private final EventBus eventBus;

    public RequestQueryHandler(EventBus eventBus)
    {
        this.eventBus = eventBus;
    }

    @Subscribe
    public void doQuery(Request request)
    {
        LOGGER.info("start query the orderNo [{}]", request.toString());
        Response response = new Response();
        this.eventBus.post(response);
    }
}
