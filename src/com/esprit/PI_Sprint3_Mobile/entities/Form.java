/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.PI_Sprint3_Mobile.entities;

import java.util.Collection;
import java.util.Date;

public class Form {

    private Integer id;
    private String title;
    private String description;
    private boolean isClosed;
    private boolean isArchived;
    private Date creationDate;
    private Date updateDate;
    private Date closeDate;
    private Date openDate;
    private Boolean areMailsSent;
    private Boolean isOpen;
    private Event event;
    private Collection<FormAnswer> formAnswerCollection;
    private Collection<FormQuestion> formQuestionCollection;

    public Form() {
    }

    public Form(Integer id) {
        this.id = id;
    }

    public Form(Integer id, String title, String description, boolean isClosed, boolean isArchived, Date creationDate, Date updateDate, Date openDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isClosed = isClosed;
        this.isArchived = isArchived;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.openDate = openDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public boolean getIsArchived() {
        return isArchived;
    }

    public void setIsArchived(boolean isArchived) {
        this.isArchived = isArchived;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Boolean getAreMailsSent() {
        return areMailsSent;
    }

    public void setAreMailsSent(Boolean areMailsSent) {
        this.areMailsSent = areMailsSent;
    }

    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Collection<FormAnswer> getFormAnswerCollection() {
        return formAnswerCollection;
    }

    public void setFormAnswerCollection(Collection<FormAnswer> formAnswerCollection) {
        this.formAnswerCollection = formAnswerCollection;
    }

    public Collection<FormQuestion> getFormQuestionCollection() {
        return formQuestionCollection;
    }

    public void setFormQuestionCollection(Collection<FormQuestion> formQuestionCollection) {
        this.formQuestionCollection = formQuestionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Form)) {
            return false;
        }
        Form other = (Form) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "entities.Form[ id=" + id + " ]";
    }

}
