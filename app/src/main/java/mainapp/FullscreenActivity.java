package mainapp;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by NB20797 on 15/01/2015.
 */
public class FullscreenActivity
        extends Activity
{
    private static final String TAG = "FullscreenActivity";
    // Custom button
    private Button fbbutton;

    // Creating Facebook CallbackManager Value
    public static CallbackManager callbackmanager;

    private static final boolean AUTO_HIDE = true;
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
    private static final int HIDER_FLAGS = 6;
    public static final String MyPREFERENCES = "MyPrefsDrinkCarefully";
    private static final String PREFS_IDADE = "peso";
    private static final boolean TOGGLE_ON_CLICK = true;
    private Button btnLogin;
    View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener()
    {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
            FullscreenActivity.this.delayedHide(3000);
            return false;
        }
    };

    //private SystemUiHider mSystemUiHider;


    Handler mHideHandler = new Handler();
    Runnable mHideRunnable = new Runnable()
    {
        public void run()
        {
          // mSystemUiHider.hide();
        }
    };


    private void delayedHide(int paramInt) {}



    private void newGame()
    {
        new MenuSobreNos().show(getFragmentManager(), "menu sobre nos");
    }

    private void setListeners()
    {
        this.btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                String peso = FullscreenActivity.this.getSharedPreferences(MyPREFERENCES, 0).getString("peso", "0");
                if (peso.equalsIgnoreCase("0")) {
                    Intent localIntent1 = new Intent(FullscreenActivity.this, PerfilForm.class);
                    FullscreenActivity.this.startActivity(localIntent1);
                    return;
                }
                Intent localIntent2 = new Intent(FullscreenActivity.this, Next.class);
                FullscreenActivity.this.startActivity(localIntent2);
            }
        });
    }

    protected void onCreate(Bundle paramBundle)
    {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_fullscreen);
        final View localView = findViewById(R.id.fullscreen_content);

        printKeyHash(FullscreenActivity.this);

        Button buttonCalculate = (Button)findViewById(R.id.entrar);
        //getResources().getDrawable(R.id.fund).setAlpha(50);
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {

                Intent localIntent1 = new Intent(FullscreenActivity.this, PerfilForm.class);
                FullscreenActivity.this.startActivity(localIntent1);
                return;
            }
        });

        /*
        LoginButton fbbutton = (LoginButton) localView.findViewById(R.id.authButton);

        callbackmanager = CallbackManager.Factory.create();

        // Set permissions
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "user_photos", "public_profile"));

        LoginManager.getInstance().registerCallback(callbackmanager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        System.out.println("Success");
                        GraphRequest.newMeRequest(
                                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject json, GraphResponse response) {
                                        if (response.getError() != null) {
                                            // handle error
                                            System.out.println("ERROR");
                                        } else {
                                            System.out.println("Success");
                                            try {

                                                String jsonresult = String.valueOf(json);
                                                System.out.println("JSON Result" + jsonresult);

                                                String str_email = json.getString("email");
                                                String str_id = json.getString("id");
                                                String str_firstname = json.getString("first_name");
                                                String str_lastname = json.getString("last_name");

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }

                                }).executeAsync();

                    }

                    @Override
                    public void onCancel() {
                        Log.d("", "On cancel");
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d("", error.toString());
                    }
                });*/

    }

    // Private method to handle Facebook login and callback
    private void onFblogin()
    {
        callbackmanager = CallbackManager.Factory.create();

        // Set permissions
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "user_photos", "public_profile"));

        LoginManager.getInstance().registerCallback(callbackmanager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        System.out.println("Success");
                        GraphRequest.newMeRequest(
                                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject json, GraphResponse response) {
                                        if (response.getError() != null) {
                                            // handle error
                                            System.out.println("ERROR");
                                        } else {
                                            System.out.println("Success");
                                            try {

                                                String jsonresult = String.valueOf(json);
                                                System.out.println("JSON Result" + jsonresult);

                                                String str_email = json.getString("email");
                                                String str_id = json.getString("id");
                                                String str_firstname = json.getString("first_name");
                                                String str_lastname = json.getString("last_name");

                                                Intent localIntent1 = new Intent(FullscreenActivity.this, PerfilForm.class);
                                                FullscreenActivity.this.startActivity(localIntent1);
                                                return;

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }

                                }).executeAsync();

                    }

                    @Override
                    public void onCancel() {
                        Log.d("", "On cancel");
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d("", error.toString());
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackmanager.onActivityResult(requestCode, resultCode, data);
    }

    protected void onPostCreate(Bundle paramBundle)
    {
        super.onPostCreate(paramBundle);
        delayedHide(100);
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

    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", context.getApplicationContext().getPackageName());

            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        }
        catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        return key;
    }

    private void createSobreWindow()
    {
        new MenuSobreNos().show(getFragmentManager(), "Sobre Nós");
    }
}
