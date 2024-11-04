package com.alpinia.valoraseries.domain.repository;

import com.alpinia.valoraseries.domain.model.SerieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface SerieRepository extends JpaRepository<SerieEntity, Integer> {

    List<SerieEntity> findAllByOrderByValoracionMediaDesc();
}
