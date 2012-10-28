package com.hospital.planning.hibernate;

import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hospital.planning.db.DoctorService;
import com.hospital.planning.primitives.Doctor;

/**
 * Service for processing Doctors
 * 
 */
@Service("doctorService")
@Transactional
public class DoctorServiceHibernate implements DoctorService {

  private static String QUERY_FIND_DOCTOR = "select doctor from Doctor doctor, Person person where ((person.firstName like :firstName or person.lastName like :lastName) and doctor.id=person.id)";

  @Autowired
  private SessionFactory sessionFactory;

  /**
   * Hibernate implementation of doctor service
   * 
   * @author gabriel.bieules
   * 
   */
  public Doctor get(Long pDoctorId) {
    Session session = sessionFactory.getCurrentSession();
    return (Doctor) session.get(Doctor.class, pDoctorId);
  }

  /**
   * Retrieves Doctor
   * 
   * @return a Doctor
   */
  @SuppressWarnings("unchecked")
  public List<Doctor> getAll() {
    Session session = sessionFactory.getCurrentSession();
    return (List<Doctor>) session.createQuery("from Doctor").list();
  }

  @SuppressWarnings("unchecked")
  public Collection<Doctor> find(Doctor pDoctor) {
    return sessionFactory.getCurrentSession().createQuery(QUERY_FIND_DOCTOR)
        .setString("firstName", pDoctor.getFirstName() + "%").setString("lastName", pDoctor.getLastName() + "%").list();
  }

  /**
   * Add a new Doctor
   * 
   * @param pDoctor
   */
  public void add(Doctor pDoctor) {
    Session session = sessionFactory.getCurrentSession();
    session.save(pDoctor);
  }

  /**
   * Deletes an existing Doctor
   * 
   * @param id
   *          the id of the existing Doctor
   */
  public void delete(Integer id) {
    Session session = sessionFactory.getCurrentSession();
    Doctor doctor = (Doctor) session.get(Doctor.class, id);
    session.delete(doctor);
  }

  /**
   * Edits an existing Doctor
   * 
   * @param id
   *          the id of the existing Doctor
   * @param firstName
   *          the first name of the existing Doctor
   * @param lastName
   *          the last name of the existing Doctor
   */
  public void edit(Integer id, String firstName, String lastName) {
    Session session = sessionFactory.getCurrentSession();
    Doctor doctor = (Doctor) session.get(Doctor.class, id);
    doctor.setFirstName(firstName);
    doctor.setLastName(lastName);
    session.save(doctor);
  }

}
