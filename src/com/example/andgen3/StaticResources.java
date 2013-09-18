package com.example.andgen3;

import org.andengine.engine.options.EngineOptions;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

public class StaticResources extends SimpleBaseGameActivity {
	
	static final int CAMERA_WIDTH = 860;
	static final int CAMERA_HIGHT = 480;
	
	BuildableBitmapTextureAtlas bgBitmapTextureAtlas;
	
	public ITextureRegion bgTextureReason;
	BitmapTextureAtlas mBitmapPlayerTextureAtlas;
	
	

	@Override
	public EngineOptions onCreateEngineOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onCreateResources() {
		// TODO Auto-generated method stub
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		bgBitmapTextureAtlas = new BuildableBitmapTextureAtlas(this.getTextureManager(), 700, 1024,TextureOptions.NEAREST);
		this.bgTextureReason = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bgBitmapTextureAtlas, this, "jungle1.png");
		
		
		try {
			bgBitmapTextureAtlas
					.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
			bgBitmapTextureAtlas.load();
			this.mBitmapPlayerTextureAtlas.load();

		} catch (TextureAtlasBuilderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	@Override
	protected Scene onCreateScene() {
		// TODO Auto-generated method stub
		return null;
	}
	public Sprite getBG(){
		
		Sprite spr = new Sprite(0, 0, bgTextureReason,this.getVertexBufferObjectManager());
		return spr;
	}
	

}
