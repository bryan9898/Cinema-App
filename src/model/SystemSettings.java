package model;

import data.SystemSettingsDAO;
import data.impl.SystemSettingsDaoImpl;

import java.util.Calendar;

public class SystemSettings {
    private String ticketPrice;
    private String WeekendSurcharge = "4.0";
    private String StudentPriceDiscount = "3.0";
    private String SeniorPriceDiscount = "2.0";
    private String ChildPriceDiscount = "4.0";
    private String PublicHolidaySurcharge = "5.0";
    private String Surcharge3D = "2.0";

    private String After6pmSurcharge = "2.0";

    private String platinumPrice;

    String Holidays;

    public SystemSettings() {
        super();
    }

    public boolean addHoliday(String date) {
        SystemSettingsDAO systemSettingsDAO = new SystemSettingsDaoImpl();
        return systemSettingsDAO.addHoliday(date);
    }

    public boolean removeHoliday(String date) {
        SystemSettingsDAO systemSettingsDAO = new SystemSettingsDaoImpl();
        return systemSettingsDAO.removeHoliday(date);
    }

    public boolean updateTicketPrice(String price) {
        SystemSettingsDAO systemSettingsDAO = new SystemSettingsDaoImpl();
        return systemSettingsDAO.updateTicketPrice(price);
    }

    public boolean updatePlatinumPrice(String price) {
        SystemSettingsDAO systemSettingsDAO = new SystemSettingsDaoImpl();
        return systemSettingsDAO.updatePlatinumPrice(price);
    }


    public void printPublicHolidays() {
        SystemSettingsDAO systemSettingsDAO = new SystemSettingsDaoImpl();
        systemSettingsDAO.printPublicHolidays();
    }

    public double getTicketPrice() {
        SystemSettingsDAO systemSettingsDAO = new SystemSettingsDaoImpl();
        return systemSettingsDAO.getTicketPrice();
    }

    public Boolean checkHoliday(String date) {
        SystemSettingsDAO systemSettingsDAO = new SystemSettingsDaoImpl();
        return systemSettingsDAO.checkHoliday(date);
    }

    public double getPlatinumPrice() {
        SystemSettingsDAO systemSettingsDAO = new SystemSettingsDaoImpl();
        return systemSettingsDAO.getPlatinumPrice();
    }

    public String getPublicHolidaySurcharge() {
        return PublicHolidaySurcharge;
    }

    public String getWeekendSurcharge() {
        return WeekendSurcharge;
    }

    public String getSurcharge3D() {return Surcharge3D;}

    public String getSeniorPriceDiscount() {return SeniorPriceDiscount;}

    public String getChildPriceDiscount() {return ChildPriceDiscount;}

    public String getStudentPriceDiscount() {return StudentPriceDiscount;}

    public String getAfter6pmSurcharge() {
        return After6pmSurcharge;
    }

}
