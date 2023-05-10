package com.mycompany.gui;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entity.Banquedesang;

public class AddForm extends Form {

    public AddForm(Form previous) {
        setTitle("Ajouter du banque de sang");
        setLayout(BoxLayout.y());
        TextField nom = new TextField("", "Nom du banque");
        TextField addresse = new TextField("", "Adresse du banque");
        TextField tel = new TextField("", "numero du telephone");
        TextField longitude = new TextField("", "Longitude");
        TextField latitude = new TextField("", "latitude");
        Button btnAdd = new Button("Add");
        btnAdd.addActionListener((evt) -> {
            Banquedesang banque = new Banquedesang();
            banque.setName(nom.getText());
            banque.setAddresse(addresse.getText());
            banque.setTel(Integer.parseInt(tel.getText()));
            banque.setLongitude(Float.parseFloat(longitude.getText()));
            banque.setLatitude(Float.parseFloat(latitude.getText()));
            
            ajoutBanque(banque);
        });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ((evt) -> {
            previous.showBack();

        }));
        addAll(nom, addresse, tel, longitude, latitude, btnAdd);
    }

    public void ajoutBanque(Banquedesang banque) {
        ConnectionRequest req = new ConnectionRequest();
        String url = "/aaddjson?Name=" + banque.getName() + "&Addresse=" + banque.getAddresse() + "&Tel=" + banque.getTel() + "&Longitude=" + banque.getLongitude() + "&latitude=" + banque.getLatitude();
        req.setUrl(url);
        req.addResponseListener((e) -> {
            if (req.getResponseCode() == 200) {
                System.out.println("Banque de sang ajoutée avec succès !");
            } else {
                System.out.println("Erreur lors de l'ajout de la banque de sang !");
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
}
