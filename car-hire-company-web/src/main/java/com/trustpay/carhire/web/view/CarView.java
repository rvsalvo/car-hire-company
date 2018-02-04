/**
 * 
 */
package com.trustpay.carhire.web.view;


/**
 * @author Rodrigo Salvo
 *
 */
public class CarView {

    private final String description;


    public CarView( String description ) {

        super();
        this.description = description;
    }


    /**
     * @return the carText
     */
    public String getCarDescription() {

        return description;
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return description;
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
        result = prime * result + ( ( description == null ) ? 0 : description.hashCode() );
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
        CarView other = (CarView) obj;
        if ( description == null ) {
            if ( other.description != null ) {
                return false;
            }
        } else if ( !description.equals( other.description ) ) {
            return false;
        }
        return true;
    }

}
