package dev.noire.protorypeEngine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

	private boolean[] keys;
	
	//keys needed:
	public boolean left, right, jump, pause, quit, enter, mute;
	
	public KeyManager() {
		keys = new boolean[256];
	}
	
	public void update() {
		
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		jump = keys[KeyEvent.VK_W];
		pause = keys[KeyEvent.VK_P];
		quit = keys[KeyEvent.VK_ESCAPE];
		enter = keys[KeyEvent.VK_ENTER];
		mute = keys[KeyEvent.VK_M];
		
	}
	
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}
	
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	public void keyTyped(KeyEvent e) {}
	
	
}
