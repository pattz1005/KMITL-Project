/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pattern.flyweight;

import java.util.*;

public class IconFactory {
    private Map iconmap = new HashMap();
    private static IconFactory factory;
    
    private IconFactory(){}
    
    public static synchronized IconFactory getInstance(){
        if(factory==null){
            factory = new IconFactory();
        }
        return factory;
    }
    
    public AbstractIcon createIcon(String key) {
	if(iconmap.containsKey(key)){
            return (AbstractIcon) iconmap.get(key);
        }
        else if(key=="folder"){
            AbstractIcon icon = new FolderIcon();
            iconmap.put(key, icon);
            return icon;
        }
        else if(key=="java"){
            AbstractIcon icon = new JavaIcon();
            iconmap.put(key, icon);
            return icon;
        }
        else if(key=="pdf"){
            AbstractIcon icon = new PdfIcon();
            iconmap.put(key, icon);
            return icon;
        }
        else if(key=="picture"){
            AbstractIcon icon = new PictureIcon();
            iconmap.put(key, icon);
            return icon;
        }
        else if(key=="text"){
            AbstractIcon icon = new TextIcon();
            iconmap.put(key, icon);
            return icon;
        }
        else if(key=="unknown"){
            AbstractIcon icon = new UnknownIcon();
            iconmap.put(key, icon);
            return icon;
        }
        else{
            return null;
        }
    }

    // Add helper methods here, if any.
}
