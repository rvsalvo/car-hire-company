/**
 * 
 */
package com.trustpay.carhire.service.test;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.trustpay.carhire.model.Book;
import com.trustpay.carhire.model.Customer;
import com.trustpay.carhire.model.Vehicle;
import com.trustpay.carhire.model.VehicleType;
import com.trustpay.carhire.service.VehicleService;


/**
 * @author Rodrigo Salvo
 *
 */
@ExtendWith( SpringExtension.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
public class VehicleServiceTest {

    @Autowired
    private VehicleService vehicleService;


    @Test
    public void save() {

        saveVehicle();

    }


    @Test
    public void listAllAvailable() {

        saveVehicle();

        Collection< Vehicle > vehicles = vehicleService.listAllAvailable();

        assertFalse( vehicles.isEmpty() );

    }


    @Test
    public void listAllBooked() {

        bookVehicle();

        Collection< Vehicle > vehicles = vehicleService.listAllBooked();

        assertFalse( vehicles.isEmpty() );

    }


    @Test
    public void book() {

        Book booked = bookVehicle();

        assertNotNull( booked );

        assertNotNull( booked.getBookedWhen() );

    }


    private Vehicle saveVehicle() {

        Vehicle vehicle = createVehicle();

        vehicleService.save( vehicle );

        return vehicle;
    }


    private Vehicle createVehicle() {

        return new Vehicle( UUID.randomUUID().toString(), VehicleType.CAR );
    }


    private Book bookVehicle() {

        Vehicle vehicle = saveVehicle();

        Customer customer = mock( Customer.class );
        when( customer.getEmail() ).thenReturn( UUID.randomUUID().toString() );

        Book booked = vehicleService.book( vehicle, customer );
        return booked;
    }

}
