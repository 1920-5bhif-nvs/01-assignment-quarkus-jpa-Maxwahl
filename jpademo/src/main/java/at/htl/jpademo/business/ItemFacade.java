package at.htl.jpademo.business;


import at.htl.jpademo.model.Item;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class ItemFacade {

    @PersistenceContext
    EntityManager em;


    public List<Item> get() {
        TypedQuery<Item> entities = em.createNamedQuery("Item.findAll",Item.class);
        return  entities.getResultList();
    }

    public Item get(long id) {
        TypedQuery<Item> entities = em.createNamedQuery("Item.findById",Item.class);
        entities.setParameter("Id",id);
        return  entities.getSingleResult();
    }

    public void remove(Item entity) {
        entity= em.merge(entity);
        em.remove(entity);
    }

    public Item save(Item entity) {
        entity=em.merge(entity);
        em.flush();
        em.refresh(entity);
        return entity;
    }

    public Item update(Item entity) {
        entity = em.merge(entity);
        em.flush();
        em.refresh(entity);
        return entity;
    }
}
