package dev.noire.protorypeEngine.UI;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import dev.noire.protorypeEngine.Handler;

public class UIManager {

	//private Handler handler;
	private ArrayList<UIObject>objects;
	
	public UIManager(Handler handler) {
		//this.handler = handler;
		objects = new ArrayList<UIObject>();
	}
	
	public void update() {
		for(UIObject o : objects)
			o.update();
	}
	
	public void render(Graphics g) {
		for(UIObject o : objects)
			o.render(g);
	}
	
	public void onMouseMoved(MouseEvent e) {
		for(UIObject o : objects)
			o.onMouseMoved(e);
	}
	
	public void onMouseReleased(MouseEvent e) {
		for(UIObject o : objects)
			o.onMouseReleased(e);
	}
	
	public void addObject(UIObject o) {
		objects.add(o);
	}
	
	public void removeObject(UIObject o) {
		objects.remove(o);
	}

	//GETTERS & SETTERS:
	public ArrayList<UIObject> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<UIObject> objects) {
		this.objects = objects;
	}
}
