package com.lhs.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

    private String title;

    private String contents;

    private LocalDateTime createDate;

    /**
     * @return the writer
     */
    public User getWriter() {
        return writer;
    }

    /**
     * @param writer the writer to set
     */
    public void setWriter(User writer) {
        this.writer = writer;
    }

    /**
     * @param contents the contents to set
     */
    public void setContents(String contents) {
        this.contents = contents;
    }

    /**
     * @return the contents
     */
    public String getContents() {
        return contents;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the createDate
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /** 
     * @return String
     */
    public String getFormattedCreateDate() {
        return ObjectUtils.isEmpty(createDate) ? "" : createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }
}