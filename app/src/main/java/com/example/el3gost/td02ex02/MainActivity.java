package com.example.el3gost.td02ex02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void BtnCredit_OnClick(View view)
    {
        Intent openCredit = new Intent(this, Credit_Activity.class);
        startActivity(openCredit);
    }

    public void BtnGameSolo_OnClick(View view)
    {
        Intent launchGame = new Intent(this, Game_Activity.class);
        int GameMode = 1;
        launchGame.putExtra("GameMode", GameMode);
        startActivity(launchGame);
    }
    public void BtnGameMulti_OnClick(View view)
    {
        Intent launchGame = new Intent(this, Game_Activity.class);
        int GameMode = 2;
        launchGame.putExtra("GameMode", GameMode);
        startActivity(launchGame);
    }
}
