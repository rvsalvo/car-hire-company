/**
 * 
 */
package com.trustpay.carhire.model;


import java.io.Serializable;


/**
 * @author Rodrigo Salvo
 *
 */
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String email;


    public Customer() {

    }


    public Customer( String email, String name ) {

        super();
        this.email = email;
        this.name = name;

    }


    /**
     * @return the name
     */
    public String getName() {

        return name;
    }


    /**
     * @param name
     *            the name to set
     */
    public void setName( String name ) {

        this.name = name;
    }


    /**
     * @return the email
     */
    public String getEmail() {

        return email;
    }


    /**
     * @param email
     *            the email to set
     */
    public void setEmail( String email ) {

        this.email = email;
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
        result = prime * result + ( ( email == null ) ? 0 : email.hashCode() );
        result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
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
        Customer other = (Customer) obj;
        if ( email == null ) {
            if ( other.email != null )
                return false;
        } else if ( !email.equals( other.email ) )
            return false;
        if ( name == null ) {
            if ( other.name != null )
                return false;
        } else if ( !name.equals( other.name ) )
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

        return "Customer [name=" + name + ", email=" + email + "]";
    }

}
