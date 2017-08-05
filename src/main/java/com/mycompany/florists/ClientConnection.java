package com.mycompany.florists;

import com.mycompany.florists.model.ProofOfPurchase;
import com.mycompany.florists.model.Shop;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import com.mycompany.florists.model.Customer;

/**
 * The class is responsible for operating connections with a customer.
 *
 * @author Sławomir Kowalczyk
 */
public class ClientConnection extends Thread {

    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    /**
     * The instance of the operated shop.
     */
    private Shop sklep;

    /**
     * Creates a connection with the customer.
     *
     * @param socket A socket through which the connection is made.
     * @param sklep The instance of the operated shop.
     */
    public ClientConnection(Socket socket, Shop sklep) {
        try {
            this.socket = socket;
            this.sklep = sklep;

            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());

            System.out.println("Polaczono z klientem");
        } catch (IOException ex) {
            System.out.println("Blad utworzenia polaczenia z klientem");
        }
    }

    /**
     * The method runs a client connection. Sends the price list and proof of
     * purchase to the shop.
     */
    @Override
    public void run() {
        boolean isRunning = true;
        try {
            while (isRunning) {
                String message = (String) input.readObject();
                if (message.equals("PRICE_LIST")) {
                    output.writeObject(sklep.getPriceList());
                } else if (message.equals("TO_CASH_BOX")) {
                    Customer c = (Customer) input.readObject();
                    ProofOfPurchase proof = sklep.toCashBox(c);
                    output.writeObject(proof);
                } else if (message.equals("EXIT")) {
                    isRunning = false;
                }
            }
        } catch (Exception ex) {
        } finally {
            System.out.println("Client - disconnect");
        }
    }

    /**
     * Closes data streams and connections with the socket.
     */
    public void close() {
        try {
            socket.close();
            input.close();
            output.close();
        } catch (IOException ex) {
        }
    }
}
