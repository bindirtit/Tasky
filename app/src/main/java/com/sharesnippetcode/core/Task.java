package com.sharesnippetcode.core;

public class Task {
    public Task() {

    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    private int _id;

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getNotes() {
        return _notes;
    }

    public void setNotes(String notes) {
        _notes = notes;
    }

    public String getDone() {
        return _done;
    }

    public void setDone(String done) {
        _done = done;
    }

    private String _name;
    private String _notes;
    private String _done;
}
