package com.pst;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyBufferClass {
    private ConcurrentHashMap<Integer, ByteArrayInputStream> map = new ConcurrentHashMap<>();
    private  AtomicInteger atomicInteger = new AtomicInteger(0);
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    Lock readLock = readWriteLock.readLock();
    Lock writeLock = readWriteLock.writeLock();

    public ConcurrentHashMap<Integer, ByteArrayInputStream> getMap() {
        return map;
    }


    public void setMap(ConcurrentHashMap<Integer, ByteArrayInputStream> map) {
        this.map = map;
    }

    public Integer add(byte[] bytes) {
        try {
            writeLock.lock();
            ByteArrayInputStream byteBuffer = new ByteArrayInputStream(bytes);
            this.map.put(atomicInteger.incrementAndGet(), byteBuffer);
            int id = atomicInteger.get();
            System.out.println(id);
            return id;
        } finally {
            writeLock.unlock();
        }

    }

    public byte[] getBytes(Integer id) {
        try {
            readLock.lock();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[16];
            if(!this.map.containsKey(id)){
                System.out.println("Element not found");
            }else {
                while ((nRead = this.map.get(id).read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }
                try {
                    buffer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(buffer.toByteArray().toString());
            }
            return buffer.toByteArray();
        }finally {
            readLock.unlock();
        }

    }
}
