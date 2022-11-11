package model;

import data.SystemSettingsDAO;
import data.impl.SystemSettingsDaoImpl;

import java.util.Calendar;

/**
 * This class represents the system settings of the cinema booking system.
 * The System settings sets the holiday dates
 * @version 1.0
 * @since 06 Nov 2022
 */
public class SystemSettings {
    /**
     * The holiday date of the system.
     */
    String Holidays;

    /**
     * The constructor to make a system settings object
     */
    public SystemSettings() {
        super();
    }

    /**
     * This method is to add the holiday date into the system.
     * @param  date The holiday date to add
     * @return true if the holiday date is added else false
     */
    public boolean addHoliday(String date) {
        SystemSettingsDAO systemSettingsDAO = new SystemSettingsDaoImpl();
        return systemSettingsDAO.addHoliday(date);
    }

    /**
     * This method is to remove the holiday date from the system.
     * @param date The holiday date to remove
     * @return true if the holiday date is removed else false
     */
    public boolean removeHoliday(String date) {
        SystemSettingsDAO systemSettingsDAO = new SystemSettingsDaoImpl();
        return systemSettingsDAO.removeHoliday(date);
    }

    /**
     * This method is to print the holiday dates from the system.
     */
    public void printPublicHolidays() {
        SystemSettingsDAO systemSettingsDAO = new SystemSettingsDaoImpl();
        systemSettingsDAO.printPublicHolidays();
    }

    /**
     * This method is to check if the date is a holiday.
     * @param date The holiday date to check
     * @return true if the date is a holiday else false
     */
    public Boolean checkHoliday(String date) {
        SystemSettingsDAO systemSettingsDAO = new SystemSettingsDaoImpl();
        return systemSettingsDAO.checkHoliday(date);
    }



}
