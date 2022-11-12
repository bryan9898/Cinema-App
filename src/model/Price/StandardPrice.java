package model.Price;

import data.SystemSettingsDAO;
import data.impl.SystemSettingsDaoImpl;

/**
 * Represents the price of standard tickets registered in the system.
 * Makes a new StandardPrice class.
 * @version 1.0
 * @since 06 Nov 2022
 */
public class StandardPrice extends Price {

	/**
	 * Default constructor for the StandardPrice class.
	 */
    public StandardPrice() {
        super();
    }

    /**
     * Gets the price of the standard ticket price.
     * @return price of standard tickets.
     */
    public double getTicketPrice() {
        SystemSettingsDAO systemSettingsDAO = new SystemSettingsDaoImpl();
        return systemSettingsDAO.getTicketPrice();
    }

    /**
     * Updates the price of standard tickets.
     * @param price New price to be updated.
     * @return True if the update is successful and False if otherwise.
     */
    public boolean updateTicketPrice(String price) {
        this.ticketPrice = Double.parseDouble(price);
        SystemSettingsDAO systemSettingsDAO = new SystemSettingsDaoImpl();
        return systemSettingsDAO.updateTicketPrice(price);
    }



}
