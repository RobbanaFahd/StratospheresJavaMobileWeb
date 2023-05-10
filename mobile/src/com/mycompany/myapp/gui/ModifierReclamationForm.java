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
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.L10NManager;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
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
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Article;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;
import java.util.ArrayList;


/**
 *
 * @author Amoulette
 */
public class ModifierReclamationForm extends Form  {

        ServiceReclamation sp = new ServiceReclamation();
           int selectedId = 0;

    public ModifierReclamationForm(Form previous,Reclamation u) {

      setTitle("Modifer une annonce ");
        setLayout(BoxLayout.y());
       
       
Picker date_facture = new Picker();
date_facture.setDate(u.getDate());
date_facture.setType(Display.PICKER_TYPE_DATE);
date_facture.setUIID("TextFieldBlack");


       add(date_facture); 
        
        
TextField tfnom = new TextField("","Nom ");
tfnom.setText(u.getNom());
add(tfnom);

TextField tdescreption = new TextField("","description");
tdescreption.setText(u.getDescription());
add(tdescreption);

TextField tftitre = new TextField("","Mail ");
tftitre.setText(u.getMail());
add(tftitre);

TextField tftag = new TextField("","Reponse ");
tftag.setText(u.getReponse());
add(tftag) ;
        
        Button btnValider = new Button("Modier ");
              add(btnValider);  

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                       
                if ((tfnom.getText().length()==0)&& (tdescreption.getText().length()==0)&& (tftitre.getText().length()==0)&& (tftag.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {

                       
                                 
            
                                 
                                 L10NManager l10n = L10NManager.getInstance();  
            u.setDatee(l10n.formatDateShortStyle(date_facture.getDate()));
                                 
                 u.setNom(tfnom.getText());
                 u.setDescription(tdescreption.getText());
                 u.setMail(tftitre.getText());
                 u.setReponse(tftag.getText());
                 
                 
                                 
                                 sp.editReclamation(u);
                                 Dialog.show("Success","Modifer avec succée Bravo",new Command("OK"));
                                 
                             }

            
                    
                }
                
                
            
        });
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
     
    }
    
   
   
    
    
}
