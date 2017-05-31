package com.lion.RuMen;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by 殷鑫 on 2017/5/19.
 */
public class LongEventMain {
    public static void  main(String[] args) throws InterruptedException {
        // Executor that will be used to construct new threads for consumers
        //创建线程池
        Executor executor = Executors.newCachedThreadPool();
        // The factory for the event
        //得到一个事件工厂
        LongEventFactory factory = new LongEventFactory();
        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;
        // Construct the Disruptor
        //创建disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, bufferSize, executor);
        // Connect the handler
        //事件处理
        disruptor.handleEventsWith(new LongEventHandler());
        // Start the Disruptor, starts all threads running
        disruptor.start();
        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        //事件生成者
        LongEventProducer producer = new LongEventProducer(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++) {
            bb.putLong(0, l);
            producer.onData(bb);
            Thread.sleep(1000);
        }
    }
}
