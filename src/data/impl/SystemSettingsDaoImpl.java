package data.impl;

import data.SystemSettingsDAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the system settings data access object.
 * The system settings data access object is used to access the system settings data.
 * @version 1.0
 * @since 06 Nov 2022
 */
public class SystemSettingsDaoImpl implements SystemSettingsDAO {

    /**
     * The system settings file path.
     */
    private static final String Account_File="SystemSettings.txt";
    /**
     * The File object to access the system settings file.
     */
    private static File dataFile;

    /**
     * The constructor to make a system settings data access object.
     */
    public SystemSettingsDaoImpl() {
        Path dPath = FileSystems.getDefault().getPath("Resources/Data/",Account_File);
        dataFile = new File(dPath.toString());
    }


    /**
     * This method is to add new holidays into the system settings file.
     * @param date The holiday date to add (Format: dd/mm/yyyy)
     * @return true if the holiday is added else false
     */
    public boolean addHoliday(String date) {
        boolean existing=false;
        ArrayList<String> holidays=getAllHolidays();
        for (String a:holidays) {
            if (a.equals(date)) {
                existing=true;
                break;
            }
        }
        if (!existing) {
            holidays.add(date);
            synToFile(holidays);
        }
        return !existing;
    }

    /**
     * This method is to save the holidays object into the system settings file.
     * @param holidays The holidays object to save
     */
    private void synToFile(ArrayList<String> holidays) {
        if(holidays.size()==0) {
            dataFile.delete();
        }else {
            try {
                ArrayList<String> prices=getAllPrices();
                FileWriter fw = new FileWriter(dataFile);
                for (String a:holidays) {
                    fw.write(a+";");
                }
                fw.append("\r\n" + prices.get(0) +";");
                fw.append("\r\n" + prices.get(1) +";");

                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method is to get all prices of tickets from the system settings file.
     * @return an array list of prices
     */
    private ArrayList<String> getAllPrices() {
        Scanner in;
        String record = null;
        String record2 = null;
        String[] fields;
        ArrayList<String> prices = new ArrayList<String>();
        try {
            in = new Scanner(dataFile);
            int counter = 0;
            while (in.hasNextLine()) {
                if (counter == 0) {
                    counter+=1;
                    record = in.nextLine();
                } else {
                    record = in.nextLine();
                    fields = record.split(";");
                    String price = fields[0];
                    prices.add(price);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return prices;
    }

    /**
     * This method is to get all holidays from the system settings file.
     * @return an array list of holidays
     */
    private ArrayList<String> getAllHolidays() {
        Scanner in;
        String record = null;
        String[] fields;
        ArrayList<String> holidays = new ArrayList<String>();
        try {
            in = new Scanner(dataFile);
            record=in.nextLine();
            fields=record.split(";");
            for (String a:fields) {
                holidays.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return holidays;
    }

    /**
     * This method is to remove holidays from the system settings file.
     * @param date The holiday date to remove (Format: dd/mm/yyyy)
     * @return true if the holiday is removed else false
     */
    public boolean removeHoliday(String date) {
        boolean existing=false;
        ArrayList<String> holidays=getAllHolidays();
        for (String a:holidays) {
            if (a.equals(date)) {
                existing=true;
                holidays.remove(a);
                synToFile(holidays);
                break;
            }
        }
        return existing;
    }


    /**
     * This method is to get update the price of tickets in the system settings file.
     * @param price The price of tickets to update (in double format)
     * @return true if the price is updated else false
     */
    public boolean updateTicketPrice(String price) {
        Scanner sc = null;
        ArrayList<String> prices=getAllPrices();
        try {
            sc = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        StringBuffer buffer = new StringBuffer();
        while (sc.hasNextLine()) {
            buffer.append(sc.nextLine()+System.lineSeparator());
        }
        String fileContents = buffer.toString();
        sc.close();
        //Replacing the old line with new line
        fileContents = fileContents.replaceAll(prices.get(0), price);
        //instantiating the FileWriter class
        FileWriter writer = null;
        try {
            writer = new FileWriter(dataFile);
            writer.append(fileContents);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    /**
     * This method is to update the price of Platinum tickets in the system settings file.
     * @param price The price of a platinum ticket to update (in double format)
     * @return true if the price is updated else false
     */
    public boolean updatePlatinumPrice(String price) {
        Scanner sc = null;
        ArrayList<String> prices=getAllPrices();
        try {
            sc = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        StringBuffer buffer = new StringBuffer();
        while (sc.hasNextLine()) {
            buffer.append(sc.nextLine()+System.lineSeparator());
        }
        String fileContents = buffer.toString();
        sc.close();
        //Replacing the old line with new line
        fileContents = fileContents.replaceAll(prices.get(1), price);
        //instantiating the FileWriter class
        FileWriter writer = null;
        try {
            writer = new FileWriter(dataFile);
            writer.append(fileContents);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    /**
     * This method is to print all holidays from the system settings file.
     */
    public void printPublicHolidays() {
        ArrayList<String> holidays=getAllHolidays();
        System.out.printf("\u001B[47m" + "\u001B[30m");
        System.out.printf("   List of Holidays   ");
        System.out.printf("\u001B[0m");
        System.out.printf("\n");
        for (String a:holidays) {
            System.out.printf("\u001B[33m");
            System.out.println(a);
            System.out.printf("\u001B[0m");
        }
        System.out.printf("\n");
    }

    /**
     * This method is to get the price of tickets from the system settings file.
     * @return the price of tickets (in double format)
     */
    public double getTicketPrice() {
        ArrayList<String> prices=getAllPrices();
        return Double.parseDouble(prices.get(0));
    }

    /**
     * This method is to check if a date is a holiday from the system settings file.
     * @param date The date to check (Format: dd/mm/yyyy)
     * (Note: The date must be in the format dd/mm/yyyy)
     * @return true if the date is a holiday else false
     */
    public Boolean checkHoliday(String date) {
        ArrayList<String> holidays=getAllHolidays();
        for (String a:holidays) {
            if (a.equals(date)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is to get the price of Platinum tickets from the system settings file.
     * @return the price of Platinum ticket (in double format)
     */
    public double getPlatinumPrice() {
        ArrayList<String> prices=getAllPrices();
        return Double.parseDouble(prices.get(1));
    }

}
