package com.hospital.planning.web;

import java.beans.PropertyEditorSupport;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import com.hospital.planning.db.AppointmentService;
import com.hospital.planning.db.DoctorService;
import com.hospital.planning.db.PatientService;
import com.hospital.planning.primitives.Appointment;
import com.hospital.planning.primitives.Doctor;
import com.hospital.planning.primitives.Patient;

/**
 * Manages pages related to Appointment
 * 
 * @author gabriel.bieules
 *
 */
@Controller
@SessionAttributes("appointments")
public class AppointmentController {
  private static Pattern PATTERN = Pattern.compile("(\\d)\\s*.*");

  @Autowired
  @Qualifier("patientService")
  private PatientService patientService;

  @Autowired
  @Qualifier("appointmentService")
  private AppointmentService appointmentService;

  @Autowired
  @Qualifier("doctorService")
  private DoctorService doctorService;

  @InitBinder
  public void setAllowedFields(WebDataBinder dataBinder) {
    dataBinder.setDisallowedFields("id");
  }

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Doctor.class, new PropertyEditorSupport() {

      @Override
      public void setAsText(String pDoctorAsText) {
        Matcher matcher = PATTERN.matcher(pDoctorAsText);
        if (!matcher.find()) {
          throw new IllegalArgumentException("Doctor format is not correct: " + pDoctorAsText);
        }
        setValue(doctorService.get(Long.valueOf(matcher.group(1))));
      }
    });
  }

  @ModelAttribute("doctorsList")
  public Collection<Doctor> populateDoctors() {
    return doctorService.getAll();
  }

  @RequestMapping(value = "/patients/{patientId}/appointments/new", method = RequestMethod.GET)
  public String setupForm(@PathVariable("patientId") Long patientId, Model model) {
    Patient patient = patientService.get(patientId);
    model.addAttribute("patient", patient);
    model.addAttribute(new Appointment());
    return "appointment";
  }

  @RequestMapping(value = "/patients/{patientId}/appointments/new", method = RequestMethod.POST)
  public String processSubmit(@PathVariable("patientId") Long patientId, @ModelAttribute Appointment pAppointment,
      BindingResult result, Model uiModel, SessionStatus status) {

    if (result.getErrorCount() != 0) {
      result.rejectValue("date", "error", new String[] { result.getAllErrors().get(0).getObjectName() }, "Error");
      return "redirect:/patients/{patientId}/appointments/new";
    }

    Patient patient = patientService.get(patientId);
    pAppointment.setPatient(patient);
    appointmentService.add(pAppointment);
    status.setComplete();
    return "redirect:/patients/{patientId}/appointments/new";
  }
  
  @RequestMapping(value = "/patients/{patientId}/appointments/{appointmentId}/delete", method = RequestMethod.GET)
  public String delete(@PathVariable("patientId") Long patientId, @PathVariable("appointmentId") Long appointmentId, Model model) {
    appointmentService.delete(appointmentId);
    Patient patient = patientService.get(patientId);
    model.addAttribute("patient", patient);
    model.addAttribute(new Appointment());
    return "appointment";
  }

}
