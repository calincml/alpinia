package com.alpinia.valoraseries.application.service;

import com.alpinia.valoraseries.domain.model.SerieEntity;
import com.alpinia.valoraseries.domain.model.ValoracionEntity;
import com.alpinia.valoraseries.domain.repository.SerieRepository;
import com.alpinia.valoraseries.domain.repository.UsuarioRepository;
import com.alpinia.valoraseries.domain.repository.ValoracionRepository;
import com.alpinia.valoraseries.infrastructure.dto.request.SerieRequest;
import com.alpinia.valoraseries.infrastructure.dto.response.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@AllArgsConstructor
public class SerieService {

    private SerieRepository serieRepository;

    private ValoracionRepository valoracionRepository;

    private UsuarioRepository usuarioRepository;

    @Cacheable("rankingCache")
    public List<RankingResponse> getRankingSeries() {
        List<SerieEntity> seriesOrdenadas = serieRepository.findAllByOrderByValoracionMediaDesc();
       return  seriesOrdenadas.stream()
               .map(serie -> {
                   SerieResponse serieResponse = entitySerieToResponse(serie);
                   List<UserVotoResponse> valoraciones = valoracionRepository.findBySerie_Id(serie.getId()).stream()
                           .map(this::entityToValoracionResponse)
                           .collect(Collectors.toList());
                   return new RankingResponse(serieResponse, valoraciones);
               })
               .collect(Collectors.toList());

    }

    @CacheEvict(value = "rankingCache", allEntries = true)
    public SerieResponse addSerie(SerieRequest request) {
        var serieToPersist = SerieEntity.builder()
                .nombre(request.getNombre())
                .caratula(request.getCaratula())
                .plataforma(request.getPlataforma())
                .sinopsis(request.getSinopsis())
                .build();

        var persisted = serieRepository.save(serieToPersist);
        return this.entitySerieToResponse(persisted);
    }

    @CacheEvict(value = "rankingCache", allEntries = true)
    public SerieResponse updateSerie(Integer id, SerieRequest request) {
        var serieToUpdate = serieRepository.findById(id).orElseThrow(() -> new RuntimeException("Serie no encontrada con el ID: " + id) );
        serieToUpdate.setNombre(request.getNombre());
        serieToUpdate.setCaratula(request.getCaratula());
        serieToUpdate.setPlataforma(request.getPlataforma());
        serieToUpdate.setSinopsis(request.getSinopsis());
        serieToUpdate.setValoracionMedia(request.getValoracionMedia());

        var serieUpdated = serieRepository.save(serieToUpdate);
        return this.entitySerieToResponse(serieUpdated);
    }

    public SerieResponse modifyCaratula(Integer id, String caratula) {
        var serieToUpdate = serieRepository.findById(id).orElseThrow(() -> new RuntimeException("Serie no encontrada con el ID: " + id) );
        serieToUpdate.setCaratula(caratula);
        var serieUpdated = serieRepository.save(serieToUpdate);
        return this.entitySerieToResponse(serieUpdated);
    }

    @CacheEvict(value = "rankingCache", allEntries = true)
    public void delete(Integer id) {
        var serieToDeelete = serieRepository.findById(id).orElseThrow(() -> new RuntimeException("Serie no encontrada con el ID: " + id) );
        this.serieRepository.delete(serieToDeelete);
    }

    private SerieResponse entitySerieToResponse(SerieEntity entity) {
        var response = new SerieResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    private UserVotoResponse entityToValoracionResponse(ValoracionEntity entity) {

        var entityUser =  usuarioRepository.findById(entity.getUsuario().getId());

        return UserVotoResponse.builder()
                .userName(entityUser.get().getNombre())
                .voto(entity.getValoracion())
                .build();
    }
}
