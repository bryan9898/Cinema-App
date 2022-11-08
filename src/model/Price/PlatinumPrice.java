package model.Price;

import data.SystemSettingsDAO;
import data.impl.SystemSettingsDaoImpl;

public class PlatinumPrice extends Price {

    public PlatinumPrice() {
        super();
    }

    public double getTicketPrice() {
        SystemSettingsDAO systemSettingsDAO = new SystemSettingsDaoImpl();
        return systemSettingsDAO.getPlatinumPrice();
    }

    @Override
    public boolean updateTicketPrice(String price) {
        this.ticketPrice = Double.parseDouble(price);
        SystemSettingsDAO systemSettingsDAO = new SystemSettingsDaoImpl();
        return systemSettingsDAO.updatePlatinumPrice(price);
    }




}
