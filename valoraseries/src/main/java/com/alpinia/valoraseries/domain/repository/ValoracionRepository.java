package com.alpinia.valoraseries.domain.repository;

import com.alpinia.valoraseries.domain.model.ValoracionEntity;
import com.alpinia.valoraseries.domain.model.ValoracionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ValoracionRepository extends JpaRepository<ValoracionEntity, Integer> {

    List<ValoracionEntity> findBySerie_Id(Integer id);

    List<ValoracionEntity> findByUsuario_Id(Integer usuarioId);

    Optional<ValoracionEntity> findById(ValoracionId id);
}