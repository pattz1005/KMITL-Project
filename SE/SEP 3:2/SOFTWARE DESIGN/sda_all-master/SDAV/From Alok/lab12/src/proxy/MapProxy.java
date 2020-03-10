/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxy;

/**
 *
 * @author alok
 */
public class MapProxy implements AbstractMap {
    private Map map;

    public MapProxy(String fileName) {
        this.map = new Map(fileName);
    }

    @Override
    public String find(String key) throws Exception {
        return this.map.find(key);
    }

    @Override
    public void add(String key, String value) throws Exception {
        this.map.add(key, value);
    }
    
}
