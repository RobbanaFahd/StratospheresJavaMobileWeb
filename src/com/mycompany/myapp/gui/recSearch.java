/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.entities.Annonce;
import com.mycompany.myapp.services.ServiceAnnonce;

import java.util.List;

/**
 *
 * @author HP
 */
public class recSearch extends Form {

    Form current;

    public recSearch(Form previous) {
        current = this;

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
                Button refreshButton = new Button();
                FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_REFRESH, UIManager.getInstance().getComponentStyle("Button"));
                refreshButton.setIcon(icon1);

                refreshButton.addActionListener(e -> new ListAnnonce(previous).show());
                add(refreshButton);

                List<Annonce> listerec = sp.affichageAnnonce();
               
 
                for (Annonce a : listerec) {
                    MultiButton m = new MultiButton();

                    m.setTextLine1("Nom: " + a.getNom());
                    m.setTextLine2("Titre: " + a.getTitre());
                    m.setTextLine3("Description: " + a.getDescreption());
                    m.setTextLine4("Local: " + a.getLocal());
                    

                    add(m);
                }

                revalidate();
            });
        });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
}
