package com.glTron.Ambilights;

import android.graphics.Color;
import android.util.Log;

import com.glTron.Game.UserPrefs;
import com.tpvision.ambilib.Effect;
import com.tpvision.ambilib.OnEffectDoneListener;

public class AmbilightEffect {
	private static String IP = "127.0.0.1";
	private static final int nolights = 17;
	private static boolean effectIsBusy = false;
	
	
	private static OnEffectDoneListener oedl = new OnEffectDoneListener() {
		@Override
		public void effectIsDone() {
			effectIsBusy = false;
		}
	};
	
	public static void splash(UserPrefs mPrefs){
		setIP(mPrefs);
		
		if(!effectIsBusy){
			Log.e("","splash");
			effectIsBusy = true;
			new Effect(IP,nolights,oedl) {
			
				@Override
				public void effect() {
					halfRun(50,10);
				}

			}.execute("");
		}
		
	}
	
	private static void setIP(UserPrefs mPrefs){
		IP = mPrefs.getIP();
	}


	public static void winEffect(final int color, UserPrefs mPrefs){
		setIP(mPrefs);

		effectIsBusy = true;
		new Effect(IP,nolights,oedl) {
			
			@Override
			public void effect() {
				random(200,20);
			}
			
		}.execute("");
		
	}
	
	public static void loseEffect(final int color, UserPrefs mPrefs){
		setIP(mPrefs);
		if(!effectIsBusy){
			effectIsBusy = true;

			new Effect(IP,nolights,oedl) {
				
				@Override
				public void effect() {
					
					for(int i = 0; i< 7;++i){
						runColor(color, Color.WHITE, 50, true);
						runColor(color, Color.WHITE, 50, false);
					}
	
				}
			}.execute("");
		}
	}
	
	public static void crash(final int color, UserPrefs mPrefs){
		setIP(mPrefs);

		if(!effectIsBusy){
			effectIsBusy = true;
			
			new Effect(IP,nolights,oedl) {
				
				@Override
				public void effect() {
					flash(color, Color.WHITE, 10, 200);
				}
			}.execute("");
		}
	}
	
	public static void crash(final int color, final int color2, UserPrefs mPrefs){
		setIP(mPrefs);
		
		if(!effectIsBusy){
			effectIsBusy = true;
			Log.e("","crash of two colors");
			if(color == color2){
				effectIsBusy = false;
				crash(color, mPrefs);
			}else
				new Effect(IP,nolights,oedl) {
					
					@Override
					public void effect() {
						flash(color, color2, 10, 200);
					}
				}.execute("");
		}
	}
}
