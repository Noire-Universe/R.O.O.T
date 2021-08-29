package dev.noire.protorypeEngine.utils;


import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import dev.noire.protorypeEngine.Handler;

public class FontManager {
	
	private Font rootFontSmall;
	private Font rootFontLarge;
	private Font quoteFont;
	private Font hudLiveDisplay;
	
	private float fontToScreenScaleSmall;
	
	public FontManager(Handler handler) {
		fontToScreenScaleSmall = handler.getWidth()/24;
		
		File file = new File("res/fonts/prstart.ttf");
		try{
			rootFontSmall = Font.createFont(Font.TRUETYPE_FONT, file).deriveFont(fontToScreenScaleSmall);
			
			GraphicsEnvironment geSmall = GraphicsEnvironment.getLocalGraphicsEnvironment();
			
			geSmall.registerFont(rootFontSmall);

		}catch(IOException|FontFormatException e) {
			rootFontSmall = new Font("ComicSans MS", Font.PLAIN, (int)(handler.getWidth()/26.666));
		}

		quoteFont = new Font("Comic Sans MS", Font.ITALIC, (int)(handler.getWidth()/32));
		rootFontLarge = new Font("Comic Sans MS", Font.ITALIC, (int)(handler.getWidth()/6));
		hudLiveDisplay = new Font("Comic Sans Ms", Font.PLAIN, (handler.getWidth()/2));
	}
	
	public Font getSmallFont() { return rootFontSmall;}
	public Font getLargeFont() {return rootFontLarge;}
	public Font getQuoteFont() {return quoteFont;}
	public Font getHudFont() {return hudLiveDisplay;}
	public float getSmallRatio() {return fontToScreenScaleSmall;}
}
