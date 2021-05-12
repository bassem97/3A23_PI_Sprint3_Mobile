/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.PI_Sprint3_Mobile.entities;

public class QuestionAnswer {

    private Integer id;
    private String question;
    private String answer;
    private int number;
    private FormAnswer formAnswer;

    public QuestionAnswer() {
    }

    public QuestionAnswer(Integer id) {
        this.id = id;
    }

    public QuestionAnswer(Integer id, String question, int number) {
        this.id = id;
        this.question = question;
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer.replaceAll(":,:", ", ");
    }

    public String getOriginalAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public FormAnswer getFormAnswer() {
        return formAnswer;
    }

    public void setFormAnswer(FormAnswer formAnswer) {
        this.formAnswer = formAnswer;
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
        if (!(object instanceof QuestionAnswer)) {
            return false;
        }
        QuestionAnswer other = (QuestionAnswer) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "entities.QuestionAnswer[ id=" + id + " ]";
    }

}
