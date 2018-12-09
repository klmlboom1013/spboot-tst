package com.lhs.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Answer
 */
@Entity
public class Answer {

    @Id
    @GeneratedValue
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(foreignKey=@ForeignKey(name="fk_answer_writer"))
    private User writer;
    
    @JsonBackReference
    @ManyToOne
    @JoinColumn(foreignKey=@ForeignKey(name="fk_answer_question"))
    private Question question;

    @Lob
    private String contents;

    @DateTimeFormat
    private LocalDateTime createDate;

    public Answer() {}

    public Answer(User writer, Question question, String contents, LocalDateTime localDateTime) {
        this.writer = writer;
        this.question = question;
        this.contents = contents;
        this.createDate = localDateTime;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Answer other = (Answer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Answer [id=" + id + ", writer=" + writer + ", question=" + question + ", contents=" + contents
				+ ", createDate=" + createDate + "]";
	}
	
	/** 
     * @return String
     */
    public String getFormattedCreateDate() {
        return ObjectUtils.isEmpty(createDate) ? "" : createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }

	//#################################################################
	// @Getter @Setter
	//#################################################################
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getWriter() {
        return this.writer;
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
        return this.contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public LocalDateTime getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }    
}