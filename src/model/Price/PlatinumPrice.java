package model.Price;

import data.SystemSettingsDAO;
import data.impl.SystemSettingsDaoImpl;

/**
 * Represents a movie registered in the system.
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
     * Gets the price of the ticket.
     */
    public double getTicketPrice() {
        SystemSettingsDAO systemSettingsDAO = new SystemSettingsDaoImpl();
        return systemSettingsDAO.getPlatinumPrice();
    }

    @Override
    /**
     * Updates the price of the ticket.
     */
    public boolean updateTicketPrice(String price) {
        this.ticketPrice = Double.parseDouble(price);
        SystemSettingsDAO systemSettingsDAO = new SystemSettingsDaoImpl();
        return systemSettingsDAO.updatePlatinumPrice(price);
    }




}
