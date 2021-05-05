package net.xerosoft.emails;

import javax.persistence.Column;
import javax.persistence.Entity;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Email extends PanacheEntity {
    @Column(name = "from_")
    public String from;

    @Column(name = "to_")
    public String to;

    @Column(name = "body_")
    public String body;
}
