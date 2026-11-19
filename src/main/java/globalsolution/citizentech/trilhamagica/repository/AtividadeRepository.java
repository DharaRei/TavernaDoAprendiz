package globalsolution.citizentech.trilhamagica.repository;

import globalsolution.citizentech.trilhamagica.model.AlquimistaModel;
import globalsolution.citizentech.trilhamagica.model.AtividadeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AtividadeRepository extends JpaRepository<AtividadeModel, Long> {
}
