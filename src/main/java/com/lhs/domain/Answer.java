package com.lhs.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Answer
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Answer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @JsonProperty
    private Long id;

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

    @DateTimeFormat
    @JsonProperty
    private LocalDateTime createDate;

    public Answer() {}

    public Answer(User writer, Question question, String contents, LocalDateTime localDateTime) {
        this.writer = writer;
        this.question = question;
        this.contents = contents;
        this.createDate = localDateTime;
    }
    
    public boolean isSammeWriter(User loginUser) {
    	return loginUser.equals(this.writer);
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
	
	//#################################################################
	// @Getter @Setter
	//#################################################################
    public String getFormattedCreateDate() {
        return ObjectUtils.isEmpty(createDate) ? "" : createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }

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

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}    
}