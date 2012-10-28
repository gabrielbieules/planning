package com.hospital.planning.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hospital.planning.db.AppointmentService;
import com.hospital.planning.primitives.Appointment;

/**
 * Hibernate implementation of appointment service
 * 
 * @author gabriel.bieules
 *
 */
@Service("appointmentService")
@Transactional
public class AppointmentServiceHibernate implements AppointmentService {

  @Autowired
  private SessionFactory sessionFactory;

  /**
   * Retrieves Appointment
   * 
   * @return a Appointment
   */
  public Appointment get(Long pAppointmentId) {
    Session session = sessionFactory.getCurrentSession();
    return (Appointment) session.get(Appointment.class, pAppointmentId);
  }

  /**
   * Add a new Appointment
   * @param pAppointment
   */
  public void add(Appointment pAppointment) {
    Session session = sessionFactory.getCurrentSession();
    session.save(pAppointment);
  }

  /**
   * Deletes an existing Appointment
   * 
   * @param pAppointmentId
   *          the id of the existing Appointment
   */
  public void delete(Long pAppointmentId) {
    Session session = sessionFactory.getCurrentSession();
    Appointment Appointment = (Appointment) session.get(Appointment.class, pAppointmentId);
    session.delete(Appointment);
  }

}
