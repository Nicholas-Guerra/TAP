package com.software_engineering.tap.Main_Notifications_Settings;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.software_engineering.tap.R;



public class DialogFragment_About extends DialogFragment
{
    View mView;
    TextView textAbout;
    ImageView close;
    private Listener mListener;

    public DialogFragment_About() {
        //Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.dialog_fragment_about, container, false);

        close = mView.findViewById(R.id.about_close_button);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
                    public void onClick(View view){
                dismiss();
            }
        });

        textAbout = mView.findViewById(R.id.aboutinfo); //id in dialog_fragment_about_xml
        textAbout.setText(Html.fromHtml(getString(R.string.about_info)));

        return mView;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        mListener.onDialogDismissed();
    }

}//end DialogFragment_about class
