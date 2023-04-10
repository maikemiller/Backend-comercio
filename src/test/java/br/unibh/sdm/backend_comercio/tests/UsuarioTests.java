package br.unibh.sdm.backend_comercio.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

import br.unibh.sdm.backend_comercio.entidades.Usuario;
import br.unibh.sdm.backend_comercio.persistencia.UsuarioRepository;

/**
 * Classe de testes para a entidade Criptomoeda.
 * <br>
 * Para rodar, antes sete a seguinte variável de ambiente:
 * -Dspring.config.location=C:/Users/jhcru/sdm/
 * <br>
 * Neste diretório, criar um arquivo application.properties contendo as
 * seguitnes variáveis:
 * <br>
 * amazon.aws.accesskey=<br>
 * amazon.aws.secretkey=<br>
 * 
 * @author jhcru
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { PropertyPlaceholderAutoConfiguration.class, UsuarioTests.DynamoDBConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuarioTests {

	private static Logger LOGGER = LoggerFactory.getLogger(UsuarioTests.class);

	@Configuration
	@EnableDynamoDBRepositories(basePackageClasses = UsuarioRepository.class)
	public static class DynamoDBConfig {

		@Value("")
		private String amazonAWSAccessKey;

		@Value("")
		private String amazonAWSSecretKey;

		public AWSCredentialsProvider amazonAWSCredentialsProvider() {
			return new AWSStaticCredentialsProvider(amazonAWSCredentials());
		}

		@Bean
		public AWSCredentials amazonAWSCredentials() {
			return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
		}

		@Bean
		public AmazonDynamoDB amazonDynamoDB() {
			return AmazonDynamoDBClientBuilder.standard().withCredentials(amazonAWSCredentialsProvider())
					.withRegion(Regions.US_EAST_1).build();
		}
	}

	@Autowired
	private UsuarioRepository repository;

	@Test
	public void teste1Criacao() throws ParseException {
		LOGGER.info("Criando objetos Usuarios ...");
		Usuario user1 = new Usuario("123", "111", "Lucas222", "Lucas@", "321senha");
		repository.save(user1);

		Usuario user2 = new Usuario("456", "222", "Mateus222", "mateus@", "123senha");
		repository.save(user2);

		LOGGER.info("Pesquisado todos Usuarios ");
		Iterable<Usuario> lista = repository.findAll();
		assertNotNull(lista.iterator());

		for (Usuario usuario : lista) {
			LOGGER.info(usuario.toString());
		}

		List<Usuario> usuarios = IteratorUtils.toList(lista.iterator());

		assertEquals(usuarios.size(), 2);
		LOGGER.info("Encontrado: {}", usuarios.size());

	}

	@Test
	public void teste2Exclusao() throws ParseException {
		LOGGER.info("Excluindo objetos Usuarios...");

		Iterable<Usuario> resultAntesDeDeletar = repository.findAll();

		for (Usuario usuario : resultAntesDeDeletar) {
			LOGGER.info("Excluindo usuario  = " + usuario.getNome());
			repository.delete(usuario);
		}

		List<Usuario> resultDepoisDeDeletar = IteratorUtils.toList(repository.findAll().iterator());

		assertEquals(resultDepoisDeDeletar.size(), 0);
		LOGGER.info("Exclusão feita com sucesso");
	}
}
