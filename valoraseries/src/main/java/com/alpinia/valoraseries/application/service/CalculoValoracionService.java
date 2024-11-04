package com.alpinia.valoraseries.application.service;

import com.alpinia.valoraseries.domain.model.ValoracionEntity;
import com.alpinia.valoraseries.domain.repository.ValoracionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CalculoValoracionService {

    private ValoracionRepository valoracionRepository;

    public double calcularValoracionMedia(Integer serieId) {
        return valoracionRepository.findBySerie_Id(serieId).stream()
                .mapToDouble(ValoracionEntity::getValoracion)
                .average()
                .orElse(0.0);
    }

}
