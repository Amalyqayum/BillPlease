package sg.edu.rp.c346.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView totalresult;
    TextView perpaxresult;
    Button btnSplit;
    Button btnReset;
    EditText etAmount;
    EditText etPax;
    EditText etDiscount;
    CheckBox checkSvs;
    CheckBox checkGst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalresult = findViewById(R.id.tvTotalBill);
        perpaxresult = findViewById(R.id.tvSplit);
        btnSplit = findViewById(R.id.buttonSplit);
        btnReset = findViewById(R.id.buttonReset);
        etAmount = findViewById(R.id.editTextAmount);
        etPax = findViewById(R.id.editTextPax);
        etDiscount = findViewById(R.id.editTextDiscount);
        checkSvs = findViewById(R.id.cbSVS);
        checkGst = findViewById(R.id.cbGST);

        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etAmount.getText().toString().trim().length() == 0){
                    return;
                }
                if(etPax.getText().toString().trim().length() == 0){
                    return;
                }

                double amt = Double.parseDouble(etAmount.getText().toString().trim());
                if(checkSvs.isChecked() && checkGst.isChecked()){
                    amt = amt * 1.17;
                }
                else if(checkSvs.isChecked() && !checkGst.isChecked()){
                    amt = amt * 1.1;
                }
                else if(checkGst.isChecked() && !checkSvs.isChecked()){
                    amt = amt * 1.07;
                }
                if(etDiscount.getText().toString().trim().length() > 0){
                    double discount = Double.parseDouble(etDiscount.getText().toString().trim());
                    amt = amt * (1-discount/100.0);
                }
                int pax = Integer.parseInt(etPax.getText().toString().trim());
                totalresult.setText("Total: $" + String.format("%.2f", amt));
                perpaxresult.setText("Each pays: $" + String.format("%.2f", amt/pax));
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etAmount.setText("");
                etPax.setText("");
                etDiscount.setText("");
            }
        });
    }
}
