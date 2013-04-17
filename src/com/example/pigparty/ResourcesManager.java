package com.example.pigparty;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.util.Log;
import android.widget.Toast;



/**
 * This class is responsible for loading and unloading the resources of each
 * MiniGame
 * For us it will also provide reference to some commonly used objects
 * (camera, engine, etc)
 * @author zac
 *
 */
public class ResourcesManager {
	
    //---------------------------------------------
    // VARIABLES
    //---------------------------------------------
    private static final ResourcesManager INSTANCE = new ResourcesManager();
    public Engine engine;
    public MainActivity activity;
    public Camera camera;
    public VertexBufferObjectManager vbom;
    
    //---------------------------------------------
    // LOADING SCREEN VARIABLES
    //---------------------------------------------
    public ITextureRegion mloadingTextureRegion;
    private BitmapTextureAtlas mloadingTextureAtlas;
    public ITiledTextureRegion mellipseRegion;
    private BitmapTextureAtlas mellipseTextureAtlas;
    
    //---------------------------------------------
    // CONNECT SCENE VARIABLES
    //---------------------------------------------
    public ITextureRegion mConnectBackgroundRegion,mConnectOKButton;
    private BitmapTextureAtlas mConnectBackgroundAtlas,mConnectMenuAtlas;
    public ITextureRegion mConnectB1,mConnectB2,mConnectB3,mConnectB4,mConnectB5,mConnectB6,mConnectB7,mConnectB8,mConnectB9,mConnectB0;
    public ITextureRegion mConnectDot, mConnectDel, mConnectTestC;
    public ITexture mFontTexture;
    public Font mFont;
    
    
    //---------------------------------------------
    // TRAFFIC SCENE VARIABLES
    //---------------------------------------------
    public ITextureRegion traffic_background_region, mCarTextureRegion,mWheelTextureRegion;
    private BitmapTextureAtlas mBackgroundTextureAtlas,mStreetTextureAtlas,mCarTextureAtlas,mirrorTextureAtlas, mFreshenerTextureAtlas,mWheelTextureAtlas;
    public ITiledTextureRegion mStreetTextureRegion,mMirrorTextureRegion, mFreshenerTextureRegion;
    private BitmapTextureAtlas mTrafficAirbagAtlas1,mTrafficAirbagAtlas2,mTrafficAirbagAtlas3;
    public ITextureRegion mTrafficAigbagRegion1,mTrafficAigbagRegion2,mTrafficAigbagRegion3;

	
	//---------------------------------------------
    // Menu Variables
    //---------------------------------------------
	private BitmapTextureAtlas mMenuTexture;
	public ITextureRegion mMenuResetTextureRegion,mMenuQuitTextureRegion;

	//---------------------------------------------
    // Start Scene Variables
    //---------------------------------------------
	private BitmapTextureAtlas mStartBackgroundAtlas,mstartTextureAtlas;
	public ITextureRegion mStartBackgroungTextureRegion, mStartTextureRegion,mConnectTextureRegion,mQuitTextureRegion;
	
	//---------------------------------------------
    // Pause Screen Variables
    //---------------------------------------------
	private BitmapTextureAtlas mPauseBackgroundAtlas,mPauseMenuAtlas;
	public ITextureRegion mPauseBackgroundTextureRegion,mPauseContinueTextureRegion,mPauseQuitTextureRegion;
	
    public void loadMenuResources()
    {
        //loadMenuGraphics();
    }
    
    public void loadGameResources()
    {
        loadGameGraphics();
    }
    
    /**
     * Loads the graphics for when user hits the menu button during the game
     */
   /* public void loadMenuGraphics(){
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
    	
		this.mMenuTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
		this.mMenuResetTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mMenuTexture, activity, "menu_reset.png", 0, 0);
		this.mMenuQuitTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mMenuTexture, activity, "menu_quit.png", 0, 50);
		this.mMenuTexture.load();
    }*/
    
