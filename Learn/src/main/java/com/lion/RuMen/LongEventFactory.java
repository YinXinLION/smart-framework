package com.lion.RuMen;

import com.lmax.disruptor.EventFactory;

/**
 * Created by 殷鑫 on 2017/5/19.
 */
public class LongEventFactory implements EventFactory {
    //Disruptor创建事件，声明一个EventFactory实例化Event对象
    public Object newInstance() {
        return new LongEvent();
    }
}
