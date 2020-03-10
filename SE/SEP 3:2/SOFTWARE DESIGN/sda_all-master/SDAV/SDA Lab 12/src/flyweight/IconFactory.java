package flyweight;

import java.util.*;

public class IconFactory {

    private Map<String, AbstractIcon> iconmap = new HashMap<>();

    public AbstractIcon createIcon(String key) {
        
        AbstractIcon result = (AbstractIcon) iconmap.get(key);
        
        if (result == null) {
            if (key.equals("folder")) {
                result = new FolderIcon();
            } else if (key.equals("java")) {
                result = new JavaIcon();
            } else if (key.equals("pdf")) {
                result = new PdfIcon();
            } else if (key.equals("picture")) {
                result = new PictureIcon();
            } else if (key.equals("text")) {
                result = new TextIcon();
            } else {
                result = new UnknownIcon();
            }
            iconmap.put(key, result);
            System.out.println("Created new Icon " + key);
        }
        
        return result;
    }
    
}
