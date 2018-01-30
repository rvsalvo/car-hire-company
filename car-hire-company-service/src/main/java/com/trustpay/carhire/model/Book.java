/**
 * 
 */
package com.trustpay.carhire.model;


import java.io.Serializable;
import java.util.Date;


/**
 * @author Rodrigo Salvo
 *
 */
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    private Vehicle vehicle;

    private Customer customer;

    private Date bookedWhen;


    public Book() {

        super();
    }


    public Book( Vehicle vehicle, Customer customer, Date bookedWhen ) {

        super();
        this.vehicle = vehicle;
        this.customer = customer;
        this.bookedWhen = bookedWhen;
    }


    /**
     * @return the vehicle
     */
    public Vehicle getVehicle() {

        return vehicle;
    }


    /**
     * @param vehicle
     *            the vehicle to set
     */
    public void setVehicle( Vehicle vehicle ) {

        this.vehicle = vehicle;
    }


    /**
     * @return the customer
     */
    public Customer getCustomer() {

        return customer;
    }


    /**
     * @param customer
     *            the customer to set
     */
    public void setCustomer( Customer customer ) {

        this.customer = customer;
    }


    /**
     * @return the bookedWhen
     */
    public Date getBookedWhen() {

        return bookedWhen;
    }


    /**
     * @param bookedWhen
     *            the bookedWhen to set
     */
    public void setBookedWhen( Date bookedWhen ) {

        this.bookedWhen = bookedWhen;
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( bookedWhen == null ) ? 0 : bookedWhen.hashCode() );
        result = prime * result + ( ( customer == null ) ? 0 : customer.hashCode() );
        result = prime * result + ( ( vehicle == null ) ? 0 : vehicle.hashCode() );
        return result;
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj ) {

        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        Book other = (Book) obj;
        if ( bookedWhen == null ) {
            if ( other.bookedWhen != null )
                return false;
        } else if ( !bookedWhen.equals( other.bookedWhen ) )
            return false;
        if ( customer == null ) {
            if ( other.customer != null )
                return false;
        } else if ( !customer.equals( other.customer ) )
            return false;
        if ( vehicle == null ) {
            if ( other.vehicle != null )
                return false;
        } else if ( !vehicle.equals( other.vehicle ) )
            return false;
        return true;
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return "Book [vehicle=" + vehicle + ", customer=" + customer + ", bookedWhen=" + bookedWhen + "]";
    }

}
