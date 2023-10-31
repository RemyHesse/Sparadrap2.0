package fr.afpa.pompey.cda22045.dao;

import java.sql.Connection;
import java.util.List;

public abstract class DAO<T> {
	
	protected Connection connect = Singleton.getInstanceDB();

	/**
	 * Méthode de création d'un objet T
	 * @param obj
	 * @return validation de la création
	 */	
	public abstract boolean create(T obj);
	
	/**
	 * Méthode de suppression d'un objet T
	 * @param obj
	 * @return validation de la suppression
	 */
	public abstract boolean delete(T obj);
	
	/**
	 * Méthode de mise à jour d'un objet T
	 * @param obj
	 * @return validation de la mise à jour
	 */
	public abstract boolean update(T obj);
	
	/**
	 * Méthode de recherche d'un objet T par son Identifiant
	 * @param pId
	 * @return l'objet T recherché
	 */
	public abstract T find(Integer pId);
	
	/**
	 * Méthode de recherche de tout les objets T
	 * @return la liste de tout les objets T
	 */
	public abstract List<T> findAll();

}
