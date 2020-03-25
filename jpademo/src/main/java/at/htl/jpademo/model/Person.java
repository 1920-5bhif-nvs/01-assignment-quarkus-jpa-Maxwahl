package at.htl.jpademo.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Person.findById",query = "select p from Person p where p.Id= :Id"),
        @NamedQuery(name = "Person.findAll",query = "select p from Person p")
})
public class Person {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    String name;
    @OneToMany(mappedBy = "person",fetch = FetchType.LAZY,cascade = {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE})
            @JsonbTransient
    List<Loan> loans;
    public Person(String name) {
        this.name = name;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }


    @Override
    public String toString() {
        return "Person{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                '}';
    }
}
