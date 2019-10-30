package com.example.notificaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView lblAcertadasValor, lblPrimerDigito, lblSegundoDigito,
            lblSignoOperacion, inputSolucion;

    private int resultadoCorrecto, resultadoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lblAcertadasValor = findViewById(R.id.lblAcertadasValor);
        lblPrimerDigito = findViewById(R.id.lblPrimerDigito);
        lblSegundoDigito = findViewById(R.id.lblSegundoDigito);
        lblSignoOperacion = findViewById(R.id.lblSignoOperacion);
        inputSolucion = findViewById(R.id.inputSolucion);
        generarOperacion(this.findViewById(R.id.Vista));
    }

    public void generarOperacion(View v){

        int signo = (int) (Math.random()*4);
        String[] signos = {"+","-","*","/"};
        lblSignoOperacion.setText(signos[signo]);

        int num1 = (int) (Math.random()*100);
        int num2 = (int) (Math.random()*100);

        if(signo == 3){
            while(num1 % num2 != 0){
                num1 = (int) (Math.random()*100);
                num2 = (int) (Math.random()*100);
            }
        }



        lblPrimerDigito.setText(num1+"");
        lblSegundoDigito.setText(num2+"");

        switch (signos[signo]){
            case "+":
                resultadoCorrecto = num1 + num2;
                break;
            case "-":
                resultadoCorrecto = num1 - num2;
                break;
            case "x":
                resultadoCorrecto = num1 * num2;
                break;
            case "/":
                resultadoCorrecto = num1 / num2;
                break;
        }
        Log.i("resultad", resultadoCorrecto+"");


    }

    public void comprobar(View v){

        if(resultadoCorrecto == resultadoUsuario){
            lblAcertadasValor.setText((Integer.parseInt(lblAcertadasValor.getText().toString())+1)+"");
        }

    }


}
