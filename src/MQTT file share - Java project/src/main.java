import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class main {
	
	static MQTT_handler mqtt;
	
	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter broker IP (test server: test.mosquitto.org:1883): ");
		
		mqtt = new MQTT_handler("tcp://" + reader.next());
		byte[] data = {0};
		
		System.out.print("Enter R for receive or S for send: ");
		String cmd = reader.next();
		if (cmd.equals("s") || cmd.equals("S")) {
			System.out.print("Enter path to file (cat.png or C:\\...\\cat.png): ");
			cmd = reader.next();
			Path path = Paths.get(cmd);
			try {
				data = Files.readAllBytes(path);
			} catch (IOException e) {
				System.out.println("File does not exists.");
				System.exit(0);
			}
			File f = new File(cmd);
			System.out.println("File loaded. Size is: " + (float)f.length()/1000000 + "MB");
			System.out.print("Enter topic name for transfer: ");
			System.out.println("Sending file...");
			long startTime = System.currentTimeMillis();
			mqtt.publish(reader.next(), data);
			float time = (float)(System.currentTimeMillis()-startTime)/1000;
			System.out.println("File was send. Time: " + time + " seconds  Speed: " + (float)(f.length()/time/1000000) + "MB/s");
			reader.close();
			System.exit(0);
		}
		else if(cmd.equals("r") || cmd.equals("R")) {
			System.out.print("Enter name of file, which you wanna save it to (egg. cat.png): ");
			mqtt.filename = reader.next();
			System.out.print("Enter topic name for transfer: ");
			mqtt.subscribe(reader.next());
			System.out.print("waiting for file...");
			reader.close();
		}
		else {
			System.out.println("incorrect character");
			System.exit(0);
		}
		/*
		Path path = Paths.get("V:\\javaTesting\\hello.png");
		try {
			data = Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		FileOutputStream fout;
		try {
			fout = new FileOutputStream("V:\\javaTesting\\heli.txt");
			fout.write(data);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/

	}
	
}
