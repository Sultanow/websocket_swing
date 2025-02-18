package com.poc.presentation;

import com.poc.model.PocModel;

public class PocPresenter {
    private PocView view;
    private PocModel model;

    public PocPresenter(PocView view, PocModel model) {
        this.view = view;
        this.model = model;

        this.view.button.addActionListener(_ -> {
            model.action();
        });
    }
}
