package com.mycompany.florists;

import com.mycompany.florists.model.Shop;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The server for application.
 *
 * @author Sławomir Kowalczyk
 */
public class Server extends Thread {

    private int portNumber;
    private boolean isRunninig;
    private List<ClientConnection> connections;
    private Shop shop;

    /**
     * Creates a server.
     *
     * @param portNumber A port number.
     */
    public Server(int portNumber) {
        this.portNumber = portNumber;
        connections = new ArrayList();
        shop = new Shop("Kwieciarnia");
    }

    /**
     * The method runs the server. It is responsible for maintaining a
     * connection with multiple clients at the same time.
     */
    @Override
    public void run() {
        isRunninig = true;
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            System.out.println("Server is running on port = " + portNumber);

            while (isRunninig) {
                Socket socket = serverSocket.accept();
                if (!isRunninig) {
                    break;
                }

                ClientConnection connection = new ClientConnection(socket, shop);
                connections.add(connection);
                connection.start();
            }
            for (ClientConnection con : connections) {
                con.close();
            }

        } catch (IOException ex) {
            System.out.println("Blad servera");
        }
    }

    /**
     * Closes the server.
     */
    public void close() {
        try {
            isRunninig = false;
            new Socket("localhost", portNumber);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
