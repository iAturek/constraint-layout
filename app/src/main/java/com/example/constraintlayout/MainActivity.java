package com.example.constraintlayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

public class MainActivity extends AppCompatActivity {

    private EditText etAmount;
    private SeekBar sbPercent;
    private TextView tvPercentValue;
    private TextView tvResult;
    private CheckBox cbRound;
    private Group groupResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmount = findViewById(R.id.etAmount);
        sbPercent = findViewById(R.id.sbPercent);
        tvPercentValue = findViewById(R.id.tvPercentValue);
        tvResult = findViewById(R.id.tvResult);
        cbRound = findViewById(R.id.cbRound);
        groupResult = findViewById(R.id.groupResult);

        Button btnCalculate = findViewById(R.id.btnCalculate);
        Button btnClear = findViewById(R.id.btnClear);

        showPercent(sbPercent.getProgress());

        sbPercent.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                showPercent(progress);
            }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) { }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) { }
        });

    }
    private void showPercent(int percent) {
        tvPercentValue.setText(getString(R.string.percent_format, percent));
    }
    private void calculate() {
        String text = etAmount.getText().toString().trim();

        if (text.isEmpty()) {
            Toast.makeText(this, R.string.error_empty_amount, Toast.LENGTH_SHORT).show();
            return;
        }

        double amount = Double.parseDouble(text.replace(',', '.'));
        double total = amount + amount * sbPercent.getProgress() / 100.0;

        if (cbRound.isChecked()) {
            total = Math.ceil(total);
        }

        tvResult.setText(getString(R.string.result_format, total));
        groupResult.setVisibility(View.VISIBLE);
    }
    private void clear() {
        etAmount.setText("");
        sbPercent.setProgress(10);
        cbRound.setChecked(false);
        groupResult.setVisibility(View.GONE);
    }
}