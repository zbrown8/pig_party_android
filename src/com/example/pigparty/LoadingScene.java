package com.example.pigparty;



import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;

import com.example.pigparty.SceneManager.SceneType;


public class LoadingScene extends MiniGameScene{

	private Sprite splash;
	private AnimatedSprite ellipse;
	@Override
	public void createScene() {
		// TODO Auto-generated method stub
		splash = new Sprite(720,480,resourcesManager.mloadingTextureRegion,vbom);
		splash.setPosition(0,0);
		attachChild(splash);
		ellipse = new AnimatedSprite(570, 215, resourcesManager.mellipseRegion, vbom);
		ellipse.animate(180);
		attachChild(ellipse);
	}

	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SceneType getSceneType() {
		// TODO Auto-generated method stub
		return SceneType.SCENE_SPLASH;
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub
	    splash.detachSelf();
	    splash.dispose();
	    this.detachSelf();
	    this.dispose();
	}

}

