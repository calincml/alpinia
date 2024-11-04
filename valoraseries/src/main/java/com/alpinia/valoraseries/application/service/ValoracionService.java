package com.alpinia.valoraseries.application.service;

import com.alpinia.valoraseries.domain.model.ValoracionEntity;
import com.alpinia.valoraseries.domain.model.ValoracionId;
import com.alpinia.valoraseries.domain.repository.SerieRepository;
import com.alpinia.valoraseries.domain.repository.UsuarioRepository;
import com.alpinia.valoraseries.domain.repository.ValoracionRepository;
import com.alpinia.valoraseries.infrastructure.dto.request.ValoracionRequest;
import com.alpinia.valoraseries.infrastructure.dto.response.ValoracionResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@AllArgsConstructor
public class ValoracionService {

    private ValoracionRepository valoracionRepository;
    private UsuarioRepository usuarioRepository;
    private SerieRepository serieRepository;
    private CalculoValoracionService calculoValoracionService;

    public List<ValoracionResponse> getAllValoraciones() {
        return valoracionRepository.findAll().stream()
                .map(this::entityToResponse)
                .collect(Collectors.toList());
    }

    public List<ValoracionResponse> getValoracionesBySerieId(Integer id) {
        return valoracionRepository.findBySerie_Id(id).stream()
                .map(this::entityToResponse)
                .collect(Collectors.toList());
    }

    public ValoracionResponse getValoracionById(Integer id) {
        var response = valoracionRepository.findById(id).orElseThrow(() -> new RuntimeException("Valoracion no encontrada con el ID: " + id));
        return this.entityToResponse(response);
    }

    public ValoracionResponse addValoracion(ValoracionRequest valoracionRequest) {
        ValoracionEntity valoracion = requestToEntity(valoracionRequest);
        ValoracionEntity savedValoracion = valoracionRepository.save(valoracion);
        actualizarValoracionMedia(valoracion.getSerie().getId());
        return entityToResponse(savedValoracion);
    }

    public ValoracionResponse updateValoracion(ValoracionRequest request) {
        var valoracionId = ValoracionId.builder()
                .serieId(request.getSerieId())
                .usuarioId(request.getUsuarioId())
                .build();
        var valoracionToUpdate = valoracionRepository.findById(valoracionId).orElseThrow(() -> new RuntimeException("Valoracion no encontrada con el ID user: " + request.getUsuarioId()+ "y el ID serie: "+request.getSerieId()) );
        valoracionToUpdate.getSerie().setId(request.getSerieId());
        valoracionToUpdate.setValoracion(request.getValoracion());
        var valoracioUpdated =  valoracionRepository.save(valoracionToUpdate);
        actualizarValoracionMedia(valoracioUpdated.getSerie().getId());
        return entityToResponse(valoracioUpdated);
    }

    public void deleteValoracion(Integer idUser, Integer idSerie ) {
        var valoracionId = ValoracionId.builder()
                .serieId(idUser)
                .usuarioId(idSerie)
                .build();
        var valoracion = valoracionRepository.findById(valoracionId).orElseThrow(() -> new RuntimeException("Serie no encontrada con el ID: " + idUser +" y el ID serie: "+idSerie));
        this.valoracionRepository.delete(valoracion);
    }


    private ValoracionEntity requestToEntity(ValoracionRequest request) {
        ValoracionEntity entity = new ValoracionEntity();
        entity.setId(ValoracionId.builder()
                        .usuarioId(request.getUsuarioId())
                        .serieId(request.getSerieId())
                .build());
        entity.setUsuario(usuarioRepository.findById(request.getUsuarioId()).orElseThrow());
        entity.setSerie(serieRepository.findById(request.getSerieId()).orElseThrow());
        entity.setValoracion(request.getValoracion());
        return entity;
    }

    private ValoracionResponse entityToResponse(ValoracionEntity entity) {
        ValoracionResponse response = new ValoracionResponse();
        response.setUsuarioId(entity.getUsuario().getId());
        response.setSerieId(entity.getSerie().getId());
        response.setValoracion(entity.getValoracion());
        return response;
    }

    private void actualizarValoracionMedia(Integer serieId) {
        var nuevaValoracionMedia = calculoValoracionService.calcularValoracionMedia(serieId);
        serieRepository.findById(serieId).ifPresent(serie -> {
            serie.setValoracionMedia( BigDecimal.valueOf(nuevaValoracionMedia));
            serieRepository.save(serie);
        });
    }
}
