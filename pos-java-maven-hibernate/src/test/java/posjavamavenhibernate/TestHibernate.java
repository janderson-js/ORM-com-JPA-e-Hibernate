package posjavamavenhibernate;

import java.util.List;

import org.junit.Test;

import dao.DAOGeneric;
import model.TelefoneUsuario;
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

		List<UsuarioPessoa> list = daoGeneric.getEntityManager()
				.createQuery("from UsuarioPessoa").getResultList();

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa + "\n");
		}
	}

	@Test
	public void testQueryListMaxResult() {
		DAOGeneric<UsuarioPessoa> daoGeneric = new DAOGeneric<UsuarioPessoa>();

		List<UsuarioPessoa> list = daoGeneric.getEntityManager()
				.createQuery("from UsuarioPessoa order by id").setMaxResults(2)
				.getResultList();

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa + "\n");
		}
	}

	@Test
	public void testQueryListParameter() {
		DAOGeneric<UsuarioPessoa> daoGeneric = new DAOGeneric<UsuarioPessoa>();

		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(
				" from UsuarioPessoa where nome = :nome or sobrenome = :sobrenome")
				.setParameter("nome", "Janderson")
				.setParameter("sobrenome", "Atualizado").getResultList();

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa + "\n");
		}
	}

	@Test
	public void testSomaIdade() {
		DAOGeneric<UsuarioPessoa> daoGeneric = new DAOGeneric<UsuarioPessoa>();

		Long somaIdade = (Long) daoGeneric.getEntityManager().createQuery("Select sum(u.idade) from UsuarioPessoa u").getSingleResult();
		
		System.out.println(somaIdade);

	}
	
	@Test
	public void testMediaIdade() {
		DAOGeneric<UsuarioPessoa> daoGeneric = new DAOGeneric<UsuarioPessoa>();

		Double somaIdade = (Double) daoGeneric.getEntityManager().createQuery("Select avg(u.idade) from UsuarioPessoa u").getSingleResult();
		
		System.out.println(somaIdade);

	}
	
	@Test
	public void testConsultarTodosUser() {
		DAOGeneric<UsuarioPessoa> daoGeneric = new DAOGeneric<UsuarioPessoa>();

		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.todos").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}

	}
	
	@Test
	public void testBuscaPorNome() {
		DAOGeneric<UsuarioPessoa> daoGeneric = new DAOGeneric<UsuarioPessoa>();

		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.buscarPorNome").setParameter("nome", "janderson").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}

	}
	
	@Test
	public void testGravaTelefone() {
		DAOGeneric daoGeneric = new DAOGeneric();

		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar2(1L,UsuarioPessoa.class);
		
		TelefoneUsuario teleUsuario = new TelefoneUsuario();
		
		teleUsuario.setTipo("Celular");
		teleUsuario.setNumero("(61) 4 4444-4444");
		teleUsuario.setPessoa(pessoa);
		
		daoGeneric.salvar(teleUsuario);

	}
	
	@Test
	public void testConsultaTelefones() {
		DAOGeneric daoGeneric = new DAOGeneric();

		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar2(1L,UsuarioPessoa.class);
		
		for (TelefoneUsuario telefoneUsuario : pessoa.getTelefoneUsuarios()) {
			System.out.println(" Nome: "+ pessoa.getNome()+" "+pessoa.getSobreNome() +"\n Tipo: "+ telefoneUsuario.getTipo()+"\n Número: " + telefoneUsuario.getNumero()+"\n");
		}

	}
}
