package data;

public interface SystemSettingsDAO {

    public boolean addHoliday(String date);
    public boolean removeHoliday(String date);
    public boolean updateTicketPrice(String price);
    public boolean updatePlatinumPrice(String price);
    public void printPublicHolidays();
    public double getTicketPrice();
    public Boolean checkHoliday(String date);
    public double getPlatinumPrice();

}
