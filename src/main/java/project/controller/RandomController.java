package project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/numbers")
public class RandomController {

    //TODO Realization of project.controller

    @GetMapping(value = "/random")
    public Integer getNumbers() {
        return 1;
    }
}
