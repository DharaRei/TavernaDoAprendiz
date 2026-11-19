package globalsolution.citizentech.trilhamagica.repository;

import globalsolution.citizentech.trilhamagica.model.FisicaLegalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface FisicaLegalRepository extends JpaRepository<FisicaLegalModel, Long> {
}
