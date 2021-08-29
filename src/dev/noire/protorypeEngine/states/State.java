package dev.noire.protorypeEngine.states;

import java.awt.Graphics;

import dev.noire.protorypeEngine.Handler;

public abstract class State {

	//State Manager:
	private static State currentState = null;
	public static State getState() {return currentState;}
	public static void setState(State state) {currentState = state;}
	
	//Class:
	protected Handler handler;
	
	public State(Handler handler) {
		this.handler = handler;
	}
	
	public abstract void update();
	public abstract void render(Graphics g);
	public abstract void loadMouseManager();
	
}
