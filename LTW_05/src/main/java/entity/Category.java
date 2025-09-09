package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
@SuppressWarnings("unused")
public class Category implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cateId;

    @Column(name = "cateName", columnDefinition = "VARCHAR(255)")
    private String cateName;

    @Column(name = "cateIcon", columnDefinition = "VARCHAR(255)")
    private String cateIcon;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Category() {}

    public Category(int cateId, String cateName, String cateIcon) {
        this.cateId = cateId;
        this.cateName = cateName;
        this.cateIcon = cateIcon;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getCateIcon() {
        return cateIcon;
    }

    public void setCateIcon(String cateIcon) {
        this.cateIcon = cateIcon;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
