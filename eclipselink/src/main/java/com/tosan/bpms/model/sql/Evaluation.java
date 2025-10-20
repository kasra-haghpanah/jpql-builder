package com.tosan.bpms.model.sql;

import com.jpql.api.interfaces.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by kasra.haghpanah on 30/11/2018.
 */
@Entity
@Table(name = "Evaluation")
@NamedQueries(
        {
                //@NamedQuery(name = "getAllUser", query = "SELECT u From com.tosan.loan.model.Reception u"),
                //@NamedQuery(name = "getByIdUser", query = "SELECT u From com.tosan.loan.model.Reception u WHERE u.id =:id"),
                // @NamedQuery(name = "getByUserNameUser", query = "SELECT u From com.tosan.loan.model.Reception u WHERE u.username =:username")
        }
)
public class Evaluation implements Model {

    public Evaluation() {
    }

    @Id
    @Column(name = "evaluationId", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer evaluationId;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    @JoinColumns(
            {
                    @JoinColumn(
                            //name = "fileMetaDataId1",// (user_username) for this table
                            //referencedColumnName = "l4610flmtdataid",
                            foreignKey = @ForeignKey(name = "FK_FILE1_Location")
                    )
            }
    )
    private File file1;


    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    @JoinColumn(
            name = "fileMetaDataId2",// (user_username) for this table
            referencedColumnName = "l4610flmtdataid",
            foreignKey = @ForeignKey(name = "FK_FILE2_Location")
    )
    private File file2;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "receptionNo",// (user_username) for this table
            referencedColumnName = "receptionNo",
            foreignKey = @ForeignKey(name = "FK_reception_Eval")
    )
    private Reception reception;


    public Integer getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Integer evaluationId) {
        this.evaluationId = evaluationId;
    }

    public Reception getReception() {
        return reception;
    }

    public void setReception(Reception reception) {
        this.reception = reception;
    }

    public File getFile1() {
        return file1;
    }

    public void setFile1(File file1) {
        this.file1 = file1;
    }

    public File getFile2() {
        return file2;
    }

    public void setFile2(File file2) {
        this.file2 = file2;
    }
}



