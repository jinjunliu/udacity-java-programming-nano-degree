// reference: https://www.digitalocean.com/community/tutorials/mockito-mock-examples
package com.udacity.catpoint.security.service;

import static org.mockito.Mockito.*;
public class SecurityServiceTest {
    public void test1() {
        // Create a mock object
        SecurityService securityService = mock(SecurityService.class);
        // Set the return value of the method
        when(securityService.getSecurityStatus()).thenReturn("OK");
        // Verify the return value
        assertEquals("OK", securityService.getSecurityStatus());
    }
}
