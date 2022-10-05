package com.kevindinh.dialog08082022;

import static android.graphics.Color.argb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    Button btnAlertDialog, btnDialog;

    View v_Color;
    TextView hexValue, rgbValue;
    SeekBar sAlfa, sRed, sGreen, sBlue;
    Button btnCancel, btnOK;
    int alfa = 255, red = 0, green = 0, blue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAlertDialog = findViewById(R.id.button_alert_dialog);
        btnDialog = findViewById(R.id.button_dialog);

        btnAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Thông báo!!!");
                builder.setMessage("Đã có bản cập nhật mới");
                builder.setIcon(android.R.drawable.star_on);
                builder.setCancelable(false);

                builder.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });

        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.layout_picker_color_dialog);

                Window window = dialog.getWindow();

                if (window != null) {
                    window.setGravity(Gravity.CENTER);
                    window.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                }

                v_Color = (View) btnDialog.findViewById(R.id.view);
                hexValue = (TextView) btnDialog.findViewById(R.id.hexValue);
                rgbValue = (TextView) btnDialog.findViewById(R.id.rgbValue);

                sAlfa = btnDialog.findViewById(R.id.s_Alfa);
                sRed = btnDialog.findViewById(R.id.s_Red);
                sGreen = btnDialog.findViewById(R.id.s_Green);
                sBlue = btnDialog.findViewById(R.id.s_Blue);

                btnOK = btnDialog.findViewById(R.id.btnOK);
                btnCancel = btnDialog.findViewById(R.id.btnCancel);

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                        dialog.dismiss();
                    }
                });
                btnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "Pick Color Success", Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.show();
            }
        });

        sAlfa.setOnSeekBarChangeListener(this);
        sRed.setOnSeekBarChangeListener(this);
        sGreen.setOnSeekBarChangeListener(this);
        sBlue.setOnSeekBarChangeListener(this);

    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

        switch (seekBar.getId()) {

            case R.id.s_Red:
                red = i;
                break;
            case R.id.s_Green:
                green = i;
                break;
            case R.id.s_Blue:
                blue = i;
                break;
            case R.id.s_Alfa:
                alfa = i;
                break;
        }
        v_Color.setBackgroundColor(argb(alfa, red, green, blue));
        String code = HexCode(alfa, red, green, blue);
        rgbValue.setText(String.format("(%d,%d,%d,%d)", alfa, red, green, blue));
        hexValue.setText(code.toUpperCase());
    }

    private String HexCode(int alfa, int red, int green, int blue) {
        String code;
        code = "#";
        code += Integer.toHexString(alfa);
        code += Integer.toHexString(red);
        code += Integer.toHexString(green);
        code += Integer.toHexString(blue);
        return code;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}