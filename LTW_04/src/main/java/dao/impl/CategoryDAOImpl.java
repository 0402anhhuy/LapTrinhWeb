package dao.impl;

import config.JPAConfig;
import dao.CategoryDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Category;

import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public List<Category> findAll() {
        EntityManager em = JPAConfig.getEntityManager();
        TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c", Category.class);
        return query.getResultList();
    }

    @Override
    public Category findById(int id) {
        EntityManager em = JPAConfig.getEntityManager();
        return em.find(Category.class, id);
    }

    @Override
    public void create(Category category) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(category);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void update(Category category) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(category);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void delete(int id) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            Category category = em.find(Category.class, id);
            if (category != null) {
                em.remove(category);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }
}
