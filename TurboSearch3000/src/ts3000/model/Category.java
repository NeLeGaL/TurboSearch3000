/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ts3000.model;


public class Category {
    public String getName() {
    	return Name;
    }
    
    public int getSize() {
    	return Size;
    }
    
    public String getAnnotation() {
    	return Annotation;
    }
    
    public Category(String name, String annotation, int size) {
    	this.Name = name;
    	this.Annotation = annotation;
    	this.Size = size;
    }
    
    private String Name;
    private String Annotation;
    private int Size;
}
