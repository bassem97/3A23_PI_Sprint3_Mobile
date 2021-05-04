/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.PI_Sprint3_Mobile.entities;

import java.util.Collection;
import java.util.Date;

public class FormAnswer {

    private Integer id;
    private Date answerDate;
    private Form form;
    private User user;
    private Collection<QuestionAnswer> questionAnswerCollection;

    public FormAnswer() {
    }

    public FormAnswer(Integer id) {
        this.id = id;
    }

    public FormAnswer(Integer id, Date answerDate) {
        this.id = id;
        this.answerDate = answerDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Date answerDate) {
        this.answerDate = answerDate;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<QuestionAnswer> getQuestionAnswerCollection() {
        return questionAnswerCollection;
    }

    public void setQuestionAnswerCollection(Collection<QuestionAnswer> questionAnswerCollection) {
        this.questionAnswerCollection = questionAnswerCollection;
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
        if (!(object instanceof FormAnswer)) {
            return false;
        }
        FormAnswer other = (FormAnswer) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "entities.FormAnswer[ id=" + id + " ]";
    }
}
