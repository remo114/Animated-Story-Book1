package com.example.andgen3;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.FadeInModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.modifier.IModifier;

import android.media.MediaPlayer;
import android.util.Log;

public class Basectivity extends SimpleBaseGameActivity {

	static final int CAMERA_WIDTH = 860;
	static final int CAMERA_HIGHT = 480;
	static float START_APPEARING_DELAY = 1.90f;
	static float APPEARING_TIME = 2.0f;
	static Boolean audioPlay = false;
	
	public Camera mCamera;
	
	public Scene mCurrentScene;
	Sprite spr;
	Sprite mama,mula,mohis,ma,megh,macch,mParrot,mArrowRighr; 
	
	BuildableBitmapTextureAtlas bitmapTextureAtlas;
	BuildableBitmapTextureAtlas TextureAtlas;
	
	public ITextureRegion mTextureRegion;
	public ITextureRegion mMamaTextureRegion;
	public ITextureRegion mMulaTextureRegion;
	public ITextureRegion mMohisTextureRegion;
	public ITextureRegion mMaTextureRegion;
	public ITextureRegion mMeghTextureRegion;
	public ITextureRegion mMacchTextureRegion;
	public ITextureRegion mMparrotRegion;
	public ITextureRegion mArrowRightRegion;
	
	String d = Basectivity.class.getSimpleName();
	
