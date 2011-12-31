package com.sk.test;

public class Caller {
    public MyCallInterface mc;
    
    //我还需要定义一个接口，以便程序员B根据我的定义编写程序实现接口。
    public interface MyCallInterface {
        public void method();
    }

    public void setCallfuc(MyCallInterface mc) {
        this.mc = mc;
    }

    public void call() {
        if (mc != null) this.mc.method();
    }
}
