package com.mycompany.florists.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 * Responsible for operating the shop.
 *
 * @author Sławomir Kowalczyk
 */
public class Shop implements Serializable {

    /**
     * The shop price list.
     */
    private PriceList priceList;
    /**
     * The shop name.
     */
    private String name;

    /**
     * Creates a shop with the given name and download in the price list with
     * the database.
     *
     * @param name The shop name.
     */
    public Shop(String name) {
        this.name = name;
        priceList = PriceList.getInstance();

        Connection conn = null;
        Statement stm = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + "priceList.db");

            stm = conn.createStatement();

            String command = "SELECT * FROM PRICES";
            ResultSet set = stm.executeQuery(command);

            while (set.next()) {
                String opis = set.getString("name");
                double cena = set.getDouble("price");
                PriceList.getInstance().setPrice(opis, cena);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @return Returns the shop name.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return Returns a map containing the price list. The key is the name of
     * the commodity, and the value is its price.
     */
    public PriceList getPriceList() {
        return priceList;
    }

    /**
     * The method issues a proof of purchase for a customer. Bussines customer -
     * invoice, and individual customer - receipt.
     *
     * @param customer The shop customer.
     * @return Returns a receipt for an individual customer, an invoice for a
     * bussiness customer, for an undefined client, returns null.
     */
    public ProofOfPurchase toCashBox(Customer customer) {
        if (customer instanceof IndividualCustomer) {
            Receipt receipt = new Receipt(this);
            List<Flower> auxiliaryList = customer.getList();
            for (int i = 0; i < auxiliaryList.size(); i++) {
                receipt.addArticle(auxiliaryList.get(i));
            }
            return receipt;
        } else if (customer instanceof BussinesCustomer) {
            BussinesCustomer bussines = (BussinesCustomer) customer;
            Invoice invoice = new Invoice(this, bussines.getCompanyName(), bussines.getStreet(), bussines.getHouseNumber(),
                    bussines.getFlatNumber(), bussines.getTown(), bussines.getPostCode(),
                    bussines.getPost(), bussines.getNipNumber());
            List<Flower> auxiliaryList = customer.getList();
            for (int i = 0; i < auxiliaryList.size(); i++) {
                invoice.addArticle(auxiliaryList.get(i));
            }
            return invoice;
        }
        return null;
    }
}
