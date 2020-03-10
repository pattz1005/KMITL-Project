package e1.proxy;

public interface AbstractMap {

    public String find(String key) throws Exception;

    public void add(String key, String value) throws Exception;

}
