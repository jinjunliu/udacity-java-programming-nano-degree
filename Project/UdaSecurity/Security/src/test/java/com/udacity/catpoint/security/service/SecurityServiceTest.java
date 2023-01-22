package com.udacity.catpoint.security.service;

import com.udacity.catpoint.image.service.ImageService;
import com.udacity.catpoint.security.data.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SecurityServiceTest {
    // create a mock image service object before each test
    // reference: https://www.baeldung.com/junit-before-beforeclass-beforeeach-beforeall
    // reference: https://www.digitalocean.com/community/tutorials/mockito-mock-examples

    @Mock
    static ImageService imageService;
    @Mock
    static SecurityRepository securityRepository;
    @Mock
    static Sensor sensor;

    static SecurityService securityService;

    @BeforeEach
    public void setUp() {
        // create security service object
        MockitoAnnotations.initMocks(this);
        securityService = new SecurityService(securityRepository, imageService);
    }

    @AfterAll
    public static void tearDown() {
        imageService = null;
        securityRepository = null;
        sensor = null;
        securityService = null;
    }

    // 1. If alarm is armed and a sensor becomes activated, put the system into pending alarm status.
    @ParameterizedTest
    // Reference: https://www.baeldung.com/parameterized-tests-junit-5
    @MethodSource("provideTest1and2Arguments")
    public void test1(ArmingStatus armingStatus, SensorType sensorType) {
        securityService.setArmingStatus(armingStatus);
        sensor.setSensorType(sensorType);
        sensor.setActive(false);
        // reference: https://stackoverflow.com/questions/45222786/mockito-when-thenreturn
        when(securityRepository.getAlarmStatus()).thenReturn(AlarmStatus.NO_ALARM);
        securityService.changeSensorActivationStatus(sensor, true);
        // reference: https://www.digitalocean.com/community/tutorials/mockito-verify
        verify(securityRepository).setAlarmStatus(AlarmStatus.PENDING_ALARM);
    }

    private static Object[][] provideTest1and2Arguments() {
        return new Object[][] {
                {ArmingStatus.ARMED_AWAY, SensorType.MOTION},
                {ArmingStatus.ARMED_HOME, SensorType.MOTION},
                {ArmingStatus.ARMED_AWAY, SensorType.DOOR},
                {ArmingStatus.ARMED_HOME, SensorType.DOOR},
                {ArmingStatus.ARMED_AWAY, SensorType.WINDOW},
                {ArmingStatus.ARMED_HOME, SensorType.WINDOW}
        };
    }

    // 2. If alarm is armed and a sensor becomes activated and the system is already pending alarm, set the alarm status to alarm.
    @ParameterizedTest
    @MethodSource("provideTest1and2Arguments")
    public void test2(ArmingStatus armingStatus, SensorType sensorType) {
        securityService.setArmingStatus(armingStatus);
        sensor.setSensorType(sensorType);
        sensor.setActive(false);
        when(securityRepository.getAlarmStatus()).thenReturn(AlarmStatus.PENDING_ALARM);
        securityService.changeSensorActivationStatus(sensor, true);
        verify(securityRepository).setAlarmStatus(AlarmStatus.ALARM);
    }

    // 3. If pending alarm and all sensors are inactive, return to no alarm state.
    @ParameterizedTest
    @EnumSource(value = SensorType.class, names = {"MOTION", "DOOR", "WINDOW"})
    public void test3(SensorType sensorType) {
        when(securityRepository.getAlarmStatus()).thenReturn(AlarmStatus.PENDING_ALARM);
        sensor.setSensorType(sensorType);
        when(securityRepository.getSensors()).thenReturn(Set.of(sensor));
        sensor.setActive(false);
        securityService.changeSensorActivationStatus(sensor, false);
        verify(securityRepository).setAlarmStatus(AlarmStatus.NO_ALARM);
    }

    // If alarm is active, change in sensor state should not affect the alarm state.


}
