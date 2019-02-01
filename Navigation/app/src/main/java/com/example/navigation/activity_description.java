package com.example.navigation;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class activity_description extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        //On récupère la classe depuis le main
        cGames game = (cGames)getIntent().getExtras().getParcelable("GAMES");

        //Init des infos
        InitGame(game);

    }

    void InitGame(cGames game){
        ImageView image = (ImageView)findViewById(R.id.image);
        TextView name = (TextView)findViewById(R.id.tName);
        TextView desc = (TextView)findViewById(R.id.tDescription);
        AssetManager assetManager = getAssets();
        Bitmap bitmap;
        InputStream path = null;

        //Chargement des images
        try {
            path = assetManager.open(game.getPath());

        } catch (IOException e) {
            e.printStackTrace();
        };

        bitmap = BitmapFactory.decodeStream(path);
        game.setAvatar(bitmap);

        //Initialisation des informations
        name.setText(game.getName());
        desc.setText(game.getText()+ "\n\n" + game.getDesc());

        image.setImageBitmap(game.getAvatar());
    }
}
