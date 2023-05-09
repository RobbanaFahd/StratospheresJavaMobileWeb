/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Annonce;
import com.mycompany.myapp.services.ServiceAnnonce;

/**
 *
 * @author Amoulette
 */
public class modifier extends Form {

    public modifier(Form previous) {
        setTitle("Modifier Annonce");
        setLayout(BoxLayout.y());

        TextField ID = new TextField("", "ID");
        TextField tnom = new TextField("", "Nom");
        TextField tdescreption = new TextField("", "Descriprtion");
        TextField ttitre = new TextField("", "Titre");
        TextField tetat = new TextField("", "Etat");
        TextField tcategorie = new TextField("", "Categorie");
        Button btnValider = new Button("Edit ");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tnom.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        float id = Float.parseFloat(ID.getText().toString());
                        Annonce t;
                        t = new Annonce((int) id, tnom.getText().toString(), tdescreption.getText().toString(), ttitre.getText().toString(), tetat.getText().toString(), tcategorie.getText().toString());
                        if (ServiceAnnonce.getInstance().modifierAnnonce(t)) {
                            Dialog.show("Success", "Congrats!!", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Annonce", new Command("OK"));
                    }

                }

            }
        });

        addAll(ID, tnom, tdescreption, ttitre, tetat, tcategorie, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
