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
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.services.ServiceEvenement;





/**
 *
 * @author joujo
 */
public class modifier_1 extends Form {

    public modifier_1(Form previous) {
        setTitle("Modifier Evenement");
        setLayout(BoxLayout.y());

        TextField ID = new TextField("", "ID");
        TextField tfnom = new TextField("","Nom ");
        TextField tflieu = new TextField("","Lieu ");
        TextField tfdate = new TextField("","Date ");
        TextField tdescription = new TextField("","Description");
        TextField tfdatefin = new TextField("","DateFin ");
          Button btnValider = new Button("Edit ");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfnom.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        
                        Evenement t;
                        t = new Evenement( tfnom.getText().toString(), tflieu.getText().toString(),tdescription.getText().toString() );
                        if (ServiceEvenement.getInstance().modifierEvenement(t)) {
                            Dialog.show("Success", "Congrats!!", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Evenement", new Command("OK"));
                    }

                }

            }
        });

        addAll(ID,tfnom ,tflieu,tfdate,tdescription,tfdatefin, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
