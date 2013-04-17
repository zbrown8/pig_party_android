package com.example.pigparty;

import org.andengine.engine.Engine;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;

import android.util.Log;



public class SceneManager {
    //---------------------------------------------
    // SCENES
    //---------------------------------------------
    
    private MiniGameScene splashScene;
    private MiniGameScene menuScene;
    private MiniGameScene trafficScene;
    private MiniGameScene startScene;
    private MiniGameScene connectScene;
    
    //---------------------------------------------
    // VARIABLES
    //---------------------------------------------
    
    private static final SceneManager INSTANCE = new SceneManager();
    
    private SceneType currentSceneType = SceneType.SCENE_SPLASH;
    
    private MiniGameScene currentScene;
    
    private Engine engine = ResourcesManager.getInstance().engine;
    
    public enum SceneType
    {
        SCENE_SPLASH,
        SCENE_MENU,
        SCENE_TRAFFIC,
        SCENE_START,
        SCENE_CONNECT
    }
    
    //---------------------------------------------
    // CLASS LOGIC
    //---------------------------------------------
    
    /**
     * This method sets the scene in the MainActivty
     * to the desired scene
     * @param scene
     */
    public void setScene(MiniGameScene scene)
    {
        engine.setScene(scene);
        currentScene = scene;
        currentSceneType = scene.getSceneType();
       
    }
    
    /**
     * This method calls the above setScene based
     * on which scene the ResourceManager wants
     * @param sceneType
     */
    public void setScene(SceneType sceneType)
    {
        switch (sceneType)
        {
            case SCENE_MENU:
                setScene(menuScene);
                break;
            case SCENE_TRAFFIC:
                setScene(trafficScene);
                break;
            case SCENE_SPLASH:
                setScene(splashScene);
                break;
            case SCENE_START:
            	setScene(startScene);
            case SCENE_CONNECT:
            	setScene(connectScene);
            default:
                break;
        }
    }
    
    
    public void createStartScene(){
    	ResourcesManager.getInstance().loadStartGraphics();
    	startScene = new StartScene();
    	currentScene = startScene;
    	setScene(startScene);
    }
    
    public void disposeStartScene(){
    	
    }
    
    public void createConnectScene(){
    	Log.e(null,"inside create connect scene");
    	ResourcesManager.getInstance().loadConnectGraphics();
    	connectScene = new ConnectScene();
    	setScene(connectScene);
    	disposeStartScene();
    }
    
    public void createTrafficScene(){
    	Log.e(null, "inside create traffic scene");
    	ResourcesManager.getInstance().loadGameGraphics();
    	trafficScene = new TrafficDodgeScene();
    	currentScene = trafficScene;
    	//pOnCreateSceneCallback.onCreateSceneFinished(trafficScene);
    	setScene(trafficScene);
    	disposeLoadingScene();
    }
    
    public void disposeTrafficScene(){
    	ResourcesManager.getInstance().unloadGameGraphics();
    	trafficScene.disposeScene();
    	trafficScene=null;
    }
    
    
    /**
     * Create Loading Scene
     * @return
     */
    public void createLoadingScene(OnCreateSceneCallback pOnCreateSceneCallback){
    	Log.d(null, "Called SceneManager.createLoadingScene");
        ResourcesManager.getInstance().loadSplashScreen();
        splashScene = new LoadingScene();
        currentScene = splashScene;
        //setScene(splashScene);
        pOnCreateSceneCallback.onCreateSceneFinished(splashScene);
    }
    
    /**
     * Get rid of Loading Scene
     * @return
     */
    private void disposeLoadingScene()
    {
        ResourcesManager.getInstance().unloadSplashScreen();
        splashScene.disposeScene();
        splashScene = null;
    }
    
    
    
    public void loadGameScene(final Engine mEngine)
    {
        setScene(splashScene);
        
        mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() 
        {
            public void onTimePassed(final TimerHandler pTimerHandler) 
            {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                
                
                ResourcesManager.getInstance().loadGameResources();
                trafficScene = new TrafficDodgeScene();
                setScene(trafficScene);
            }
        }));
    }
    
    //---------------------------------------------
    // GETTERS AND SETTERS
    //---------------------------------------------
    
    public static SceneManager getInstance()
    {
        return INSTANCE;
    }
    
    public SceneType getCurrentSceneType()
    {
        return currentSceneType;
    }
    
    public MiniGameScene getCurrentScene()
    {
        return currentScene;
    }
    

}
