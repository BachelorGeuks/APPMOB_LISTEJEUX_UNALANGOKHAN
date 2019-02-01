package com.example.navigation;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Création des variables
        ArrayList<cGames> games = new ArrayList<cGames>();
        final RecyclerView list = (RecyclerView) findViewById(R.id.recycler);

        //Initialisation
        initList(games);

        //Set du layoutmanager
        list.setLayoutManager(new LinearLayoutManager(this));

        //Création de l'adapter
        list.setAdapter(new GamesAdapter(this,R.layout.activity_games,games,new GamesAdapter.OnItemClickListener(){
            //Ajout de l'evenement OnClick pour la liste
            @Override
            public void onItemClick(cGames item) {
                cGames selectedItem = item;

                //Création de l'intent
                Intent intent = new Intent(MainActivity.this, activity_description.class);

                //Envoie de l'intent
                intent.putExtra("GAMES",item);

                //Démarrage de la nouvelle activité
                startActivity(intent);
            }
        }));
    }

    private void initList(ArrayList<cGames> games){
        String[] allName = getResources().getStringArray(R.array.game);
        String[] allEditeur = getResources().getStringArray(R.array.editeur);
        String[] allImages = getResources().getStringArray(R.array.image);
        String[] allDescs = getResources().getStringArray(R.array.description);
        cGames game;

        //Boucle pour le nombre d'éléments qu'il y a
        for(int i = 0; i<allImages.length; i++){
            game = new cGames();

            //Init des informations depuis le strings.xml
            game.setName(allName[i]);
            game.setText("Editeur: " + allEditeur[i]);
            game.setDesc(allDescs[i]);

            //Init des images
            AssetManager assetManager = getAssets();
            InputStream path = null;
            try {

                game.setPath(allImages[i].toLowerCase()+".jpg");
                path = assetManager.open(game.getPath());

            } catch (IOException e) {
                e.printStackTrace();
            };

            Bitmap bitmap = BitmapFactory.decodeStream(path);
            game.setAvatar(bitmap);

            //Ajout dans la liste
            games.add(game);
        }
    }
}

