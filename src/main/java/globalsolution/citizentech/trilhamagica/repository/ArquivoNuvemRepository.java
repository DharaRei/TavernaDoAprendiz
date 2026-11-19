package globalsolution.citizentech.trilhamagica.repository;

import globalsolution.citizentech.trilhamagica.model.ArquivoNuvemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ArquivoNuvemRepository  extends JpaRepository<ArquivoNuvemModel, Long> {
}