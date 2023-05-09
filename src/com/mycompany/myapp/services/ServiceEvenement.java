/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author joujo
 */
public class ServiceEvenement {
     public static ServiceEvenement instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ArrayList<Evenement> listEvenement = new ArrayList<>();
     public ArrayList<Evenement> events;

    public ServiceEvenement() {
        req = new ConnectionRequest();
    }

    public static ServiceEvenement getInstance() {

        if (instance == null) {
            instance = new ServiceEvenement();
        }
        return instance;

    }

//ajout
    public boolean ajoutEvenement(Evenement Evenement) {

        // int myInt = annonce.getTel();
        // String myString = Integer.toString(myInt);
        String url = Statics.Base_URL + "/addEventMobile?nom=" + Evenement.getNom() + "&lieu=" + Evenement.getLieu() + "&date="+Evenement.getDate()+ "&description=" + Evenement.getDescription() + "&datefin=" + Evenement.getDatefin()+ "&nbrPersonnes=" + Evenement.getNbrPersonnes();
        // String url =Statics.Base_URL+"/annonce/ajouteM?nom="+annonce.getNom()+"&descreption="+annonce.getDescreption()+"&titre="+ annonce.getTitre()+"&etat="+annonce.getEtat()+"&catégorie="+annonce.getCategorie();

        req.setUrl(url);
        req.setPost(false);
        /* req.addArgument("nom", annonce.getNom());
        req.addArgument("image", annonce.getImage());
        req.addArgument("descreption", annonce.getDescreption());
        req.addArgument("titre", annonce.getTitre());
        req.addArgument("tag", annonce.getTag());
        req.addArgument("tel",myString);
        req.addArgument("email", annonce.getEmail());
        req.addArgument("local", annonce.getLocal());
        req.addArgument("etat", annonce.getEtat());
        req.addArgument("catégorie", annonce.getCategorie());
         */
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        /* String nom = t.getNom();
        String image=t.getImage();
        String descreption=t.getDescreption();
        String titre=t.getTitre();
        String tag=t.getTag();
        String email=t.getEtat();
        String local=t.getLocal();
        String etat=t.getEtat();
        String cat=t.getCategorie();
      
       
//String url= "http://127.0.0.1:8000/"+"annonce/ajouteM?nom="+nom+" &image="+image+" &email="+t.getEmail()+" &prenom=" +t.getPrenom();
  //      req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });*/
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;

    }

    //affichage
    
    
 
      
    
    
    
    public ArrayList<Evenement> affichageEvenement() {
        ArrayList<Evenement> result = new ArrayList<>();

        String url = "http://127.0.0.1:8000/" + "afficheeventMobile";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapAnnonce = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listofMaps = (List<Map<String, Object>>) mapAnnonce.get("root");
                    for (Map<String, Object> obj : listofMaps) {
                        Evenement an = new Evenement();
                            float id = Float.parseFloat(obj.get("id").toString());
                        String nom = obj.get("nom").toString();
                         String lieu = obj.get("lieu").toString();
                         String date = obj.get("date").toString();
                        String description = obj.get("description").toString();
                        String datefin = obj.get("datefin").toString();
                         float nbrPersonnes = Float.parseFloat(obj.get("nbrPersonnes").toString());
                       

                      

                        an.setId((int) id);
                        an.setNom(nom);
                        an.setLieu(lieu);
                          an.setDate(date);
                        an.setDescription(description);
                        an.setDatefin(datefin);
                           an.setNbrPersonnes((int) nbrPersonnes);
                       
                       

                        result.add(an);

                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return result;

    }

    //*********************************************update***************************************************/    
    public boolean modifierEvenement(Evenement Evenement) {

        String url = "http://127.0.0.1:8000/" + "updateeventMobile/id=" + Evenement.getId()+"&nom=" + Evenement.getNom() + "&lieu=" + Evenement.getLieu() + "&date=" + Evenement.getDate()+  "&description=" + Evenement.getDescription()+ "&date=" + Evenement.getDate()+ "&nbrPersonnes=" + Evenement.getNbrPersonnes() ;

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        return resultOK;

    }

    //////////////////////////////////delete///////////////////////////////////////
  public boolean deleteEvenement(int id) {
        
        System.out.println("********");
        String url = "http://127.0.0.1:8000/"  +"deleteEventmobile/id="+id;
ConnectionRequest req = new ConnectionRequest();
      req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseCodeListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    }

    

