package com.pst;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MyBufferClass {
    private ConcurrentHashMap<Integer, ByteArrayInputStream> map = new ConcurrentHashMap<>();
    private volatile AtomicInteger atomicInteger = new AtomicInteger(0);

    public ConcurrentHashMap<Integer, ByteArrayInputStream> getMap() {
        return map;
    }

    public void setMap(ConcurrentHashMap<Integer, ByteArrayInputStream> map) {
        this.map = map;
    }

    public synchronized Integer add(byte[] bytes) {
        ByteArrayInputStream byteBuffer = new ByteArrayInputStream(bytes);
        this.map.put(atomicInteger.incrementAndGet(), byteBuffer);
        int id = atomicInteger.get();
        System.out.println(id);
        return id;
    }

    public byte[] getBytes(Integer id) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] data = new byte[16];
        if(this.map.get(id).equals(null)){
            System.out.println(new Exception("нет такого элемента"));
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
    }
}
