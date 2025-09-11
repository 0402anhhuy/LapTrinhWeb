package dao.impl;

import config.JPAConfig;
import dao.UserProfileDao;
import enity.UserProfile;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class UserProfileDaoImpl implements UserProfileDao {
    @Override
    public void createUserProfile(UserProfile userProfile) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans =  em.getTransaction();
        try{
            trans.begin();
            em.persist(userProfile);
            trans.commit();
        }catch(Exception e){
            trans.rollback();
        }finally{
            em.close();
        }
    }
}
