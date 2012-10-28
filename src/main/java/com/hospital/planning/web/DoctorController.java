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

import com.hospital.planning.db.DoctorService;
import com.hospital.planning.primitives.Doctor;

/**
 * Manages pages related to Doctor
 * 
 * @author gabriel.bieules
 *
 */
@Controller
public class DoctorController {

  @Autowired
  @Qualifier("doctorService")
  private DoctorService doctorService;
  
  @RequestMapping(value = "/doctors/search", method = RequestMethod.GET)
  public String setupSearch(Model model) {
    model.addAttribute(new Doctor());
    return "doctor/search";
  }

  @RequestMapping(value = "/doctors/search", method = RequestMethod.POST)
  public String submitSearch(@ModelAttribute Doctor pDoctor,
      BindingResult result, Model model, SessionStatus status) {
    Collection<Doctor> doctors = doctorService.find(pDoctor);
    if (doctors.size() == 0) {
      result.rejectValue("firstName", "nodoctor", "not found");
      return "doctor/search";
    }
    if (doctors.size() > 1) {
      model.addAttribute("doctors", doctors);
      return "doctor/list";
    }
    Doctor doctor = doctors.iterator().next();
    return "redirect:/doctors/" + doctor.getId();
  }

  @RequestMapping("/doctors/{doctorId}")
  public ModelAndView clientHandler(@PathVariable("doctorId") Long doctorId) {
    ModelAndView mav = new ModelAndView("doctor/show");
    Doctor doctor = doctorService.get(doctorId);
    mav.addObject("doctor", doctor);
    return mav;
  }

}
