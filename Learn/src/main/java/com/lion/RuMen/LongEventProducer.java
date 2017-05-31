package com.lion.RuMen;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * Created by 殷鑫 on 2017/5/19.
 */
//这是一个生成事件源，事件源使用一个ByteBuffer来模拟接收的数据(即事件源会在IO读取到一部分数据的时候触发事件)
public class LongEventProducer {
    private final RingBuffer<LongEvent> ringBuffer;
    public LongEventProducer(RingBuffer<LongEvent> ringBuffer){
        this.ringBuffer = ringBuffer;
    }

/**
 * onData用来发布事件，每调用一次就发布一次事件事件
 * 它的参数会通过事件传递给消费者
 **/
    public void onData(ByteBuffer bb) {
        //可以把ringBuffer看做一个事件队列，那么next就是得到下面一个事件槽
        long sequence = ringBuffer.next();
        try {
            //用上面的索引取出一个空的事件用于填充
            LongEvent event = ringBuffer.get(sequence);// for the sequence
            event.setValue(bb.getLong(0));
        } finally {
            //发布事件
            ringBuffer.publish(sequence);
        }
    }
}
