package ro.deloittedigital.bootcampbackend.services.interfaces;


import ro.deloittedigital.bootcampbackend.boundry.dto.RateDTO;
import ro.deloittedigital.bootcampbackend.entity.model.Rate;

import java.util.List;

public interface RateService {

    Rate addRate(RateDTO rateDTO);

    Rate updateRate(Long rateId, RateDTO rateDTO);

    List<Rate> getAll();
}
