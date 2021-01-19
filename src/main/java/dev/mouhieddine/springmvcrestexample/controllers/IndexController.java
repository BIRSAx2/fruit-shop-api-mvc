package dev.mouhieddine.springmvcrestexample.controllers
        ;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : Mouhieddine.dev
 * @since : 1/19/2021, Tuesday
 **/
@Controller
public class IndexController {

  @RequestMapping({"/", ""})
  public String getIndex(){
    return "index";
  }
}
