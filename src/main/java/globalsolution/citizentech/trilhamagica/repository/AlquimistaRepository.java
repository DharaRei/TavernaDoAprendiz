package globalsolution.citizentech.trilhamagica.repository;


import globalsolution.citizentech.trilhamagica.model.AlquimistaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AlquimistaRepository extends JpaRepository<AlquimistaModel, Long> {
}
