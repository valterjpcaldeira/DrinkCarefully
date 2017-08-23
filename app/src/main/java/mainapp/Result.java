package mainapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;

/**
 * Created by NB20797 on 15/01/2015.
 */
public class Result
        extends Activity
{
    public static final String MyPREFERENCES = "MyPrefsDrinkCarefully";
    private static final String PREFS_ALCOOL = "alcool";
    private double alcool;

    private void newGame()
    {
        new MenuSobreNos().show(getFragmentManager(), "menu sobre nos");
    }

    protected void onCreate(Bundle paramBundle)
    {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(paramBundle);
        setContentView(R.layout.result);



        SharedPreferences localSharedPreferences = Result.this.getSharedPreferences(MyPREFERENCES, 0);
        String str = localSharedPreferences.getString(PREFS_ALCOOL, "0");

        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://www.facebook.com/drinkcarefully"))
                .setImageUrl(Uri.parse("https://scontent-mia1-1.xx.fbcdn.net/hphotos-xap1/v/t1.0-9/11949416_1642415646042164_9221008640956832839_n.jpg?oh=00aa9fe07acae279949c8b06372ce8c1&oe=56712A52"))
                .setContentTitle("DrinkCarefully")
                .setContentDescription("Hoje portei-me bem (" + str + ")!")
                .build();

        ShareButton shareButton = (ShareButton)findViewById(R.id.share);
        shareButton.setShareContent(content);

        ((TextView)findViewById(R.id.id_alcool)).setText(str + "");
        return;
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
