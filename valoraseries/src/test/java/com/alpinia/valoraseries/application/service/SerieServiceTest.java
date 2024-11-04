package com.alpinia.valoraseries.application.service;

import com.alpinia.valoraseries.domain.model.SerieEntity;
import com.alpinia.valoraseries.domain.model.UsuarioEntity;
import com.alpinia.valoraseries.domain.model.ValoracionEntity;
import com.alpinia.valoraseries.domain.repository.SerieRepository;
import com.alpinia.valoraseries.domain.repository.UsuarioRepository;
import com.alpinia.valoraseries.domain.repository.ValoracionRepository;
import com.alpinia.valoraseries.infrastructure.dto.request.SerieRequest;
import com.alpinia.valoraseries.infrastructure.dto.response.RankingResponse;
import com.alpinia.valoraseries.infrastructure.dto.response.SerieResponse;
import com.alpinia.valoraseries.infrastructure.dto.response.UserVotoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SerieServiceTest {

    @Mock
    private SerieRepository serieRepository;

    @Mock
    private ValoracionRepository valoracionRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private SerieService serieService;

    private SerieEntity serieEntity;
    private SerieRequest serieRequest;
    private UsuarioEntity usuarioEntity;
    private ValoracionEntity valoracionEntity;

    @BeforeEach
    void setUp() {
        serieEntity = new SerieEntity();
        serieEntity.setId(1);
        serieEntity.setNombre("Test Serie");

        serieRequest = new SerieRequest();
        serieRequest.setNombre("Test Serie");
        serieRequest.setCaratula("URL");
        serieRequest.setPlataforma("Test Platform");
        serieRequest.setSinopsis("Test synopsis");

        usuarioEntity = new UsuarioEntity();
        usuarioEntity.setId(1);
        usuarioEntity.setNombre("Test User");

        valoracionEntity = new ValoracionEntity();
        valoracionEntity.setUsuario(usuarioEntity);
        valoracionEntity.setValoracion(5);
    }

    @Test
    void getRankingSeries_shouldReturnRankingResponses() {
        when(serieRepository.findAllByOrderByValoracionMediaDesc()).thenReturn(List.of(serieEntity));
        when(valoracionRepository.findBySerie_Id(1)).thenReturn(List.of(valoracionEntity));
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuarioEntity));

        List<RankingResponse> responses = serieService.getRankingSeries();

        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals("Test Serie", responses.get(0).getSerie().getNombre());
        verify(serieRepository, times(1)).findAllByOrderByValoracionMediaDesc();
    }

    @Test
    void addSerie_shouldSaveAndReturnSerieResponse() {
        when(serieRepository.save(any(SerieEntity.class))).thenReturn(serieEntity);

        SerieResponse response = serieService.addSerie(serieRequest);

        assertNotNull(response);
        assertEquals("Test Serie", response.getNombre());
        verify(serieRepository, times(1)).save(any(SerieEntity.class));
    }

    @Test
    void updateSerie_shouldUpdateAndReturnSerieResponse() {
        when(serieRepository.findById(1)).thenReturn(Optional.of(serieEntity));
        when(serieRepository.save(any(SerieEntity.class))).thenReturn(serieEntity);

        SerieResponse response = serieService.updateSerie(1, serieRequest);

        assertNotNull(response);
        assertEquals("Test Serie", response.getNombre());
        verify(serieRepository, times(1)).save(any(SerieEntity.class));
    }

    @Test
    void modifyCaratula_shouldUpdateCaratula() {
        when(serieRepository.findById(1)).thenReturn(Optional.of(serieEntity));
        when(serieRepository.save(any(SerieEntity.class))).thenReturn(serieEntity);

        SerieResponse response = serieService.modifyCaratula(1, "New URL");

        assertNotNull(response);
        assertEquals("New URL", response.getCaratula());
        verify(serieRepository, times(1)).save(any(SerieEntity.class));
    }

    @Test
    void delete_shouldRemoveSerie() {
        when(serieRepository.findById(1)).thenReturn(Optional.of(serieEntity));

        serieService.delete(1);

        verify(serieRepository, times(1)).delete(serieEntity);
    }
}
