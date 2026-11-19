package globalsolution.citizentech.trilhamagica.service;

import globalsolution.citizentech.trilhamagica.model.AlquimistaModel;
import globalsolution.citizentech.trilhamagica.repository.AlquimistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlquimistaService {

    @Autowired
    private AlquimistaRepository alquimistaRepository;

    public List<AlquimistaModel> listarTodos() {
        return alquimistaRepository.findAll();
    }

    public Optional<AlquimistaModel> buscarPorId(Long id) {
        return alquimistaRepository.findById(id);
    }

    public AlquimistaModel salvar(AlquimistaModel alquimista) {
        return alquimistaRepository.save(alquimista);
    }

    public AlquimistaModel atualizar(Long id, AlquimistaModel alquimistaAtualizado) {
        return alquimistaRepository.findById(id).map(alquimista -> {
            alquimista.setNomeResultado(alquimistaAtualizado.getNomeResultado());
            alquimista.setElemento1(alquimistaAtualizado.getElemento1());
            alquimista.setElemento2(alquimistaAtualizado.getElemento2());
            alquimista.setCompostoResultante(alquimistaAtualizado.getCompostoResultante());
            alquimista.setExplicacaoCientifica(alquimistaAtualizado.getExplicacaoCientifica());
            alquimista.setDataDescoberta(alquimistaAtualizado.getDataDescoberta());
            alquimista.setAtividade(alquimistaAtualizado.getAtividade());
            alquimista.setProfessorCadastrou(alquimistaAtualizado.getProfessorCadastrou());
            return alquimistaRepository.save(alquimista);
        }).orElseThrow(() -> new RuntimeException("Alquimista não encontrado com o ID: " + id));
    }

    public void deletar(Long id) {
        alquimistaRepository.deleteById(id);
    }
}