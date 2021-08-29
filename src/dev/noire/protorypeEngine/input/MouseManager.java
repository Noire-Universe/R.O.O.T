package dev.noire.protorypeEngine.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import dev.noire.protorypeEngine.UI.UIManager;

public class MouseManager implements MouseMotionListener, MouseListener {
	private boolean leftPressed, rightPressed;
	private int xMouse, yMouse;
	private UIManager uiManager;
	
	
	public MouseManager() {
		
	}
	
	public void setUIManager(UIManager uiManager) {
		this.uiManager = uiManager;
	}
	
	public boolean isLeftPressed() {return leftPressed;}
	public boolean isRightPressed() {return rightPressed;}
	public int getXMouse() {return xMouse;}
	public int getYMouse() {return yMouse;}

	public void mouseDragged(MouseEvent e) {}
	
	public void mouseMoved(MouseEvent e) {
		xMouse = e.getX();
		yMouse = e.getY();
		if(uiManager!=null)
			uiManager.onMouseMoved(e);
	}
	
	public void mouseClicked(MouseEvent e) {}
	
	public void mousePressed(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1)
			leftPressed = true;
		else if(e.getButton()==MouseEvent.BUTTON3)
			rightPressed = true;
	}
	
	public void mouseReleased(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1)
			leftPressed = false;
		else if(e.getButton()==MouseEvent.BUTTON3)
			rightPressed = false;
		if(uiManager!=null)
			uiManager.onMouseReleased(e);
	}
	
	public void mouseEntered(MouseEvent e) {}
	
	public void mouseExited(MouseEvent e) {}

}
