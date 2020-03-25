package at.htl.jpademo.business;


import at.htl.jpademo.model.Loan;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;

@ApplicationScoped
public class LoanFacade {

    @Inject
    EntityManager em;


    public List<Loan> get() {
        TypedQuery<Loan> entities = em.createNamedQuery("Loan.findAll",Loan.class);
        return  entities.setHint("javax.persistence.fetchgraph",em.getEntityGraph("loan-entity-graph")).getResultList();
    }

    public Loan get(long id) {
        EntityGraph eg = em.getEntityGraph("loan-entity-graph");
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.loadgraph", eg);
        return  em.find(Loan.class,id,properties);
    }

    public void remove(Loan entity) {
        entity= em.merge(entity);
        em.remove(entity);
    }

    public Loan save(Loan entity) {
        entity=em.merge(entity);
        em.flush();
        em.refresh(entity);
        return entity;
    }

    public Loan update(Loan entity) {
        entity = em.merge(entity);
        em.flush();
        em.refresh(entity);
        return entity;
    }
}

