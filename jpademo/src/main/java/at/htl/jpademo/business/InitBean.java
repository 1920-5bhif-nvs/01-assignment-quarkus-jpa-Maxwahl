package at.htl.jpademo.business;

import at.htl.jpademo.model.*;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class InitBean {

    @Inject
    EntityManager em;

    @Transactional
    void init(@Observes StartupEvent event){
        System.err.print("******** hello");
        CD cd =new CD("test",9.11,"classic","mozart",123.1);
        em.persist(cd);
        Person p = new Person("Meier");
        Person p2 = new Person("Hofer");
        em.persist(p);
        em.persist(p2);
        Exemplar e = new Exemplar(cd, Weariness.undamaged);
        em.persist(e);
        List<Exemplar> exemplars = new ArrayList<>();
        exemplars.add(e);
        Loan l = new Loan(p,exemplars, LocalDate.now(),LocalDate.now());
        em.persist(l);
    }
}
