package sf.hmg.turntest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import android.graphics.Bitmap;

/**
 * <p>Bitmap�����</p>
 * 
 * <p>����2Q�Ľ��㷨����</p>
 * <p>http://www.vldb.org/conf/1994/P439.PDF</p>
 *
 * ʹ�ã�
 *    LCache static lCache = new LCache();
 *    
 *    ...
 *    
 *    
 *    String imgUrl = "http://monstar.ch/uploads/img/201212/22085340_Zun9.jpg";
 *    if(lCache.isCached(imgUrl)){
 *        Bitmap tBitmap = lCache.get(imgUrl);
 *    }else{
 *        //�ӷ���˻�ȡͼ��
 *        Bitmap tBitmap = ����˻�ȡ��Bitmap
 *        
 *        lCache.put(imgUrl,tBitmap);
 *    }
 * 
 * @author lei.guoting
 */
public class LCache{
	private static final int DEFAULT_MAX_SIZE = (int)(1024 * 1024 * 3.5f);      //Ĭ�ϻ���ش�С  3.5M
	private static final int DEFAULT_IN_QUEUE_MAX_SIZE = 40;                    //fInCacheQue����Ĭ�ϴ�С
	private static final int DEFAULT_OUT_QUEUE_MAX_SIZE = 60;                   //fOutCacheQue����Ĭ�ϴ�С
	private static final int DEFAULT_CACHE_QUEUE_MAX_SIZE = 0;                  //finalCacheQue����Ĭ�ϴ�С
	
	private final HashMap<String,Ref> cache;           //�����
    private final Queue<String> fInCacheQue;           //һ�����棬Ain
    private final Queue<String> fOutCacheQue;          //���һ�������¼��Aout
    private final Queue<String> finalCacheQue;         //���ջ��棬Am
    
    private final int cacheMaxSize;
    private int size;
    private int curBitmapSize;
	
	/**
	 * ����Ĭ�ϴ�С��Cache
	 */
	public LCache() {
		this(DEFAULT_MAX_SIZE);
	}
	
	/**
	 * ����ָ����С��Cache
	 * 
	 * @param cacheMaxSize Cache�������
	 */
	public LCache(int cacheMaxSize){
		if(0 >= cacheMaxSize){
			throw new IllegalArgumentException("cacheMaxSize must be greater than 0");
		}
		
		this.cacheMaxSize = cacheMaxSize;
		this.cache = new HashMap<String,Ref>();
		this.fInCacheQue = new Queue<String>(DEFAULT_IN_QUEUE_MAX_SIZE);
		this.fOutCacheQue = new Queue<String>(DEFAULT_OUT_QUEUE_MAX_SIZE);
		this.finalCacheQue = new Queue<String>(DEFAULT_CACHE_QUEUE_MAX_SIZE);
	}
	
	/**
	 * 
	 * @param key keyֵ
	 * @return  true-key��Ӧ��Bitmap�ڸû������
	 *          false-key��Ӧ��Bitmap���ڸû������
	 */
	public synchronized boolean isCached(String key){
		return this.cache.containsKey(key);
	}
	
	/**
	 * ��ȡ�������Bitmap
	 * 
	 * @param key keyֵ
	 * @return   Bitmap
	 */
	public Bitmap get(String key){
		if(null == key){
			throw new NullPointerException("The key can not be null");
		}
		
		if("".equals(key)){
			throw new IllegalArgumentException("The key dons't value");
		}
		
		return this.getFromCache(key);
	}
	
	/**
	 * ��Bitmap���浽�û������
	 * 
	 * @param key
	 * @param mBitmap
	 */
    public synchronized void put(String key,Bitmap mBitmap){
    	if(this.cache.containsKey(key)){
    		return ;
    	}
    	
        if(this.fOutCacheQue.contains(key)){
        	this.finalCacheQue.addToHead(key);
        	this.fOutCacheQue.remove(key);
        	this.reclaimCache(key,mBitmap);
        }
        
        else{
        	this.fInCacheQue.addToHead(key);
        	this.reclaimCache(key,mBitmap);
        	if(this.fInCacheQue.isOverflow()){
        		String tKey = this.fInCacheQue.removeFromTail();
        		this.clearCache(tKey);
        		this.fOutCacheQue.addToHead(tKey);
        		this.fOutCacheQue.trim();
        	}
        }
    }
    
    private void reclaimCache(String key, Bitmap mBitmap){
    	if(this.hasFreeCache(mBitmap)){
            this.putIntoCache(key, mBitmap);
    	}
    	
    	else if(this.fInCacheQue.isOverflow()){
    		do{
    			String tKey = this.fInCacheQue.removeFromTail();
    			this.clearCache(tKey);
    			this.fOutCacheQue.addToHead(key);
    		}while(this.cacheMaxSize < (this.size + this.curBitmapSize));    		
    		this.fOutCacheQue.trim();
    		this.putIntoCache(key, mBitmap);    		
    	}
    	
    	else{
    		do{
    			String tKey = this.finalCacheQue.removeFromTail();
    			this.clearCache(tKey);
    		}while(this.cacheMaxSize < (this.size + this.curBitmapSize));     		
    		this.putIntoCache(key, mBitmap);
    	}
    }
    
