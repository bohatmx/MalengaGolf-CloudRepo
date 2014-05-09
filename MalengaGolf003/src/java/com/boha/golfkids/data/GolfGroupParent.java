/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.golfkids.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "golfGroupParent")
@NamedQueries({
    @NamedQuery(name = "GolfGroupParent.deleteByGolfGroup", 
            query = "delete FROM GolfGroupParent g "
                    + "where g.golfGroup.golfGroupID = :id")

})
public class GolfGroupParent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "golfGroupParentID")
    private Integer golfGroupParentID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateRegistered")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistered;
    @JoinColumn(name = "golfGroupID", referencedColumnName = "golfGroupID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private GolfGroup golfGroup;
    @JoinColumn(name = "parentID", referencedColumnName = "parentID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Parent parent;

    public GolfGroupParent() {
    }

    public GolfGroupParent(Integer golfGroupParentID) {
        this.golfGroupParentID = golfGroupParentID;
    }

    public GolfGroupParent(Integer golfGroupParentID, Date dateRegistered) {
        this.golfGroupParentID = golfGroupParentID;
        this.dateRegistered = dateRegistered;
    }

    public Integer getGolfGroupParentID() {
        return golfGroupParentID;
    }

    public void setGolfGroupParentID(Integer golfGroupParentID) {
        this.golfGroupParentID = golfGroupParentID;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public GolfGroup getGolfGroup() {
        return golfGroup;
    }

    public void setGolfGroup(GolfGroup golfGroup) {
        this.golfGroup = golfGroup;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (golfGroupParentID != null ? golfGroupParentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GolfGroupParent)) {
            return false;
        }
        GolfGroupParent other = (GolfGroupParent) object;
        if ((this.golfGroupParentID == null && other.golfGroupParentID != null) || (this.golfGroupParentID != null && !this.golfGroupParentID.equals(other.golfGroupParentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.golfkids.data.GolfGroupParent[ golfGroupParentID=" + golfGroupParentID + " ]";
    }
    
}
