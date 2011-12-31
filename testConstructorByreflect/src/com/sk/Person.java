package com.sk;

public class Person {
    
    public Person(){
        System.out.println("无参");
    }
    
    public Person(String name){
        System.out.println(name);
    }
    
    public Person(String name, String number){
        System.out.println("我是第二个构造方法");
    }
    
    public void getPerson(){
        System.out.println("我是一个person");
    }
}
