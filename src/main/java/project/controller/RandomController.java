package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.dto.ParamsDTO;
import project.service.ParamsService;

import javax.validation.Valid;

@RestController
@RequestMapping("/numbers")
public class RandomController {

    @Autowired
    private ParamsService paramsService;

    @PostMapping(value = "/random")
    public @ResponseBody ParamsDTO getNumbers(ParamsDTO paramsDTO) {
        return paramsService.getParams(paramsDTO.getAngle());
    }
}
