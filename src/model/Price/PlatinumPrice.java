package model.Price;

import data.SystemSettingsDAO;
import data.impl.SystemSettingsDaoImpl;

/**
 * Represents the price of platinum tickets registered in the system.
 * Makes a new PlatinumPrice class.
 * @version 1.0
 * @since 06 Nov 2022
 */
public class PlatinumPrice extends Price {

	/**
	 * Default constructor for the PlatinumPrice class.
	 */
    public PlatinumPrice() {
        super();
    }

    /**
     * Gets the price of the platinum price tickets.
     * @return price of platinum price tickets.
     */
    public double getTicketPrice() {
        SystemSettingsDAO systemSettingsDAO = new SystemSettingsDaoImpl();
        return systemSettingsDAO.getPlatinumPrice();
    }


    /**
     * Updates the price of the ticket.
     * @param price The price of the ticket.
     * @return true if the price is updated else false
     */
    public boolean updateTicketPrice(String price) {
        this.ticketPrice = Double.parseDouble(price);
        SystemSettingsDAO systemSettingsDAO = new SystemSettingsDaoImpl();
        return systemSettingsDAO.updatePlatinumPrice(price);
    }




}
