package com.example.andgen3;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.FadeInModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.background.modifier.LoopBackgroundModifier;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.modifier.IModifier;

import android.media.MediaPlayer;
import android.util.Log;
import android.view.Display;

public class Basectivity extends SimpleBaseGameActivity {

	int CAMERA_WIDTH;
	int CAMERA_HIGHT;
	int START_APPEARING_DELAY = 13;
	static float APPEARING_TIME = 2.0f;
	Boolean audioPlay = false;

	public Camera mCamera;

	public Scene mCurrentScene;
	Sprite spr;
	Sprite mama, mula, mohis, ma, megh, macch, mParrot,mParrot2,mParrot3, mArrowRighr,mLetter;
	AnimatedSprite Parrpt_flying;
	MediaPlayer mediaPlayer = new MediaPlayer();

	static BuildableBitmapTextureAtlas bitmapTextureAtlas;
	BuildableBitmapTextureAtlas bgbitmapTextureAtlas;
	BuildableBitmapTextureAtlas TextureAtlas;

	public ITextureRegion mTextureRegion;
	public ITextureRegion mMamaTextureRegion;
	public ITextureRegion mMulaTextureRegion;
	public ITextureRegion mMohisTextureRegion;
	public ITextureRegion mMaTextureRegion;
	public ITextureRegion mMeghTextureRegion;
	public ITextureRegion mMacchTextureRegion;
	public ITextureRegion mMparrotRegion;
	public ITextureRegion mMparrot2Region;
	public ITextureRegion mMparrot3Region;
	public ITextureRegion mArrowRightRegion;
	public ITextureRegion mLetterRegion;
	public TiledTextureRegion mMparrot1Region;
	
	
	static BitmapTextureAtlas mBitmapPlayerTextureAtlas;
	public static TiledTextureRegion mPlayerTextureRegion;

	String d = Basectivity.class.getSimpleName();

