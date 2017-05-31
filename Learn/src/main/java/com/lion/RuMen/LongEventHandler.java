package com.lion.RuMen;

import com.lmax.disruptor.EventHandler;

/**
 * Created by 殷鑫 on 2017/5/19.
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    //需要一个事件消费者，事件处理器，把事件中存储的数据打印到终端
    public void onEvent(LongEvent event ,long l, boolean b)throws Exception{
        System.out.println(event.getValue());
    }
}
