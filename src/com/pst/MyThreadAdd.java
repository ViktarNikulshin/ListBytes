package com.pst;

public class MyThreadAdd implements Runnable {
    private MyBufferClass myBufferClass;
    private byte[] buffer;

    public MyThreadAdd(MyBufferClass myBufferClass, byte[] buffer) {
        this.myBufferClass = myBufferClass;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        myBufferClass.add(this.buffer);
    }
}
