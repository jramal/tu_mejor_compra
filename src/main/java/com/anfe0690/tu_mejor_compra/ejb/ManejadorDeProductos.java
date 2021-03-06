package com.anfe0690.tu_mejor_compra.ejb;

import com.anfe0690.tu_mejor_compra.entity.Producto;
import com.anfe0690.tu_mejor_compra.entity.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
@LocalBean
public class ManejadorDeProductos implements Serializable{

	private static final Logger logger = LoggerFactory.getLogger(ManejadorDeProductos.class);
	@PersistenceContext(name = "tuMejorCompra")
	private EntityManager em;

	@PostConstruct
	public void postConstruct() {
		logger.trace("postConstruct");
	}

	@PreDestroy
	public void preDestroy() {
		logger.trace("preDestroy");
	}

	public void persistProducto(Producto p) {
		em.persist(p);
	}

	public Producto obtenerProductoPorId(long id) {
		return em.find(Producto.class, id);
	}

	public boolean existeProductoConNombre(String nombreProducto) throws NoResultException {
		try {
			em.createQuery("SELECT p FROM Producto p WHERE p.nombre = :nombreProducto",
					Producto.class).setParameter("nombreProducto", nombreProducto).getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}

	public List<Producto> obtenerTodosLosProductos() {
		return em.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
	}

	public void removerProducto(Producto producto) {
		em.remove(em.find(Producto.class, producto.getId()));
	}

	public int removerTodosLosProductos() {
		return em.createQuery("DELETE FROM Producto p").executeUpdate();
	}

	public void mergeProducto(Producto producto) {
		em.merge(producto);
	}

}
