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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.ui.Display;
import com.mycompany.myapp.entities.Article;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.io.OutputStream;

import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author msi
 */
public class ServiceReclamation {

    public ArrayList<Reclamation> reclamation;
    public ArrayList<Article> article;

    public static ServiceReclamation instance = null;
    public boolean resultOK;

    private ConnectionRequest req;

    public ServiceReclamation() {
        req = new ConnectionRequest();
    }

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }

    public ArrayList<Reclamation> parseReclamations(String jsonText) {
        try {

            System.out.println(jsonText);
            reclamation = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Reclamation a = new Reclamation();

                a.setId((int) Float.parseFloat(obj.get("id").toString()));
                a.setNom(obj.get("nom").toString());
                a.setDescription(obj.get("description").toString());
                try {
                    String dateString = (String) obj.get("date");
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = formatter.parse(dateString);
                    a.setDate(date);
                } catch (ParseException e) {
                    // Handle the exception, such as logging an error message or throwing a custom exception
                }
                a.setArticle_id((int) Float.parseFloat(obj.get("id").toString()));
                a.setMail(obj.get("mail").toString());
//                a.setReponse(obj.get("reponse").toString());

                reclamation.add(a);
            }
        } catch (IOException ex) {

        }
        return reclamation;
    }

    public ArrayList<Article> parseArticles(String jsonText) {
        try {

            System.out.println(jsonText);
            article = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Article a = new Article();

                a.setId((int) Float.parseFloat(obj.get("id").toString()));

                a.setNom(obj.get("nom").toString());
                a.setTitre(obj.get("titre").toString());
                a.setDescription(obj.get("description").toString());

                article.add(a);
            }
        } catch (IOException ex) {

        }
        return article;
    }

    public ArrayList<Reclamation> getAllReclamations() {
        String url = Statics.Base_URL + "/mobile/Reclamation";
        System.out.print(url);
        req.setUrl(url);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reclamation = parseReclamations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return reclamation;
    }

    public ArrayList<Article> getAllArticles() {
        String url = Statics.Base_URL + "/mobile/article";
        System.out.print(url);
        req.setUrl(url);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                article = parseArticles(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return article;
    }

    public boolean addReclamation(Reclamation u) {
        String url = Statics.Base_URL + "/mobile/addReclamation"
                + "?nomP=" + u.getNom() + ""
                + "&descriptionP=" + u.getDescription() + ""
                + "&reponse=" + u.getReponse() + ""
                + "&mail=" + u.getMail() + ""
                + "&current_date=" + u.getDatee() + ""
                + "&article_id=" + u.getArticle_id() + "";
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; // Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean editReclamation(Reclamation u) {
        String url = Statics.Base_URL + "/mobile/updateReclamation/" + u.getId() + ""
                + "?nomP=" + u.getNom() + ""
                + "&descriptionP=" + u.getDescription() + ""
                + "&reponse=" + u.getReponse() + ""
                + "&mail=" + u.getMail() + ""
                + "&current_date=" + u.getDatee() + "";

        req.setUrl(url);
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; // HTTP OK status
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean deleteReclamation(int id) {
        String url = Statics.Base_URL + "/mobile/deleteReclamation/" + id;
        req.setUrl(url);
        req.setHttpMethod("DELETE");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean sendemail(String email, String name) {
        String url = Statics.Base_URL + "/mobile/email"
                + "?nomP=" + name + ""
                + "&mail=" + email + "";

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

}
