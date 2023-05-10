/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Amoulette
 */
public class ListReclamation extends Form {
        ServiceReclamation sp = new ServiceReclamation();

    public ListReclamation(Form previous) {
        add(new InfiniteProgress());
        Display.getInstance().scheduleBackgroundTask(() -> {

            Display.getInstance().callSerially(() -> {

                removeAll();
                setLayout(BoxLayout.y());
    
                Style s = UIManager.getInstance().getComponentStyle("Description");

                Form hi = new Form("Toolbar", new BoxLayout(BoxLayout.Y_AXIS));
                     Button refreshButton = new Button();
                FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_REFRESH, UIManager.getInstance().getComponentStyle("Button"));
                refreshButton.setIcon(icon1);

                refreshButton.addActionListener(e -> new ListReclamation(previous).show());
                add(refreshButton);
                Button gui_Button_12 = new Button();
                gui_Button_12.setText("search");
                gui_Button_12.setUIID("Label");
                gui_Button_12.setName("Button_12");
                FontImage.setMaterialIcon(gui_Button_12, FontImage.MATERIAL_CIRCLE);
                TextField searchField = new TextField("", "Toolbar Search"); // <1>
                searchField.getHintLabel().setUIID("Title");
                searchField.setUIID("Title");
                searchField.getAllStyles().setAlignment(Component.LEFT);
                hi.getToolbar().setTitleComponent(searchField);

                List<Reclamation> listea = sp.getAllReclamations();
                for (Reclamation a : listea) {

                    MultiButton m = new MultiButton();

                     m.setTextLine1("Nom: " + a.getNom());
                     m.setTextLine2("Descrription: " + a.getDescription());
                     m.setTextLine3("Mail: " + a.getMail());
//                     m.setTextLine4("Reponse: " + a.getReponse());

                    add(m);
                    Button btnModifier = new Button();
                    FontImage.setMaterialIcon(btnModifier, FontImage.MATERIAL_EDIT);
                    m.addComponent(BorderLayout.SOUTH, btnModifier);
                    btnModifier.addActionListener(e -> {
                        new ModifierReclamationForm(this,a).show();
                    });
             Button mail = new Button();
                    FontImage.setMaterialIcon(mail, FontImage.MATERIAL_MAIL);
                    m.addComponent(BorderLayout.EAST, mail);
                    mail.addActionListener(e -> {
                                                Dialog dig = new Dialog("MAIL");
                        if (dig.show("Mail", "Êtes-vous sûr de envoyer un email a "+a.getNom() +" ?", "Annuler", "Oui")) {
                        } else {
                            dig.dispose();
                           sp.sendemail(a.getMail(), a.getNom());
                           new ListReclamation(previous).show();
                        }
                    });
                    Button btnSupprimer = new Button();
                    Image icon = FontImage.createMaterial(FontImage.MATERIAL_DELETE, "ButtonIcon", 5);
                    btnSupprimer.setIcon(icon);
                    m.addComponent(BorderLayout.WEST, btnSupprimer);
                    btnSupprimer.addActionListener(e -> {
                        Dialog dig = new Dialog("Suppression");
                        if (dig.show("Suppression", "Êtes-vous sûr de vouloir supprimer cet élément ?", "Annuler", "Oui")) {
                        } else {
                            dig.dispose();
                            if (sp.deleteReclamation(a.getId())) {
new ListReclamation(this).show();
                            }
                        }
                    });
                }

                revalidate();
            });
        });

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
