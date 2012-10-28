package com.hospital.planning.primitives;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Representation of Appointment table
 * 
 * @author gabriel.bieules
 *
 */
@Entity
@Table(name = "appointment")
public class Appointment {

  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  private Long id;

  @Column(name = "visit_date")
  private Long date;

  @ManyToOne(fetch=FetchType.EAGER)
  @JoinColumn(name="doctor_id")
  private Doctor doctor;

  @ManyToOne(fetch=FetchType.EAGER)
  @JoinColumn(name="patient_id")
  private Patient patient;

  public Long getId() {
    return id;
  }

  public void setId(Long pId) {
    id = pId;
  }

  public Long getDate() {
    return date;
  }

  public void setDate(Long pDate) {
    date = pDate;
  }

  public Doctor getDoctor() {
    return doctor;
  }

  public void setDoctor(Doctor pDoctor) {
    doctor = pDoctor;
  }

  public Patient getPatient() {
    return patient;
  }

  public void setPatient(Patient pPatient) {
    patient = pPatient;
  }

}
