package com.dgx.aplicacion.neva.puche.diego.personalizedkeyboard;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements NextTool{

    MyKeyboard keyboard;
    boolean GetEditFocus = false;
    boolean FirstAnimetion = false;
    int NumOfEdit = 1;
    EditText Ed1,Ed2,Ed3,Ed4,Ed5,Ed6;
    InputConnection ic;

    private ObjectAnimator animatorAlpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Ed1 = findViewById(R.id.Edit_1);
        Ed2 = findViewById(R.id.Edit_2);
        Ed3 = findViewById(R.id.Edit_3);
        Ed4 = findViewById(R.id.Edit_4);
        Ed5 = findViewById(R.id.Edit_5);
        Ed6 = findViewById(R.id.Edit_6);

        Ed1.setSingleLine();
        Ed2.setSingleLine();
        Ed3.setSingleLine();
        Ed4.setSingleLine();
        Ed5.setSingleLine();
        Ed6.setSingleLine();

        KeyInit();
        keyboard.setListenerNT(this);
    }

    private void KeyInit(){

        keyboard = (MyKeyboard) findViewById(R.id.Tkeyboard);

        Ed1.setRawInputType(InputType.TYPE_CLASS_TEXT);
        Ed1.setTextIsSelectable(true);
        Ed2.setRawInputType(InputType.TYPE_CLASS_TEXT);
        Ed2.setTextIsSelectable(true);
        Ed3.setRawInputType(InputType.TYPE_CLASS_TEXT);
        Ed3.setTextIsSelectable(true);
        Ed4.setRawInputType(InputType.TYPE_CLASS_TEXT);
        Ed4.setTextIsSelectable(true);
        Ed5.setRawInputType(InputType.TYPE_CLASS_TEXT);
        Ed5.setTextIsSelectable(true);
        Ed6.setRawInputType(InputType.TYPE_CLASS_TEXT);
        Ed6.setTextIsSelectable(true);

        Ed1.setOnFocusChangeListener(focusListener);
        Ed2.setOnFocusChangeListener(focusListener);
        Ed3.setOnFocusChangeListener(focusListener);
        Ed4.setOnFocusChangeListener(focusListener);
        Ed5.setOnFocusChangeListener(focusListener);
        Ed6.setOnFocusChangeListener(focusListener);

    }

    private View.OnFocusChangeListener focusListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus){
                if(!FirstAnimetion){
                    AnimateOpen();
                }
                FirstAnimetion = true;
                GetEditFocus = true;
                switch (v.getId()){
                    case R.id.Edit_1:
                        ic = Ed1.onCreateInputConnection(new EditorInfo());
                        NumOfEdit = 1;
                        break;
                    case R.id.Edit_2:
                        ic = Ed2.onCreateInputConnection(new EditorInfo());
                        NumOfEdit = 2;
                        break;
                    case R.id.Edit_3:
                        ic = Ed3.onCreateInputConnection(new EditorInfo());
                        NumOfEdit = 3;
                        break;
                    case R.id.Edit_4:
                        ic = Ed4.onCreateInputConnection(new EditorInfo());
                        NumOfEdit = 4;
                        break;
                    case R.id.Edit_5:
                        ic = Ed5.onCreateInputConnection(new EditorInfo());
                        NumOfEdit = 5;
                        break;
                    case R.id.Edit_6:
                        ic = Ed6.onCreateInputConnection(new EditorInfo());
                        NumOfEdit = 6;
                        break;
                }
                keyboard.setInputConnection(ic);
            } else {
                GetEditFocus = false;
            }
        }
    };

    @Override
    public void onBackPressed() {
        if(GetEditFocus){
            GetEditFocus = false;
            FirstAnimetion = false;
            keyboard.setVisibility(View.GONE);
            ClearAllFocus();
        }else{
            finish();
        }
    }

    private void AnimateOpen(){
        animatorAlpha = ObjectAnimator.ofFloat(keyboard, View.ALPHA,0.0f, 1.0f);
        animatorAlpha.setDuration(500);
        AnimatorSet animatorSetAlpha = new AnimatorSet();
        animatorSetAlpha.playTogether(animatorAlpha);
        animatorSetAlpha.start();
        Log.d("HOLA","sii");

        keyboard.setVisibility(View.VISIBLE);
    }

    @Override
    public void nextEditText() {
        switch (NumOfEdit){
            case 1:
                NumOfEdit = 2;
                Ed2.requestFocus();
                break;
            case 2:
                NumOfEdit = 3;
                Ed3.requestFocus();
                break;
            case 3:
                NumOfEdit = 4;
                Ed4.requestFocus();
                break;
            case 4:
                NumOfEdit = 5;
                Ed5.requestFocus();
                break;
            case 5:
                NumOfEdit = 6;
                Ed6.requestFocus();
                break;
            case 6:
                GetEditFocus = false;
                FirstAnimetion = false;
                keyboard.setVisibility(View.GONE);
                Ed6.clearFocus();
                break;
        }
    }

    @Override
    public void HideKeyBoard() {
        GetEditFocus = false;
        FirstAnimetion = false;
        keyboard.setVisibility(View.GONE);
        ClearAllFocus();
    }


    private void ClearAllFocus(){
        Ed1.clearFocus();
        Ed2.clearFocus();
        Ed3.clearFocus();
        Ed4.clearFocus();
        Ed5.clearFocus();
        Ed6.clearFocus();
    }
}
