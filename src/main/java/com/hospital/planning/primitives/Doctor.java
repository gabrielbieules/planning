package com.hospital.planning.primitives;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Representation of Doctor table
 * 
 * @author gabriel.bieules
 *
 */
@Entity
@Table(name = "doctor")
@PrimaryKeyJoinColumn(name = "id")
public class Doctor extends Person {

  @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
  private List<Appointment> appointments;

  public List<Appointment> getAppointments() {
    return appointments;
  }

  public void setAppointments(List<Appointment> pAppointments) {
    appointments = pAppointments;
  }

  @Override
  public String toString() {
    return String.format("%d %s %s", getId(), getFirstName(), getLastName());
  }

}
