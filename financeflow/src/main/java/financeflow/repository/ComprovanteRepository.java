package financeflow.repository;


import financeflow.enums.ComprovanteStatus;
import financeflow.model.entity.ComprovanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ComprovanteRepository extends JpaRepository<ComprovanteEntity, UUID> {

     List<ComprovanteEntity> findByUsuarioId(UUID usuarioId);

     List <ComprovanteEntity> findByStatus (ComprovanteStatus status);

     Optional<ComprovanteEntity> findByIdAndUsuarioId(UUID id, UUID usuarioId);
}
