package globalsolution.citizentech.trilhamagica.repository;

import globalsolution.citizentech.trilhamagica.model.MatemagicamenteModel;
import globalsolution.citizentech.trilhamagica.model.MaterialModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MatemagicamenteRepository extends JpaRepository<MatemagicamenteModel, Long> {
}
