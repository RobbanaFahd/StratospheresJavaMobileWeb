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
import com.mycompany.myapp.entities.Annonce;
import com.mycompany.myapp.services.ServiceAnnonce;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Amoulette
 */
public class ListAnnonce extends Form {

    public ListAnnonce(Form previous) {
        /*  setTitle("Liste des annonces");
        setLayout(BoxLayout.y());

        ArrayList<Annonce> tasks = ServiceAnnonce.getInstance().affichageAnnonce();
        for (Annonce t : tasks) {
            addElement(t);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

   public void addElement(Annonce t) {
    Container container = new Container(BoxLayout.y());
   //  Label nom = new Label("Afficher Catégoerie");
     Label IDTxt = new Label("ID : " + t.getId());
     Label nomTxt = new Label("Nom : " + t.getNom());
    // Label imageTxt = new Label("image : " + t.getImage());
     Label descriptionTxt = new Label("description : " + t.getDescreption());
     Label titreTxt = new Label("Titre : " + t.getTitre());
     //Label tagTxt = new Label("tag : " + t.getTag());
    // Label telTxt = new Label("tel : " + t.getTel());
     //Label emailTxt = new Label("email : " + t.getEmail());
   //  Label localTxt = new Label("local : " + t.getLocal());
     Label etatTxt = new Label("etat : " + t.getEtat());
     Label categorieTxt = new Label("Catégorie : " + t.getCategorie());
     
    Button btnModifier = new Button("Modifier ");
    Button btnSupprimer = new Button("Supprimer ");
    //container.addComponent(nom);
    container.addComponent( IDTxt);
    container.addComponent(nomTxt);
    //container.addComponent(imageTxt);
     container.addComponent(descriptionTxt);
      container.addComponent(titreTxt); 
     // container.addComponent(tagTxt);
     // container.addComponent(telTxt); 
      //container.addComponent(emailTxt); 
     // container.addComponent(localTxt); 
      container.addComponent(etatTxt); 
      container.addComponent(categorieTxt); 
    container.addComponent( btnModifier);
    container.addComponent( btnSupprimer);
     
    add(container);
         */

        ServiceAnnonce sp = new ServiceAnnonce();
        add(new InfiniteProgress());
        Display.getInstance().scheduleBackgroundTask(() -> {

            Display.getInstance().callSerially(() -> {

                removeAll();
                setLayout(BoxLayout.y());
                Button searchButton = new Button();
                FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, "Search Icon", 4);
                searchButton.setIcon(searchIcon);

                add(searchButton);
                Style s = UIManager.getInstance().getComponentStyle("Description");

                Form hi = new Form("Toolbar", new BoxLayout(BoxLayout.Y_AXIS));
                searchButton.addActionListener(e -> {
                    hi.show();
                });
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
                // FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
                ArrayList<Annonce> list1;
                list1 = ServiceAnnonce.getInstance().affichageAnnonce();
                //hi.add(gui_Button_12);
                searchField.addDataChangeListener((i1, i2) -> { // <2>
                    String t = searchField.getText();

                    if (t.length() < 1) {
                        for (Component cmp : hi.getContentPane()) {
                            cmp.setHidden(false);
                            cmp.setVisible(true);
                        }
                    } else {
                        t = t.toLowerCase();
                        for (Component cmp : hi.getContentPane()) {
                            String val = null;
                            //hi.add(gui_Button_12);
                            if (cmp instanceof Label) {
                                val = ((Label) cmp).getText();
                            } else {
                                if (cmp instanceof TextArea) {
                                    val = ((TextArea) cmp).getText();
                                } else {
                                    val = (String) cmp.getPropertyValue("text");
                                }
                            }
                            boolean show = val != null && val.toLowerCase().indexOf(t) > -1;
                            cmp.setHidden(!show); // <3>
                            cmp.setVisible(show);
                            //hi.add(gui_Button_12);
                        }
                    }
                    hi.getContentPane().animateLayout(250);
                    //  hi.add(gui_Button_12);
                });
                hi.getToolbar().addCommandToRightBar("", searchIcon, (e) -> {
                    searchField.startEditingAsync(); // <4>
                    //    hi.add(gui_Button_12);
                });

                for (Annonce A : list1) {
                    Label b = new Label(A.getDescreption());

                    hi.add(b);

                    b.addPointerPressedListener(e -> {

                        if (A.getDescreption() == b.getText()) {
                            new recSearch(previous).show();
                        }
                    });
                }
                 Button mapButton = new Button("Afficher la carte");
                mapButton.addActionListener(e -> {
                    MapForm mapForm = new MapForm();
                    mapForm.show();
                });
                add(mapButton);
                Button refreshButton = new Button();
                FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_REFRESH, UIManager.getInstance().getComponentStyle("Button"));
                refreshButton.setIcon(icon1);

                refreshButton.addActionListener(e -> new ListAnnonce(previous).show());
                add(refreshButton);

                List<Annonce> listea = sp.affichageAnnonce();
                for (Annonce a : listea) {

                    MultiButton m = new MultiButton();

                     m.setTextLine1("Nom: " + a.getNom());
                     m.setTextLine2("Titre: " + a.getTitre());
                     m.setTextLine3("Description: " + a.getDescreption());
                     m.setTextLine4("local: " + a.getLocal());

                    add(m);
                    Button btnModifier = new Button();
                    FontImage.setMaterialIcon(btnModifier, FontImage.MATERIAL_EDIT);
                    m.addComponent(BorderLayout.SOUTH, btnModifier);
                    btnModifier.addActionListener(e -> {
                        new modifier(this).show();
                    });

                    Button btnSupprimer = new Button();
                    Image icon = FontImage.createMaterial(FontImage.MATERIAL_DELETE, "ButtonIcon", 5);
                    btnSupprimer.setIcon(icon);
                    m.addComponent(BorderLayout.WEST, btnSupprimer);
                    btnSupprimer.addActionListener(e -> {
                        Dialog dig = new Dialog("Suppression");
                        if (dig.show("Suppression", "Êtes-vous sûr de vouloir supprimer cet élément ?", "Annuler", "Oui")) {
                            dig.dispose();
                        } else {
                            dig.dispose();
                            if (ServiceAnnonce.getInstance().deleteAnnonce(a.getId())) {
                                // Élément supprimé avec succès
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
