# MQTT-File-Share-client
You can send any file up to 250MB to anyone with internet access. You can use your private server if you want.
You can send files to multiple clients.

This is console client coded in Java with Eclipse Paho library. You can use it on windows, or linux with or without graphical interface.
This program sends files from one client to another via MQTT broker. MQTT file limitation depends on MQTT broker server you use, but max is 250MB.
Speed is limited by your internet service provider or broker internet service provider.

I did some testing: I have internet download and upload 100Mb/s (12.5MB/s). 
My server in another city have 100Mb/s (12.5MB/s) upload and download too.
The file I was sending was about 200MB. Upload took almost 18s with establishing comunication. So the upload speed was approximately 11MB/s (88Mb/s).

# Step by step tutorial how to use it:
1. clone or download Client_app folder
2. if you don't have Java installed, download it: https://java.com/en/download/ and install.
3. if you are using windows, just open file MQTT_File_Share_client-Windows-run.bat. If linux, than MQTT_File_Share_client-Linux-run.sh.
4. enter MQTT broker server IP. You can use mosquitto test server (test.mosquitto.org:1883), but there is size limit a few KB.
You can use any other free MQTT server, or you can install mosquito MQTT broker on your server within 5 minutes and use it.

receiving file: 

5. enter character "R" for receiving
6. enter path to file you want to save it. If you want to save the file for example to file cat.png in the same folder as this program, 
you can type "cat.png" and hit enter. If you want to save cat.png in other folder, you have to enter full path to this file.
For example I want to save cat.png in folder C:\photos\cats so I have to enter "C:\photos\cats\cat.png".
7. enter topic on which you will be waiting on other client to send file.
8. when sender will send a file to the same topic, it will save the file and close this app.

Sending file: 

5. enter character "S" for sending
6. enter path to file you want to send. If you have for example file cat.png in the same folder as this program, 
you can type "cat.png" and hit enter. If you have file cat.png in other folder, you have to enter full path to this file.
For example I have cat.png in folder C:\photos\cats If I want to send this file, I have to enter "C:\photos\cats\cat.png".
7. enter topic on which the file will be send.
8. file will be send to the server and if the file isn't bigger than maximum broker size limit, the server will send the file to
clients who waiting on the same topic.

#issues:
1. File was send from client to server, but not received by client. Reason: You are not on the same server and topic,
or you sent bigger file than the server can hadle. Try sending .txt file with one line of text. If you will receive this small file, but not a larger one,
the server settings limits the maximal size. I recommand you to use your own server.

2. Can't open received file. Reason: Sender sent for example video.mp4, but you save it as video.avi. You can save video.mp4 as something.mp4, but not as something.mkv for example.
