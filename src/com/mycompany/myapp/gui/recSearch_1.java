/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.services.ServiceEvenement;
import java.util.List;

/**
 *
 * @author joujo
 */
public class recSearch_1 extends Form {

    Form current;

    public recSearch_1(Form previous) {
        current = this;

        ServiceEvenement sp = new ServiceEvenement ();
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

                refreshButton.addActionListener(e -> new ListEvenement (previous).show());
                add(refreshButton);

                List<Evenement > listerec = sp.affichageEvenement ();

                for (Evenement  a : listerec) {
                    MultiButton m = new MultiButton();

                    m.setTextLine1("Nom: " + a.getNom());
                     m.setTextLine2("Lieu: " + a.getLieu());
                      m.setTextLine3("Date: " + a.getDate());
                     m.setTextLine4("Description: " + a.getDescription());
                   
                    

                    add(m);
                }

                revalidate();
            });
        });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
}

