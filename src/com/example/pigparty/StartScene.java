package com.example.pigparty;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;

import android.util.Log;

import com.example.pigparty.SceneManager.SceneType;

public class StartScene extends MiniGameScene implements IOnMenuItemClickListener{

	public static final int MENU_START = 0;
	public static final int MENU_QUIT = 1;
	public static final int MENU_CONNECT = 2;
	private MenuScene mMenuScene;
	private Sprite backgroundSprite;


	@Override
	public void createScene() {
		// TODO Auto-generated method stub
		createBackground();
		createMenuChildScene();

	}

	private void createBackground(){
		setBackground(new Background(0,0,0));
		backgroundSprite = new Sprite(720,480,resourcesManager.mStartBackgroungTextureRegion,vbom);
		backgroundSprite.setPosition(0,0);
		attachChild(backgroundSprite);
	}
	
	private void createMenuChildScene(){
		ResourcesManager resourcesManager = ResourcesManager.getInstance();
		
		
		mMenuScene = new MenuScene(camera);
		
		//Set up start button
		final IMenuItem startButton = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_START, resourcesManager.mStartTextureRegion, resourcesManager.activity.getVertexBufferObjectManager()), 1.2f, 1);
		mMenuScene.addMenuItem(startButton);
		
		//Set up quit button
		final IMenuItem quitButton  = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_QUIT, resourcesManager.mQuitTextureRegion, resourcesManager.activity.getVertexBufferObjectManager()), 1.2f, 1);
		mMenuScene.addMenuItem(quitButton);
		
		final IMenuItem connectButton = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_CONNECT, resourcesManager.mConnectTextureRegion, resourcesManager.activity.getVertexBufferObjectManager()), 1.2f, 1);
		mMenuScene.addMenuItem(connectButton);
		
	    mMenuScene.buildAnimations();
	    mMenuScene.setBackgroundEnabled(false);
	    
		//Set the positions on screen
	    startButton.setPosition(90,340);
	    connectButton.setPosition(250,340);
	    quitButton.setPosition(474,340);
	    
		//startButton.setPosition(startButton.getX(), startButton.getY() + 10);
		//quitButton.setPosition(quitButton.getX(), quitButton.getY() - 110);
	    
		this.mMenuScene.setOnMenuItemClickListener(this);
		
		//attach the buttons to the StartScene
		setChildScene(this.mMenuScene, false, true, true);
	}
	
	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SceneType getSceneType() {
		// TODO Auto-generated method stub
		return SceneType.SCENE_START;
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		
		Log.e(null, "Menu Item clicked! with ID = "+pMenuItem.getID());
        switch(pMenuItem.getID())
        {
        case MENU_START:
        	if(ConnectionManager.getInstance().isConnected()==true)
        		SceneManager.getInstance().loadGameScene(engine);
        	else
        		resourcesManager.gameToast("Please Connect to Computer First");
            return true;
        case MENU_QUIT:
        	if(ConnectionManager.getInstance().isConnected()==true)
        		ConnectionManager.getInstance().close();
        	else
        		System.exit(0);
            return true;
        case MENU_CONNECT:
        	SceneManager.getInstance().createConnectScene();
        	return true;
        default:
            return false;
    }
	}

}
