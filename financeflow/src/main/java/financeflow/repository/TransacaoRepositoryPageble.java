package financeflow.repository;

import financeflow.model.TransacaoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface TransacaoRepositoryPageble extends JpaRepository<TransacaoEntity, UUID> {

    Page<TransacaoEntity> findByUsuarioId (UUID usuarioId, Pageable pageable);

}
