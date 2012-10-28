package com.hospital.planning.db;

import com.hospital.planning.primitives.Appointment;

/**
 * Appointment DB service
 * 
 * @author gabriel.bieules
 * 
 */
public interface AppointmentService {

  void add(Appointment pAppointment);

  void delete(Long pAppointmentId);

}
