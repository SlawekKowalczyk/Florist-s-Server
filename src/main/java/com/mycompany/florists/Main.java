package com.mycompany.florists;

import java.util.Scanner;

/**
 * @author Sławomir Kowalczyk
 */
public class Main {

    public static void main(String[] args) {
        Server server = new Server(50000);
        server.start();
        Scanner scan = new Scanner(System.in);
        while (true) {
            String command = scan.next();
            if (command.equals("EXIT")) {
                server.close();
                break;
            }
        }
    }

}
