package financeflow.repository;

import financeflow.model.entity.TransacaoEntity;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransacaoRepository extends JpaRepository<TransacaoEntity, UUID> {

    //SELECT * FROM Transacao WHERE Usuario_Id = UUID usuarioID
    List<TransacaoEntity> findByUsuarioId(UUID usuarioId);

    // SELECT * FROM Transacao WHERE ID  = ?  and Usuario_Id = ?
    Optional<TransacaoEntity> findByIdAndUsuarioId(UUID id ,  UUID usuarioId);

    //metodo para buscar por usuario, data transacao entre as datas de inicio e fim do mes
    List<TransacaoEntity> findByUsuarioIdAndDataTransacaoBetween(UUID usuarioId,
                                                                 LocalDate dataInicio,
                                                                 LocalDate dataFim);

    Page<TransacaoEntity> findByUsuarioId (UUID usuarioId, Pageable pageable);


    void deleteByUsuarioId(UUID usuarioId);

}
