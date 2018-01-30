/**
 * 
 */
package com.trustpay.carhire.service;


import java.util.Collection;

import com.trustpay.carhire.model.Book;
import com.trustpay.carhire.model.Customer;
import com.trustpay.carhire.model.Vehicle;


/**
 * @author Rodrigo Salvo
 *
 */
public interface VehicleService {

    void save( Vehicle vehicle );


    Collection< Vehicle > listAllAvailable();


    Collection< Vehicle > listAllBooked();


    Book book( Vehicle vehicle, Customer customer );
}
