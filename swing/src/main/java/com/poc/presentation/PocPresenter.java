package com.poc.presentation;

import com.poc.ValueModel;
import com.poc.model.ModelProperties;
import com.poc.model.PocModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;

public class PocPresenter {
    private PocView view;
    private PocModel model;

    public PocPresenter(PocView view, PocModel model) {
        this.view = view;
        this.model = model;

        this.view.button.addActionListener(_ -> {
            model.action();
        });

        initializeBindings();
    }

    private void bind(JTextComponent source, ModelProperties prop) {
        source.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    var content = e.getDocument().getText(0, e.getDocument().getLength());
                    var model = (ValueModel<String>) PocPresenter.this.model.model.get(prop);
                    model.setField(content);
                    System.out.println(e.getDocument().getText(0, e.getDocument().getLength()));
                } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    var content = e.getDocument().getText(0, e.getDocument().getLength());
                    var model = (ValueModel<String>) PocPresenter.this.model.model.get(prop);
                    model.setField(content);
                    System.out.println(e.getDocument().getText(0, e.getDocument().getLength()));
                } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }


    private void bind(JRadioButton source, ModelProperties prop) {
        source.addChangeListener(evt -> {
            var model = (ValueModel<Boolean>) PocPresenter.this.model.model.get(prop);
            model.setField(source.isSelected());
            System.out.println(source.isSelected());
        });
    }


    private void initializeBindings() {
        bind(view.textArea, ModelProperties.TEXT_AREA);
        bind(view.male, ModelProperties.MALE);
        bind(view.female, ModelProperties.FEMALE);
        bind(view.diverse, ModelProperties.DIVERSE);
    }
}
