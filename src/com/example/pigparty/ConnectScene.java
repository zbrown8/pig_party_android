package com.example.pigparty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.HorizontalAlign;

import android.util.Log;
import android.widget.Toast;

import com.example.pigparty.SceneManager.SceneType;

public class ConnectScene extends MiniGameScene implements IOnMenuItemClickListener{
	
	
	
	public static final int KEY_1 = 1;
	public static final int KEY_2 = 2;
	public static final int KEY_3 = 3;
	public static final int KEY_4 = 4;
	public static final int KEY_5 = 5;
	public static final int KEY_6 = 6;
	public static final int KEY_7 = 7;
	public static final int KEY_8 = 8;
	public static final int KEY_9 = 9;
	public static final int KEY_0 = 0;
	public static final int KEY_DOT = 10;
	public static final int KEY_DEL = 11;
	public static final int KEY_CONNECT = 12;
	
	private Sprite backgroundSprite,OKSprite;
	public Text centerText;
	public StringBuilder ipbuilder;
	
	private boolean connection;
	public int strLength;
	private MenuScene mMenuScene;
	
	@Override
	public void createScene() {
		// TODO Auto-generated method stub
		createBackground();
		ipbuilder = new StringBuilder("               ");
		strLength=0;
		connection = false;
		centerText = new Text(145, 220, resourcesManager.mFont, ipbuilder.toString(), new TextOptions(HorizontalAlign.LEFT), vbom);
		attachChild(centerText);
		
		createMenuChildScene();
		
		
		
	}
	
	private void createBackground(){
		setBackground(new Background(0,0,0));
		backgroundSprite = new Sprite(720,480,resourcesManager.mConnectBackgroundRegion,vbom);
		backgroundSprite.setPosition(0,0);
		attachChild(backgroundSprite);
	}
	
