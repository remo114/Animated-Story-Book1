package com.example.andgen3;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.FadeInModifier;
import org.andengine.entity.modifier.FadeOutModifier;
import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.MoveXModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.modifier.MoveYModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.modifier.IModifier;
import org.andengine.util.modifier.IModifier.IModifierListener;

import android.util.Log;

public class Basectivity extends SimpleBaseGameActivity {

	static final int CAMERA_WIDTH = 860;
	static final int CAMERA_HIGHT = 480;
	
	public Camera mCamera;
	
	public Scene mCurrentScene;
	Sprite spr;
	Sprite mama,mula,mohis,ma,megh,macch; 
	
	BuildableBitmapTextureAtlas bitmapTextureAtlas;
	BuildableBitmapTextureAtlas TextureAtlas;
	
	public ITextureRegion mTextureRegion;
	public ITextureRegion mMamaTextureRegion;
	public ITextureRegion mMulaTextureRegion;
	public ITextureRegion mMohisTextureRegion;
	public ITextureRegion mMaTextureRegion;
	public ITextureRegion mMeghTextureRegion;
	public ITextureRegion mMacchTextureRegion;
	
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
	 	mCurrentScene.setBackground(new Background(0.09804f, 0.7274f, 0f));
//	 	 final float centerX = (CAMERA_HIGHT-this.mTextureRegion.getWidth()) / 2;
//	     final float centerY = (CAMERA_HIGHT-this.mTextureRegion.getHeight()) / 2;
	 	Log.d(d,"1111");
	 	spr = new Sprite(0, 0, mTextureRegion, this.getVertexBufferObjectManager());
	    spr.setHeight(CAMERA_HIGHT);
	    spr.setWidth(CAMERA_WIDTH);
	    mCurrentScene.attachChild(spr);
	    Log.d(d,"mama");
	    mama = new Sprite(20, 20, mMamaTextureRegion, this.getVertexBufferObjectManager());
	    mama.setHeight(150.0f);
	    mama.setWidth(150.0f);
	   loadMama();
	   Log.d(d,"ma");
	    ma = new Sprite(200, 20, mMaTextureRegion, this.getVertexBufferObjectManager());
	    ma.setHeight(150.0f);
	    ma.setWidth(150.0f);
	    ma.registerEntityModifier(new DelayModifier(44));
	    loadMa();
	    Log.d(d,"mula");
	    mula = new Sprite(400, 20,mMulaTextureRegion, this.getVertexBufferObjectManager());
	    mula.setWidth(150.0f);
	    mula.setHeight(150.0f);	      
	    loadMula();
	    Log.d(d,"megh");
	    megh = new Sprite(20, 200,mMeghTextureRegion, this.getVertexBufferObjectManager());
	    megh.setWidth(150.0f);
	    megh.setHeight(150.0f);	      
	    loadMegh();
	    Log.d(d,"macch");
	    macch = new Sprite(200, 200,mMacchTextureRegion, this.getVertexBufferObjectManager());
	    macch.setWidth(220.0f);
	    macch.setHeight(150.0f);     
	    loadMacch();
	    
	    mohis = new Sprite(400, 150,mMohisTextureRegion, this.getVertexBufferObjectManager());
	    mohis.setWidth(200.0f);
	    mohis.setHeight(200.0f);     
	    loadMohis();
		return mCurrentScene;
	}
	
	void loadMama() {
		DelayModifier dMod = new DelayModifier(1,new IEntityModifierListener() {

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
	    FadeInModifier macch_fim2 = new FadeInModifier(3.0f);
	    SequenceEntityModifier macch_sm = new SequenceEntityModifier(dMod, macch_fim2);
	    mama.registerEntityModifier(macch_sm);
	    mCurrentScene.attachChild(mama);
	}
	
	void loadMa() {
		DelayModifier dMod = new DelayModifier(5,new IEntityModifierListener() {

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
	    FadeInModifier macch_fim2 = new FadeInModifier(3.0f);
	    SequenceEntityModifier macch_sm = new SequenceEntityModifier(dMod, macch_fim2);
	    ma.registerEntityModifier(macch_sm);
	    mCurrentScene.attachChild(ma);
	}
	void loadMula() {
		DelayModifier dMod = new DelayModifier(10,new IEntityModifierListener() {

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
	    FadeInModifier macch_fim2 = new FadeInModifier(3.0f);
	    SequenceEntityModifier macch_sm = new SequenceEntityModifier(dMod, macch_fim2);
	    mula.registerEntityModifier(macch_sm);
	    mCurrentScene.attachChild(mula);
	}
	
	void loadMegh() {
		DelayModifier dMod = new DelayModifier(15,new IEntityModifierListener() {

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
	    FadeInModifier macch_fim2 = new FadeInModifier(3.0f);
	    SequenceEntityModifier macch_sm = new SequenceEntityModifier(dMod, macch_fim2);
	    megh.registerEntityModifier(macch_sm);
	    mCurrentScene.attachChild(megh);
	}
	
	void loadMacch() {
		DelayModifier dMod = new DelayModifier(20,new IEntityModifierListener() {

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
	    FadeInModifier macch_fim2 = new FadeInModifier(3.0f);
	    SequenceEntityModifier macch_sm = new SequenceEntityModifier(dMod, macch_fim2);
	    macch.registerEntityModifier(macch_sm);
	    mCurrentScene.attachChild(macch);
	}

	void loadMohis() {
		DelayModifier dMod = new DelayModifier(25,new IEntityModifierListener() {

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
	    FadeInModifier macch_fim2 = new FadeInModifier(3.0f);
	    SequenceEntityModifier macch_sm = new SequenceEntityModifier(dMod, macch_fim2);
	    mohis.registerEntityModifier(macch_sm);
	    mCurrentScene.attachChild(mohis);
	}

}
