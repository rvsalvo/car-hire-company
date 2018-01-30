/**
 * 
 */
package com.trustpay.carhire.web.controller;


import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Rodrigo Salvo
 *
 */
@RestController
public class AuthenticationController {

    @RequestMapping( "/user" )
    public Principal user( Principal user ) {

        return user;
    }

}
