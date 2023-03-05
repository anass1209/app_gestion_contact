package com.example.lasttp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText name, id;
    private Button save, delete, update;
    private ListView mylistview;
    ArrayList<Personne> arag;
    Cusstomeradapter c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDataBase mb=new MyDataBase(this,"personne",null,2);
        name = (EditText) findViewById(R.id.name);
        id = (EditText) findViewById(R.id.id);
        save = (Button) findViewById(R.id.save);
        update = (Button) findViewById(R.id.update);
        delete = (Button) findViewById(R.id.delete);
        mylistview = (ListView) findViewById(R.id.mview);
        Button affichageBtn = (Button) findViewById(R.id.affichage);

        arag=new ArrayList<Personne>();
        c=new Cusstomeradapter(arag);
        mylistview.setAdapter(c);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mb.inserer(name.getText().toString());
            }
        });

        affichageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Personne a =new Personne(name.getText().toString());
                arag.add(a);
                c.notifyDataSetChanged();
                */
                
            }
        });

    }

    class Cusstomeradapter extends BaseAdapter {
        ArrayList<Personne> listperso = new ArrayList<Personne>();

        public Cusstomeradapter(ArrayList<Personne> listo) {
            this.listperso = listo;
        }

        @Override
        public int getCount() {
            return listperso.size();
        }

        @Override
        public Object getItem(int i) {
            return listperso.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater li = getLayoutInflater();
            View v = li.inflate(R.layout.listviewdesign, null);
            TextView nom = v.findViewById(R.id.namm);
            nom.setText(listperso.get(i).Nom);
            Button info = v.findViewById(R.id.info);
            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder al = new AlertDialog.Builder(MainActivity.this);
                    al.setTitle("Affichage")
                            .setMessage(listperso.get(i).Afficher())
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    listperso.remove(i);
                                    notifyDataSetChanged();
                                }
                            });
                    al.show();
                }
            });
            return v;
        }

    }}
