package br.unibh.sdm.backend_comercio.rest;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.unibh.sdm.backend_comercio.entidades.Orcamento;
import br.unibh.sdm.backend_comercio.negocio.OrcamentoService;

/**
 * Classe contendo as definições de serviços REST/JSON para Cotacao
 * @author jhcru
 *
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "orcamento")
public class OrcamentoController {
   
    private final OrcamentoService orcamentoService;

    public OrcamentoController(OrcamentoService orcamentoService){
        this.orcamentoService=orcamentoService;
    }

    @GetMapping(value = "")
    public List<Orcamento> getOrcamentos(){
        return orcamentoService.getOrcamentos();
    }
    
    @GetMapping(value="{id}")
    public Orcamento getOrcamentoById(@PathVariable String id) throws Exception{
        if(!ObjectUtils.isEmpty(id)){
           return orcamentoService.getOrcamentoById(id);
        }
        throw new Exception("Orcamento com codigo "+id+" nao encontrada");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Orcamento createOrcamento(@RequestBody @NotNull Orcamento orcamento) throws Exception {
         return orcamentoService.saveOrcamento(orcamento);
    }
    
    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Orcamento updateOrcamento(@PathVariable String id, 
    		@RequestBody @NotNull Orcamento orcamento) throws Exception {
         return orcamentoService.saveOrcamento(orcamento);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{id}")
    public boolean updateOrcamento(@PathVariable String id) throws Exception {
        orcamentoService.deleteOrcamento(id);
         return true;
    }
    
}