	private void createMenuChildScene(){
		ResourcesManager resourcesManager = ResourcesManager.getInstance();
		
		
		mMenuScene = new MenuScene(camera);
		
		//Set up start button
		final IMenuItem Button1 = new ScaleMenuItemDecorator(new SpriteMenuItem(KEY_1, resourcesManager.mConnectB1, resourcesManager.activity.getVertexBufferObjectManager()), 1.2f, 1);
		mMenuScene.addMenuItem(Button1);
		
		final IMenuItem Button2 = new ScaleMenuItemDecorator(new SpriteMenuItem(KEY_2, resourcesManager.mConnectB2, resourcesManager.activity.getVertexBufferObjectManager()), 1.2f, 1);
		mMenuScene.addMenuItem(Button2);
		
		final IMenuItem Button3 = new ScaleMenuItemDecorator(new SpriteMenuItem(KEY_3, resourcesManager.mConnectB3, resourcesManager.activity.getVertexBufferObjectManager()), 1.2f, 1);
		mMenuScene.addMenuItem(Button3);
		
		final IMenuItem Button4 = new ScaleMenuItemDecorator(new SpriteMenuItem(KEY_4, resourcesManager.mConnectB4, resourcesManager.activity.getVertexBufferObjectManager()), 1.2f, 1);
		mMenuScene.addMenuItem(Button4);
		
		final IMenuItem Button5 = new ScaleMenuItemDecorator(new SpriteMenuItem(KEY_5, resourcesManager.mConnectB5, resourcesManager.activity.getVertexBufferObjectManager()), 1.2f, 1);
		mMenuScene.addMenuItem(Button5);
		
		final IMenuItem Button6 = new ScaleMenuItemDecorator(new SpriteMenuItem(KEY_6, resourcesManager.mConnectB6, resourcesManager.activity.getVertexBufferObjectManager()), 1.2f, 1);
		mMenuScene.addMenuItem(Button6);
		
		final IMenuItem Button7 = new ScaleMenuItemDecorator(new SpriteMenuItem(KEY_7, resourcesManager.mConnectB7, resourcesManager.activity.getVertexBufferObjectManager()), 1.2f, 1);
		mMenuScene.addMenuItem(Button7);
		
		final IMenuItem Button8 = new ScaleMenuItemDecorator(new SpriteMenuItem(KEY_8, resourcesManager.mConnectB8, resourcesManager.activity.getVertexBufferObjectManager()), 1.2f, 1);
		mMenuScene.addMenuItem(Button8);
		
		final IMenuItem Button9 = new ScaleMenuItemDecorator(new SpriteMenuItem(KEY_9, resourcesManager.mConnectB9, resourcesManager.activity.getVertexBufferObjectManager()), 1.2f, 1);
		mMenuScene.addMenuItem(Button9);
		
		final IMenuItem Button0 = new ScaleMenuItemDecorator(new SpriteMenuItem(KEY_0, resourcesManager.mConnectB0, resourcesManager.activity.getVertexBufferObjectManager()), 1.2f, 1);
		mMenuScene.addMenuItem(Button0);
		
		final IMenuItem ButtonDot = new ScaleMenuItemDecorator(new SpriteMenuItem(KEY_DOT, resourcesManager.mConnectDot, resourcesManager.activity.getVertexBufferObjectManager()), 1.2f, 1);
		mMenuScene.addMenuItem(ButtonDot);
		
		final IMenuItem ButtonDel = new ScaleMenuItemDecorator(new SpriteMenuItem(KEY_DEL, resourcesManager.mConnectDel, resourcesManager.activity.getVertexBufferObjectManager()), 1.2f, 1);
		mMenuScene.addMenuItem(ButtonDel);
		
		final IMenuItem ButtonConnect = new ScaleMenuItemDecorator(new SpriteMenuItem(KEY_CONNECT, resourcesManager.mConnectTestC, resourcesManager.activity.getVertexBufferObjectManager()), 1.2f, 1);
		mMenuScene.addMenuItem(ButtonConnect);
		
	    mMenuScene.buildAnimations();
	    mMenuScene.setBackgroundEnabled(false);
	    
		//Set the positions on screen
	    Button1.setPosition(10,340);
	    Button2.setPosition(70,340);
	    Button3.setPosition(130,340);
	    Button4.setPosition(190,340);
	    Button5.setPosition(250,340);
	    Button6.setPosition(310,340);
	    Button7.setPosition(370,340);
	    Button8.setPosition(430,340);
	    Button9.setPosition(490,340);
	    Button0.setPosition(550,340);
	    ButtonDot.setPosition(610,340);
	    ButtonDel.setPosition(670,340);
	    ButtonConnect.setPosition(60,400);
		//startButton.setPosition(startButton.getX(), startButton.getY() + 10);
		//quitButton.setPosition(quitButton.getX(), quitButton.getY() - 110);
	    
		this.mMenuScene.setOnMenuItemClickListener(this);
		
		//attach the buttons to the StartScene
		setChildScene(this.mMenuScene, false, true, true);
	}

	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub
		SceneManager.getInstance().createStartScene();
	}

	@Override
	public SceneType getSceneType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub

	}

	private void updateIP(char num){
		ipbuilder.setCharAt(strLength, num);
		centerText.setText(ipbuilder.toString());
		if(strLength<14)
			strLength++;
	}
	
	private void delete(){ 
		if(strLength>0){
			strLength--;
			ipbuilder.setCharAt(strLength, ' ');
			
		}
		else
			ipbuilder.setCharAt(strLength, ' ');
		
		centerText.setText(ipbuilder.toString());
		
			
	}
	
	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		
		Log.e(null, "Menu Item clicked! with ID = "+pMenuItem.getID());
        switch(pMenuItem.getID())
        {
        case KEY_DOT:
        	updateIP('.');
            return true;
        case KEY_1:
        	updateIP('1');
        	return true;
        case KEY_2:
        	updateIP('2');
        	return true;
        case KEY_3:
        	updateIP('3');
        	return true;
        case KEY_4:
        	updateIP('4');
        	return true;
        case KEY_5:
        	updateIP('5');
        	return true;
        case KEY_6:
        	updateIP('6');
        	return true;
        case KEY_7:
        	updateIP('7');
        	return true;
        case KEY_8:
        	updateIP('8');
        	return true;
        case KEY_9:
        	updateIP('9');
        	return true;
        case KEY_0:
        	updateIP('0');
        	return true;
        case KEY_DEL:
        	delete();
        	return true;
        case KEY_CONNECT:
        	testConnection();
        	return true;
        default:
            return false;
    }
	}
	
	private void testConnection(){
		String ip = ipbuilder.substring(0, strLength);
		Log.d(null,"ip is "+ip);
		if(ConnectionManager.getInstance().isConnected()==true)
			ConnectionManager.getInstance().sendChar('x');
		
		ConnectionManager.getInstance().openConnection(ip);
		
		/*if(ip.equals("")){
			Log.d(null, "empty ip");
			gameToast("Please Enter valid ip");
		}*/
		//else
			//ConnectionManager.getInstance().openConnection(ip);
			//ConnectionManager.getInstance().sendData("test");
			//ConnectionManager.getInstance().close();
		//String ip = ipbuilder.substring(0, strLength);
		//Log.d(null,"ip is "+ip);
		/*if(connection==false){
			if(ip.equals("")){
				Log.d(null, "empty ip");
				gameToast("Please Enter valid ip");
			}
			else{
				Runnable runnable = new Runnable(){
					String ip = ipbuilder.substring(0,strLength);
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
								Socket s = new Socket(ip,6969);
								BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
								char[] buf = new char[1];
								input.read(buf);
								if(buf[0]=='c')
									connection=true;
								else{
									gameToast("IP is already in use");
								}
									
								
							}
							 catch (UnknownHostException e1) {
								// TODO Auto-generated catch block
								Log.d(null, "Wrong ip");
								gameToast("Wrong ip");
								
								e1.printStackTrace();
							} catch (IOException e1) {
								Log.d(null, "Wrong ip");
								gameToast("Wrong ip");
								// TODO Auto-generated catch block
								
							}
					} 
				};
				Thread thread = new Thread(runnable);
				thread.start();
			}
		}
		else
			gameToast("Already Connected to "+ip);
		*/
	}
	


}
