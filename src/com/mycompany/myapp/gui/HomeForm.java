/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.util.List;


public class HomeForm extends Form {

    private static final char ADD_EVENT_ICON_NAME = FontImage.MATERIAL_ADD_CIRCLE_OUTLINE;
    private static final char LIST_EVENT_ICON_NAME = FontImage.MATERIAL_LIST_ALT;
    private static final char ADD_ANNONCE_ICON_NAME = FontImage.MATERIAL_NOTE_ADD;
    private static final char LIST_ANNONCE_ICON_NAME = FontImage.MATERIAL_LIST_ALT;
   
    private static final String ADD_EVENT_BUTTON_TEXT = "Ajouter Evenement";
    private static final String LIST_EVENT_BUTTON_TEXT = "Afficher Les evenement";
    private static final String ADD_ANNONCE_BUTTON_TEXT = "Ajouter Annonce";
    private static final String LIST_ANNONCE_BUTTON_TEXT = "Afficher Les Annocnces";


    public HomeForm() {

        setTitle("Menu");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));

        Button addEventButton = new Button("");
        addEventButton.setIcon(FontImage.createMaterial(ADD_EVENT_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
        addEventButton.setText(ADD_EVENT_BUTTON_TEXT);
        addEventButton.addActionListener(e -> new AjoutEvenementForm(this).show());

        Button listEventButton = new Button("");
        listEventButton.setIcon(FontImage.createMaterial(LIST_EVENT_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
        listEventButton.setText(LIST_EVENT_BUTTON_TEXT);
        listEventButton.addActionListener(e -> new ListEvenement(this).show());
      
        Button addAnnonceButton = new Button("");
        addAnnonceButton.setIcon(FontImage.createMaterial(ADD_ANNONCE_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
        addAnnonceButton.setText(ADD_ANNONCE_BUTTON_TEXT);
        addAnnonceButton.addActionListener(e -> new AjoutAnnonceForm(this).show());

        Button listAnnonceButton = new Button("");
        listAnnonceButton.setIcon(FontImage.createMaterial(LIST_ANNONCE_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
        listAnnonceButton.setText(LIST_ANNONCE_BUTTON_TEXT);
        listAnnonceButton.addActionListener(e -> new ListAnnonce(this).show());

        addAll(addEventButton, listEventButton, addAnnonceButton, listAnnonceButton);
    }
}
