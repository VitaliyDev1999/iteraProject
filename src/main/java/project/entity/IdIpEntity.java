package project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ip_address")
public class IdIpEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name = "ip", unique = true)
    private String ip;

    @OneToMany(mappedBy="ip")
    @JsonIgnore
    private List<HistoryDbEntity> historyDbEntities;

    public IdIpEntity() {
    }

    public IdIpEntity(String ip) {
        this.ip = ip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<HistoryDbEntity> getHistoryDbEntities() {
        return historyDbEntities;
    }

    public void setHistoryDbEntities(List<HistoryDbEntity> historyDbEntities) {
        this.historyDbEntities = historyDbEntities;
    }

    @Override
    public String toString() {
        return "IdIpEntity{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                '}';
    }
}