    private void putIntoCache(String key, Bitmap mBitmap){
    	this.cache.put(key, new Ref(mBitmap,this.curBitmapSize));
		this.size += this.curBitmapSize;
		this.curBitmapSize = 0;
    }
    
    private boolean hasFreeCache(Bitmap mBitmap){
    	boolean hasFreeCache = true;
    	this.curBitmapSize = this.sizeOf(mBitmap);
    	if(this.cacheMaxSize < (this.size + this.curBitmapSize)){
    		hasFreeCache = false;
    	}
    	
    	return hasFreeCache;
    }
    
    private void clearCache(String key){
    	Ref  tRef = this.cache.remove(key);
        if(null != tRef){
        	this.size -= tRef.getSize();
        	if(null != tRef.getBitmap()){
            	tRef.getBitmap().recycle();	
        	}
        }
    }
    
    private Bitmap getFromCache(String key){
    	Bitmap tBitmap = null;
    	synchronized(this){
    		if(this.finalCacheQue.contains(key)){
        		this.finalCacheQue.moveToHead(key);
        	}
    		tBitmap = this.cache.get(key).getBitmap();
    	}
    	
    	return  tBitmap;
    }
	
	/**
	 * Bitmap �洢��С
	 * 
	 * @param mBitmap
	 * @return 
	 */
	private int sizeOf (Bitmap mBitmap){
		int weight = 0;
		switch(mBitmap.getConfig()){
		case ALPHA_8 :
			weight = 1;
			break;
		case ARGB_4444 :
			weight = 2;
			break;
		case ARGB_8888 :
			weight = 4;
			break;
		case RGB_565 :
			weight = 2;
			break;
		default :
			weight = 1;
			break;
		}

		return (mBitmap.getWidth() * mBitmap.getHeight() * weight);
	}
	
	public synchronized void destory(){
		this.cache.clear();
		this.finalCacheQue.clear();
		this.fInCacheQue.clear();
		this.fOutCacheQue.clear();
	}
	
	
	/**
	 * ����
	 */
	class Queue<T>{
	    private int capacity;
		
	    private final List<T> list;
	    
	    /**
	     * @param capacity  ���������������Ϊ0�����capacityΪ0����ǰ�����޴�С����
	     */
		public Queue(int capacity){
			this.capacity = capacity;
			if(0 < this.capacity){
				this.list = new ArrayList<T>(this.capacity);
			}else{
				this.list = new LinkedList<T>();
			}
			
		}
		
		/**
		 * ��������
		 * 
		 * @return ��������
		 */
		public int capacity(){
			return this.capacity;
		}
		
		/**
		 * ���е�ǰ��С
		 * 
		 * @return ���д�С
		 */
		public int size(){
			return this.list.size();
		}
		
		/**
		 * �ö������Ƿ���keyֵ
		 * 
		 * @param key
		 * @return true ����keyֵ��false ������keyֵ
		 */
		public boolean contains(T key){
			return this.list.contains(key);
		}
		
		/**
		 * �����Ƿ����
		 * 
		 * @return 
		 */
		public boolean isOverflow(){
			if(0 == capacity){
				return false;
			}
			
			else{
				return (this.list.size() > this.capacity);	
			}
		}
		
		/**
		 * ��������key�ƶ�������ͷ
		 * 
		 * @param key ֵ
		 */
		public void moveToHead(T key){
			this.list.remove(key);
			this.list.add(key);
		}
		
		/**
		 * ��key��ӵ�����ͷ
		 * 
		 * @param key ֵ
		 */
		public void addToHead(T key){
			this.list.add(key);
		}
		
		/**
		 * ɾ���������һ��ֵ
		 * 
		 * @return ��ǰɾ������ֵ
		 */
		public T removeFromTail(){
			T tKey = null;
			if(0 < this.list.size()){
				tKey = this.list.remove(0);
			}
			return tKey;
		}
		
		/**
		 * ɾ��������keyֵ
		 * 
		 * @param key  keyֵ
		 * @return   ɾ���ɹ����ص�ǰkey��ɾ��ʧ�ܷ���null
		 */
		public T remove(T key){
			if(this.list.remove(key)){
				return key;
			}
			return null;
		}
		
		/**
		 * ��Queue�г���capacity�ĳ��Ƚ�ȡ��
		 *      ���list.size <= capacity, ʲô������
		 */
		public void trim(){
			if((0 == capacity) || (this.list.size() <= this.capacity)){
				return ;
			}
			
			int tNum = this.list.size() - this.capacity;
			for(int idx = 0; idx < tNum; idx ++){
				this.removeFromTail();
			}
		}
		
		public void clear(){
			this.list.clear();
		}
	}
	
	class Ref{
		//private SoftReference<Bitmap> softReference;
		private Bitmap mBitmap;
		private int size;
		
		public Ref(Bitmap mBitmap,int size){
			//this.softReference = new SoftReference<Bitmap>(mBitmap);
			this.mBitmap = mBitmap;
			this.size = size;
		}
		
		public int getSize(){
			return size;
		}
		
		public Bitmap getBitmap(){
			//return softReference.get();
			return this.mBitmap;
		}
	}
}