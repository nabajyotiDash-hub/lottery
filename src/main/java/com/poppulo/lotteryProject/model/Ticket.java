package com.poppulo.lotteryProject.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Line> lines;

    private boolean checked;

    public Ticket(){
        this.lines = new ArrayList<>();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Line> getLines() {
        Collections.sort(lines);
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", lines=" + lines +
                ", checked=" + checked +
                '}';
    }
}
