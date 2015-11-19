package com.example.el3gost.td02ex02;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Game_Activity extends AppCompatActivity {

    private ImageView imgGrille;
    private int currentPlayer;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private TextView curPlayerInfo;
    private TextView grillePlainText;
    private GrilleStatus grille;
    private int gameMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_);
        gameMode = getIntent().getIntExtra("GameMode", 0);
        //imgGrille = (ImageView) findViewById(R.id.imageView_Grille);
        //imgGrille.setImageResource(R.drawable.grille);
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);
        btn5 = (Button) findViewById(R.id.button5);
        btn6 = (Button) findViewById(R.id.button6);
        btn7 = (Button) findViewById(R.id.button7);
        btn8 = (Button) findViewById(R.id.button8);
        btn9 = (Button) findViewById(R.id.button9);
        curPlayerInfo = (TextView) findViewById(R.id.textViewCurrentPlayer);
        curPlayerInfo.setText("Tour de X");
        grillePlainText = (TextView) findViewById(R.id.textViewGrillePlainText);
        grille = new GrilleStatus();
        currentPlayer=1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_, menu);
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

    public void makeMove(View view)
    {
        int idBtn = view.getId();
        if (!isBtnFilled(idBtn)) {
            fillBtn(currentPlayer, idBtn);
        }
        if(gameMode == 2) {
            drawGrille();
            checkResults();
            grillePlainText.setText(grille.printPlaintTxtGrille());
            if (currentPlayer == 1) {
                currentPlayer = 2;
                curPlayerInfo.setText("Tour de X");
            } else {
                currentPlayer = 1;
                curPlayerInfo.setText("Tour de O");
            }
        }
        else
        {
            checkResults();
            makeIAMove();
            drawGrille();
            checkResults();
            grillePlainText.setText(grille.printPlaintTxtGrille());
        }
    }

    public void makeIAMove()
    {
        currentPlayer = 2;
        switch (grille.SearchForBestChoiceIA())
        {
            case 0: grille.setGrilleValue(0, 0, currentPlayer);break;
            case 1: grille.setGrilleValue(0, 1, currentPlayer);break;
            case 2: grille.setGrilleValue(0, 2, currentPlayer);break;
            case 3: grille.setGrilleValue(1, 0, currentPlayer);break;
            case 4: grille.setGrilleValue(1, 1, currentPlayer);break;
            case 5: grille.setGrilleValue(1, 2, currentPlayer);break;
            case 6: grille.setGrilleValue(2, 0, currentPlayer);break;
            case 7: grille.setGrilleValue(2, 1, currentPlayer);break;
            case 8: grille.setGrilleValue(2, 2, currentPlayer);break;
        }
        currentPlayer = 1;
    }

    private void checkResults()
    {
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        int res = grille.checkResults();
        if(res >= 0)
            CreateDialog(res);
    }

    private boolean isBtnFilled(int IDBtn)
    {
        Button btntoCheck = (Button) findViewById(IDBtn);
        if(btntoCheck.isEnabled() == true)
            return false;
        else
            return true;
    }

    private void fillBtn(int currentPlayer, int IDBtn)
    {
        Button btntoFill = (Button) findViewById(IDBtn);
        if(btntoFill == btn1)
            grille.setGrilleValue(0, 0, currentPlayer);
        if(btntoFill == btn2)
            grille.setGrilleValue(0, 1, currentPlayer);
        if(btntoFill == btn3)
            grille.setGrilleValue(0, 2, currentPlayer);
        if(btntoFill == btn4)
            grille.setGrilleValue(1, 0, currentPlayer);
        if(btntoFill == btn5)
            grille.setGrilleValue(1, 1, currentPlayer);
        if(btntoFill == btn6)
            grille.setGrilleValue(1, 2, currentPlayer);
        if(btntoFill == btn7)
            grille.setGrilleValue(2, 0, currentPlayer);
        if(btntoFill == btn8)
            grille.setGrilleValue(2, 1, currentPlayer);
        if(btntoFill == btn9)
            grille.setGrilleValue(2, 2, currentPlayer);
        btntoFill.setEnabled(false);
    }

    private void CreateDialog(int Mode)
    {
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        switch (Mode)
        {
            case 0 :
                dlg.setTitle("Egalité");
                dlg.setMessage("(-_-)");
                dlg.setNeutralButton("Recommencer",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                recreate();
                            }
                        });
                dlg.setPositiveButton("Quitter",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                finish();
                            }
                        });break;
            case 1 :
                dlg.setTitle("Victoire Joueur X");
                dlg.setMessage("Félicitations !");
                dlg.setPositiveButton("Quitter",
                    new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int which)
                        {
                            finish();
                        }
                    });break;
            case 2 :
                dlg.setTitle("Victoire Joueur O");
                dlg.setMessage("Félicitations !");
                dlg.setPositiveButton("Quitter",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });break;

        }
        dlg.create().show();
    }

    private void drawGrille()
    {
        btn1.setBackgroundResource(getDrawableForGrille(0, 0));
        btn2.setBackgroundResource(getDrawableForGrille(0, 1));
        btn3.setBackgroundResource(getDrawableForGrille(0, 2));
        btn4.setBackgroundResource(getDrawableForGrille(1, 0));
        btn5.setBackgroundResource(getDrawableForGrille(1, 1));
        btn6.setBackgroundResource(getDrawableForGrille(1, 2));
        btn7.setBackgroundResource(getDrawableForGrille(2, 0));
        btn8.setBackgroundResource(getDrawableForGrille(2, 1));
        btn9.setBackgroundResource(getDrawableForGrille(2, 2));
    }

    private int getDrawableForGrille(int i, int j)
    {
        int res = 0;
        switch (grille.getGrilleValue(i, j))
        {
            case 0: res = R.drawable.blanc; break;
            case 1: res = R.drawable.croix; break;
            case 2: res = R.drawable.rond; break;
        }
        return res;
    }
}
