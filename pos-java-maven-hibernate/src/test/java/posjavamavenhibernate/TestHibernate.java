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
	public void testDAOGeneric() {
		DAOGeneric<UsuarioPessoa> daoGeneric = new DAOGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = new UsuarioPessoa();
		
		pessoa.setNome("Janderson");
		pessoa.setSobreNome("Souza");
		pessoa.setIdade(23);
		pessoa.setEmail("janderson@gmail.com");
		pessoa.setLogin("teste");
		pessoa.setSenha("123");

		daoGeneric.salvar(pessoa);

	}
}
