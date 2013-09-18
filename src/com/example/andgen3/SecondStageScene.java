package com.example.andgen3;

import org.andengine.engine.options.EngineOptions;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.ui.activity.SimpleBaseGameActivity;

public class SecondStageScene extends SimpleBaseGameActivity {
	
	public Scene mCurrentScene;
	
	public SecondStageScene() {
		super();
		mCurrentScene.setBackground(new Background(0.09804f, 0.7274f, 0f));
		//StaticResources sr = new StaticResources();
		//mCurrentScene.attachChild(sr.getBG());
		
	}

	@Override
	public EngineOptions onCreateEngineOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onCreateResources() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Scene onCreateScene() {
		// TODO Auto-generated method stub
		return null;
	}

}
