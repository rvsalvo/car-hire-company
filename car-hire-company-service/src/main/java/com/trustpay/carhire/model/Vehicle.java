/**
 * 
 */
package com.trustpay.carhire.model;


import java.io.Serializable;


/**
 * @author Rodrigo Salvo
 *
 */
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1L;

    private String plate;

    private VehicleType type;

    private Integer wheels;

    private Integer passengers;

    private boolean booked;


    public Vehicle() {

        super();
    }


    public Vehicle( String plate, VehicleType type ) {

        super();
        this.plate = plate;
        this.type = type;
    }


    public Vehicle( String plate, VehicleType type, Integer wheels, Integer passengers ) {

        super();
        this.plate = plate;
        this.type = type;
        this.wheels = wheels;
        this.passengers = passengers;
    }


    /**
     * @return the type
     */
    public VehicleType getType() {

        return type;
    }


    /**
     * @param type
     *            the type to set
     */
    public void setType( VehicleType type ) {

        this.type = type;
    }


    /**
     * @return the wheels
     */
    public Integer getWheels() {

        return wheels;
    }


    /**
     * @param wheels
     *            the wheels to set
     */
    public void setWheels( Integer wheels ) {

        this.wheels = wheels;
    }


    /**
     * @return the passengers
     */
    public Integer getPassengers() {

        return passengers;
    }


    /**
     * @param passengers
     *            the passengers to set
     */
    public void setPassengers( Integer passengers ) {

        this.passengers = passengers;
    }


    /**
     * @return the plate
     */
    public String getPlate() {

        return plate;
    }


    /**
     * @param plate
     *            the plate to set
     */
    public void setPlate( String plate ) {

        this.plate = plate;
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
        result = prime * result + ( ( passengers == null ) ? 0 : passengers.hashCode() );
        result = prime * result + ( ( plate == null ) ? 0 : plate.hashCode() );
        result = prime * result + ( ( type == null ) ? 0 : type.hashCode() );
        result = prime * result + ( ( wheels == null ) ? 0 : wheels.hashCode() );
        return result;
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj ) {

        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final Vehicle other = (Vehicle) obj;
        if ( passengers == null ) {
            if ( other.passengers != null ) {
                return false;
            }
        } else if ( !passengers.equals( other.passengers ) ) {
            return false;
        }
        if ( plate == null ) {
            if ( other.plate != null ) {
                return false;
            }
        } else if ( !plate.equals( other.plate ) ) {
            return false;
        }
        if ( type != other.type ) {
            return false;
        }
        if ( wheels == null ) {
            if ( other.wheels != null ) {
                return false;
            }
        } else if ( !wheels.equals( other.wheels ) ) {
            return false;
        }
        return true;
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return "Vehicle [plate=" + plate + ", type=" + type + ", wheels=" + wheels + ", passengers=" + passengers + "]";
    }


    /**
     * @return the booked
     */
    public boolean isBooked() {

        return booked;
    }


    /**
     * @param booked
     *            the booked to set
     */
    public void setBooked( boolean booked ) {

        this.booked = booked;
    }

}
