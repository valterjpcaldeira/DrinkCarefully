package mainapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class Team extends Activity
{
    public static final String MyPREFERENCES = "MyPrefsDrinkCarefully";
    private static final String PREFS_CITY = "cidade";
    private static final String PREFS_PESO = "peso";
    private static final String PREFS_SEXO = "sexo";
    private final String DefaultPasswordValue = "";
    private final String DefaultUnameValue = "";
    private String PasswordValue;
    private String UnameValue;

    public static void hideSoftKeyboard(Activity paramActivity)
    {
        ((InputMethodManager)paramActivity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(paramActivity.getCurrentFocus().getWindowToken(), 0);
    }

    private void newGame()
    {
        new MenuSobreNos().show(getFragmentManager(), "sobreNos");
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        //Set layout
        setContentView(R.layout.team);
        //setupUI(findViewById(2131361844));
        //getResources().getDrawable(2130837558).setAlpha(50);

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
                createSobreWindow();
                return true;
            default:
                return super.onOptionsItemSelected(paramMenuItem);

        }

    }

    private void createSobreWindow()
    {
        new MenuSobreNos().show(getFragmentManager(), "Sobre Nós");
    }

    public void setupUI(View paramView)
    {
        if (!(paramView instanceof EditText)) {
            paramView.setOnTouchListener(new View.OnTouchListener()
            {
                public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
                {
                    Team.hideSoftKeyboard(Team.this);
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
}

