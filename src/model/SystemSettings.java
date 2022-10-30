package model;

import data.SystemSettingsDAO;

import java.util.Calendar;

public class SystemSettings {
    private String ticketPrice;
    private String WeekendSurcharge = "4.0";
    private String StudentPriceDiscount = "3.0";
    private String SeniorPriceDiscount = "2.0";
    private String ChildPriceDiscount = "4.0";
    private String PublicHolidaySurcharge = "5.0";
    private String Surcharge3D = "2.0";
    private String platinumPrice;

    String Holidays;

    public SystemSettings() {
        super();
    }

    public boolean addHoliday(String date) {
        SystemSettingsDAO systemSettingsDAO = new SystemSettingsDAO();
        return systemSettingsDAO.addHoliday(date);
    }

    public boolean removeHoliday(String date) {
        SystemSettingsDAO systemSettingsDAO = new SystemSettingsDAO();
        return systemSettingsDAO.removeHoliday(date);
    }

    public boolean updateTicketPrice(String price) {
        SystemSettingsDAO systemSettingsDAO = new SystemSettingsDAO();
        return systemSettingsDAO.updateTicketPrice(price);
    }

    public boolean updatePlatinumPrice(String price) {
        SystemSettingsDAO systemSettingsDAO = new SystemSettingsDAO();
        return systemSettingsDAO.updatePlatinumPrice(price);
    }


    public void printPublicHolidays() {
        SystemSettingsDAO systemSettingsDAO = new SystemSettingsDAO();
        systemSettingsDAO.printPublicHolidays();
    }

    public double getTicketPrice() {
        SystemSettingsDAO systemSettingsDAO = new SystemSettingsDAO();
        return systemSettingsDAO.getTicketPrice();
    }

    public Boolean checkHoliday(String date) {
        SystemSettingsDAO systemSettingsDAO = new SystemSettingsDAO();
        return systemSettingsDAO.checkHoliday(date);
    }

    public double getPlatinumPrice() {
        SystemSettingsDAO systemSettingsDAO = new SystemSettingsDAO();
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
}
