package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Fahd
 */
public class indexForm extends Form {

    public indexForm() {
        this.setTitle("Home Page");
        setLayout(BoxLayout.y());
        Button btnAdd = new Button("Create");
        Button btnShow = new Button("Afficher");
        Button btnMap = new Button("Afficher Map");
        btnShow.addActionListener((evt) -> {
            new ShowForm(this).show();

        });
        btnAdd.addActionListener((evt) -> {
            new AddForm(this).show();

        });

        add(btnAdd);
        add(btnShow);
        add(btnMap);
    }

}
