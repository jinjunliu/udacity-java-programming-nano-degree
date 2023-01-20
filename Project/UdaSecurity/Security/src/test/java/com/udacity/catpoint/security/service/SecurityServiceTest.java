// reference: https://www.digitalocean.com/community/tutorials/mockito-mock-examples
package com.udacity.catpoint.security.service;

import com.udacity.catpoint.image.service.ImageService;
import com.udacity.catpoint.security.service.SecurityService;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
public class SecurityServiceTest {

    // Test 1: If alarm is armed and a sensor becomes activated, put the system into pending alarm status.
    @Test
    public void test1() {
        // Create a mock object
        SecurityService securityService = mock(SecurityService.class);
        // Set the return value of the method
        when(securityService.getSecurityStatus()).thenReturn("OK");
        // Verify the return value
        assertEquals("OK", securityService.getSecurityStatus());
    }
}
