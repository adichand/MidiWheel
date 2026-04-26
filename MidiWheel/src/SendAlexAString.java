import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SendAlexAString implements Runnable{
	private String ip = "localhost";
	private Scanner scanner = new Scanner (System.in);
	private Thread thread;
	private int errors = 0;
	private boolean unableToCommunicateWithFriend = false;
	
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;
	
	private ServerSocket serverSocket;
	
	public SendAlexAString(){
		thread = new Thread(this, "SendAlexAString");
		thread.start();
	}
	
	public static void main(String[] args) {
		SendAlexAString sendAlexAString = new SendAlexAString();
	}

	@Override
	public void run() {
		while(true) {
			tick();
			listenForServerRequest();
		}
		
	}
	
	private void tick() {
		if (errors >= 10) {
			unableToCommunicateWithFriend = true;
		}
	}
	
	private void listenForServerRequest() {
		Socket socket = null;
		try {
			socket = serverSocket.accept();
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
