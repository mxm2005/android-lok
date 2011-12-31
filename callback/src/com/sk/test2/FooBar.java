package com.sk.test2;

public class FooBar {
    private ICallBack callBack;

    public void setCallBack(ICallBack callBack) {
        this.callBack = callBack;
    }

    public void doSth() {
        if (callBack != null) callBack.postExec();
    }
    
    public interface ICallBack {
        void postExec();
    }
}