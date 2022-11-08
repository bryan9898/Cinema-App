package model.Price;

public abstract class Price {

    protected String WeekendSurcharge = "4.0";
    protected String StudentPriceDiscount = "3.0";
    protected String SeniorPriceDiscount = "2.0";
    protected String ChildPriceDiscount = "4.0";
    protected String PublicHolidaySurcharge = "5.0";
    protected String Surcharge3D = "2.0";

    protected String After6pmSurcharge = "2.0";

    protected double ticketPrice;

    public double getTicketPrice() {
        return 0;
    }

    public boolean updateTicketPrice(String price){
        return false;
    };

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
