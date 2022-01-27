package posjavamavenhibernate;

import org.junit.Test;

import dao.DAOGeneric;
import model.UsuarioPessoa;

public class TestHibernate {

	@Test
	public void testHibernateUtil() {
		HibernateUtil.getEntityManager();
	}

	@Test
	public void testDAOGenericSalvar() {
		DAOGeneric<UsuarioPessoa> daoGeneric = new DAOGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = new UsuarioPessoa();
		
		pessoa.setNome("Janderson");
		pessoa.setSobreNome("Souza 2");
		pessoa.setIdade(23);
		pessoa.setEmail("janderson@gmail.com");
		pessoa.setLogin("teste");
		pessoa.setSenha("123");

		daoGeneric.salvar(pessoa);

	}

	@Test
	public void testDAOGenericBuscar() {
		DAOGeneric<UsuarioPessoa> daoGeneric = new DAOGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = new UsuarioPessoa();

		pessoa.setId(2L);
		
		pessoa = daoGeneric.pesquisar(pessoa);

		System.out.println(pessoa);

	}
}
