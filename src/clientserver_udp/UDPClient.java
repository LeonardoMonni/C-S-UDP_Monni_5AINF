/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientserver_udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
       int serverPort = 6789;
       InetAddress serverAddress;
       byte[] bufferIN = new byte[1024]; //buffer spedizione
       byte[] bufferOUT = new byte[1024]; //buffer ricezione
       DatagramSocket dSocket;
       DatagramPacket inPacket;
       DatagramPacket outPacket;
       BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
       
       try{
           serverAddress = InetAddress.getLocalHost();
           dSocket = new DatagramSocket();
           System.out.println("Client pronto all'utilizzo - iniserire un dato da inviare:");
           //preparazione al messaggio da spedire
           String daSpedire = input.readLine();
           bufferOUT = daSpedire.getBytes();
           //trasmissione del dato al server
           inPacket = new DatagramPacket(bufferOUT, bufferOUT.length, serverAddress, serverPort);
           dSocket.send(inPacket);
           //ricezione del dato dal server
           outPacket = new DatagramPacket(bufferIN, bufferIN.length);
           dSocket.receive(inPacket);
           String ricevuto = new String(outPacket.getData());
           //elaborazione dei dati ricevuti
           int numCaratteri = outPacket.getLength();
           // per eliminare i caratteri in eccesso
           ricevuto = ricevuto.substring(0,numCaratteri);
           System.out.println("dal Server: " + ricevuto);
           
           //termina elaborazione
           dSocket.close();
           
       } catch (UnknownHostException ex) {
           System.err.println(ex.getMessage());
           
       } catch (IOException ex) {
           System.err.println(ex.getMessage());   
       }
       
    }  
}
