package com.xitij.android.syncdemojumpsum.helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Android on 12-06-2015.
 */
public abstract class API implements OnResponseListner {

    public abstract void onResult(String result);

    public abstract void onTimeOutException();

    private static final String NAMESPACE = "http://tempuri.org/";
    private static final String BASE_URL = "http://jumpsum.com/PlayerMobile.asmx?wsdl";
    private static final String SOAP_ACTION = "http://tempuri.org/";
    private static final String METHOD_CANDIDATEAUTHENTICATIONJSON = "CandidateAuthenticationJson";


    private HashMap<String, String> param_map;
    private String param_method;
    private Context _context;
    ProgressDialog pd;

    public API(Context ctx, HashMap<String, String> param_map, String param_method) {
        this.param_map = param_map;
        this.param_method = param_method;
        this._context = ctx;
    }

    public String RequestPost() {

        String response = "";
        SoapObject request = new SoapObject(NAMESPACE, param_method);
        Functions.logD("Request For Webservices", "======");
        for (Map.Entry<String, String> entry : param_map.entrySet()) {
            Functions.logD(entry.getKey(), entry.getValue());
            request.addProperty(entry.getKey(), entry.getValue().toString().toString());
        }
        Functions.logD("Request For Webservices", "======");
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
        HttpTransportSE androidHttpTransport = new HttpTransportSE(BASE_URL);
        try {
            androidHttpTransport.call(SOAP_ACTION + param_method, envelope);
            //SoapObject resultsRequestSOAP = (SoapObject) envelope.bodyIn;
            //response = resultsRequestSOAP.toString();
            response = envelope.getResponse().toString();
        } catch (Exception e) {
            e.printStackTrace();

            onTimeOutException();
        }
        return response;
    }

    public synchronized final API call() {

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
              //  pd = ProgressDialog.show(_context, "Loading", "Please wait..", true);
            }

            private String response = new String();

            @Override
            protected Void doInBackground(Void... voids) {
                response = RequestPost();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
               // pd.dismiss();
                onResult(response);
            }

        }.execute();
        return this;

    }

}