	@Override
	public EngineOptions onCreateEngineOptions() {		
		
		mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HIGHT);
		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HIGHT), mCamera);
	}

	@Override
	protected void onCreateResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		bitmapTextureAtlas = new BuildableBitmapTextureAtlas(this.getTextureManager(), 1024, 1024);
		this.mTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bitmapTextureAtlas, this,"jungle1.png");
		this.mMamaTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bitmapTextureAtlas, this,"mama.png");
		this.mMaTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bitmapTextureAtlas, this,"ma.png");
		this.mMulaTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bitmapTextureAtlas, this,"mula.png");
		this.mMeghTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bitmapTextureAtlas, this,"cloud.png");
		this.mMacchTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bitmapTextureAtlas, this,"mass.png");
		this.mMohisTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bitmapTextureAtlas, this,"buffalo.png");
		this.mMparrotRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bitmapTextureAtlas, this,"parrot.png");
		this.mArrowRightRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bitmapTextureAtlas, this,"arrow_right.png");
		
			try {
				bitmapTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
				bitmapTextureAtlas.load();			    
				
			} catch (TextureAtlasBuilderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	@Override
	protected Scene onCreateScene() {
		mEngine.registerUpdateHandler(new FPSLogger());
	    //mCurrentScene = new Scene();
	 	mCurrentScene =new Scene();
	 	mCurrentScene.setTouchAreaBindingOnActionMoveEnabled(true);
	 	mCurrentScene.setBackground(new Background(0.09804f, 0.7274f, 0f));
//	 	 final float centerX = (CAMERA_HIGHT-this.mTextureRegion.getWidth()) / 2;
//	     final float centerY = (CAMERA_HIGHT-this.mTextureRegion.getHeight()) / 2;
	 	Log.d(d,"1111");
	 	spr = new Sprite(0, 0, mTextureRegion, this.getVertexBufferObjectManager());
	    spr.setHeight(CAMERA_HIGHT);
	    spr.setWidth(CAMERA_WIDTH);
	    mCurrentScene.attachChild(spr);
	    Log.d(d,"mama");
	    mama = new Sprite(20, 20, mMamaTextureRegion, this.getVertexBufferObjectManager()){
	    	 @Override
	         public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
	    		 switch (pSceneTouchEvent.getAction()){
		    		 case TouchEvent.ACTION_DOWN:{
		    			 //Log.d(d,"mama touched down");
		    			 //mama.setScale(1.05f);
		    			 mama.setScaleCenter(mama.getX(), mama.getY());
		    			 mama.setScale(1.09f);
		    			 break;
		    		 }
		    		 case TouchEvent.ACTION_MOVE:{
		    			// Log.d(d,"mama touched move");
		    			 break;
		    		 }
		    		 case TouchEvent.ACTION_UP:{
		    			 Log.d(d,"mama touched up");
		    			// mama.setScale(1.00f);
		    			 playAudio(R.raw.ma);
		    			 
		    			 mama.setScaleCenter(mama.getX(), mama.getY());
		    			 mama.setScale(1.0f);
		    			 break;
		    		 }
		    		 default:{
		    			 
		    		 }
	    		 }
	    		 return false;
			 }
	    };
	    mCurrentScene.registerTouchArea(mama);
	   
	    mama.setHeight(150.0f);
	    mama.setWidth(150.0f);
	    mCurrentScene.attachChild(mama);
	   loadMama();
	   Log.d(d,"ma");
	    ma = new Sprite(200, 20, mMaTextureRegion, this.getVertexBufferObjectManager()){
	    	 @Override
	         public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
	    		 switch (pSceneTouchEvent.getAction()){
		    		 case TouchEvent.ACTION_DOWN:{
		    			 //Log.d(d,"mama touched down");
		    			 break;
		    		 }
		    		 case TouchEvent.ACTION_MOVE:{
		    			// Log.d(d,"mama touched move");
		    			 break;
		    		 }
		    		 case TouchEvent.ACTION_UP:{
		    			 Log.d(d,"ma touched up");
		    			 playAudio(R.raw.ma);
		    			 break;
		    		 }
		    		 default:{
		    			 
		    		 }
	    		 }
	    		 return false;
			 }
	    };
	    mCurrentScene.registerTouchArea(ma);
	    ma.setHeight(150.0f);
	    ma.setWidth(150.0f);
	    mCurrentScene.attachChild(ma);
	    loadMa();
	    Log.d(d,"mula");
	    mula = new Sprite(400, 20,mMulaTextureRegion, this.getVertexBufferObjectManager()){
	    	 @Override
	         public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
	    		 switch (pSceneTouchEvent.getAction()){
		    		 case TouchEvent.ACTION_DOWN:{
		    			 //Log.d(d,"mama touched down");
		    			 break;
		    		 }
		    		 case TouchEvent.ACTION_MOVE:{
		    			// Log.d(d,"mama touched move");
		    			 break;
		    		 }
		    		 case TouchEvent.ACTION_UP:{
		    			 Log.d(d,"mula touched up");
		    			 playAudio(R.raw.ma);
		    			 break;
		    		 }
		    		 default:{
		    			 
		    		 }
	    		 }
	    		 return false;
			 }
	    };
	    mCurrentScene.registerTouchArea(mula);
	    mula.setWidth(150.0f);
	    mula.setHeight(150.0f);
	    mCurrentScene.attachChild(mula);
	    loadMula();
	    Log.d(d,"megh");
	    megh = new Sprite(20, 200,mMeghTextureRegion, this.getVertexBufferObjectManager()){
	    	 @Override
	         public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
	    		 switch (pSceneTouchEvent.getAction()){
		    		 case TouchEvent.ACTION_DOWN:{
		    			 //Log.d(d,"mama touched down");
		    			 break;
		    		 }
		    		 case TouchEvent.ACTION_MOVE:{
		    			// Log.d(d,"mama touched move");
		    			 break;
		    		 }
		    		 case TouchEvent.ACTION_UP:{
		    			 Log.d(d,"megh touched up");
		    			 playAudio(R.raw.ma);
		    			 break;
		    		 }
		    		 default:{
		    			 
		    		 }
	    		 }
	    		 return false;
			 }
	    };
	    mCurrentScene.registerTouchArea(megh);
	    megh.setWidth(150.0f);
	    megh.setHeight(150.0f);
	    mCurrentScene.attachChild(megh);
	    loadMegh();
	    Log.d(d,"macch");
	    macch = new Sprite(200, 200,mMacchTextureRegion, this.getVertexBufferObjectManager()){
	    	 @Override
	         public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
	    		 switch (pSceneTouchEvent.getAction()){
		    		 case TouchEvent.ACTION_DOWN:{
		    			 //Log.d(d,"mama touched down");
		    			 break;
		    		 }
		    		 case TouchEvent.ACTION_MOVE:{
		    			// Log.d(d,"mama touched move");
		    			 break;
		    		 }
		    		 case TouchEvent.ACTION_UP:{
		    			 Log.d(d,"macch touched up");
		    			 playAudio(R.raw.ma);
		    			 break;
		    		 }
		    		 default:{
		    			 
		    		 }
	    		 }
	    		 return false;
			 }
	    };
	    mCurrentScene.registerTouchArea(macch);
	    macch.setWidth(220.0f);
	    macch.setHeight(150.0f);
	    mCurrentScene.attachChild(macch);
	    loadMacch();
	    
	    mohis = new Sprite(400, 150,mMohisTextureRegion, this.getVertexBufferObjectManager()){
	    	 @Override
	         public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
	    		 switch (pSceneTouchEvent.getAction()){
		    		 case TouchEvent.ACTION_DOWN:{
		    			 //Log.d(d,"mama touched down");
		    			 break;
		    		 }
		    		 case TouchEvent.ACTION_MOVE:{
		    			// Log.d(d,"mama touched move");
		    			 break;
		    		 }
		    		 case TouchEvent.ACTION_UP:{
		    			 Log.d(d,"Mohis touched up");
		    			 playAudio(R.raw.ma);
		    			 break;
		    		 }
		    		 default:{
		    			 
		    		 }
	    		 }
	    		 return false;
			 }
	    };
	    mCurrentScene.registerTouchArea(mohis);
	    mohis.setWidth(200.0f);
	    mohis.setHeight(200.0f);    
	    mCurrentScene.attachChild(mohis);
	    loadMohis();
	    
	    mParrot = new Sprite(CAMERA_WIDTH-200,CAMERA_HIGHT/2-100,mMparrotRegion, this.getVertexBufferObjectManager()){
	    	 @Override
	         public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
	    		 switch (pSceneTouchEvent.getAction()){
		    		 case TouchEvent.ACTION_DOWN:{
		    			 //Log.d(d,"mama touched down");
		    			 break;
		    		 }
		    		 case TouchEvent.ACTION_MOVE:{
		    			// Log.d(d,"mama touched move");
		    			 break;
		    		 }
		    		 case TouchEvent.ACTION_UP:{
		    			 Log.d(d,"Parrot touched up");
		    			 break;
		    		 }
		    		 default:{
		    			 
		    		 }
	    		 }
	    		 return false;
			 }
	    };
	    mCurrentScene.registerTouchArea(mParrot);
	    mParrot.setWidth(200.0f);
	    mParrot.setHeight(200.0f);
	    mCurrentScene.attachChild(mParrot);
	    loadMparrot();
	    
	    mArrowRighr = new Sprite(CAMERA_WIDTH-100,CAMERA_HIGHT- 100,mArrowRightRegion, this.getVertexBufferObjectManager()){
	    	 @Override
	         public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
	    		 switch (pSceneTouchEvent.getAction()){
		    		 case TouchEvent.ACTION_DOWN:{
		    			 //Log.d(d,"mama touched down");
		    			 break;
		    		 }
		    		 case TouchEvent.ACTION_MOVE:{
		    			// Log.d(d,"mama touched move");
		    			 break;
		    		 }
		    		 case TouchEvent.ACTION_UP:{
		    			 Log.d(d,"arrow touched up");
		    			 mCurrentScene.detachSelf();
		    			 break;
		    		 }
		    		 default:{
		    			 
		    		 }
	    		 }
	    		 return false;
			 }
	    };
	    mCurrentScene.registerTouchArea(mArrowRighr);
	    mArrowRighr.setWidth(100.0f);
	    mArrowRighr.setHeight(100.0f);
	    mCurrentScene.attachChild(mArrowRighr);
	    loadArrow();
	    
		return mCurrentScene;
	}
	
	void loadMama() {
		DelayModifier dMod = new DelayModifier(START_APPEARING_DELAY,new IEntityModifierListener() {

			@Override
			public void onModifierStarted(IModifier<IEntity> arg0,
					IEntity arg1) {
				mama.setVisible(false);
			}

			@Override
			public void onModifierFinished(IModifier<IEntity> arg0,
					IEntity arg1) {
				mama.setVisible(true);
			}
		});
	    FadeInModifier macch_fim2 = new FadeInModifier(APPEARING_TIME);
	    SequenceEntityModifier macch_sm = new SequenceEntityModifier(dMod, macch_fim2);
	    mama.registerEntityModifier(macch_sm);
	}
	
	void loadMa() {
		DelayModifier dMod = new DelayModifier(START_APPEARING_DELAY+=3,new IEntityModifierListener() {

			@Override
			public void onModifierStarted(IModifier<IEntity> arg0,
					IEntity arg1) {
				ma.setVisible(false);
			}

			@Override
			public void onModifierFinished(IModifier<IEntity> arg0,
					IEntity arg1) {
				ma.setVisible(true);
			}
		});
	    FadeInModifier macch_fim2 = new FadeInModifier(APPEARING_TIME);
	    SequenceEntityModifier macch_sm = new SequenceEntityModifier(dMod, macch_fim2);
	    ma.registerEntityModifier(macch_sm);
	}
	void loadMula() {
		DelayModifier dMod = new DelayModifier(START_APPEARING_DELAY+=5,new IEntityModifierListener() {

			@Override
			public void onModifierStarted(IModifier<IEntity> arg0,
					IEntity arg1) {
				mula.setVisible(false);
			}

			@Override
			public void onModifierFinished(IModifier<IEntity> arg0,
					IEntity arg1) {
				mula.setVisible(true);
			}
		});
	    FadeInModifier macch_fim2 = new FadeInModifier(APPEARING_TIME);
	    SequenceEntityModifier macch_sm = new SequenceEntityModifier(dMod, macch_fim2);
	    mula.registerEntityModifier(macch_sm);
	}
	
	void loadMegh() {
		DelayModifier dMod = new DelayModifier(START_APPEARING_DELAY+=5,new IEntityModifierListener() {

			@Override
			public void onModifierStarted(IModifier<IEntity> arg0,
					IEntity arg1) {
				megh.setVisible(false);
			}

			@Override
			public void onModifierFinished(IModifier<IEntity> arg0,
					IEntity arg1) {
				megh.setVisible(true);
			}
		});
	    FadeInModifier macch_fim2 = new FadeInModifier(APPEARING_TIME);
	    SequenceEntityModifier macch_sm = new SequenceEntityModifier(dMod, macch_fim2);
	    megh.registerEntityModifier(macch_sm);
	}
	
	void loadMacch() {
		DelayModifier dMod = new DelayModifier(START_APPEARING_DELAY+=5,new IEntityModifierListener() {

			@Override
			public void onModifierStarted(IModifier<IEntity> arg0,
					IEntity arg1) {
				macch.setVisible(false);
			}

			@Override
			public void onModifierFinished(IModifier<IEntity> arg0,
					IEntity arg1) {
				macch.setVisible(true);
			}
		});
	    FadeInModifier macch_fim2 = new FadeInModifier(APPEARING_TIME);
	    SequenceEntityModifier macch_sm = new SequenceEntityModifier(dMod, macch_fim2);
	    macch.registerEntityModifier(macch_sm);
	}

	void loadMohis() {
		DelayModifier dMod = new DelayModifier(START_APPEARING_DELAY+=5,new IEntityModifierListener() {

			@Override
			public void onModifierStarted(IModifier<IEntity> arg0,
					IEntity arg1) {
				mohis.setVisible(false);
			}

			@Override
			public void onModifierFinished(IModifier<IEntity> arg0,
					IEntity arg1) {
				mohis.setVisible(true);
			}
		});
	    FadeInModifier macch_fim2 = new FadeInModifier(APPEARING_TIME);
	    SequenceEntityModifier macch_sm = new SequenceEntityModifier(dMod, macch_fim2);
	    mohis.registerEntityModifier(macch_sm);
	}
	void loadArrow() {
		DelayModifier dMod = new DelayModifier(START_APPEARING_DELAY+=5,new IEntityModifierListener() {

			@Override
			public void onModifierStarted(IModifier<IEntity> arg0,
					IEntity arg1) {
				mArrowRighr.setVisible(false);
			}

			@Override
			public void onModifierFinished(IModifier<IEntity> arg0,
					IEntity arg1) {
				mArrowRighr.setVisible(true);
			}
		});
	    FadeInModifier macch_fim2 = new FadeInModifier(APPEARING_TIME);
	    SequenceEntityModifier macch_sm = new SequenceEntityModifier(dMod, macch_fim2);
	    mArrowRighr.registerEntityModifier(macch_sm);
	}
	
	void loadMparrot() {
		MoveModifier mMod = new MoveModifier(4.0f, CAMERA_WIDTH+200, CAMERA_WIDTH-250, CAMERA_HIGHT/2-100, CAMERA_HIGHT/2-100);
		mParrot.registerEntityModifier(mMod);
	}
	void playAudio(int val){
		 MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),val);
	        try {
	            mediaPlayer.start();
	            mediaPlayer.setLooping(false);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}
