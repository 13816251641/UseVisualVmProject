package test;

import java.util.HashMap;
import java.util.Map;

/**
 * TestMemory和map变量引用的HashMap类会被gc回收,
 * 因为我在代码里加了map = null;
 */
public class Main2 {

    /*声明缓存对象*/
    private static Map map = new HashMap();

    public static void main(String[] args) {
        try {
            Thread.sleep(10000);//给打开visualvm时间
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //循环添加对象到缓存
        for(int i=0; i<1000000;i++){
            TestMemory t = new TestMemory();
            map.put("key"+i,t);
        }
        System.out.println("first");
        //为dump出堆提供时间
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=0; i<1000000;i++){
            TestMemory t = new TestMemory();
            map.put("key"+i,t);
        }
        System.out.println("second");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=0; i<3000000;i++){
            TestMemory t = new TestMemory();
            map.put("key"+i,t);
        }
        System.out.println("third");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=0; i<4000000;i++){
            TestMemory t = new TestMemory();
            map.put("key"+i,t);
        }
        System.out.println("forth");
        map = null;
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("qqqq");
    }
}
