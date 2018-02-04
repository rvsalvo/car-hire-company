/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2017
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.trustpay.carhire.web.test.configuration;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


/**
 * For scanning the packages for Spring bean creations. So we don't need a .XML file.
 * 
 * @author Rodrigo Salvo
 *
 */
@EnableAutoConfiguration
@ComponentScan( "com.trustpay.carhire" )
public class CarHireCompanyContextConfiguration {

}
