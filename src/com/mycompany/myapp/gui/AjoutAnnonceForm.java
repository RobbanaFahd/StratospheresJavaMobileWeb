/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;


import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Annonce;
import com.mycompany.myapp.services.ServiceAnnonce;


import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;


/**
 *
 * @author Amoulette
 */
public class AjoutAnnonceForm extends Form  {


    public AjoutAnnonceForm(Form previous) {

      setTitle("Ajouter une annonce ");
        setLayout(BoxLayout.y());
       
        TextField tfnom = new TextField("","Nom ");
        TextField tfimage = new TextField("","Image ");
        TextField tdescreption = new TextField("","description");
        TextField tftitre = new TextField("","Titre ");
        TextField tftag = new TextField("","Tag ");
        TextField tftel = new TextField("","Téleohone ");
        TextField tfemail = new TextField("","Email "); 
        TextField tflocal = new TextField("","Localisation ");
        TextField tfetat = new TextField("","Etat ");
        TextField tfcategorie = new TextField("","Catégorie ");
        Button btnValider = new Button("Add ");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 String telString = tftel.getText();
                int telInt = Integer.parseInt(telString);
                
                if ((tfnom.getText().length()==0)&& (tdescreption.getText().length()==0)&& (tftitre.getText().length()==0)&& (tfcategorie.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                       
                        Annonce t = new Annonce(tfnom.getText().toString(),
                                tfimage.getText().toString(),
                                tdescreption.getText().toString(),
                                tftitre.getText().toString(),
                                tftel.getText().toString(),
                                telInt,
                                tfemail.getText().toString(),
                                tflocal.getText().toString(),
                                tfetat.getText().toString(),
                                tfcategorie.getText().toString()
                       );
        
                        if( ServiceAnnonce.getInstance().ajoutAnnonce(t)) {
                            Dialog.show("Success","Ajouter avec succée Bravo",new Command("OK"));
                        }else Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfnom ,tfimage,tdescreption,tftitre ,tftag,tftel ,tfemail,tflocal,tfetat,tfcategorie,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
     
    }
    
   
   
    
    
}
