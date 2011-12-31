package com.chapter8.aidl;

import com.chapter8.aidl.IAIDLServerService.Stub;
import com.chapter8.aidl.IAIDLServerService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class TestService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return mBinder;
    }

    private IAIDLServerService.Stub mBinder = new Stub() {
        @Override
        public String sayHello() throws RemoteException {
            return "啦啦啦啦啦啦";
        }
        
        @Override
        public Book getBook() throws RemoteException {
            Book mBook = new Book();
            mBook.setBookName("广州雄兵");
            mBook.setBookPrice(90);
            return mBook;
        }
    };
}
