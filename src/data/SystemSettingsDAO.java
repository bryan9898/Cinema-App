package data;

import model.Movies.Movies;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class SystemSettingsDAO {

    private static final String Account_File="SystemSettings.txt";
    private static File dataFile;

    public SystemSettingsDAO() {
        Path dPath = FileSystems.getDefault().getPath("Resources/Data/",Account_File);
        dataFile = new File(dPath.toString());
    }


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

    public double getTicketPrice() {
        ArrayList<String> prices=getAllPrices();
        return Double.parseDouble(prices.get(0));
    }

    public Boolean checkHoliday(String date) {
        ArrayList<String> holidays=getAllHolidays();
        for (String a:holidays) {
            if (a.equals(date)) {
                return true;
            }
        }
        return false;
    }

    public double getPlatinumPrice() {
        ArrayList<String> prices=getAllPrices();
        return Double.parseDouble(prices.get(1));
    }
}
