package com.hospital.planning.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.hospital.planning.db.PatientService;
import com.hospital.planning.primitives.Patient;

/**
 * Manages pages related to Patient
 * 
 * @author gabriel.bieules
 *
 */
@Controller
public class PatientController {

  @Autowired
  @Qualifier("patientService")
  private PatientService patientService;

  @RequestMapping(value = "/patients/new", method = RequestMethod.GET)
  public String setupNew(Model model) {
    model.addAttribute(new Patient());
    return "patient/form";
  }

  @RequestMapping(value = "/patients/new", method = RequestMethod.POST)
  public String submitNew(@ModelAttribute Patient patient, BindingResult result, SessionStatus status) {

    if (patient.getFirstName() == null || "".equals(patient.getFirstName())) {
      result.rejectValue("firstName", "empty", "must not be empty");
      return "patient/form";
    }
    if (patient.getLastName() == null || "".equals(patient.getLastName())) {
      result.rejectValue("lastName", "empty", "must not be empty");
      return "patient/form";
    }

    patientService.add(patient);
    status.setComplete();
    return "redirect:/patients/" + patient.getId();
  }

  @RequestMapping(value = "/patients/search", method = RequestMethod.GET)
  public String setupSearch(Model model) {
    model.addAttribute(new Patient());
    return "patient/search";
  }

  @RequestMapping(value = "/patients/search", method = RequestMethod.POST)
  public String submitSearch(@ModelAttribute Patient pPatient, BindingResult result, Model model, SessionStatus status) {
    Collection<Patient> patients = patientService.find(pPatient);
    if (patients.size() == 0) {
      result.rejectValue("firstName", "nopatient", "not found");
      return "patient/search";
    }
    if (patients.size() > 1) {
      model.addAttribute("patients", patients);
      return "patient/list";
    }
    Patient patient = patients.iterator().next();
    return "redirect:/patients/" + patient.getId();
  }

  @RequestMapping("/patients/{patientId}")
  public ModelAndView clientHandler(@PathVariable("patientId") Long patientId) {
    ModelAndView mav = new ModelAndView("patient/show");
    Patient patient = patientService.get(patientId);
    mav.addObject("patient", patient);
    return mav;
  }

}
