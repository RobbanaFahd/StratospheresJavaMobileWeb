package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entity.Banquedesang;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class ServiceBanque {

    private ArrayList<Banquedesang> banques;

    public static ServiceBanque instance = null;
    public boolean resultOK;
    private final ConnectionRequest req;

    private ServiceBanque() {
        req = new ConnectionRequest();
    }

    public static ServiceBanque getInstance() {
        if (instance == null) {
            instance = new ServiceBanque();
        }
        return instance;
    }

    public ArrayList<Banquedesang> parseBanques(String jsonText) {
        try {
            banques = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> banquesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            ArrayList<Map<String, Object>> list = (ArrayList<Map<String, Object>>) banquesListJson.get("root");
            for (Map<String, Object> obj : list) {
                Banquedesang banque = new Banquedesang();

                try {
                    int id = (int) Float.parseFloat(obj.get("id").toString());
                    banque.setId(id);
                } catch (Exception e1) {
                    System.out.println("Error setting ID");
                }

                try {
                    banque.setName(obj.get("name").toString());
                } catch (Exception e2) {
                    System.out.println("Error setting name");
                }

                try {
                    banque.setAddresse(obj.get("adresse").toString());
                } catch (Exception e3) {
                    System.out.println("Error setting address");
                }

                try {
                    float longitude = Float.parseFloat(obj.get("longitude").toString());
                    banque.setLongitude(longitude);
                } catch (Exception e4) {
                    System.out.println("Error setting longitude");
                }

                try {
                    float latitude = Float.parseFloat(obj.get("latitude").toString());
                    banque.setLatitude(latitude);
                } catch (Exception e5) {
                    System.out.println("Error setting latitude");
                }

                banques.add(banque);
            }

        } catch (IOException ex) {
            System.out.println("Error parsing banques: " + ex.getMessage());
        }

        return banques;
    }

    public ArrayList<Banquedesang> getAllBanques() {
        String url = "http://127.0.0.1:8000/banquedesang/display";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                banques = parseBanques(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return banques;
    }

    public void ajoutBanque(Banquedesang banquedesang) {
        ConnectionRequest req = new ConnectionRequest();

        String url = "/aaddjson?Name=" + banquedesang.getName() + "&Addresse=" + banquedesang.getAddresse() + "&Tel=" + banquedesang.getTel() + "&Longitude" + banquedesang.getLongitude() + "&latitude" + banquedesang.getLatitude(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation

        req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

    }

    public void deleteBanque(int id) {
        String url = "https://127.0.0.1:8000/banquedesang/deletej/" + id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((evt) -> {
            if (req.getResponseCode() == 200) {
                System.out.println("Banquedesang object with id=" + id + " deleted successfully.");
            } else {
                System.out.println("Error deleting Banquedesang object with id=" + id + ".");
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

}
