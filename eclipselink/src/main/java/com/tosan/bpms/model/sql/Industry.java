package com.tosan.bpms.model.sql;


import com.jpql.api.annotations.Like;
import com.jpql.api.interfaces.Model;

import javax.persistence.*;


/**
 * Created by kasra.haghpanah on 27/11/2016.
 */
// نوع صنعت
@Entity
@Table(name = "Industry")
@NamedQueries(
        {
                //@NamedQuery(name = "getAllUser", query = "SELECT u From com.tosan.loan.model.Reception u"),
                //@NamedQuery(name = "getByIdUser", query = "SELECT u From com.tosan.loan.model.Reception u WHERE u.id =:id"),
                // @NamedQuery(name = "getByUserNameUser", query = "SELECT u From com.tosan.loan.model.Reception u WHERE u.username =:username")
        }
)
public class Industry implements Model {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(generator = "system-uuid")
    @Like
//  @GenericGenerator(name = "uuid", strategy = "uuid2")
    String industryId;


//    @ManyToOne(
//            mappedBy = "industry" ,
//            cascade = CascadeType.ALL ,
//            fetch = FetchType.LAZY
//    )
//    List<Article> articles;


    @Column(name = "faTitle")
    String faTitle;


    @Column(name = "enTitle")
    String enTitle;


    //@Version
    @Column(name = "VERSION")
    private int version;


    public Industry() {
    }


    public String getIndustryId() {
        return industryId;
    }

    public void setIndustryId(String industryId) {
        this.industryId = industryId;
    }

    public String getFaTitle() {
        return faTitle;
    }

    public void setFaTitle(String faTitle) {
        this.faTitle = faTitle;
    }

    public String getEnTitle() {
        return enTitle;
    }

    public void setEnTitle(String enTitle) {
        this.enTitle = enTitle;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

//    public List<Article> getArticles() {
//        return articles;
//    }
//
//    public void setArticles(List<Article> articles) {
//        this.articles = articles;
//    }
}
