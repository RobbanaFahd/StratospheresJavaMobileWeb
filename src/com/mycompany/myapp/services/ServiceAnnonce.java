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
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Annonce;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Amoulette
 */
public class ServiceAnnonce {

    public static ServiceAnnonce instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ArrayList<Annonce> listAnnonce = new ArrayList<>();

    public ServiceAnnonce() {
        req = new ConnectionRequest();
    }

    public static ServiceAnnonce getInstance() {

        if (instance == null) {
            instance = new ServiceAnnonce();
        }
        return instance;

    }

//ajout
    public boolean ajoutAnnonce(Annonce annonce) {

        // int myInt = annonce.getTel();
        // String myString = Integer.toString(myInt);
        String url = Statics.Base_URL + "/aaddjson?nom=" + annonce.getNom() + "&image=" + annonce.getImage() + "&descreption=" + annonce.getDescreption() + "&titre=" + annonce.getTitre() + "&tag=" + annonce.getTag() + "&tel=" + annonce.getTel() + "&=email" + annonce.getEmail() + "&local=" + annonce.getLocal() + "&etat=" + annonce.getEtat() + "&catégorie=" + annonce.getCategorie();
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
    public ArrayList<Annonce> affichageAnnonce() {
        ArrayList<Annonce> result = new ArrayList<>();

        String url = "http://127.0.0.1:8000/" + "alistjson";
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
                        Annonce an = new Annonce();
                        float id = Float.parseFloat(obj.get("id").toString());
                        String nom = obj.get("nom").toString();
                        String image = obj.get("image").toString();
                        String descreption = obj.get("descreption").toString();
                        String titre = obj.get("titre").toString();
                        String tag = obj.get("tag").toString();
                        float tel = Float.parseFloat(obj.get("tel").toString());

                        String email = obj.get("email").toString();
                        String local = obj.get("local").toString();
                        String etat = obj.get("etat").toString();
                        String categorie = obj.get("categorie").toString();

                        an.setId((int) id);
                        an.setNom(nom);
                        an.setImage(image);
                        an.setDescreption(descreption);
                        an.setTitre(titre);
                        an.setTag(tag);
                        an.setTel((int) tel);
                        an.setEmail(email);
                        an.setLocal(local);
                        an.setEtat(etat);
                        an.setCategorie(categorie);

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
    public boolean modifierAnnonce(Annonce t) {

        String url = "http://127.0.0.1:8000/" + "annonce/modifierM?id=" + t.getId() + "&nom=" + t.getNom() + "&descreption=" + t.getDescreption() + "&titre=" + t.getTitre() + "&etat=" + t.getEtat() + "&categorie=" + t.getCategorie();

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
    public boolean deleteAnnonce(int id) {
        String url = "http://127.0.0.1:8000/" + "annonce/deleteM?id=" + id;

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
