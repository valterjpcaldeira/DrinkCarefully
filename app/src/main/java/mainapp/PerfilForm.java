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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class PerfilForm extends Activity
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
        setContentView(R.layout.perfilform);
        //setupUI(findViewById(2131361844));
        //getResources().getDrawable(2130837558).setAlpha(50);

        EditText pesoText = (EditText) findViewById(R.id.peso);
        int radioSexId = ((RadioGroup) findViewById(R.id.radioSex)).getCheckedRadioButtonId();
        SharedPreferences localSharedPreferences = PerfilForm.this.getSharedPreferences(MyPREFERENCES, 0);
        Button botaoGuardar = (Button) findViewById(R.id.next);
        String localidade;
        Spinner localSpinner = (Spinner) findViewById(R.id.spinner1);
        String pesoString;
        int pesoInt;

        //Obter Cidade, default = Liosboa
        localidade = localSharedPreferences.getString(PREFS_CITY, "");
        if (localidade.isEmpty()) {
            localidade = "Lisboa";
            localSpinner.setSelection(4);
        }else{
            if (localidade.equalsIgnoreCase("Coimbra"))
            {
                localSpinner.setSelection(1);
            }else {
                if (localidade.equalsIgnoreCase("Évora"))
                {
                    localSpinner.setSelection(2);
                }else {
                    if (localidade.equalsIgnoreCase("Faro"))
                    {
                        localSpinner.setSelection(3);
                    }else {
                        if (localidade.equalsIgnoreCase("Lisboa"))
                        {
                            localSpinner.setSelection(4);
                        }else {
                            localSpinner.setSelection(5);
                        }
                    }
                }
            }
        }
        //Obter peso, default = ""
        pesoString = localSharedPreferences.getString(PREFS_PESO, "");
        if (pesoString.isEmpty()) {
            pesoInt = 0;
        }
        pesoText.setText(pesoString, TextView.BufferType.EDITABLE);

        //Obter sexo, default=masculino
        String sexoString = localSharedPreferences.getString("sexo", "");
        if (sexoString.equals("feminino")) {
            ((RadioButton) findViewById(R.id.radioButtonF)).setChecked(true);
        } else {
            ((RadioButton) findViewById(R.id.radioButtonM)).setChecked(true);
        }

        //O que fazer qd GUARDA
        botaoGuardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                //RadioGroup localRadioGroup = (RadioGroup)PerfilForm.this.findViewById(2131361847);
                SharedPreferences.Editor localEditor = PerfilForm.this.getSharedPreferences(MyPREFERENCES, 0).edit();
                //localRadioGroup.getCheckedRadioButtonId();
                if (((RadioButton) PerfilForm.this.findViewById(R.id.radioButtonF)).isChecked()) {
                    localEditor.putString("sexo", "feminino");
                } else {
                    localEditor.putString("sexo", "masculino");
                }
                EditText pesoText;
                localEditor.putString("cidade", ((Spinner) PerfilForm.this.findViewById(R.id.spinner1)).getSelectedItem().toString());
                pesoText = (EditText) PerfilForm.this.findViewById(R.id.peso);
                String pesoString = String.valueOf(pesoText.getText());
                if (pesoString.isEmpty()) {
                    pesoText.setError("Precisa de inserir o seu pêso");
                    return;
                }
                if (Integer.parseInt(pesoString) < 30) {
                    pesoText.setError("Pêso demasiado baixo");
                    return;
                }
                localEditor.putString("peso", pesoString);
                localEditor.commit();
                Intent localIntent = new Intent(PerfilForm.this, Next.class);
                PerfilForm.this.startActivity(localIntent);
            }
        });
        return;
    }



    public boolean onCreateOptionsMenu(Menu paramMenu)
    {
        getMenuInflater().inflate(R.menu.menu_left_meu_perfil, paramMenu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem paramMenuItem)
    {
        switch (paramMenuItem.getItemId())
        {
            case R.id.sobre:
                startActivity(new Intent(this, Team.class));
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
                    PerfilForm.hideSoftKeyboard(PerfilForm.this);
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

