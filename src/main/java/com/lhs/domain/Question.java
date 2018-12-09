package com.lhs.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.ObjectUtils;

/**
 * Question
 */
@Entity
public class Question {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="fk_question_writer"))
    private User writer;
    
    @OneToMany(mappedBy="question")
    @OrderBy("id ASC")
    private List<Answer> answers;

    private String title;

    @Lob
    private String contents;

    @DateTimeFormat
    private LocalDateTime createDate;

    
    public Question() {}
    
    public Question(User writer, String title, String contents, LocalDateTime createDate) {
		super();
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.createDate= createDate;
	}

    
	@Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", writer='" + getWriter() + "'" +
            ", title='" + getTitle() + "'" +
            ", contents='" + getContents() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            "}";
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
		Question other = (Question) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void update (Question question) {
        this.title = question.getTitle();
        this.contents = question.getContents();
    }

    public boolean isSameWriter(User user) {
        return this.writer.equals(user);
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

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
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

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
}