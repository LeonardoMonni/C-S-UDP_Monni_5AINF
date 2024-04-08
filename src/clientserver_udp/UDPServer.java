/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientserver_udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

/**
 *
 * @author monni
 */
public class UDPServer {
    
    public static void main(String[] args) {
       int porta = 1000;
       DatagramSocket dSocket;
       DatagramPacket inPacket;
       DatagramPacket outPacket;
       byte[] buffer, bufferOut;
       String messageIn, messageOut;
       Date data;
       
       try{
           dSocket = new DatagramSocket(porta);
           System.out.println("Apertura porta in corso");
           
           while(true){
               System.out.println("Server in ascolto sulla porta " + porta);
               buffer = new byte[256];
               
           }
       } catch (IOException e) {
           e.printStackTrace();   
       }
    }
    
}
