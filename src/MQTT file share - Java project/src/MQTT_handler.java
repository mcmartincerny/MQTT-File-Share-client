
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.paho.client.mqttv3.*;

public class MQTT_handler implements MqttCallback {

	MqttClient client;
	public byte[] msg = {0};
	public String filename = "null.txt";

	public MQTT_handler(String ip) {
		try {
			client = new MqttClient(ip, "TransferClient: " + System.currentTimeMillis()); //use current millis like random number
			client.connect();
		} catch (MqttException e) {
			System.out.println("Can't connect to the server");
			System.exit(0);
		}
	}

	public void publish(String topic, byte[] payload) {
		try {
			MqttMessage message = new MqttMessage();
			message.setPayload(payload);
			client.publish(topic, message);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	public void subscribe(String topic) {
		client.setCallback(this);
        try {
			client.subscribe(topic);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void connectionLost(Throwable cause) {

	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("Receiving file...");
		msg = message.getPayload();
		FileOutputStream fout;
		try {
			fout = new FileOutputStream(filename);
			fout.write(msg);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		File f = new File(filename);
		System.out.println("File name: " + filename + "  Size: " + (float)f.length()/1000000 + "MB");
		System.exit(0);
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {

	}
	
}