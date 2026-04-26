import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

// no magic numbers
static int NUM_ALLOWED_ERRORS = 10;

public class SendAdiAString implements Runnable{
	private String ip = "localhost";
	private Scanner scanner = new Scanner (System.in);
	private Thread thread;
	private int errors = 0;
	private boolean unableToCommunicateWithFriend = false;
	
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;
	
	private ServerSocket serverSocket;
	
	public SendAdiAString(){
		thread = new Thread(this, "SendAdiAString");
		thread.start();
	}
	
	public static void main(String[] args) {
		SendAdiAString sendAdiAString = new SendAdiAString();
	}

	@Override
	public void run() {
		while(true) {
			tick();
			listenForServerRequest();
		}
		
	}
	
	private void tick() {
		if (errors >= NUM_ALLOWED_ERRORS) {
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
