package model.Price;

/**
 * Represents the price of tickets registered in the system.
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
     * Default constructor for the Price class
     */
    Price(){
    	
    }
    
    /**
     * Gets the price of the ticket.
     * @return Price of the ticket.
     */
    public double getTicketPrice() {
        return 0;
    }

    /**
     * Gets the surcharge for public holiday.
     * @return surcharge for public holiday.
     */
    public String getPublicHolidaySurcharge() {
        return PublicHolidaySurcharge;
    }

    /**
     * Gets the surcharge for weekends.
     * @return surcahrge for weekends.
     */
    public String getWeekendSurcharge() {
        return WeekendSurcharge;
    }

    /**
     * Gets the surcharge for a 3D movie.
     * @return surcharfe for a 3D movie.
     */
    public String getSurcharge3D() {return Surcharge3D;}

    /**
     * Gets discount for senior price.
     * @return discount for senior price.
     */
    public String getSeniorPriceDiscount() {return SeniorPriceDiscount;}

    /**
     * Gets discount for child price.
     * @return discount for child price.
     */
    public String getChildPriceDiscount() {return ChildPriceDiscount;}

    /**
     * Gets discount for student price.
     * @return discount for student price.
     */
    public String getStudentPriceDiscount() {return StudentPriceDiscount;}

    /**
     * Gets the surcharge after 6pm.
     * @return surcharge after 6pm.
     */
    public String getAfter6pmSurcharge() {
        return After6pmSurcharge;
    }

}
