import com.pst.MyBufferClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.concurrent.ConcurrentHashMap;


public class MyBufferClassTest {
    private MyBufferClass myBufferClass;
    @Before
        public void initMap(){
        this.myBufferClass = new MyBufferClass();
        this.myBufferClass.setMap(new ConcurrentHashMap<Integer, ByteArrayInputStream>());

    }


    @Test
    public void add() {
        byte [] bytes = new byte[]{1};
        Integer id = myBufferClass.add(bytes);
        Assert.assertEquals(1L,id.longValue());
    }

    @Test
    public void getBytes() {
        byte [] bytes = new byte[]{1};
        myBufferClass.add(bytes);
        byte [] bytes1 = myBufferClass.getBytes(1);
        Assert.assertArrayEquals(bytes,bytes1);
    }
}