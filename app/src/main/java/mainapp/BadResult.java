package mainapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;

public class BadResult
        extends Activity
{
    public static final String MyPREFERENCES = "MyPrefsDrinkCarefully";
    private static final String PREFS_ALCOOL = "alcool";
    private static final String PREFS_CITY = "cidade";
    private static final String PREFS_TIME = "tempo";
    private static final String TAXI_AVEIRO = "tel:234385799";
    private static final String TAXI_COIMBRA = "tel:239499090";
    private static final String TAXI_EVORA = "tel:266734734";
    private static final String TAXI_FARO = "tel:289827203";
    private static final String TAXI_LISBOA = "tel:219362113";
    private static final String TAXI_PORTO = "tel:220997336";
    private static String numeroEscolhido = "219362113";
    private double alcool;
    private double tempo;

    private void createSobreWindow()
    {
        new MenuSobreNos().show(getFragmentManager(), "Sobre Nós");
    }

    private int returnHours(double paramDouble)
    {
        return (int)Math.floor(paramDouble);
    }

    private int returnMins(Double paramDouble)
    {
        return (int)(60.0D * (paramDouble.doubleValue() - Math.floor(paramDouble.doubleValue())));
    }

    protected void onCreate(Bundle paramBundle)
    {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(paramBundle);
        setContentView(R.layout.bad_result);
        SharedPreferences localSharedPreferences = BadResult.this.getSharedPreferences(MyPREFERENCES, 0);
        String cidadeString;
        String taxaAlcool;
        String tempoDeEspera;

        taxaAlcool = localSharedPreferences.getString("alcool", "1");
        tempoDeEspera = localSharedPreferences.getString("tempo", "1");


        if (!localSharedPreferences.getString("cidade", "Lisboa").isEmpty())
        {
            cidadeString = localSharedPreferences.getString("cidade", "Lisboa");
            if (cidadeString.equalsIgnoreCase("Aveiro")) {
                numeroEscolhido = TAXI_AVEIRO;
            }else{
                if (cidadeString.equalsIgnoreCase("Coimbra")) {
                    numeroEscolhido = TAXI_COIMBRA;
                }else{
                    if (cidadeString.equalsIgnoreCase("Evora")) {
                        numeroEscolhido = TAXI_EVORA;
                    }else{
                        if (cidadeString.equalsIgnoreCase("Faro")) {
                            numeroEscolhido = TAXI_FARO;
                        }else{
                            if (cidadeString.equalsIgnoreCase("Porto")) {
                                numeroEscolhido = TAXI_PORTO;
                            }else{
                                numeroEscolhido = TAXI_LISBOA;
                            }
                        }
                    }
                }
            }

        }

        this.tempo = (Math.floor(100.0D * Double.parseDouble(tempoDeEspera)) / 100.0D);
        TextView textTempoDeEspera = (TextView)findViewById(R.id.time);
        String minutosString = returnMins(Double.valueOf(this.tempo)) + "";
        if (minutosString.length() == 1) {
            minutosString = "0" + minutosString;
        }
        textTempoDeEspera.setText(returnHours(this.tempo) + "h" + minutosString + "m");
        TextView textAlcoolTax = (TextView)findViewById(R.id.id_alcool);
        if (Double.valueOf(taxaAlcool) > 10)
        {
            TextView textAviso = (TextView)findViewById(R.id.textoAviso);
            textAviso.clearComposingText();
            textAviso.setText("Pára de brincar e não conduzas!!");
        }
        textAlcoolTax.setText(taxaAlcool + "");
        ((ImageButton)findViewById(R.id.taxiButton)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                new BadResult.JanelaConfirmaLigacao().show(BadResult.this.getFragmentManager(), "");
            }
        });

        ShareLinkContent content = new ShareLinkContent.Builder()
            .setContentUrl(Uri.parse("https://www.facebook.com/drinkcarefully"))
                .setImageUrl(Uri.parse("https://scontent-mia1-1.xx.fbcdn.net/hphotos-xpf1/v/t1.0-9/10421393_1642415649375497_3799316460896710040_n.jpg?oh=adb2bfafcd0cfc53def45fde62da49a7&oe=567CE9AB"))
                .setContentTitle("DrinkCarefully")
                .setContentDescription("É melhor não levar o carro hoje ("+taxaAlcool+")!")
                        .build();
        ShareButton shareButton = (ShareButton)findViewById(R.id.share);
        shareButton.setShareContent(content);

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

    public class JanelaConfirmaLigacao extends DialogFragment
    {
        public Dialog onCreateDialog(Bundle paramBundle)
        {
            AlertDialog.Builder localBuilder = new AlertDialog.Builder(getActivity());
            localBuilder.setMessage("Tem a certeza que pretende ligar para o táxi(" + BadResult.numeroEscolhido.substring(4) + ")?").setPositiveButton(R.string.sim_taxi, new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
                {
                    Intent localIntent = new Intent("android.intent.action.CALL");
                    localIntent.setData(Uri.parse(BadResult.numeroEscolhido));
                    BadResult.JanelaConfirmaLigacao.this.startActivity(localIntent);
                }
            }).setNegativeButton(R.string.nao_taxi, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                }
            });
            return localBuilder.create();
        }
    }
}
