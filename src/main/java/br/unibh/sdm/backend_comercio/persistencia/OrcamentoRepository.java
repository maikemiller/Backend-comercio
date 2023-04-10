package br.unibh.sdm.backend_comercio.persistencia;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import br.unibh.sdm.backend_comercio.entidades.Orcamento;

/**
 * Esta classe estende o padrão CrudRepository 
 * @author jhcru
 *
 */
@EnableScan()
public interface OrcamentoRepository extends CrudRepository<Orcamento, String> {
	
	List<Orcamento> findByCodigo(String codigo);
	
}
