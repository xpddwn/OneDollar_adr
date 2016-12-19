package com.jf.luckydollar;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by user on 2016/7/16.
 */

public class HttpHelper {
    public HashMap<String,String> param = new HashMap<String, String>();
    public static String urlpre = "http://ip";
    public String urlstr;
    public HttpHelper(String murl){
        urlstr = urlpre + murl;
    }
    public JSONObject data;

    public void addParam(String key,String value){
        param.put(key,value);
    }

    public String getparam(){
        String p = new String();
        Iterator it = param.keySet().iterator();
        while(it.hasNext()){
            String key = (String)it.next();
            p += key + "=" + param.get(key);
            if(it.hasNext()){
                p += "&";
            }
        }
        return p;
    }

    public void connect_POST(){
        URL url = null;
        String result = "";
        try {
            url = new URL(urlstr);
            Log.d("debug", "begin"+urlstr);
            HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setRequestMethod("POST");
            urlConn.setUseCaches(false);
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConn.setRequestProperty("Charset", "utf-8");
            Log.d("debug", "hi");
            String paramstr = getparam();
            Log.d("debug", "paramstr =" + paramstr);
            urlConn.connect();
            Log.d("debug", "connect");
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
            URLEncoder.encode(paramstr, "utf-8");
            dop.writeBytes(paramstr);
            dop.flush();
            dop.close();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream(),"UTF-8"));
            String readLine = null;
            while((readLine = bufferedReader.readLine())!=null){
                result += readLine;
            }
            Log.d("debug", "result =" + result);
            bufferedReader.close();
            urlConn.disconnect();
        }catch (IOException e){
            result = "{\"status\":\"false\",\"result\":\"false\",\"data\":\"\"}";
            e.printStackTrace();
        }
        try{
            if(result != null){
                data = new JSONObject(result);
            }
            Log.d("debug",result);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public String getnetStatus(){
        String status;
        try {
            status = data.get("status").toString();
        } catch (JSONException e) {
            status = null;
            e.printStackTrace();
        }
        return status;
    }

    public String getData(){
        String d;
        try{
            d = data.get("data").toString();
        } catch (JSONException e){
            d = null;
            e.printStackTrace();
        }
        return d;
    }

    public String getResult(){
        String result;
        try{
            result = data.get("result").toString();
        } catch (JSONException e) {
            result = null;
            e.printStackTrace();
        }
        return result;
    }

    public JSONArray getDatabyArray(){
        String jsondata;
        JSONArray mdata;
        try{
            jsondata = data.get("data").toString();
            if(jsondata != null)
                mdata = new JSONArray(jsondata);
            else
                mdata = null;
        } catch (JSONException e) {
            mdata = null;
            e.printStackTrace();
        }
        return mdata;
    }

    public ArrayList<HashMap<String,String>> getArrayMapdata(){
        ArrayList<HashMap<String,String>> datalist = new ArrayList<HashMap<String, String>>();
        JSONArray jsondata = getDatabyArray();
        if(jsondata != null) {
            try {
                for (int i = 0; i < jsondata.length(); i++) {
                    HashMap tmpmap = new HashMap<String, String>();
                    JSONObject obj = new JSONObject(jsondata.get(i).toString());
                    Iterator it = obj.keys();
                    while (it.hasNext()) {
                        String key = (String) it.next();
                        String value = obj.getString(key);
                        tmpmap.put(key, value);
                    }
                    datalist.add(tmpmap);
                }
            } catch (JSONException e) {
                datalist = null;
                e.printStackTrace();
            }
        }else{
            datalist = null;
        }
        return datalist;
    }

    public JSONObject getDatabyObject(){
        String jsondata;
        JSONObject mdata;
        try {
            jsondata = data.get("data").toString();
            mdata = new JSONObject(jsondata);
        } catch (JSONException e) {
            mdata = null;
            e.printStackTrace();
        }
        return mdata;
    }
}
