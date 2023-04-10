package br.unibh.sdm.backend_comercio.persistencia;

import java.util.List;
import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import br.unibh.sdm.backend_comercio.entidades.Usuario;

/**
 * Esta classe estende o padrão CrudRepository 
 * @author jhcru
 *
 */
@EnableScan()
public interface UsuarioRepository extends CrudRepository<Usuario, String> {
	
	Optional<Usuario> findById(String id);
	
}
