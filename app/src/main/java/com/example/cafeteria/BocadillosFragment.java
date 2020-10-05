package com.example.cafeteria;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class BocadillosFragment extends Fragment {
    static ArrayList<String> Nombres = new ArrayList<>();
    final static ArrayList<String> ima = new ArrayList<>();
    final static ArrayList<String> dinero = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView listView;

    @Override
    public void onResume() {
        super.onResume();

        getData updateTask = new getData();
        updateTask.execute();

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bocadillos, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = (ListView)getView().findViewById(R.id.listview);
        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, Nombres);

/*
        adapter= new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, ima);
        adapter= new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, dinero);
        */

        listView.setAdapter(adapter);
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(),String.valueOf(position),Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    private class getData extends AsyncTask<String, Void, String> {
        String data = "";
        @Override
        protected String doInBackground(String... params) {
            Nombres.clear();
            try {
                URL url = new URL("https://tucafeteria.000webhostapp.com/listview.php");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while (line != null) {
                    line = reader.readLine();
                    data += line;
                }

                JSONArray jArray = new JSONArray(data);
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json = (JSONObject) jArray.get(i);
                    Nombres.add(new String(json.getString("Nombre")));
                    //ima.add(json.getString("icon"));
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return "Executed";
        }
        @Override
        protected void onPostExecute(String result) {
            CustomListView customListView = new CustomListView(getActivity(),Nombres);
            listView.setAdapter(customListView);
        }

    }

}