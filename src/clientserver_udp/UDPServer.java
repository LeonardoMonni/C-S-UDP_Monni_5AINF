/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientserver_udp;

import java.io.*;
import java.net.*;

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
        byte[] bufferIN = new byte[256]; //buffer spedizione
        byte[] bufferOUT = new byte[256]; //buffer ricezione
        boolean attivo = true; //per ripetizione del servizio

        try {
            dSocket = new DatagramSocket(porta);
            System.out.println("Apertura porta in corso");
            System.out.println("Server in ascolto sulla porta: " + porta);
            
            while (attivo){
                //definizione del datagramma
                inPacket = new DatagramPacket(bufferIN, bufferIN.length);
                //attesa della ricezione dato dal client 
                dSocket.receive(inPacket);
                //analisi del pacchetto ricevuto
                String ricevuto = new String(inPacket.getData());
                int numCaratteri = inPacket.getLength();
                System.out.println("Ricevuto datagramma di lunghezza: "+ numCaratteri);
                // per eliminare i caratteri in eccesso
                ricevuto = ricevuto.substring(0, numCaratteri);
                System.out.println("Contenuto: " + ricevuto);
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
                if (ricevuto.equals("fine")) {
                    System.out.println("Server in chiusura, grazie dell'utilizzo!");
                    attivo = false;
                }
            }
            dSocket.close();

        } catch (UnknownHostException ex) {
            System.err.println(ex.getMessage());

        } catch (SocketException ex) {
            System.err.println(ex.getMessage());
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

    }

}
