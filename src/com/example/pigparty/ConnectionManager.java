package com.example.pigparty;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import android.util.Log;

public class ConnectionManager implements Runnable{
	/**
	 * This is what I think I need
	 * I need to make Socket s a public variable? so classes can access it
	 * I need a method to:
	 * 		start connection 
	 * 		close connection
	 * 		send data
	 * 		receive data
	 * According to stackOverflow - a thread terminates when the run function returns
	 * so this is how i will terminate each thread
	 * you cant restart a Thread once is has already started/ended
	 * so I am using an ExecutorService which from what I understand does what I need
	 * do i maybe want to run each possible method (send,receive, open,close)
	 * on different threads? im not exactly sure how multithreaded programming works yet
	 */
	
	private static ConnectionManager INSTANCE = new ConnectionManager();
	public Socket s;
	public BufferedReader in;
	public InputStream in2;
	public BufferedWriter out2;
	public OutputStream out;
	public String ip,toServer,fromServer;
	private LinkedList<Thread> threads;
	private boolean connection;
	private ExecutorService executorService;
	private Future future;
	private int curThread;
	private Object lock;
	private PrintWriter out3;
	private char c;
	public ConnectionManager(){
		threads = new LinkedList<Thread>();
		curThread=0;
		future = null;
		lock = new Object();
		c='c';
		s=null;
		executorService = Executors.newCachedThreadPool();
	}
	
	
	public enum ConnectionType{
		OPEN,
		CLOSE,
		SEND,
		RECEIVE
	}
	
	private ConnectionType currentConnectionType;
	
	//---------------------------------------------
    // Methods
    //---------------------------------------------
	
	public boolean isConnected(){
		if(s==null)
			return false;
		else
			return true;
	}
	
	public void setConnectionType(ConnectionType t){
		currentConnectionType = t;
	}
	
	public void openConnection(String ip){
		setConnectionType(ConnectionType.OPEN);
		this.ip=ip;
		threads.addLast(new Thread(this));
		Thread t =threads.getLast();
		t.start();
		
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void openConnection(){
		Log.d(null,"inside openConnection()");
			if(connection==false){
				if(ip.equals("")){
					Log.d(null, "empty ip");
					ResourcesManager.getInstance().gameToast("Please Enter valid ip");
				}
				else{
					Log.d(null,"inside openConnection ip is "+ip);
					try{
						Log.d(null,"inside try loop");
						
						s = new Socket(ip,6969);
						in = new BufferedReader(new InputStreamReader(s.getInputStream()));
						out = s.getOutputStream();
						out3 = new PrintWriter(s.getOutputStream(), true);
						out2 = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
						ResourcesManager.getInstance().gameToast("Connected!");
						char buf[] = new char[1];
						in.read(buf);
						if(buf[0]=='c'){
							connection=true;
							out.write('c');
						}
						else{
							ResourcesManager.getInstance().gameToast("IP is already in use");
						}
					}
					catch(IOException e){
						ResourcesManager.getInstance().gameToast("Connection Failed");
						e.printStackTrace();
					}
				}
			}
			else
				ResourcesManager.getInstance().gameToast("Already Connected to "+ip);
	}
	
	public void sendChar(char c){
		setConnectionType(ConnectionType.SEND);
		this.c=c;
		threads.addLast(new Thread(this));
		Thread t = threads.getLast();
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendChar(){
		try {
			out.write(c);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void sendData(String toServer){
		setConnectionType(ConnectionType.SEND);
		this.toServer = toServer;
		Log.d(null, "toServer is "+this.toServer);
		threads.addLast(new Thread(this));
		Thread t =threads.getLast();
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void sendData(){
			try {

				Log.d(null, "in thread toServer is "+toServer);
				out2.write("test");
				out3.println(300);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void close(){
		setConnectionType(ConnectionType.CLOSE);
		threads.addLast(new Thread(this));
		Thread t = threads.getLast();
		t.start();
	}
	
	private void closeConnection(){
		try {
			s.close();
			s=null;
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void receive(){
		setConnectionType(ConnectionType.RECEIVE);
		executorService.submit(this);
	}
	
	private void receiveData(){
			try {
				while(future.get()!=null){}
				fromServer = in.readLine() + System.getProperty("line.separator");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	@Override
	public void run() {
		synchronized(lock){
		if(threads.size()>10)
			threads.remove();
		// TODO Auto-generated method stub
		switch(currentConnectionType){
			case OPEN:
				openConnection();
				return;
			case CLOSE:
				closeConnection();
				return;
			case SEND:
				sendChar();
				return;
			case RECEIVE:
				receiveData();
				return;
			default:
				return;
		}
		}
		
	}
	
	
	public static ConnectionManager getInstance(){
		return INSTANCE;
	}
}
