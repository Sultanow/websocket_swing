package com.poc.presentation;

import java.awt.*;

import javax.swing.*;

public class PocView {
    protected JFrame frame = new JFrame("Allegro");
    protected JTextArea textArea = new JTextArea();
    protected JTextField tf_name = new JTextField();
    protected JTextField tf_first = new JTextField();
    protected JTextField tf_dob = new JTextField();
    protected JTextField tf_zip = new JTextField();
    protected JTextField tf_ort = new JTextField();
    protected JTextField tf_street = new JTextField();
    protected JTextField tf_hausnr = new JTextField();
    protected JTextField tf_ze_iban = new JTextField();
    protected JTextField tf_ze_bic = new JTextField();
    protected JTextField tf_ze_valid_from = new JTextField();

    protected JRadioButton rb_female = new JRadioButton("Weiblich");
    protected JRadioButton rb_male = new JRadioButton("Männlich");
    protected JRadioButton rb_diverse = new JRadioButton("Divers");
    protected ButtonGroup bg_gender = new ButtonGroup();

    protected JButton button = new JButton("Anordnen");

    public PocView() {
        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.ipady = 4;
        c.insets = new Insets(4, 4, 4, 4);
        c.anchor = GridBagConstraints.FIRST_LINE_END;

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Vorname"), c);

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(tf_first, c);

        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Name"), c);

        c.gridx = 3;
        c.gridy = 0;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(tf_name, c);

        c.gridx = 4;
        c.gridy = 0;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Geburtsdatum"), c);

        c.gridx = 5;
        c.gridy = 0;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(tf_dob, c);

        bg_gender.add(rb_female);
        bg_gender.add(rb_male);
        bg_gender.add(rb_diverse);
        rb_female.setSelected(true);

        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0;
        c.fill = GridBagConstraints.CENTER;
        panel.add(new JLabel("Geschlecht"), c);

        JPanel genderPanel = new JPanel();
        genderPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        genderPanel.add(rb_female);
        genderPanel.add(rb_male);
        genderPanel.add(rb_diverse);

        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1;
        c.gridwidth = 5;
        c.anchor = GridBagConstraints.WEST;
        panel.add(genderPanel, c);

        // Reset grid layout
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.FIRST_LINE_END;

        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Strasse"), c);

        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(tf_street, c);

        c.gridx = 2;
        c.gridy = 2;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("PLZ"), c);

        c.gridx = 3;
        c.gridy = 2;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(tf_zip, c);

        c.gridx = 4;
        c.gridy = 2;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Ort"), c);

        c.gridx = 5;
        c.gridy = 2;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(tf_ort, c);

        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("IBAN"), c);

        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(tf_ze_iban, c);

        c.gridx = 2;
        c.gridy = 3;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("BIC"), c);

        c.gridx = 3;
        c.gridy = 3;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(tf_ze_bic, c);

        c.gridx = 4;
        c.gridy = 3;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Gültig ab"), c);

        c.gridx = 5;
        c.gridy = 3;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(tf_ze_valid_from, c);

        c.gridx = 0;
        c.gridy = 4;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("RT"), c);

        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 6;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        textArea.setPreferredSize(new Dimension(200, 400));
        textArea.setBorder(BorderFactory.createEtchedBorder());
        panel.add(textArea);
        panel.add(textArea, c);

        c.gridx = 1;
        c.gridy = 5;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;

        panel.add(button, c);

        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 650);
        frame.setVisible(true);
    }
}
