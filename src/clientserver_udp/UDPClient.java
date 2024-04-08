/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientserver_udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author Monni Leonardo
 */
public class UDPClient {
    
    public static void main(String[] args) {
       int porta = 1000;
       InetAddress serverAddress;
       DatagramSocket dSocket;
       DatagramPacket inPacket;
       DatagramPacket outPacket;
       byte[] buffer;
       String message = "Richiesta data e ora";
       String response;
       
       try{
           serverAddress = InetAddress.getLocalHost();
           System.out.println("Indirizzo del server trovato");
           dSocket = new DatagramSocket();
           outPacket = new DatagramPacket(message.getBytes(), message.length(), serverAddress, porta);
           dSocket.send(outPacket);
           buffer = new byte[256];
           inPacket = new DatagramPacket(buffer, buffer.length);
           dSocket.receive(inPacket);
           response = new String(inPacket.getData(), 0, inPacket.getLength());
           //
           System.out.println("Connessione stabilita");
           System.out.println("Data e ora del server");
           System.out.println("Connessione chiusa");
           
       } catch (UnknownHostException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();   
       }
       
    }
    
}
