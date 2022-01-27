package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import posjavamavenhibernate.HibernateUtil;

public class DAOGeneric<E> {

	private EntityManager entityManager = HibernateUtil.getEntityManager();

	public void salvar(E entidade) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(entidade);
		transaction.commit();
	}

	public E pesquisar(E entidade) {

		Object id = HibernateUtil.getPrimaryKey(entidade);

		E e = (E) entityManager.find(entidade.getClass(), id);

		return e;

	}

	public E pesquisar2(Long id, Class<E> entidade) {

		E e;
		e = (E) entityManager.find(entidade, id);
		return e;

	}

	public E atualizar(E entidade) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		E atualizado = entityManager.merge(entidade);
		transaction.commit();

		return atualizado;
	}

	public void deletarPorId(E entidade) {
		Object id = HibernateUtil.getPrimaryKey(entidade);

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		entityManager.createNativeQuery(
				"delete from " + entidade.getClass().getSimpleName().toLowerCase() + " where id=" + id).executeUpdate();
		transaction.commit();
	}
}
