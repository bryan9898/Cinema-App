package data;

/**
 * This interface is used to define the methods that are used to access the System Settings Data
 * @version 1.0
 * @since 06 Nov 2022
 */
public interface SystemSettingsDAO {

    /**
     * This method is to add new holidays into the system settings file.
     * @param date The holiday date to add (Format: dd/mm/yyyy)
     * @return true if the holiday is added else false
     */
    public boolean addHoliday(String date);
    /**
     * This method is to remove holidays from the system settings file.
     * @param date The holiday date to remove (Format: dd/mm/yyyy)
     * @return true if the holiday is removed else false
     */
    public boolean removeHoliday(String date);
    /**
     * This method is to get update the price of tickets in the system settings file.
     * @param price The price of tickets to update (in double format)
     * @return true if the price is updated else false
     */
    public boolean updateTicketPrice(String price);
    /**
     * This method is to update the price of Platinum tickets in the system settings file.
     * @param price The price of a platinum ticket to update (in double format)
     * @return true if the price is updated else false
     */
    public boolean updatePlatinumPrice(String price);
    /**
     * This method is to print all holidays from the system settings file.
     */
    public void printPublicHolidays();
    /**
     * This method is to get the price of tickets from the system settings file.
     * @return the price of tickets (in double format)
     */
    public double getTicketPrice();
    /**
     * This method is to check if a date is a holiday from the system settings file.
     * @param date The date to check (Format: dd/mm/yyyy)
     * (Note: The date must be in the format dd/mm/yyyy)
     * @return true if the date is a holiday else false
     */
    public Boolean checkHoliday(String date);
    /**
     * This method is to get the price of Platinum tickets from the system settings file.
     * @return the price of Platinum ticket (in double format)
     */
    public double getPlatinumPrice();

}
