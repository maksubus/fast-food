package org.zhokha.fastfood.service.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zhokha.fastfood.persistence.model.Day;
import org.zhokha.fastfood.persistence.model.DayProduct;
import org.zhokha.fastfood.persistence.model.Model;
import org.zhokha.fastfood.service.dto.DayDto;

/**
 * author: maks
 * date: 16.08.15
 */

@Component
public class DayAssembler {

    @Autowired
    ProductAssembler productAssembler;

    public DayDto toDto(Day day) {
        DayDto dayDto = new DayDto();
        return toDto(day, dayDto);
    }

    public DayDto toDto(Day day, DayDto dayDto) {
        dayDto.setId(day.getId());
        dayDto.setDate(day.getDate());
        dayDto.setCookedAmount(day.getCookedAmount());
        dayDto.setRevenue(day.getRevenue());
        dayDto.setRestoName(day.getResto().getName());

        for (DayProduct dayProduct : day.getDayProducts()) {
            dayDto.getProducts().add(productAssembler.toDto(dayProduct));
        }

        return dayDto;
    }

    public Day toModel(DayDto dto) {
        return null;
    }

    public Model toModel(DayDto dayDto, Day day) {
        return null;
    }
}
