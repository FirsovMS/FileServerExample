package com.company.services.dbService.dataSets;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "modules")
public class ModulesDataSet implements Serializable {
    private static final long serialVersionUID = 12389235875239525L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name",unique = true, nullable = false)
    private String name;

    @Column(name = "crc",nullable = false)
    private long crc;

    @SuppressWarnings("UnusedDeclaration")
    public ModulesDataSet() {}

    public ModulesDataSet(long id, String fname, long crc){
        this.setId(id);
        this.setName(fname);
        this.setCrc(crc);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String fname) {
        this.name = fname;
    }

    public long getCrc() {
        return crc;
    }

    public void setCrc(long crc) {
        this.crc = crc;
    }
}
