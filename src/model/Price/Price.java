package model.Price;

/**
 * Represents a movie registered in the system.
 * Makes a new Price class with weekend surcharge, student price discount, senior price discount, child price discount, public holiday surcharge, surcharge for 3D movies, surcharge after 6pm and price of the ticket.
 * @version 1.0
 * @since 06 Nov 2022
 */
public abstract class Price {

	/**
	 * Weekend surcharge.
	 */
    protected String WeekendSurcharge = "4.0";
    
    /**
     * Student price discount.
     */
    protected String StudentPriceDiscount = "3.0";
    
    /**
     * Senior price discount.
     */
    protected String SeniorPriceDiscount = "2.0";
    
    /**
     * Child price discount.
     */
    protected String ChildPriceDiscount = "4.0";
    
    /**
     * Public holiday surcharge.
     */
    protected String PublicHolidaySurcharge = "5.0";
    
    /**
     * Surcharge for 3D movie.
     */
    protected String Surcharge3D = "2.0";

    /**
     * Surcharge after 6pm.
     */
    protected String After6pmSurcharge = "2.0";

    /**
     * Price for the ticket.
     */
    protected double ticketPrice;

    /**
     * Gets the price of the ticket.
     * @return Price of the ticket.
     */
    public double getTicketPrice() {
        return 0;
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
