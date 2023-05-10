package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entity.Banquedesang;
import com.mycompany.services.ServiceBanque;
import java.util.ArrayList;

public class ShowForm extends Form {

    ShowForm(Form previous) {
        setTitle("Affichage du banque");
        setLayout(BoxLayout.y());

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> previous.showBack());

        // Get all the Banquedesang objects
        ArrayList<Banquedesang> banques = ServiceBanque.getInstance().getAllBanques();

        // Create a list of components to display the information of each object
        for (Banquedesang banque : banques) {
            // Create a container for each object's information and a delete button
            Component container = new Label("Name: " + banque.getName() + "\n"
                    + "Address: " + banque.getAddresse() + "\n"
                    + "Longitude: " + banque.getLongitude() + "\n"
                    + "Latitude: " + banque.getLatitude());
            Button deleteBtn = new Button("Delete");
            deleteBtn.addActionListener(evt -> {
                // Call the deleteBanque method of ServiceBanque to delete the Banquedesang object
                ServiceBanque.getInstance().deleteBanque(banque.getId());
                // Remove the container and the delete button from the form
                removeComponent(container);
                removeComponent(deleteBtn);
            });
            // Add the container and the delete button to the form using a container with a BoxLayout Y axis layout
            add(BoxLayout.encloseY(container, deleteBtn, new Label("---------------------------------------")));
        }
    }

    public static ShowForm getInstance(Form previous) {
        return new ShowForm(previous);
    }
}
