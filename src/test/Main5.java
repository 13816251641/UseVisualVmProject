package test;

import java.util.ArrayList;
import java.util.List;

/**
 * 总结:TestMemory类型的数组也会被gc回收的,jvm会判断有没有别的引用它,
 * 如果没有array和TestMemory也会被回收!!!
 * old gen gc的时候将会stop the world!
 */
public class Main5 {

    public static void main(String[] args) {
        TestMemory [] array = new TestMemory[24000000];
        try {
            Thread.sleep(10000);//给打开visualvm时间
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //循环添加对象到缓存
        for(int i=0; i<1000000; i++){
            TestMemory t = new TestMemory();
            array[i] = t;
        }
        System.out.println("first");
        //为dump出堆提供时间
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=1000000; i<2000000; i++){
            TestMemory t = new TestMemory();
            array[i] = t;
        }
        System.out.println("second");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=2000000; i<5000000;i++){
            TestMemory t = new TestMemory();
            array[i] = t;
        }
        System.out.println("third");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i = 5000000; i<24000000;i++){
            TestMemory t = new TestMemory();
            array[i] = t;
        }
        System.out.println("forth");
        /*
           往内存中放入了很多Person对象,导致old gen大小
           不够开启gc,gc会回收TestMemory数组,但是我后面
           有array.length的打印,所以导致TestMemory类型的数组
           无法被回收,导致系统不断在gc,gc会导致stop the world,
           卡死在gc上然后System.out.println(array.length);一直无法打印
         */
        List<Person> list = new ArrayList<>();
        for(int i=0;i<=1000000;i++){
            list.add(new Person());
        }
        /*
           如果没有array.length,gc可以正常回收TestMemory类型的数组
         */
        System.out.println(array.length);
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("qqqq");
    }
}
