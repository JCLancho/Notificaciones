package com.example.notificaciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView lblAcertadasValor, lblPrimerDigito, lblSegundoDigito,
            lblSignoOperacion, inputSolucion;

    private Button btnComprobar;

    private int resultadoCorrecto;

    private NotificationManager notificationManager;
    static final String CANAL_ID ="mi_canal";
    static final int NOTIFICACION_ID =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lblAcertadasValor = findViewById(R.id.lblAcertadasValor);
        lblPrimerDigito = findViewById(R.id.lblPrimerDigito);
        lblSegundoDigito = findViewById(R.id.lblSegundoDigito);
        lblSignoOperacion = findViewById(R.id.lblSignoOperacion);
        inputSolucion = findViewById(R.id.inputSolucion);
        btnComprobar = findViewById(R.id.btnComprobar);
        generarOperacion();
    }

    public void generarOperacion(){

        int signo = (int) (Math.random()*4);
        String[] signos = {"+", "-", "*", "/"};
        lblSignoOperacion.setText(signos[signo]);

        int num1 = (int) (Math.random()*100);
        int num2 = (int) ((Math.random()*99)+1);



        if(signo == 3){
            Log.i("que hago", "estoy dividiendo");
            while(num1 % num2 != 0){
                num1 = (int) (Math.random()*100);
                num2 = (int) ((Math.random()*99)+1);
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
            case "*":
                resultadoCorrecto = num1 * num2;
                break;
            case "/":
                resultadoCorrecto = num1 / num2;
                break;
        }
        Log.i("resultad", resultadoCorrecto+"");


    }

    public void comprobar(View v){

        if(!inputSolucion.getText().toString().equals("")) {
            int resultadoUsuario = Integer.parseInt(inputSolucion.getText().toString());
            if (resultadoCorrecto == resultadoUsuario) {
                lblAcertadasValor.setText((Integer.parseInt(lblAcertadasValor.getText().toString()) + 1) + "");
            }
            if (lblAcertadasValor.getText().toString().equals("10")) {
                notificar();
                btnComprobar.setEnabled(false);
                inputSolucion.setEnabled(false);
            } else {
                inputSolucion.setText("");
                generarOperacion();
            }
        }else{
            Toast toast = Toast.makeText(getApplicationContext(), "Introduce algun resultado", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void notificar(){

        //Creamos notificacion
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //Creamos el canal. SOLO puede hacerse en dispositivos con ver.8 o más.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CANAL_ID,
                    "Mis notificaciones",
                    NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("Descripción del canal");
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(MainActivity.this,CANAL_ID)
                .setSmallIcon(R.drawable.icono)
                .setContentTitle("Enhorabuena!!")
                .setContentText("Has acertado 10 operaciones!!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL);
        notificationManager.notify(NOTIFICACION_ID, notificacion.build());

    }


}
