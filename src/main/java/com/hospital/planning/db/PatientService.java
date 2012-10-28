package com.hospital.planning.db;

import java.util.Collection;

import com.hospital.planning.primitives.Patient;

/**
 * PAtient DB service
 * 
 * @author gabriel.bieules
 * 
 */
public interface PatientService {

  Patient get(Long pPatientId);

  void add(Patient pPatient);

  Collection<Patient> find(Patient pPatient);

}
