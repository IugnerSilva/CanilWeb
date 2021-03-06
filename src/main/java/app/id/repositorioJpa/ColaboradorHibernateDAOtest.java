/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.id.repositorioJpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import app.id.model.Colaborador;
import app.id.model.HibernateUtil;
import app.id.model.dao.ColaboradorDAO;

/**
 *
 * @author
 */
public class ColaboradorHibernateDAOtest implements ColaboradorDAO<Colaborador> {

	private EntityManager em;

	private static ColaboradorDAO instance;

        private ColaboradorHibernateDAOtest(){
           
            
        }
        
        
	public static ColaboradorDAO getInstance() {

		if (instance == null) {
			instance = new ColaboradorHibernateDAOtest();

		}
		return instance;
	}
	
	public Colaborador inserir(Colaborador colaborador) {
		em = HibernateUtil.getEntityManager();

		try {
			this.em.getTransaction().begin();
			this.em.persist(colaborador);

			this.em.getTransaction().commit();

		} catch (Exception e) {
			System.out.println("erro ao " + e);
			this.em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			this.em.close();
		}
		return colaborador;
	}
	public void alterar(Colaborador colaborador) {
		em = HibernateUtil.getEntityManager();

		try {
			this.em.getTransaction().begin();

			this.em.merge(colaborador);

			this.em.getTransaction().commit();

		} catch (Exception e) {
			System.out.println("erro ao " + e);
			this.em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			this.em.close();
		}

	}

	public Colaborador recuperar(String cpf) {
		em = HibernateUtil.getEntityManager();

		Colaborador col = null;

		try {
			col = em.find(Colaborador.class, cpf);

		} finally {
			em.close();
		}
		return col;

	}

	public void deletar(Integer idCol) {
		
		em = HibernateUtil.getEntityManager();
		
		
		Colaborador cola = em.find(Colaborador.class, idCol);
		try {
			em.getTransaction().begin();
			em.remove(cola);
			em.getTransaction().commit();
		} finally {
			em.close();
		}

	}

	public List<Colaborador> listarTodos() {
		em = HibernateUtil.getEntityManager();
		List<Colaborador> colaboradores;

		try {
			TypedQuery<Colaborador> c = em.createQuery("from Colaborador", Colaborador.class);
			colaboradores = c.getResultList();

		} catch (Exception e) {
			colaboradores = new ArrayList();

		} finally {
			em.close();
		}
		return colaboradores;
	}
	public void alterarSenha(Colaborador colaborador) {
		em = HibernateUtil.getEntityManager();

		try {
			this.em.getTransaction().begin();

			this.em.merge(colaborador);

			this.em.getTransaction().commit();

		} catch (Exception e) {
			System.out.println("erro ao " + e);
			this.em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			this.em.close();
		}

	}

}

