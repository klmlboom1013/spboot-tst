package com.lhs.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Question
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Question extends AbstractEntity {
	
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="fk_question_writer"))
    @JsonProperty
    private User writer;
    
    @OneToMany(mappedBy="question")
    @OrderBy("id DESC")
    @JsonProperty
    private List<Answer> answers;

    @JsonProperty
    private String title;

    @Lob
    @JsonProperty
    private String contents;
    
    @JsonProperty
    private Integer countAnswer =0;

    public Question() {}
    
    public Question(User writer, String title, String contents) {
		this.writer = writer;
		this.title = title;
		this.contents = contents;
	}

    
    public void addAnswer() {
    	countAnswer++;
    }
    
    public void deleteAnswer() {
    	countAnswer--;
    }
    
	public void update (Question question) {
        this.title = question.getTitle();
        this.contents = question.getContents();
    }

    public boolean isSameWriter(User user) {
        return this.writer.equals(user);
    }

    @Override
	public String toString() {
		return "Question [" + super.toString() + "writer=" + writer + ", answers=" + answers + ", title=" + title + ", contents=" + contents
				+ ", countAnswer=" + countAnswer + "]";
	}
    
	// #################################################################
	// @Getter @Setter
	// #################################################################
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

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Integer getCountAnswer() {
		return countAnswer;
	}

	public void setCountAnswer(Integer countAnswer) {
		this.countAnswer = countAnswer;
	}
	
   
}