package com.lhs.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Question
 */
@Entity
public class Question {
    @Id
    @GeneratedValue
    private Long id;

    private String writer;

    private String title;

    private String contents;

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
     * @param writer the writer to set
     */
    public void setWriter(String writer) {
        this.writer = writer;
    }

    /**
     * @return the writer
     */
    public String getWriter() {
        return writer;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
}