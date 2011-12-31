package com.sk.test;

public class B implements Caller.MyCallInterface {

    @Override
    public void method() {
        System.out.println("回调");
    }

    public static void main(String args[]) {
        Caller call = new Caller();
        call.setCallfuc(new B());
        call.call();
    }
}