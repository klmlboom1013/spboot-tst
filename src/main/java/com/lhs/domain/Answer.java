package com.lhs.domain;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Answer
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Answer  extends AbstractEntity {
	
    @ManyToOne
    @JoinColumn(foreignKey=@ForeignKey(name="fk_answer_writer"))
    @JsonProperty
    private User writer;
    
    @ManyToOne
    @JoinColumn(foreignKey=@ForeignKey(name="fk_answer_question"))
    @JsonProperty
    private Question question;

    @Lob
    @JsonProperty
    private String contents;

    public Answer() {}

    public Answer(User writer, Question question, String contents) {
        this.writer = writer;
        this.question = question;
        this.contents = contents;
    }
    
    public boolean isSammeWriter(User loginUser) {
    	return loginUser.equals(this.writer);
    }
	
	
	
	@Override
	public String toString() {
		return "Answer [" + super.toString() + "writer=" + writer + ", question=" + question + ", contents=" + contents + "]";
	}

	//#################################################################
	// @Getter @Setter
	//#################################################################
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getWriter() {
		return writer;
	}

	public void setWriter(User writer) {
		this.writer = writer;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}    
}