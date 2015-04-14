package com.docu.components.util.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.docu.components.constants.app.Constants;


public class MemcachedUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(MemcachedUtil.class); 
	protected static MemCachedClient mcc = new MemCachedClient();
	static {
		String memServerAddress = PropertiesUtil.get(Constants.MEMCACHED_SERVERS);
		String[] servers = {memServerAddress};
		Integer[] weights = { 3 };

		SockIOPool pool = SockIOPool.getInstance();

		pool.setServers(servers);
		pool.setWeights(weights);

		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaxIdle(1000 * 60 * 60 * 6);

		pool.setMaintSleep(30);

		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setSocketConnectTO(0);

		pool.initialize();

		mcc.flushAll();
	}

	protected MemcachedUtil() {

	}

	public static boolean add(String key, Object value) {
		logger.info("add key is:"+key+" and value is:"+value);
		return mcc.add(key, value);
	}

	public static boolean add(String key, Object value, Date expiry) {
		logger.info("add key is:"+key+" and value is:"+value+" add expiry is:"+expiry);
		return mcc.add(key, value, expiry);
	}
	public static boolean add(String key, Object value, Integer seconds) {
		return mcc.add(key, value, seconds);
	}
	public static boolean set(String key, Object value) {
		long seconds = System.currentTimeMillis()+ 10 * 1000;
		seconds = 10*1000;
		return mcc.set(key, value, new Date(seconds));
	}

	public static boolean set(String key, Object value, Integer seconds) {
		logger.info("set key is:"+key+" set value is:"+value+" set seconds is:"+seconds);
		return mcc.set(key, value, seconds);
	}

	public static boolean replace(String key, Object value) {
		return mcc.replace(key, value);
	}

	public static boolean replace(String key, Object value, Date expiry) {
		return mcc.replace(key, value, expiry);
	}

	public static Object get(String key) {
		return mcc.get(key);
	}
	
	public static Boolean delete(String key){
		return mcc.delete(key);
	}
	
	public static Map<String, Object> getMulti(String[] key){
		return mcc.getMulti(key);
	}
	
	public static Boolean flushAll(){
		return mcc.flushAll();
	}
	
	public static List<String> getAllKeys() {
        List<String> list = new ArrayList<String>();

        Map<String, Map<String, String>> items = mcc.statsItems();
        for (Iterator<String> itemIt = items.keySet().iterator(); itemIt.hasNext();) {
            String itemKey = itemIt.next();
            
            Map<String, String> maps = items.get(itemKey);
            for (Iterator<String> mapsIt = maps.keySet().iterator(); mapsIt.hasNext();) {
                String mapsKey = mapsIt.next();
                String mapsValue = maps.get(mapsKey);
          
                if (mapsKey.endsWith("number")) {
                    String[] arr = mapsKey.split(":");
                    int slabNumber = Integer.valueOf(arr[1].trim());
                    int limit = Integer.valueOf(mapsValue.trim());
                    
                    Map<String, Map<String, String>> dumpMaps = mcc.statsCacheDump(slabNumber, limit);
                    for (Iterator<String> dumpIt = dumpMaps.keySet().iterator(); dumpIt.hasNext();) {
                        String dumpKey = dumpIt.next();
                     
                        Map<String, String> allMap = dumpMaps.get(dumpKey);
                        for (Iterator<String> allIt = allMap.keySet().iterator(); allIt.hasNext();) {
                            String allKey = allIt.next();
                            list.add(allKey.trim());
                        }
                    }
                }
            }
        }
        return list;
    }
}
