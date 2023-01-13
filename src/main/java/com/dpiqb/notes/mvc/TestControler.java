package com.dpiqb.notes.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/test")
@Controller
public class TestControler {
  @GetMapping
  public ModelAndView get(){
    ModelAndView result = new ModelAndView("test");
    return result;
  }
}