    /**
     * Loads the graphics for the first screen - start screen
     */
    public void loadStartGraphics(){
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/Start/");
    	
    	this.mStartBackgroundAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 1024, 512, TextureOptions.BILINEAR);
    	this.mStartBackgroungTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mStartBackgroundAtlas, activity, "Titlepage3.png", 0, 0);
    	this.mStartBackgroundAtlas.load();
    	
    	this.mstartTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 1024, 128, TextureOptions.BILINEAR);
    	this.mStartTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mstartTextureAtlas, activity, "start_button.png", 0, 0);
    	this.mConnectTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mstartTextureAtlas, activity, "connect_button.png", 0, 40);
    	this.mQuitTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mstartTextureAtlas, activity, "quit_button.png", 0, 80);
    	this.mstartTextureAtlas.load();
    }
    
    public void loadPauseGraphics(){
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/Pause/");
    	this.mPauseBackgroundAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
    	this.mPauseBackgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mPauseBackgroundAtlas, activity, "Pausedballoon.png", 0, 0);
    	this.mPauseBackgroundAtlas.load();
    	
    	this.mPauseMenuAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
    	this.mPauseContinueTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mPauseMenuAtlas, activity, "Continuebut.png", 0, 0);
    	this.mPauseQuitTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mPauseMenuAtlas, activity, "Quitbut.png", 0, 39);
    	this.mPauseMenuAtlas.load();
    	
    }
    public void loadConnectGraphics(){
    	FontFactory.setAssetBasePath("Font/");
    	mFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
    	this.mFont = FontFactory.createFromAsset(activity.getFontManager(), mFontTexture, activity.getAssets(), "apple_casual.ttf", 48, true, android.graphics.Color.WHITE);
    	this.mFont.load();
    	
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/Connect/");
    	
    	this.mConnectBackgroundAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 1024, 512, TextureOptions.BILINEAR);
    	this.mConnectBackgroundRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mConnectBackgroundAtlas, activity, "EnterIP2.png", 0, 0);
    	this.mConnectBackgroundAtlas.load();
    
    	this.mConnectMenuAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
    	this.mConnectB1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mConnectMenuAtlas, activity, "01.png", 0, 0);
    	this.mConnectB2 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mConnectMenuAtlas, activity, "02.png", 50, 0);
    	this.mConnectB3 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mConnectMenuAtlas, activity, "03.png", 100, 0);
    	this.mConnectB4 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mConnectMenuAtlas, activity, "04.png", 150, 0);
    	this.mConnectB5 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mConnectMenuAtlas, activity, "05.png", 200, 0);
    	this.mConnectB6 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mConnectMenuAtlas, activity, "06.png", 250, 0);
    	this.mConnectB7 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mConnectMenuAtlas, activity, "07.png", 450, 0);
    	this.mConnectB8 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mConnectMenuAtlas, activity, "08.png", 300, 0);
    	this.mConnectB9 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mConnectMenuAtlas, activity, "09.png", 350, 0);
    	this.mConnectB0 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mConnectMenuAtlas, activity, "0.png", 400, 0);
    	this.mConnectDot = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mConnectMenuAtlas, activity, "dot.png", 500, 0);
    	this.mConnectDel = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mConnectMenuAtlas, activity, "delete.png", 550, 0);
    	this.mConnectTestC = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mConnectMenuAtlas, activity, "Connect.png", 600, 0);
    	this.mConnectMenuAtlas.load();
    	
    	
    }
    
    
    /**
     * This method loads all of the graphics for the 
     * TrafficDodge MiniGame.
     */
    public void loadGameGraphics(){
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/traffic/");
    	
    	//This is loading everything from stacy's images
    	this.mBackgroundTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 720, 480, TextureOptions.BILINEAR);
    	this.traffic_background_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBackgroundTextureAtlas, activity, "Background_1.png", 0, 0);
    	mBackgroundTextureAtlas.load();
    	
    	this.mStreetTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 1024, 512, TextureOptions.BILINEAR);
    	this.mStreetTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mStreetTextureAtlas, activity, "rs05.png", 0, 0, 3,1);
    	this.mStreetTextureAtlas.load();
    	
    	this.mCarTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 720, 480, TextureOptions.BILINEAR);
    	this.mCarTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mCarTextureAtlas, activity, "Car_dashboard.png", 0, 0);
		this.mCarTextureAtlas.load();
		
		this.mirrorTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 1024, 128, TextureOptions.BILINEAR);
		this.mMirrorTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mirrorTextureAtlas, activity, "RearviewMirrorSprite.png", 0, 0, 2,1);
		this.mirrorTextureAtlas.load();
		
		this.mFreshenerTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
		this.mFreshenerTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mFreshenerTextureAtlas, activity, "AirFreshnerSprite.png", 0, 0, 2,1);
    	this.mFreshenerTextureAtlas.load();
    	
    	this.mWheelTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(),512,512,TextureOptions.BILINEAR);
    	this.mWheelTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mWheelTextureAtlas, activity, "Wheel.png", 0, 0);
    	this.mWheelTextureAtlas.load();
    	
    	//for airbag im gonna comment it out for now
    	/*
    	this.mTrafficAirbagAtlas1= new BitmapTextureAtlas(activity.getTextureManager(),1024,512,TextureOptions.BILINEAR);
    	this.mTrafficAirbagAtlas2= new BitmapTextureAtlas(activity.getTextureManager(),1024,512,TextureOptions.BILINEAR);
    	this.mTrafficAirbagAtlas3= new BitmapTextureAtlas(activity.getTextureManager(),1024,512,TextureOptions.BILINEAR);
    	
    	this.mTrafficAigbagRegion1= BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mTrafficAirbagAtlas1, activity, "Airbagsprite1.png", 0, 0);
    	this.mTrafficAigbagRegion2= BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mTrafficAirbagAtlas2, activity, "Airbagsprite2.png", 0, 0);
    	this.mTrafficAigbagRegion3= BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mTrafficAirbagAtlas3, activity, "Airbagsprite3.png", 0, 0);
    	
    	mTrafficAirbagAtlas1.load();
    	mTrafficAirbagAtlas2.load();
    	mTrafficAirbagAtlas3.load();*/
    	
    	loadPauseGraphics();

    }
    
    /**
     * This unloads all of the TrafficDodge Resources.
     * We need to do this do make memory for
     * other resources in the future.
     */
    public void unloadGameGraphics(){


    	mirrorTextureAtlas.unload();
    	mMirrorTextureRegion=null;
    	//mWheelAtlas.unload();

    }
    
    /**
     * This is for our loading screen
     */
    public void loadSplashScreen()
    {
    	Log.d(null,"Called ResourceManager.loadSplashScreen");
	    BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/Loading/");
	    mloadingTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 720, 480, TextureOptions.BILINEAR);
	    mloadingTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mloadingTextureAtlas, activity, "Loadingpage2.png", 0, 0);
	    mloadingTextureAtlas.load();
	    
	    mellipseTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(),512,128,TextureOptions.BILINEAR);
	    mellipseRegion =  BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mellipseTextureAtlas, activity, "Loadingsprite.png", 0, 0, 3,1);
	    mellipseTextureAtlas.load();
    }
    /**
     * This is for our loading screen
     */
    public void unloadSplashScreen()
    {
    	mloadingTextureAtlas.unload();
    	mloadingTextureRegion = null;
    	mellipseTextureAtlas.unload();
    	mellipseRegion=null;
    }
    
    /**
     * @param engine
     * @param activity
     * @param camera
     * @param vbom
     * <br><br>
     * We use this method at beginning of game loading, to prepare Resources Manager properly,
     * setting all needed parameters, so we can latter access them from different classes (eg. scenes)
     */
    public static void prepareManager(Engine engine, MainActivity activity, Camera camera, VertexBufferObjectManager vbom){
        getInstance().engine = engine;
        getInstance().activity = activity;
        getInstance().camera = camera;
        getInstance().vbom = vbom;
    }
    
    public void gameToast(final String msg) {
    	activity.runOnUiThread(new Runnable() {
        @Override
        public void run() {
           Toast.makeText(ResourcesManager.getInstance().activity.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        }
    	});
    }
    
    //---------------------------------------------
    // GETTERS AND SETTERS
    //---------------------------------------------
    
    public static ResourcesManager getInstance()
    {
        return INSTANCE;
    }
}