package com.hospital.planning.primitives;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Representation of Person table
 * 
 * @author gabriel.bieules
 *
 */
@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  private Long id;
  
  @Column(name="first_name")
  private String firstName;
  
  @Column(name="last_name")
  private String lastName;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String pFirstName) {
    firstName = pFirstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String pLastName) {
    lastName = pLastName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long pId) {
    id = pId;
  }

}