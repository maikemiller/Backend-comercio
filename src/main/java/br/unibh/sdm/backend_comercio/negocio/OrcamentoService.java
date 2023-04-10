package br.unibh.sdm.backend_comercio.negocio;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.unibh.sdm.backend_comercio.entidades.Orcamento;
import br.unibh.sdm.backend_comercio.persistencia.OrcamentoRepository;

/**
 * Classe contendo a lógica de negócio para Cotação
 * @author jhcru
 *
 */
@Service
public class OrcamentoService {

    private static final Logger logger= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    
    private final OrcamentoRepository orcamentoRepo;

    public OrcamentoService(OrcamentoRepository orcamentoRepository){
        this.orcamentoRepo=orcamentoRepository;
    }
    
    public List<Orcamento> getOrcamentos(){
        if(logger.isInfoEnabled()){
            logger.info("Buscando todos os objetos");
        }
        Iterable<Orcamento> lista = this.orcamentoRepo.findAll();
        if (lista == null) {
        	return new ArrayList<Orcamento>();
        }
        return IteratorUtils.toList(lista.iterator());
    }  

    public Orcamento getOrcamentoById(String id){
        if(logger.isInfoEnabled()){
            logger.info("Buscando Cotacao com o codigo {}",id);
        }
        Optional<Orcamento> retorno = this.orcamentoRepo.findById(id);
        if(!retorno.isPresent()){
            throw new RuntimeException("Cotacao com o id "+id+" nao encontrada");
        }
        return retorno.get();
    }
    
    public List<Orcamento> getOrcamentoByCodigo(String codigo){
    	if(logger.isInfoEnabled()){
            logger.info("Buscando todos os objetos");
        }
        Iterable<Orcamento> lista = this.orcamentoRepo.findByCodigo(codigo);
        if (lista == null) {
        	return new ArrayList<Orcamento>();
        }
        return IteratorUtils.toList(lista.iterator());
    }
    
    public Orcamento saveOrcamento(Orcamento orcamento){
        if(logger.isInfoEnabled()){
            logger.info("Salvando Orcamento com os detalhes {}",orcamento.toString());
        }
        return this.orcamentoRepo.save(orcamento);
    }
    
    public void deleteOrcamento(String id){
        if(logger.isInfoEnabled()){
            logger.info("Excluindo Orcamento com id {}",id);
        }
        this.orcamentoRepo.deleteById(id);
    }

    public boolean isOrcamentoExists(Orcamento orcamento){
    	Optional<Orcamento> retorno = this.orcamentoRepo.findById(orcamento.getCodigo());
        return retorno.isPresent() ? true:  false;
    }

}