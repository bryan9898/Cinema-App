package model.Movies;

import data.BookingDAO;
import model.Account;
import model.Cinema;
import model.SystemSettings;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Bookings {

    public Bookings() {
    }

    public boolean bookSeats(Account a, TimeSlots timeSlots, int seats, Integer[][] allSeats, int child, int senior, int student){
        SystemSettings systemSettings = new SystemSettings();
        Cinema cinema = new Cinema();
        int type = cinema.getType(timeSlots.getCineplex(),timeSlots.getCinemaNum());
        double price = 0.0;
        if(type == 3){ //Platinum price
            price = systemSettings.getPlatinumPrice();
        } else {
            price = systemSettings.getTicketPrice();
        }
        Boolean checkHoliday = systemSettings.checkHoliday(timeSlots.getDate());
        if(checkHoliday){
            System.out.println(timeSlots.getDate()+" is a Public Holiday, surcharge of $"+systemSettings.getPublicHolidaySurcharge()+" will be added to the ticket price");
            price += Double.parseDouble(systemSettings.getPublicHolidaySurcharge());
        } else{
            Calendar c = Calendar.getInstance();
            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
            try{
                Date dt1 = format1.parse(timeSlots.getDate());
                DateFormat format2 = new SimpleDateFormat("EEEE");
                String finalDay = format2.format(dt1);
                if (finalDay.equals("Saturday") || finalDay.equals("Sunday")) {
                    System.out.println(timeSlots.getDate()+" is a weekend, surcharge of $"+systemSettings.getWeekendSurcharge()+" will be added to the ticket price");
                    price += Double.parseDouble(systemSettings.getWeekendSurcharge());
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        Movies mv = new Movies();
        if(mv.check3D(timeSlots.getMovieName())){
            System.out.println(timeSlots.getMovieName()+" is a 3D movie, surcharge of $"+systemSettings.getSurcharge3D()+" will be added to the ticket price");
            price += Double.parseDouble(systemSettings.getSurcharge3D());
        }

        double childPrice = Double.parseDouble(systemSettings.getChildPriceDiscount()) * child;
        double seniorPrice = Double.parseDouble(systemSettings.getSeniorPriceDiscount()) * senior;
        double studentPrice = Double.parseDouble(systemSettings.getStudentPriceDiscount()) * student;

        double totalPrice = (price * seats)  - childPrice - seniorPrice - studentPrice;

        System.out.printf("\n");
        System.out.printf("\n");
        System.out.printf("\t------------------------------------\n"); // \tab
        System.out.printf("\t");
        System.out.printf("\u001B[47m" + "\u001B[30m");
        System.out.printf("           Price Summary            ");
        System.out.printf("\u001B[0m");
        System.out.printf("\n\t------------------------------------");
        System.out.printf("\n");
        UUID uuid= UUID.randomUUID();
        String id = uuid.toString();
        System.out.printf("\u001B[36m"); System.out.printf("\tBooking ID: %s \n",id); System.out.printf("\u001B[0m");
        for(int i = 0; i < seats; i++){
            System.out.printf("\u001B[36m");
            System.out.println("\t Seat "+(i+1)+": $"+price + " (Row "+allSeats[i][0]+", Column "+allSeats[i][1]+")");
            if(child > 0){
                System.out.println("\t "+ child + " Child Discount: -$"+childPrice);
                child--;
            } else
            if(senior > 0){
                System.out.println("\t "+ senior + " Senior Discount: -$"+seniorPrice);
                senior--;
            } else
            if(student > 0){
                System.out.println("\t "+ student + " student Discount: -$"+studentPrice);
                student--;
            }
            System.out.printf("\u001B[0m");
        }
        System.out.printf("\u001B[36m");
        System.out.println("\t Total Price: $" + totalPrice);
        System.out.printf("\u001B[0m");

        System.out.printf("\n");

        BookingDAO bookingDAO = new BookingDAO();
        bookingDAO.bookSeats(id,a,timeSlots,allSeats,totalPrice);


        return false;
    }

}
