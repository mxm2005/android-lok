package com.chapter8.aidl;
import com.chapter8.aidl.Book;
interface IAIDLServerService {
	String sayHello(); 
    Book getBook();
}  