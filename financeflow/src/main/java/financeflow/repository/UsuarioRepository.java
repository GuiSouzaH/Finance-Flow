package financeflow.repository;


import financeflow.model.entity.UsuarioEntity;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository <UsuarioEntity, UUID> {

    Optional<UsuarioEntity> findByEmail (String email);
    boolean existsByEmail(String email);

}
