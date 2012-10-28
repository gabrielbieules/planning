package com.hospital.planning.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Entry point
 * 
 * @author gabriel.bieules
 *
 */
@Controller
public class HospitalControler {

  @RequestMapping("/")
  public ModelAndView welcomeHandler() {
    return new ModelAndView("hospital");
  }

}
