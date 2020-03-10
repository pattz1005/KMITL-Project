package proxy;

import java.util.HashMap;

public class MapProxy implements AbstractMap {

    public MapProxy(String fileName) {
        this.fileName = fileName;
    }

    public String find(String key) throws Exception {
        return get(key);
    }

    public void add(String key, String value) throws Exception {
        Map map = getMap();
        map.add(key, value);
        put(key, value);
    }

    private Map getMap() {
        if (map == null) {
            map = new Map(fileName);
        }
        return map;
    }

    private String get(String key) {
        return (String) hashtable.get(key);
    }

    private void put(String key, String value) {
        hashtable.put(key, value);
    }

    private String fileName;
    private Map map = null;
    private HashMap<String, String> hashtable = new HashMap<>();
    
}
