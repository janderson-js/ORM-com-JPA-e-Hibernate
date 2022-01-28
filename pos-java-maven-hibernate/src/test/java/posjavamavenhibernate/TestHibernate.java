package posjavamavenhibernate;

import java.util.List;

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
		pessoa.setSobreNome("Souza 3");
		pessoa.setIdade(45);
		pessoa.setEmail("janderso222n@gmail.com");
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

	@Test
	public void testDAOGenericBuscar2() {
		DAOGeneric<UsuarioPessoa> daoGeneric = new DAOGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = daoGeneric.pesquisar2(2L, UsuarioPessoa.class);

		System.out.println(pessoa);

	}

	@Test
	public void testDAOGenericAtualizar() {

		DAOGeneric<UsuarioPessoa> daoGeneric = new DAOGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = daoGeneric.pesquisar2(1L, UsuarioPessoa.class);
		
		pessoa.setIdade(55);
		pessoa.setSobreNome("Atualizado");
		
		pessoa = daoGeneric.atualizar(pessoa);

		System.out.println(pessoa);

	}

	@Test
	public void testDeletar() {
		DAOGeneric<UsuarioPessoa> daoGeneric = new DAOGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = daoGeneric.pesquisar2(6L, UsuarioPessoa.class);

		daoGeneric.deletarPorId(pessoa);

	}

	@Test
	public void testConsultarTodos() {
		DAOGeneric<UsuarioPessoa> daoGeneric = new DAOGeneric<UsuarioPessoa>();

		List<UsuarioPessoa> list = daoGeneric.listar(UsuarioPessoa.class);

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa + "\n\n");
		}

	}

	@Test
	public void testQueryList() {
		DAOGeneric<UsuarioPessoa> daoGeneric = new DAOGeneric<UsuarioPessoa>();

		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery("from UsuarioPessoa").getResultList();

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa + "\n");
		}
	}

	@Test
	public void testQueryListMaxResult() {
		DAOGeneric<UsuarioPessoa> daoGeneric = new DAOGeneric<UsuarioPessoa>();

		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery("from UsuarioPessoa order by id")
				.setMaxResults(2)
				.getResultList();

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa + "\n");
		}
	}
	
	@Test
	public void testQueryListParameter() {
		DAOGeneric<UsuarioPessoa> daoGeneric = new DAOGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(" from 					UsuarioPessoa where nome = :nome or sobrenome = :sobrenome")
					.setParameter("nome", "Janderson")
					.setParameter("sobrenome","Atualizado").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa + "\n");
		}
	}
}
