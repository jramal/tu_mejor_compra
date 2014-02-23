package com.anfe0690.tu_mejor_compra;

import java.io.Serializable;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
@LocalBean
public class ManejadorDeUsuarios implements Serializable {

	private static final long serialVersionUID = 1L;
	@PersistenceContext(name = "tuMejorCompra")
	private EntityManager entityManager;

	@PostConstruct
	private void postConstruct() {
		Logger.getLogger(ManejadorDeUsuarios.class.getName()).log(Level.INFO, "postConstruct");
	}

	@PreDestroy
	private void preDestroy() {
		Logger.getLogger(ManejadorDeUsuarios.class.getName()).log(Level.INFO, "preDestroy");
	}

	public void guardarUsuario(Usuario usuario) {
		entityManager.persist(usuario);
	}

	public void mergeUsuario(Usuario usuario) {
		entityManager.merge(usuario);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Usuario buscarUsuarioPorNombre(String nombre) throws IllegalArgumentException {
		try {
			return entityManager.find(Usuario.class, nombre.toLowerCase());
		} catch (IllegalArgumentException e) {
			throw e;
		}
	}

	public void removeUsuario(Usuario usuario) {
		entityManager.remove(entityManager.find(Usuario.class, usuario.getNombre()));
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Usuario getUsuarioPadreDeProducto(long productoId) {
		TypedQuery<Usuario> typedQuery =
				entityManager.createQuery("SELECT u FROM Usuario u JOIN u.productos p WHERE p.id = :productoId", Usuario.class)
						.setParameter("productoId", productoId);
		return typedQuery.getSingleResult();
	}

}
