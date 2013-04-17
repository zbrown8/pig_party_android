package com.example.pigparty;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



/**
 * This just allows a user to enter the computer's ip address.
 * It is a temporary solution to starting the game and 
 * will not be how the final implementation will work
 * @author zac
 * PARAMETERS:
 *  connectButton - used for connecting to server
 *  startButton - used for starting the game activity
 *  ipfield - textfield for user to enter ipaddress
 *  ip - the ip address entered by user
 *  connection - a boolean to see if the connection succeeds
 */
public class ConnectActivity extends Activity implements OnClickListener{
	private Button connectButton,startButton;
	private EditText ipfield;
	private String ip;
	public boolean connection;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ipfield = (EditText)this.findViewById(R.id.ipfield);
        ipfield.setText("");
        ip=null;
        connectButton = (Button)this.findViewById(R.id.connectbutton);
        connectButton.setOnClickListener(this);
        
        startButton = (Button)this.findViewById(R.id.startbutton);
        startButton.setOnClickListener(this);

    }

    /**
     * required for button listeners. determines which button was pressed
     * and acts accordingly
     */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		
		if(id==R.id.connectbutton){
			/*I need to test the connection with the ip address*/
			/*for now ill just test the socket connection open and close
			 * if it is a success ill start the game activity
			 * if it is a fail then ill toast message saying try again
			 */
			Log.d(null, "connectbutton pushed");
			testConnection();
			
		}
		else if(id==R.id.startbutton){
			if(connection==true)
				startGame(this,MainActivity.class);
			else
				Toast.makeText(getApplicationContext(), "Incorrect ip address try again", Toast.LENGTH_SHORT).show();

		}
			
	}
	
	/**
	 * tests a socket connection from the value in the edittext field
	 * any socket/tcp connections cant be performed in the main activity thread
	 * so we create a Runnable variable to run the socket in a new thread
	 */
	private void testConnection(){ 
			connection=false;
			ip = ipfield.getText().toString();
			Log.d(null,"ip is "+ip);
			if(ip.equals("")) 
				Toast.makeText(getApplicationContext(), "Please Enter an IP Address", Toast.LENGTH_SHORT).show();
			else{
				Runnable runnable = new Runnable(){
	
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
								Socket s = new Socket(ip,6969);
								s.close();
								connection=true;
								
							}
							 catch (UnknownHostException e1) {
								// TODO Auto-generated catch block
								Log.d(null, "Wrong ip");
								
								e1.printStackTrace();
							} catch (IOException e1) {
								Log.d(null, "Wrong ip");
								// TODO Auto-generated catch block
								
							}
					}
				};
				Thread thread = new Thread(runnable);
				thread.start();
			}
	}
	
	/**
	 * starts the game activity.
	 * we need to send the correct IPAddress from this activity
	 * to do that we need to send it through an intent.
	 * the intent stores data between activities
	 * This is called when the startConnection button is pressed
	 * @param a - this is the current ConnectActivity we are running. It will 
	 * 			  start the game activity by calling startActivity(intent)
	 * @param c - this is the class of the next activity to be called
	 * 			  in this case it is the game activity (MainActivity)
	 */
	public void startGame(Activity a, Class<?> c){
		Intent intent = new Intent(a,c);
		intent.putExtra("ipaddress", ip);
		a.startActivity(intent);
	}

}