package com.udacity.catpoint.security.service;

import com.udacity.catpoint.image.service.ImageService;
import com.udacity.catpoint.security.data.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SecurityServiceTest {
    private BufferedImage bufferedImage;
    private SecurityService securityService;
    @Mock
    private ImageService imageService;

    @Mock
    private SecurityRepository securityRepository;

    @BeforeEach
    void init() {
        securityService = new SecurityService(securityRepository, imageService);
    }

    // Test 1: If alarm is armed and a sensor becomes activated, put the system into pending alarm status.
    @ParameterizedTest
    @EnumSource(value = ArmingStatus.class, names = {"ARMED_HOME", "ARMED_AWAY"})
    public void pendingWhenAlarmArmedAndSensorActivated(ArmingStatus armingStatus) {
        Sensor sensor = new Sensor("sensor", SensorType.DOOR);
        sensor.setActive(false);
        when(securityRepository.getArmingStatus()).thenReturn(armingStatus);
        when(securityRepository.getAlarmStatus()).thenReturn(AlarmStatus.NO_ALARM);
        securityService.changeSensorActivationStatus(sensor, true);
        verify(securityRepository).setAlarmStatus(AlarmStatus.PENDING_ALARM);
    }

    // Test 2: If alarm is armed and a sensor becomes activated and the system is already pending alarm,
    // set the alarm status to alarm.
    @ParameterizedTest
    @EnumSource(value = ArmingStatus.class, names = {"ARMED_HOME", "ARMED_AWAY"})
    public void alarmWhenAlarmArmedAndSensorActivatedAndStatusPending(ArmingStatus armingStatus) {
        Sensor sensor = new Sensor("sensor", SensorType.DOOR);
        when(securityRepository.getArmingStatus()).thenReturn(armingStatus);
        when(securityRepository.getAlarmStatus()).thenReturn(AlarmStatus.PENDING_ALARM);
        securityService.changeSensorActivationStatus(sensor, true);
        verify(securityRepository).setAlarmStatus(AlarmStatus.ALARM);
    }

    // Test 3: If pending alarm and all sensors are inactive, return to no alarm state.
    @Test
    public void noAlarmWhenPendingAlarmAndAllSensorsInactive() {
        when(securityRepository.getAlarmStatus()).thenReturn(AlarmStatus.PENDING_ALARM);
        Sensor sensor = new Sensor("sensor", SensorType.DOOR);
        securityService.changeSensorActivationStatus(sensor, false);
        verify(securityRepository).setAlarmStatus(AlarmStatus.NO_ALARM);
    }

    // Test 4: If alarm is active, change in sensor state should not affect the alarm state.
    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void alarmStatusNotChangedWhenAlarmActiveIfSensorDeactivated(boolean sensorActive) {
        when(securityRepository.getAlarmStatus()).thenReturn(AlarmStatus.ALARM);
        Sensor sensor = new Sensor("sensor", SensorType.DOOR);
        sensor.setActive(sensorActive);
        securityService.changeSensorActivationStatus(sensor, !sensorActive);
        verify(securityRepository, never()).setAlarmStatus(any(AlarmStatus.class));
    }

    // Test 5: If a sensor is activated while already active and the system is in pending state,
    // change it to alarm state.
    @Test
    public void alarmWhenSensorActivatedAndStatusPending() {
        when(securityRepository.getAlarmStatus()).thenReturn(AlarmStatus.PENDING_ALARM);
        Sensor sensor = new Sensor("sensor", SensorType.DOOR);
        sensor.setActive(true);
        securityService.changeSensorActivationStatus(sensor, true);
        verify(securityRepository).setAlarmStatus(AlarmStatus.ALARM);
    }

    // Test 6: If a sensor is deactivated while already inactive, make no changes to the alarm state.
    @Test
    public void noAlarmStateChangeWhenSensorDeactivatedAndStatusPending() {
        Sensor sensor = new Sensor("sensor", SensorType.DOOR);
        sensor.setActive(false);
        securityService.changeSensorActivationStatus(sensor, false);
        verify(securityRepository, never()).setAlarmStatus(any(AlarmStatus.class));
    }

    // Test 7: If the image service identifies an image containing a cat while the system is armed-home,
    // put the system into alarm status.
    @Test
    public void alarmWhenImageServiceIdentifiesCat() {
        when(securityRepository.getArmingStatus()).thenReturn(ArmingStatus.ARMED_HOME);
        when(imageService.imageContainsCat(any(BufferedImage.class), anyFloat())).thenReturn(true);
        bufferedImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        securityService.processImage(bufferedImage);
        verify(securityRepository).setAlarmStatus(AlarmStatus.ALARM);
    }

    // Test 8: If the image service identifies an image that does not contain a cat,
    // change the status to no alarm as long as the sensors are not active.
    @Test
    public void noAlarmWhenImageServiceIdentifiesNoCat() {
        when(imageService.imageContainsCat(any(BufferedImage.class), anyFloat())).thenReturn(false);
        bufferedImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        securityService.processImage(bufferedImage);
        verify(securityRepository).setAlarmStatus(AlarmStatus.NO_ALARM);
    }

    // Test 9: If the system is disarmed, set the status to no alarm.
    @Test
    public void noAlarmWhenSystemDisarmed() {
        securityService.setArmingStatus((ArmingStatus.DISARMED));
        verify(securityRepository).setAlarmStatus(AlarmStatus.NO_ALARM);
    }

    // Test 10: If the system is armed, reset all sensors to inactive.
    @ParameterizedTest
    @EnumSource(value = ArmingStatus.class, names = {"ARMED_HOME", "ARMED_AWAY"})
    public void resetSensorsWhenSystemArmed(ArmingStatus armingStatus) {
        Set<Sensor> sensors = new HashSet<>();
        Sensor sensor1 = new Sensor("sensor1", SensorType.DOOR);
        sensor1.setActive(true);
        Sensor sensor2 = new Sensor("sensor1", SensorType.DOOR);
        sensor2.setActive(false);
        sensors.add(sensor1);
        sensors.add(sensor2);
        when(securityRepository.getSensors()).thenReturn(sensors);
        securityService.setArmingStatus(armingStatus);
        securityService.getSensors().forEach(sensor -> assertFalse(sensor.getActive()));
    }


    // Test 11: If the system is armed-home while the camera shows a cat,
    // set the alarm status to alarm.
    @Test
    public void alarmWhenCameraShowsCat() {
        when(securityRepository.getArmingStatus()).thenReturn(ArmingStatus.DISARMED);
        when(imageService.imageContainsCat(any(BufferedImage.class), anyFloat())).thenReturn(true);
        bufferedImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        securityService.processImage(bufferedImage);
        securityService.setArmingStatus(ArmingStatus.ARMED_HOME);
        verify(securityRepository).setAlarmStatus(AlarmStatus.ALARM);
    }
}
