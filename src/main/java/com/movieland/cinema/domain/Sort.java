package com.movieland.cinema.domain;

public enum Sort {
    ASC("ASC"),
    DESC("ASC");

    public final String label;

    private Sort(String label) {
        this.label = label;
    }
}
