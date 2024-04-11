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
public class UDPServer {
    
    public static void main(String[] args) {
       int porta = 6789;
       DatagramSocket dSocket;
       DatagramPacket inPacket;
       DatagramPacket outPacket;
       byte[] bufferIN = new byte[1024]; //buffer spedizione
       byte[] bufferOUT = new byte[1024]; //buffer ricezione
       boolean attivo = true; //per ripetizione del servizio
       
       
       try{
           dSocket = new DatagramSocket(porta);
           System.out.println("Apertura porta in corso");
           
           while(attivo){
               System.out.println("Server in ascolto sulla porta: " + porta);
               //definizione del datagramma
               inPacket = new DatagramPacket(bufferIN, bufferIN.length);
               //attesa della ricezione dato dal client 
               dSocket.receive(inPacket);
               //analisi del pacchetto ricevuto
               String ricevuto = new String(inPacket.getData());
               int numCaratteri = inPacket.getLength();
               // per eliminare i caratteri in eccesso
               ricevuto = ricevuto.substring(0,numCaratteri);
               System.out.println("Ricevuto: " + ricevuto);
               //riconoscimento dei parametri del socket del client 
               InetAddress clientAddress = inPacket.getAddress();
               int clientPort = inPacket.getPort();
               //preparazione del dato da spedire
               String daSpedire = ricevuto.toUpperCase();
               bufferOUT = daSpedire.getBytes();
               //invio del datagramma
               outPacket = new DatagramPacket(bufferOUT, bufferOUT.length, clientAddress, clientPort);
               dSocket.send(outPacket);
               //controllo termine esecuzione del server
               if(ricevuto.equals("fine")){
                   System.out.println("Server in chiusura, grazie dell'utilizzo!");
                   attivo=false;
               }
           }
           dSocket.close();
           
       } catch (UnknownHostException ex) {
           System.err.println(ex.getMessage());
           
       } catch (IOException ex) {
           System.err.println(ex.getMessage());   
       }
       
    }
    
}
