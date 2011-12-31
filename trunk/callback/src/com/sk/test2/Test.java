package com.sk.test2;

import com.sk.test2.FooBar.ICallBack;

public class Test implements ICallBack{
    public static void main(String[] args) {
//        FooBar foo = new FooBar();
//        foo.setCallBack(new ICallBack() {
//            public void postExec() {
//                System.out.println("method executed.");
//            }
//        });
        
        FooBar foo = new FooBar();
//        foo.setCallBack(new ICallBack() {
//            
//            @Override
//            public void postExec() {
//                // TODO Auto-generated method stub
//                System.out.println("ni hao a ....");
//            }
//        });
        foo.setCallBack(new Test());
        foo.doSth();
    }

    @Override
    public void postExec() {
        // TODO Auto-generated method stub
        System.out.println("= = = = = ");
    }
}