	@Override
	public EngineOptions onCreateEngineOptions() {

		Display display = getWindowManager().getDefaultDisplay();
		CAMERA_HIGHT = display.getHeight();
		CAMERA_WIDTH = display.getWidth();
		mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HIGHT);
		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR,new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HIGHT), mCamera);
	}

	@Override
	protected void onCreateResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		bitmapTextureAtlas = new BuildableBitmapTextureAtlas(this.getTextureManager(),2624, 2624,TextureOptions.NEAREST);
		//bgbitmapTextureAtlas = new BuildableBitmapTextureAtlas(this.getTextureManager(),1024, 1024,TextureOptions.NEAREST);
		this.mTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bitmapTextureAtlas, this, "jungle16.png");
		this.mMamaTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bitmapTextureAtlas, this, "mama.png");
		this.mMaTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bitmapTextureAtlas, this, "ma.png");
		this.mMulaTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.bitmapTextureAtlas, this, "mula.png");
		this.mMeghTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.bitmapTextureAtlas, this, "megh.png");
		this.mMacchTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.bitmapTextureAtlas, this, "moi.png");
		this.mMohisTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.bitmapTextureAtlas, this, "mohis.png");
		this.mMparrotRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.bitmapTextureAtlas, this, "parrot1.png");
		this.mArrowRightRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bitmapTextureAtlas, this, "arrow_right.png");
		this.mLetterRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bitmapTextureAtlas, this, "letter_mo.png");
		
		
		
		this.mBitmapPlayerTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 1600,1200, TextureOptions.DEFAULT);
		this.mPlayerTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapPlayerTextureAtlas, this, "sprite_perrot4.png", 0, 0,5,3);

		try {
			bitmapTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
			bitmapTextureAtlas.load();
			this.mBitmapPlayerTextureAtlas.load();
			//bgbitmapTextureAtlas.load();

		} catch (TextureAtlasBuilderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected Scene onCreateScene() {
		mEngine.registerUpdateHandler(new FPSLogger());
		// mCurrentScene = new Scene();
		mCurrentScene = new Scene();
		mCurrentScene.setTouchAreaBindingOnActionMoveEnabled(true);
		//mCurrentScene.setBackground(new Background(0.09804f, 0.7274f, 0f));
		
		Log.d(d, "1111");
		spr = new Sprite(0, 0, mTextureRegion,this.getVertexBufferObjectManager());
		spr.setHeight(CAMERA_HIGHT);
		spr.setWidth(CAMERA_WIDTH);
		mCurrentScene.attachChild(spr);
		
		//Log.d(d, "camera"+ CAMERA_HIGHT);
		mama = new Sprite(20 + (CAMERA_HIGHT*0.0001f), 20 + (CAMERA_WIDTH*0.0001f), mMamaTextureRegion,
				this.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN: {
					mama.setScale(1.09f);
					break;
				}
				case TouchEvent.ACTION_MOVE: {
					// Log.d(d,"mama touched move");
					break;
				}
				case TouchEvent.ACTION_UP: {
					Log.d(d, "mama touched up");
					// mama.setScale(1.00f);
					playAudio2(R.raw.mama);

					//mama.setScaleCenter(mama.getX(), mama.getY());
					mama.setScale(1.0f);
					break;
				}
				default: {

				}
				}
				return false;
			}
		};
		mCurrentScene.registerTouchArea(mama);

		mama.setHeight(150.0f + (CAMERA_HIGHT*0.0001f));
		mama.setWidth(150.0f + (CAMERA_WIDTH*0.0001f));
		mCurrentScene.attachChild(mama);
		loadMama();
		Log.d(d, "ma");
		ma = new Sprite(200 + (CAMERA_HIGHT*0.0001f), 20 + (CAMERA_WIDTH*0.0001f), mMaTextureRegion,
				this.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN: {
					//ma.setScaleCenter(ma.getX(), ma.getY());
					ma.setScale(1.09f);
					// Log.d(d,"mama touched down");
					break;
				}
				case TouchEvent.ACTION_MOVE: {
					// Log.d(d,"mama touched move");
					break;
				}
				case TouchEvent.ACTION_UP: {
					Log.d(d, "ma touched up");
					//ma.setScaleCenter(ma.getX(), ma.getY());
					ma.setScale(1.0f);
					playAudio2(R.raw.ma);
					break;
				}
				default: {

				}
				}
				return false;
			}
		};
		mCurrentScene.registerTouchArea(ma);
		ma.setHeight(150.0f + (CAMERA_HIGHT*0.0001f));
		ma.setWidth(150.0f + (CAMERA_WIDTH*0.0001f));
		mCurrentScene.attachChild(ma);
		loadMa();
		Log.d(d, "mula");
		mula = new Sprite(400 + (CAMERA_HIGHT*0.0001f), 20 + (CAMERA_WIDTH*0.0001f), mMulaTextureRegion,
				this.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN: {
					//mula.setScaleCenter(mula.getX(), mula.getY());
					mula.setScale(1.09f);
					// Log.d(d,"mama touched down");
					break;
				}
				case TouchEvent.ACTION_MOVE: {
					// Log.d(d,"mama touched move");
					break;
				}
				case TouchEvent.ACTION_UP: {
					Log.d(d, "mula touched up");
					//mula.setScaleCenter(mula.getX(), mula.getY());
					mula.setScale(1.0f);
					playAudio2(R.raw.mula);
					break;
				}
				default: {

				}
				}
				return false;
			}
		};
		mCurrentScene.registerTouchArea(mula);
		mula.setWidth(150.0f + (CAMERA_HIGHT*0.0001f));
		mula.setHeight(150.0f + (CAMERA_WIDTH*0.0001f));
		mCurrentScene.attachChild(mula);
		loadMula();
		Log.d(d, "megh");
		megh = new Sprite(20 + (CAMERA_HIGHT*0.0001f), 200 + (CAMERA_WIDTH*0.0001f), mMeghTextureRegion,
				this.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN: {
					megh.setScale(1.09f);
					// Log.d(d,"mama touched down");
					break;
				}
				case TouchEvent.ACTION_MOVE: {
					
					break;
				}
				case TouchEvent.ACTION_UP: {
					//megh.setScaleCenter(megh.getX(), megh.getY());
					megh.setScale(1.0f);
					Log.d(d, "megh touched up");
					playAudio2(R.raw.megh);
					break;
				}
				default: {

				}
				}
				return false;
			}
		};
		mCurrentScene.registerTouchArea(megh);
		megh.setWidth(150.0f + (CAMERA_WIDTH*0.0001f));
		megh.setHeight(150.0f + (CAMERA_HIGHT*0.0001f));
		mCurrentScene.attachChild(megh);
		loadMegh();
		Log.d(d, "macch");
		macch = new Sprite(200 + (CAMERA_HIGHT*0.0001f), 200 + (CAMERA_WIDTH*0.0001f), mMacchTextureRegion,
				this.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN: {
					//macch.setScaleCenter(macch.getX(), macch.getY());
					macch.setScale(1.09f);
					// Log.d(d,"mama touched down");
					break;
				}
				case TouchEvent.ACTION_MOVE: {
					
					// Log.d(d,"mama touched move");
					break;
				}
				case TouchEvent.ACTION_UP: {
					//macch.setScaleCenter(macch.getX(), macch.getY());
					macch.setScale(1.0f);
					Log.d(d, "macch touched up");
					playAudio2(R.raw.moi);
					break;
				}
				default: {

				}
				}
				return false;
			}
		};
		mCurrentScene.registerTouchArea(macch);
		macch.setWidth(150.0f + (CAMERA_WIDTH*0.0001f));
		macch.setHeight(150.0f + (CAMERA_HIGHT*0.0001f));
		mCurrentScene.attachChild(macch);
		loadMacch();

		mohis = new Sprite(400 + (CAMERA_HIGHT*0.0001f), 200 + (CAMERA_WIDTH*0.0001f), mMohisTextureRegion,
				this.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN: {
					//mohis.setScaleCenter(mohis.getX(), mohis.getY());
					mohis.setScale(1.09f);
					// Log.d(d,"mama touched down");
					break;
				}
				case TouchEvent.ACTION_MOVE: {
					// Log.d(d,"mama touched move");
					break;
				}
				case TouchEvent.ACTION_UP: {
					//mohis.setScaleCenter(mohis.getX(), mohis.getY());
					mohis.setScale(1.0f);
					Log.d(d, "Mohis touched up");
					playAudio2(R.raw.mohish);
					break;
				}
				default: {

				}
				}
				return false;
			}
		};
		mCurrentScene.registerTouchArea(mohis);
		mohis.setWidth(150.0f + (CAMERA_WIDTH*0.0001f));
		mohis.setHeight(150.0f + (CAMERA_HIGHT*0.0001f));
		mCurrentScene.attachChild(mohis);
		loadMohis();

		
		final float centerX = (CAMERA_WIDTH - this.mPlayerTextureRegion.getWidth()) / 2;
		final float centerY = (CAMERA_HIGHT - this.mPlayerTextureRegion.getHeight()) / 2;

		/* Create the sprite and add it to the scene. */
		Parrpt_flying = new AnimatedSprite(centerX-5000, centerY-9000, this.mPlayerTextureRegion, this.getVertexBufferObjectManager()){
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
				final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN: {
					break;
				}
				case TouchEvent.ACTION_MOVE: {
					
					break;
				}
				case TouchEvent.ACTION_UP: {
					playAudio2(R.raw.mo);
					break;
				}
				default: {

				}
				}
				return false;
			}
		};

		Parrpt_flying.setPosition(CAMERA_WIDTH - 200, CAMERA_HIGHT / 2 - 500);
		Parrpt_flying.animate(new long[]{200, 200, 200, 200, 200}, 3, 7, true);
		Parrpt_flying.setFlippedHorizontal(true);
		Parrpt_flying.setHeight(200f);
		Parrpt_flying.setWidth(200f);
		mCurrentScene.registerTouchArea(Parrpt_flying);
				
		
		/*final Path path = new Path(2).to(CAMERA_WIDTH, CAMERA_HIGHT/2).to(1, 10);

		Parrpt_flying.registerEntityModifier(new LoopEntityModifier(new PathModifier(10, path, null, new IPathModifierListener() {
			@Override
			public void onPathStarted(final PathModifier pPathModifier, final IEntity pEntity)
			{

			}

			@Override
			public void onPathWaypointStarted(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex) {
				switch(pWaypointIndex) 
				{
					case 0:
						Parrpt_flying.animate(new long[]{200, 200, 200}, 3, 5, true);
						Parrpt_flying.setFlippedHorizontal(true);
						break;
				}
			}

			@Override
			public void onPathWaypointFinished(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex) {

			}

			@Override
			public void onPathFinished(final PathModifier pPathModifier, final IEntity pEntity) {

			}
		})));
*/		
	//	mCurrentScene.attachChild(Parrpt_flying);
		
				mLetter = new Sprite(CAMERA_WIDTH - 200, CAMERA_HIGHT / 2 + 50,mLetterRegion, this.getVertexBufferObjectManager()) {
					@Override
					public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
							final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
						switch (pSceneTouchEvent.getAction()) {
						case TouchEvent.ACTION_DOWN: {
							// Log.d(d,"mama touched down");
							break;
						}
						case TouchEvent.ACTION_MOVE: {
							// Log.d(d,"mama touched move");
							break;
						}
						case TouchEvent.ACTION_UP: {
							//Log.d(d, "Parrot touched up");
							playAudio2(R.raw.mo);
							break;
						}
						default: {

						}
						}
						return false;
					}
				};
				mCurrentScene.registerTouchArea(mLetter);
		
		mParrot = new Sprite(CAMERA_WIDTH + 300, CAMERA_HIGHT / 2 + 20,mMparrotRegion, this.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN: {
					// Log.d(d,"mama touched down");
					break;
				}
				case TouchEvent.ACTION_MOVE: {
					// Log.d(d,"mama touched move");
					break;
				}
				case TouchEvent.ACTION_UP: {
					Log.d(d, "Parrot touched up");
					break;
				}
				default: {

				}
				}
				return false;
			}
		};
		/*mCurrentScene.registerTouchArea(mParrot);
		mParrot.setWidth(200.0f);
		mParrot.setHeight(200.0f);*/
		mCurrentScene.attachChild(Parrpt_flying);
		loadMparrot();
		
		
		mLetter.setHeight(100f + (CAMERA_HIGHT*0.0001f));
		mLetter.setWidth(100f + (CAMERA_WIDTH*0.0001f));
		mCurrentScene.attachChild(mLetter);
		
		mArrowRighr = new Sprite(CAMERA_WIDTH - 100, CAMERA_HIGHT - 100,
				mArrowRightRegion, this.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN: {
					// Log.d(d,"mama touched down");
					break;
				}
				case TouchEvent.ACTION_MOVE: {
					// Log.d(d,"mama touched move");
					break;
				}
				case TouchEvent.ACTION_UP: {
					Log.d(d, "arrow touched up");
					//mCurrentScene.detachSelf();
					//SecondStageScene ss = new SecondStageScene();
					//mCurrentScene.detachChildren();
					//mCurrentScene.detachSelf();
					//ss.mCurrentScene.attachChild(spr);
					
					
					break;
				}
				default: {

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
		DelayModifier dMod = new DelayModifier(START_APPEARING_DELAY,
				new IEntityModifierListener() {

					@Override
					public void onModifierStarted(IModifier<IEntity> arg0,
							IEntity arg1) {
						mama.setVisible(false);
					}

					@Override
					public void onModifierFinished(IModifier<IEntity> arg0,
							IEntity arg1) {
						mama.setVisible(true);
						playAudio(R.raw.m_mama);
					}
				});
		FadeInModifier macch_fim2 = new FadeInModifier(APPEARING_TIME);
		SequenceEntityModifier macch_sm = new SequenceEntityModifier(dMod,
				macch_fim2);
		mama.registerEntityModifier(macch_sm);
	}

	void loadMa() {
		DelayModifier dMod = new DelayModifier(START_APPEARING_DELAY += 3,
				new IEntityModifierListener() {

					@Override
					public void onModifierStarted(IModifier<IEntity> arg0,
							IEntity arg1) {
						ma.setVisible(false);
					}

					@Override
					public void onModifierFinished(IModifier<IEntity> arg0,
							IEntity arg1) {
						ma.setVisible(true);
						playAudio(R.raw.m_ma);
					}
				});
		FadeInModifier macch_fim2 = new FadeInModifier(APPEARING_TIME);
		SequenceEntityModifier macch_sm = new SequenceEntityModifier(dMod,
				macch_fim2);
		ma.registerEntityModifier(macch_sm);
	}

	void loadMula() {
		DelayModifier dMod = new DelayModifier(START_APPEARING_DELAY += 5,
				new IEntityModifierListener() {

					@Override
					public void onModifierStarted(IModifier<IEntity> arg0,
							IEntity arg1) {
						mula.setVisible(false);
					}

					@Override
					public void onModifierFinished(IModifier<IEntity> arg0,
							IEntity arg1) {
						mula.setVisible(true);
						playAudio(R.raw.m_mula);
					}
				});
		FadeInModifier macch_fim2 = new FadeInModifier(APPEARING_TIME);
		SequenceEntityModifier macch_sm = new SequenceEntityModifier(dMod,
				macch_fim2);
		mula.registerEntityModifier(macch_sm);
	}

	void loadMegh() {
		DelayModifier dMod = new DelayModifier(START_APPEARING_DELAY += 5,
				new IEntityModifierListener() {

					@Override
					public void onModifierStarted(IModifier<IEntity> arg0,
							IEntity arg1) {
						megh.setVisible(false);
					}

					@Override
					public void onModifierFinished(IModifier<IEntity> arg0,
							IEntity arg1) {
						megh.setVisible(true);
						playAudio(R.raw.m_megh);
					}
				});
		FadeInModifier macch_fim2 = new FadeInModifier(APPEARING_TIME);
		SequenceEntityModifier macch_sm = new SequenceEntityModifier(dMod,
				macch_fim2);
		megh.registerEntityModifier(macch_sm);
	}

	void loadMacch() {
		DelayModifier dMod = new DelayModifier(START_APPEARING_DELAY += 5,
				new IEntityModifierListener() {

					@Override
					public void onModifierStarted(IModifier<IEntity> arg0,
							IEntity arg1) {
						macch.setVisible(false);
					}

					@Override
					public void onModifierFinished(IModifier<IEntity> arg0,
							IEntity arg1) {
						macch.setVisible(true);
						playAudio(R.raw.m_moi);
					}
				});
		FadeInModifier macch_fim2 = new FadeInModifier(APPEARING_TIME);
		SequenceEntityModifier macch_sm = new SequenceEntityModifier(dMod,
				macch_fim2);
		macch.registerEntityModifier(macch_sm);
	}

	void loadMohis() {
		DelayModifier dMod = new DelayModifier(START_APPEARING_DELAY += 5,
				new IEntityModifierListener() {

					@Override
					public void onModifierStarted(IModifier<IEntity> arg0,
							IEntity arg1) {
						mohis.setVisible(false);
					}

					@Override
					public void onModifierFinished(IModifier<IEntity> arg0,
							IEntity arg1) {
						mohis.setVisible(true);
						playAudio(R.raw.m_mohish);
					}
				});
		FadeInModifier macch_fim2 = new FadeInModifier(APPEARING_TIME);
		SequenceEntityModifier macch_sm = new SequenceEntityModifier(dMod,
				macch_fim2);
		mohis.registerEntityModifier(macch_sm);
	}

	void loadArrow() {
		DelayModifier dMod = new DelayModifier(START_APPEARING_DELAY += 5,
				new IEntityModifierListener() {

					@Override
					public void onModifierStarted(IModifier<IEntity> arg0,
							IEntity arg1) {
						mArrowRighr.setVisible(false);
					}

					@Override
					public void onModifierFinished(IModifier<IEntity> arg0,
							IEntity arg1) {
						//mArrowRighr.setVisible(true);
						audioPlay = true;
					}
				});
		FadeInModifier macch_fim2 = new FadeInModifier(APPEARING_TIME);
		SequenceEntityModifier macch_sm = new SequenceEntityModifier(dMod,
				macch_fim2);
		mArrowRighr.registerEntityModifier(macch_sm);
	}

	void loadMparrot() {
		MoveModifier mMod = new MoveModifier(4.0f, CAMERA_WIDTH + 200,CAMERA_WIDTH - 250, CAMERA_HIGHT / 2 -30,CAMERA_HIGHT / 2 - 80);
		MoveModifier mModLetter = new MoveModifier(4.0f, CAMERA_WIDTH + 200,CAMERA_WIDTH - 210, CAMERA_HIGHT / 2 + 60,CAMERA_HIGHT / 2 + 20);
		DelayModifier dMod = new DelayModifier(1,new IEntityModifierListener() {

					@Override
					public void onModifierStarted(IModifier<IEntity> arg0,
							IEntity arg1) {
						
					}

					@Override
					public void onModifierFinished(IModifier<IEntity> arg0,
							IEntity arg1) {
						playAudio(R.raw.parrot_introducing_mo);
						mLetter.setVisible(false);
						
					}
				});
		SequenceEntityModifier macch_sm = new SequenceEntityModifier(mMod,dMod);
		SequenceEntityModifier mLetter_sm = new SequenceEntityModifier(mModLetter,dMod);
		RotationModifier rm1 = new RotationModifier(0.4f, 0.0f, 0.3f);
		RotationModifier rm2 = new RotationModifier(0.4f, 0.3f, 0.0f);
		Parrpt_flying.registerEntityModifier(macch_sm);
		mLetter.registerEntityModifier(mLetter_sm);
		SequenceEntityModifier mLetter_sm2 = new SequenceEntityModifier(rm1,rm2);
		LoopEntityModifier Lpm = new LoopEntityModifier(mLetter_sm2);
		mLetter.registerEntityModifier(Lpm);
		
	}

	void playAudio(int val) {
		if(mediaPlayer.isPlaying() == false ){
			//mediaPlayer.release();
			MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),val);
			try {
				
					mediaPlayer.start();
					mediaPlayer.setLooping(false);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	void playAudio2(int val) {
		Log.d(d, "Is Playing Before If ");
		if(audioPlay){
			if (!mediaPlayer.isPlaying()){
				mediaPlayer.reset();
				Log.d(d, "Is Playing: " + mediaPlayer.isPlaying());
				mediaPlayer = MediaPlayer.create(getApplicationContext(),val);
				try {
					mediaPlayer.start();
					mediaPlayer.setLooping(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			audioPlay = true;
			
		}
	}

}