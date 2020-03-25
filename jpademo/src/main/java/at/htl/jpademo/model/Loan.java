package at.htl.jpademo.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@NamedQueries({
        @NamedQuery(name = "Loan.findById",query = "select l from Loan l where l.Id= :Id"),
        @NamedQuery(name = "Loan.findAll",query = "select l from Loan l")
})
@NamedEntityGraph(
        name = "loan-entity-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "Id"),
                @NamedAttributeNode(value = "person",subgraph = "personGraph")
        }
        ,subgraphs = {
        @NamedSubgraph(
                name = "personGraph",
                attributeNodes = {
                        @NamedAttributeNode("Id"),
                        @NamedAttributeNode("name")
                }
        )
}
)
public class Loan implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @ManyToOne(fetch = LAZY)
    private Person person;
    @ManyToMany(fetch = LAZY)
    private List<Exemplar> exemplars;
    @Basic(fetch = LAZY)
    private LocalDate doT;
    @Basic(fetch = LAZY)
    private LocalDate doAR;
    @Basic(fetch = LAZY)
    private LocalDate doR;

    public Loan(Person person, List<Exemplar> exemplars, LocalDate doT, LocalDate doR) {
        this.person = person;
        this.exemplars = exemplars;
        this.doT = doT;
        this.doR = doR;
    }

    public Loan() {
    }

    //region getter and setter
    public Long getId() {
        return Id;
    }

    private void setId(Long id) {
        Id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public LocalDate getDoT() {
        return doT;
    }

    public void setDoT(LocalDate doT) {
        this.doT = doT;
    }

    public LocalDate getDoR() {
        return doR;
    }

    public void setDoR(LocalDate doR) {
        this.doR = doR;
    }

    public List<Exemplar> getExemplars() {
        return exemplars;
    }

    public void setExemplar(List<Exemplar> exemplars) {
        this.exemplars = exemplars;
    }

    public LocalDate getDoAR() {
        return doAR;
    }

    public void setDoAR(LocalDate doAR) {
        this.doAR = doAR;
    }

    public  void addExemplar(Exemplar exemplar){
        this.exemplars.add(exemplar);
        exemplar.addLoan(this);
    }
    //endregion


    @Override
    public String toString() {
        return "Loan{" +
                "Id=" + Id +
                ", person=" + person.toString() +
                ", exemplars=" + exemplars.toString() +
                ", doT=" + doT +
                ", doAR=" + doAR +
                ", doR=" + doR +
                '}';
    }
}
