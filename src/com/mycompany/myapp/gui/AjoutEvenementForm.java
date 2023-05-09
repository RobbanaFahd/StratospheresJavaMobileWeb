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
import static com.codename1.push.PushContent.setTitle;
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
 * @author joujo
 */
public class AjoutEvenementForm extends Form {
    public AjoutEvenementForm(Form previous) {

      setTitle("Ajouter un evenement ");
        setLayout(BoxLayout.y());
       
        TextField tfnom = new TextField("","Nom ");
        TextField tflieu = new TextField("","Lieu ");
        TextField tfdate = new TextField("","Date ");
        TextField tdescreption = new TextField("","Description");
        TextField tfdatefin = new TextField("","DateFin ");
         TextField tfnbr_personnes = new TextField("","Nombre des personnes "); 
        Button btnValider = new Button("Add ");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String nbrPString = tfnbr_personnes.getText();
                int nbrInt = Integer.parseInt(nbrPString);
                
                if ((tfnom.getText().length()==0)&& (tflieu.getText().length()==0)&&(tfdate.getText().length()==0)&&(tdescreption.getText().length()==0)&&(tfdatefin.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                       
                        Evenement t = new Evenement(tfnom.getText().toString(),
                               tflieu.getText().toString(),
                                tfdate.getText().toString(),
                                tdescreption.getText().toString(),
                                tfdatefin.getText().toString(),
                                nbrInt);
                                
                            
                       
        
                        if( ServiceEvenement.getInstance().ajoutEvenement(t)) {
                            Dialog.show("Success","Ajouter avec succée",new Command("OK"));
                        }else Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfnom ,tflieu,tfdate,tdescreption,tfdatefin,tfnbr_personnes,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
     
    }
    
   
    
}
