package financeflow.repository;

import financeflow.dto.TransacaoResponseDTO;
import financeflow.model.TransacaoEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface iTransacaoRepository extends JpaRepository<TransacaoEntity, UUID> {

    //SELECT * FROM Transacao WHERE Usuario_Id = UUID usuarioID
    List<TransacaoEntity> findByUsuarioId(UUID usuarioId);
    // SELECT * FROM Transacao WHERE ID  = ?  and Usuario_Id = ?
    Optional<TransacaoEntity> findByIdAndUsuarioId(UUID id ,  UUID usuarioId);


}
