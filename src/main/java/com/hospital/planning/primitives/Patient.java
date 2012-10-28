package com.hospital.planning.primitives;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Representation of Patient table
 * 
 * @author gabriel.bieules
 *
 */
@Entity
@Table(name = "patient")
@PrimaryKeyJoinColumn(name="id")
public class Patient extends Person {

  @Column(name="phone")
  private String phone;

  @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER)
  private List<Appointment> appointments;

  public List<Appointment> getAppointments() {
    return appointments;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String pTelephone) {
    phone = pTelephone;
  }

  public void setAppointments(List<Appointment> pAppointments) {
    appointments = pAppointments;
  }

}
