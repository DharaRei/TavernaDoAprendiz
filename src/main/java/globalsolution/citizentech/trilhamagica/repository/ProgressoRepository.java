package globalsolution.citizentech.trilhamagica.repository;

import globalsolution.citizentech.trilhamagica.model.ProgressoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface ProgressoRepository extends JpaRepository<ProgressoModel, Long> {
    Optional<ProgressoModel> findById(Long alunoId);
}
