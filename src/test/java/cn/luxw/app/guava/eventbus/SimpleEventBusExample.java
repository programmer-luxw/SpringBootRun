package cn.luxw.app.guava.eventbus;

import com.google.common.eventbus.EventBus;

import cn.luxw.app.guava.eventbus.listeners.SimpleListener;

/***************************************
 * @author:Alex Wang
 * @Date:2017/10/18
 * 532500648
 ***************************************/
public class SimpleEventBusExample
{
    public static void main(String[] args)
    {
        final EventBus eventBus = new EventBus();
        eventBus.register(new SimpleListener());
        System.out.println("post the simple event.");
        eventBus.post("Simple Event");
    }
}
