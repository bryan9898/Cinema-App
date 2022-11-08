package model.Price;

import data.SystemSettingsDAO;
import data.impl.SystemSettingsDaoImpl;

public class StandardPrice extends Price {

    public StandardPrice() {
        super();
    }

    public double getTicketPrice() {
        SystemSettingsDAO systemSettingsDAO = new SystemSettingsDaoImpl();
        return systemSettingsDAO.getTicketPrice();
    }
    @Override
    public boolean updateTicketPrice(String price) {
        this.ticketPrice = Double.parseDouble(price);
        SystemSettingsDAO systemSettingsDAO = new SystemSettingsDaoImpl();
        return systemSettingsDAO.updateTicketPrice(price);
    }



}
