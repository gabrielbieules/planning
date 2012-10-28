package com.hospital.planning.db;

import java.util.Collection;
import com.hospital.planning.primitives.Doctor;

/**
 * Doctor DB service
 * 
 * @author gabriel.bieules
 * 
 */
public interface DoctorService {

  Doctor get(Long pValueOf);

  Collection<Doctor> getAll();

  Collection<Doctor> find(Doctor pDoctor);

}
