package com.example.pigparty;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;

import com.example.pigparty.SceneManager.SceneType;

import android.util.Log;

public class TrafficDodgeScene extends MiniGameScene implements IOnMenuItemClickListener{
	public Sprite theWheel,car;
	
	private Sprite backgroundSprite,carSprite;
	private AnimatedSprite streetSprite,mirrorSprite,freshenerSprite;
	public Sprite wheelSprite;
	
	public static final int MENU_CONTINUE = 0;
	public static final int MENU_QUIT = 1;
	protected MenuScene pauseScreen;
	private TimerHandler spriteTimeHandler;
	
	private Sprite[] airbag;
	int xxx;
	public void createPauseScene(){
		ResourcesManager resourcesManager = ResourcesManager.getInstance();
		((MainActivity) activity).enableAccelerometer();
		
		
		pauseScreen = new MenuScene(camera);
		
		//Set up background image
		final Sprite menuBackgroundSprite = new Sprite(0,0,resourcesManager.mPauseBackgroundTextureRegion,vbom);
		pauseScreen.attachChild(menuBackgroundSprite);
		menuBackgroundSprite.setPosition(200, 20);
		//Set up continue button
		final IMenuItem continueButton = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_CONTINUE, resourcesManager.mPauseContinueTextureRegion, resourcesManager.activity.getVertexBufferObjectManager()), 1.2f, 1);
		pauseScreen.addMenuItem(continueButton);
		
		//Set up quit button
		final IMenuItem quitButton  = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_QUIT, resourcesManager.mPauseQuitTextureRegion, resourcesManager.activity.getVertexBufferObjectManager()), 1.2f, 1);
		pauseScreen.addMenuItem(quitButton);
		
	    pauseScreen.buildAnimations();
	    pauseScreen.setBackgroundEnabled(false);
	    
	    continueButton.setPosition(270, 200);
	    quitButton.setPosition(320, 240);
	    
	    pauseScreen.setOnMenuItemClickListener(this);
	    
	    
	}
	
	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
        switch(pMenuItem.getID())
        {
        case MENU_CONTINUE:
			((MainActivity) activity).enableAccelerometer();
			ConnectionManager.getInstance().sendChar('p');
			this.pauseScreen.back();
            return true;
        case MENU_QUIT:
        	SceneManager.getInstance().createStartScene();
        	ConnectionManager.getInstance().sendChar('q');
            return true;
        default:
            return false;
    }
	}
	
	
	@Override
	public void createScene() {
		setBackground(new Background(0, 0, 0));

		backgroundSprite = new Sprite(0,0,resourcesManager.traffic_background_region,vbom);
		backgroundSprite.setPosition(0, 0);
		attachChild(backgroundSprite);
		
		streetSprite = new AnimatedSprite(0,145,resourcesManager.mStreetTextureRegion,vbom);
		streetSprite.animate(120);
		attachChild(streetSprite);
		
		carSprite = new Sprite(0,0,resourcesManager.mCarTextureRegion,vbom);
		carSprite.setPosition(0,0);
		attachChild(carSprite);
		
		mirrorSprite = new AnimatedSprite(350,44,resourcesManager.mMirrorTextureRegion,vbom);
		mirrorSprite.animate(500);
		attachChild(mirrorSprite);
		
		freshenerSprite = new AnimatedSprite(490,130,resourcesManager.mFreshenerTextureRegion,vbom);
		freshenerSprite.animate(300);
		attachChild(freshenerSprite);
		
		wheelSprite = new Sprite(0,0,resourcesManager.mWheelTextureRegion,vbom);
		wheelSprite.setPosition(110, 270);
		attachChild(wheelSprite);
		
		ConnectionManager.getInstance().sendChar('s');
		
		createPauseScene();
		
		
		//for airbag im gonna comment it out for now
		/*airbag = new Sprite[3];
		airbag[0] = new Sprite(0,0,resourcesManager.mTrafficAigbagRegion1,vbom);
		airbag[1] = new Sprite(0,0,resourcesManager.mTrafficAigbagRegion2,vbom);
		airbag[2] = new Sprite(0,0,resourcesManager.mTrafficAigbagRegion3,vbom);
		attachChild(airbag[0]);
		attachChild(airbag[1]);
		attachChild(airbag[2]);
		airbag[0].setPosition(0, 0);
		airbag[1].setPosition(0, 0);
		airbag[2].setPosition(0, 0);
		xxx=0;
		airbag[0].setVisible(false);
		airbag[1].setVisible(false);
		airbag[2].setVisible(false);
		engine.registerUpdateHandler(spriteTimeHandler = new TimerHandler(.1f, new ITimerCallback() {
            
            @Override
            public void onTimePassed(final TimerHandler pTimerHandler) {
                    // TODO Auto-generated method stub                  
            	spriteTimeHandler.reset();
            	Log.d(null, "inside timer");
              if(xxx==0){
            	  if(airbag[2].isVisible())
            		  airbag[2].setVisible(false);
            	  airbag[0].setVisible(true);
            	  xxx++;
              }
              else if(xxx==1){
            	  airbag[0].setVisible(false);
            	  airbag[1].setVisible(true);
            	  xxx++;
              }
              else{
            	  airbag[1].setVisible(false);
            	  airbag[2].setVisible(true);
            	  
              }
                                         
                                                     
            }
    }));*/
		
		Log.e(null, "finished creating traffic.createScene");
	}


	
	
	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub
		//If the Menu Scene is showing right now
		if(hasChildScene()) {
			ConnectionManager.getInstance().sendChar('p');
			((MainActivity) activity).enableAccelerometer();
			this.pauseScreen.back();
		//Otherwise Display it
		} else {
			((MainActivity) activity).disableAccelerometer();
			ConnectionManager.getInstance().sendChar('p');
			setChildScene(pauseScreen, false, true, true);
		}
	} 


	@Override
	public SceneType getSceneType() {
		// TODO Auto-generated method stub
		return SceneType.SCENE_TRAFFIC;
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub
		backgroundSprite.detachSelf();
		backgroundSprite.dispose();
		/*Dashboard.detachSelf();
		Dashboard.dispose();
		Mirror.detachSelf();
		Mirror.dispose();
		theWheel.detachSelf();
		theWheel.dispose();*/
	}
	
	public String toString(){
		return "TrafficDodgeScene";
	}



}