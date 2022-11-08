package model;

import data.SystemSettingsDAO;
import data.impl.SystemSettingsDaoImpl;

import java.util.Calendar;

public class SystemSettings {

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


    public void printPublicHolidays() {
        SystemSettingsDAO systemSettingsDAO = new SystemSettingsDaoImpl();
        systemSettingsDAO.printPublicHolidays();
    }

    public Boolean checkHoliday(String date) {
        SystemSettingsDAO systemSettingsDAO = new SystemSettingsDaoImpl();
        return systemSettingsDAO.checkHoliday(date);
    }



}
