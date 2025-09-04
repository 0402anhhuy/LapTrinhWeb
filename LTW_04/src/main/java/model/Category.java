package model;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cateid")
    private Integer cateid;

    @Column(name = "catename")
    private String catename;

    @Column(name = "icon")
    private String icon;

    // Getters & Setters
    public Integer getCateid() {
        return cateid;
    }

    public void setCateid(Integer cateid) {
        this.cateid = cateid;
    }

    public String getCatename() {
        return catename;
    }

    public void setCatename(String catename) {
        this.catename = catename;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
