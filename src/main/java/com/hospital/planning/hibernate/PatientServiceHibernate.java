package com.hospital.planning.hibernate;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hospital.planning.db.PatientService;
import com.hospital.planning.primitives.Patient;

/**
 * Hibernate implementation of patient service
 * 
 * @author gabriel.bieules
 *
 */
@Service("patientService")
@Transactional
public class PatientServiceHibernate implements PatientService {

  private static String QUERY_FIND_PATIENT = "select patient from Patient patient, Person person where ((person.firstName like :firstName or person.lastName like :lastName) and patient.id=person.id)";

  @Autowired
  private SessionFactory sessionFactory;

  /**
   * Retrieves patient
   * 
   * @return a patient
   */
  public Patient get(Long patientId) {
    Session session = sessionFactory.getCurrentSession();
    return (Patient) session.get(Patient.class, patientId);
  }

  /**
   * Add a new patient
   * @param pPatient
   */
  public void add(Patient pPatient) {
    Session session = sessionFactory.getCurrentSession();
    session.save(pPatient);
  }

  @SuppressWarnings("unchecked")
  public Collection<Patient> find(Patient pPatient) {
    return sessionFactory.getCurrentSession().createQuery(QUERY_FIND_PATIENT)
        .setString("firstName", pPatient.getFirstName() + "%")
        .setString("lastName", pPatient.getLastName() + "%")
        .list();
  }

  /**
   * Deletes an existing patient
   * 
   * @param id
   *          the id of the existing patient
   */
  public void delete(Integer id) {
    Session session = sessionFactory.getCurrentSession();
    Patient patient = (Patient) session.get(Patient.class, id);
    session.delete(patient);
  }

  /**
   * Edits an existing patient
   * 
   * @param id
   *          the id of the existing patient
   * @param firstName
   *          the first name of the existing patient
   * @param lastName
   *          the last name of the existing patient
   */
  public void edit(Integer id, String firstName, String lastName) {
    Session session = sessionFactory.getCurrentSession();
    Patient patient = (Patient) session.get(Patient.class, id);
    patient.setFirstName(firstName);
    patient.setLastName(lastName);
    session.save(patient);
  }


}
