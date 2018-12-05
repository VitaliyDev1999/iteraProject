package project.service;

import org.springframework.stereotype.Service;
import project.dto.ParamsDTO;

@Service
public class ParamsService {

    public ParamsDTO getParams(Integer angle){
        return new ParamsDTO(180, 37);
    }
}
