package com.chapter8.aidl;

import com.chapter8.aidl.IAIDLServerService.Stub;
import com.chapter8.aidl.IAIDLServerService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AidlServerService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return mBinder;
    }
    
    //在AIDL文件中定义的接口实现。
    private IAIDLServerService.Stub mBinder = new Stub() {
        
        @Override
        public String sayHello() throws RemoteException {
            // TODO Auto-generated method stub
            return "哈哈你好";
        }
        
        @Override
        public Book getBook() throws RemoteException {
            // TODO Auto-generated method stub
            Book mBook = new Book();
            mBook.setBookName("雄兵~~~~");
            mBook.setBookPrice(50);
            return mBook;
        }
    };
    
//    @Override
//    public IBinder onBind(Intent intent) {
//        return mBinder;
//    }
//
//    /**
//     * 在AIDL文件中定义的接口实现。
//     */
//    private IAIDLServerService.Stub mBinder = new Stub() {
//
//        public String sayHello() throws RemoteException {
//            return "Hello";
//        }
//
//        public Book getBook() throws RemoteException {
//            Book mBook = new Book();
//            mBook.setBookName("Android应用开发");
//            mBook.setBookPrice(50);
//            return mBook;
//        }
//    };
}