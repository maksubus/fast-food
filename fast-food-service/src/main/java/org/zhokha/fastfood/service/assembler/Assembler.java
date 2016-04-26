package org.zhokha.fastfood.service.assembler;

import org.zhokha.fastfood.persistence.model.Model;
import org.zhokha.fastfood.service.dto.Dto;

/**
 * author: maks
 * date: 16.08.15
 */
public interface Assembler {

    Dto toDto(Model model, Dto dto);
    Model toModel(Dto dto, Model model);
}
