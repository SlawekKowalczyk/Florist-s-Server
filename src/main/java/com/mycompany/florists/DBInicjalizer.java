package com.mycompany.florists;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * The class is responsible for creating a price list. When launched it deletes
 * existing one and creates a new one.
 *
 * @author Sławomir Kowalczyk
 */
public class DBInicjalizer {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stm = null;
        Map<String, Double> priceList;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + "priceList.db");

            stm = conn.createStatement();

            String command = "DROP TABLE PRICES";
            try {
                stm.executeUpdate(command);
            } catch (Exception ex) {
                
            }

            command = "CREATE TABLE PRICES("
                    + "name TEXT,"
                    + "price REAL"
                    + ");";

            stm.executeUpdate(command);

            priceList = new HashMap<>();

            priceList.put("Gozdzik BIALY", 5.5);
            priceList.put("Gozdzik CZERWONY", 6.5);
            priceList.put("Gozdzik ZIELONY", 5.0);
            priceList.put("Gozdzik NIEBIESKI", 7.5);
            priceList.put("Gozdzik ZOLTY", 6.5);
            priceList.put("Gozdzik FIOLETOWY", 5.5);
            priceList.put("Gozdzik ROZOWY", 9.5);

            priceList.put("Irys BIALY", 4.5);
            priceList.put("Irys CZERWONY", 5.5);
            priceList.put("Irys ZIELONY", 4.0);
            priceList.put("Irys NIEBIESKI", 6.5);
            priceList.put("Irys ZOLTY", 5.5);
            priceList.put("Irys FIOLETOWY", 5.5);
            priceList.put("Irys ROZOWY", 8.5);

            priceList.put("Lilja BIALY", 3.5);
            priceList.put("Lilja CZERWONY", 5.5);
            priceList.put("Lilja ZIELONY", 4.0);
            priceList.put("Lilja NIEBIESKI", 6.5);
            priceList.put("Lilja ZOLTY", 5.5);
            priceList.put("Lilja FIOLETOWY", 5.5);
            priceList.put("Lilja ROZOWY", 8.5);

            priceList.put("Roza BIALY", 3.5);
            priceList.put("Roza CZERWONY", 5.5);
            priceList.put("Roza ZIELONY", 4.0);
            priceList.put("Roza NIEBIESKI", 6.5);
            priceList.put("Roza ZOLTY", 5.5);
            priceList.put("Roza FIOLETOWY", 5.5);
            priceList.put("Roza ROZOWY", 8.5);

            priceList.put("Storczyk BIALY", 3.5);
            priceList.put("Storczyk CZERWONY", 5.5);
            priceList.put("Storczyk ZIELONY", 4.0);
            priceList.put("Storczyk NIEBIESKI", 6.5);
            priceList.put("Storczyk ZOLTY", 5.5);
            priceList.put("Storczyk FIOLETOWY", 5.5);
            priceList.put("Storczyk ROZOWY", 8.5);

            priceList.put("Tulipan BIALY", 3.5);
            priceList.put("Tulipan CZERWONY", 5.5);
            priceList.put("Tulipan ZIELONY", 4.0);
            priceList.put("Tulipan NIEBIESKI", 6.5);
            priceList.put("Tulipan ZOLTY", 5.5);
            priceList.put("Tulipan FIOLETOWY", 5.5);
            priceList.put("Tulipan ROZOWY", 8.5);

            priceList.put("Folia", 1.5);
            priceList.put("Rafia", 2.5);
            priceList.put("Siatka", 1.0);
            priceList.put("Wstazka", 2.5);

            for (Map.Entry<String, Double> entry : priceList.entrySet()) {

                command = "INSERT INTO PRICES VALUES("
                        + "\"" + entry.getKey() + "\","
                        + entry.getValue()
                        + ");";

                stm.executeUpdate(command);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
