package project.entity;

import javax.persistence.*;

@Entity
public class HistoryDbEntity {

    @Column(unique = true, name="ip")
    private String ip;

    @Column(name="type")
    private String type;

    @Column(name="bet")
    private String bet;

    @Column(name="result")
    private String result;

    @Column(name="win")
    private  char win;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

}
