package com.example.arduino4;

import android.os.StrictMode;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by urano on 2015-10-01.
 */
public class Connection {

    private  int ServerPort;
    private  String ServerIP;

    private Socket socket;
    private BufferedReader networkReader;
    private BufferedWriter networkWriter;
    PrintWriter out;

    private float[] angleInt;

    Connection(String IP,int Port)
    {
        ServerIP = IP;
        ServerPort = Port;
        angleInt = new float[2];
    }

    public void Connect(){

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            socket = new Socket(ServerIP, ServerPort);

            networkWriter =

                    new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            networkReader =

                    new BufferedReader(new InputStreamReader(socket.getInputStream()));


            Log.w("Socket", "Socket Started");

            out = new PrintWriter(networkWriter, true);


        } catch (IOException e) {


            System.out.println(e);

            e.printStackTrace();

        }

    }

    public void Send(String a) {

        try {

            out.println(a);
            //out.println(angleInt[0]+","+angleInt[1]);



        } catch (Exception e) {

        }
    }

    public void Stop(){
        try {
            out.close();
            socket.close();

        } catch (IOException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }
    }




}
