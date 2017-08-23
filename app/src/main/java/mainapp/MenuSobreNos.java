package mainapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

/**
 * Created by NB20797 on 04/01/2015.
 */
public class MenuSobreNos
        extends DialogFragment
{
    public Dialog onCreateDialog(Bundle paramBundle)
    {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(getActivity());
        localBuilder.setMessage(R.string.sobre_nos);
        return localBuilder.create();
    }
}
