package mainapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

/**
 * Created by NB20797 on 15/01/2015.
 */
public class Next
        extends Activity
{
    public static final String MyPREFERENCES = "MyPrefsDrinkCarefully";
    private static final String PREFS_ALCOOL = "alcool";
    private static final String PREFS_PESO = "peso";
    private static final String PREFS_SEXO = "sexo";
    private static final String PREFS_TIME = "tempo";
    private final double BEBIDA_BRANCA_GRAMAS = 16.199999999999999D;
    private double absintoG = 10.4D;
    private int absintoQ;
    private double aguardenteG = 8.800000000000001D;
    private int aguardenteQ;
    private int bebBrancaQ;
    private double cervejaG = 13.199999999999999D;
    private int cervejaQ;
    private double champanheG = 12.32D;
    private int champanheQ;
    private int peso;
    private double rumG = 10.32D;
    private int rumQ;
    private String sex;
    private double tempo;
    private double time;
    private double vinhoG = 16.800000000000001D;
    private int vinhoQ;
    private double vodkaG = 11.199999999999999D;
    private int vodkaQ;

    private double calculateAlcool()
    {
        int numerobebidas = this.bebBrancaQ + this.vinhoQ + this.cervejaQ + this.champanheQ + this.vodkaQ + this.aguardenteQ + this.rumQ + this.absintoQ;
        this.time += (0.3D * numerobebidas);
        double d1 = this.BEBIDA_BRANCA_GRAMAS * this.bebBrancaQ + this.vinhoQ * this.vinhoG + this.cervejaQ * this.cervejaG + this.champanheG * this.champanheQ + this.vodkaQ * this.vodkaG + this.aguardenteG * this.aguardenteQ + this.rumQ * this.rumG + this.absintoQ * this.absintoG;
        double num = 0.68;
        if (this.sex.equalsIgnoreCase("feminino")) {
            num = 0.55;
        }
        double d2 = d1 / (num * this.peso);
        double d3 = d2 - 0.2D * this.time;
        if (d3 < 0.0D) {
            d3 = 0.0D;
        }
        return round(d3,2);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    private double convertTimeToHour(int horas, int minutos)
    {
        return minutos / 60.0D + horas;
    }

    private double getGramas(double paramDouble1, double paramDouble2)
    {
        return 0.8D * (paramDouble2 * (paramDouble1 * 10.0D));
    }

    public static void hideSoftKeyboard(Activity paramActivity)
    {
        ((InputMethodManager)paramActivity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(paramActivity.getCurrentFocus().getWindowToken(), 0);
    }

    private void newGame()
    {
        new MenuSobreNos().show(getFragmentManager(), "menu sobre nos");
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(R.layout.next);
        setupUI(findViewById(R.id.scrollView1));
        SharedPreferences localSharedPreferences = Next.this.getSharedPreferences(MyPREFERENCES, 0);
        this.peso = Integer.parseInt(localSharedPreferences.getString(PREFS_PESO, "0"));
        this.sex = localSharedPreferences.getString(PREFS_SEXO, "feminino");
        Button buttonCalculate = (Button)findViewById(R.id.calculate);
        //getResources().getDrawable(R.id.fund).setAlpha(50);
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                //Horas
                EditText horasText = (EditText) findViewById(R.id.horas);
                int horas = 0;
                String aux = String.valueOf(horasText.getText());
                if(!aux.isEmpty()) {
                    horas = Integer.parseInt(aux);
                }

                //Minutos
                EditText minutosText = (EditText) findViewById(R.id.minutos);
                aux = String.valueOf(minutosText.getText());
                int minutos = 0;
                if(!aux.isEmpty()) {
                    minutos = Integer.parseInt(aux);
                }

                time = convertTimeToHour(horas,minutos);

                EditText cervejaTXT = (EditText) findViewById(R.id.cerveja_qtd);
                aux = String.valueOf(cervejaTXT.getText());
                if(aux.isEmpty()){
                    cervejaQ = 0;
                }else{
                    cervejaQ = Integer.parseInt(aux);
                }


                cervejaTXT = (EditText) findViewById(R.id.vinho_qtd);
                aux = String.valueOf(cervejaTXT.getText());
                if(aux.isEmpty()){
                    vinhoQ = 0;
                }else{
                    vinhoQ = Integer.parseInt(aux);
                }

                cervejaTXT = (EditText) findViewById(R.id.champanheQ);
                aux = String.valueOf(cervejaTXT.getText());
                if(aux.isEmpty()){
                    champanheQ = 0;
                }else{
                    champanheQ = Integer.parseInt(aux);
                }


                cervejaTXT = (EditText) findViewById(R.id.vodkaQ);
                aux = String.valueOf(cervejaTXT.getText());
                if(aux.isEmpty()){
                    vodkaQ = 0;
                }else{
                    vodkaQ = Integer.parseInt(aux);
                }


                cervejaTXT = (EditText) findViewById(R.id.rumQ);
                aux = String.valueOf(cervejaTXT.getText());
                if(aux.isEmpty()){
                    rumQ = 0;
                }else{
                    rumQ = Integer.parseInt(aux);
                }


                cervejaTXT = (EditText) findViewById(R.id.aguardenteQ);
                aux = String.valueOf(cervejaTXT.getText());
                if(aux.isEmpty()){
                    aguardenteQ = 0;
                }else{
                    aguardenteQ = Integer.parseInt(aux);
                }

                cervejaTXT = (EditText) findViewById(R.id.absuntoQ);
                aux = String.valueOf(cervejaTXT.getText());
                if(aux.isEmpty()){
                    absintoQ = 0;
                }else{
                    absintoQ = Integer.parseInt(aux);
                }

                cervejaTXT = (EditText) findViewById(R.id.beb_branca_qtd);
                aux = String.valueOf(cervejaTXT.getText());
                if(aux.isEmpty()){
                    bebBrancaQ = 0;
                }else{
                    bebBrancaQ = Integer.parseInt(aux);
                }

                double bac = calculateAlcool();

                SharedPreferences.Editor localEditor = Next.this.getSharedPreferences(MyPREFERENCES, 0).edit();
                localEditor.putString(PREFS_ALCOOL, bac+"");
                localEditor.commit();
                if(bac >= 0.5){
                    localEditor.putString(PREFS_TIME, time+"");
                    Intent localIntent2 = new Intent(Next.this, BadResult.class);
                    Next.this.startActivity(localIntent2);
                }else{
                    Intent localIntent2 = new Intent(Next.this, Result.class);
                    Next.this.startActivity(localIntent2);
                }
                localEditor.commit();

               return;
            }
        });
    }



    public void setupUI(View paramView)
    {
        if (!(paramView instanceof EditText)) {
            paramView.setOnTouchListener(new View.OnTouchListener()
            {
                public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
                {
                    Next.hideSoftKeyboard(Next.this);
                    return false;
                }
            });
        }
        if ((paramView instanceof ViewGroup)) {
            for (int i = 0; i < ((ViewGroup)paramView).getChildCount(); i++) {
                setupUI(((ViewGroup)paramView).getChildAt(i));
            }
        }
    }


    public boolean onCreateOptionsMenu(Menu paramMenu)
    {
        getMenuInflater().inflate(R.menu.menu_left, paramMenu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem paramMenuItem)
    {
        switch (paramMenuItem.getItemId())
        {

            case R.id.sobre:
                startActivity(new Intent(this, Team.class));
                return true;
            case R.id.perfil:
                startActivity(new Intent(this, PerfilForm.class));
                return true;
            default:
                return super.onOptionsItemSelected(paramMenuItem);

        }

    }

    private void createSobreWindow()
    {
        new MenuSobreNos().show(getFragmentManager(), "Sobre Nós");
    }
}
