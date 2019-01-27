package com.dgx.aplicacion.neva.puche.diego.personalizedkeyboard;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MyKeyboard extends LinearLayout implements View.OnClickListener{

    private Button Key_1, Key_2, Key_3, Key_4,
            Key_5, Key_6, Key_7, Key_8,
            Key_9, Key_0, Key_Slash, Key_Minus;
    private ImageButton Key_Delete,Key_Hide,Key_Next;

    private LinearLayout layT, lay3;


    private SparseArray<String> keyValues = new SparseArray<>();
    private InputConnection inputConnection;

    public MyKeyboard(Context context) {
        this(context, null, 0);
    }

    public MyKeyboard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        LayoutInflater.from(context).inflate(R.layout.pkeyboard, this, true);

        Key_Next = (ImageButton) findViewById(R.id.key_next);
        Key_Hide = (ImageButton) findViewById(R.id.key_hide);
        layT = (LinearLayout) findViewById(R.id.key_layT);
        lay3 = (LinearLayout) findViewById(R.id.key_lay3);

        Key_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nexttool.nextEditText();
            }
        });
        Key_Hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nexttool.HideKeyBoard();
            }
        });

        // Create a OnClickListener to avoid touching a widget in the background
        layT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        lay3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Key_1 = (Button) findViewById(R.id.tkey_1);
        Key_1.setOnClickListener(this);
        Key_2 = (Button) findViewById(R.id.tkey_2);
        Key_2.setOnClickListener(this);
        Key_3 = (Button) findViewById(R.id.tkey_3);
        Key_3.setOnClickListener(this);
        Key_4 = (Button) findViewById(R.id.tkey_4);
        Key_4.setOnClickListener(this);
        Key_5 = (Button) findViewById(R.id.tkey_5);
        Key_5.setOnClickListener(this);
        Key_6 = (Button) findViewById(R.id.tkey_6);
        Key_6.setOnClickListener(this);
        Key_7 = (Button) findViewById(R.id.tkey_7);
        Key_7.setOnClickListener(this);
        Key_8 = (Button) findViewById(R.id.tkey_8);
        Key_8.setOnClickListener(this);
        Key_9 = (Button) findViewById(R.id.tkey_9);
        Key_9.setOnClickListener(this);
        Key_0 = (Button) findViewById(R.id.tkey_0);
        Key_0.setOnClickListener(this);
        Key_Delete = (ImageButton) findViewById(R.id.tkey_delete);
        Key_Delete.setOnClickListener(this);
        Key_Slash = (Button) findViewById(R.id.tkey_slash);
        Key_Slash.setOnClickListener(this);
        Key_Minus = (Button) findViewById(R.id.tkey_minus);
        Key_Minus.setOnClickListener(this);

        keyValues.put(R.id.tkey_1, "1");
        keyValues.put(R.id.tkey_2, "2");
        keyValues.put(R.id.tkey_3, "3");
        keyValues.put(R.id.tkey_4, "4");
        keyValues.put(R.id.tkey_5, "5");
        keyValues.put(R.id.tkey_6, "6");
        keyValues.put(R.id.tkey_7, "7");
        keyValues.put(R.id.tkey_8, "8");
        keyValues.put(R.id.tkey_9, "9");
        keyValues.put(R.id.tkey_0, "0");
        keyValues.put(R.id.tkey_slash, "/");
        keyValues.put(R.id.tkey_minus, "-");
    }

    @Override
    public void onClick(View view) {
        if (inputConnection == null)
            return;

        switch (view.getId()){
            case R.id.tkey_delete:
                CharSequence selectedText = inputConnection.getSelectedText(0);

                if (TextUtils.isEmpty(selectedText)) {
                    inputConnection.deleteSurroundingText(1, 0);
                } else {
                    inputConnection.commitText("", 1);
                }
                break;
            default:
                String value = keyValues.get(view.getId());
                inputConnection.commitText(value, 1);
        }

    }

    public void setInputConnection(InputConnection ic) {
        inputConnection = ic;
    }

    private NextTool nexttool;
    public void setListenerNT(NextTool listener){
        this.nexttool = listener;
    }

}
