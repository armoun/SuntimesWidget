/**
    Copyright (C) 2014 Forrest Guice
    This file is part of SuntimesWidget.

    SuntimesWidget is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    SuntimesWidget is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with SuntimesWidget.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.forrestguice.suntimeswidget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class HelpDialog extends DialogFragment
{
    public static final String KEY_HELPTEXT = "helpText";

    /**
     * The text content displayed by the help dialog.
     */
    private String rawContent = "";
    public String getContent()
    {
        return rawContent;
    }
    public void setContent( String content )
    {
        rawContent = content;
        if (txtView != null)
        {
            txtView.setText(Html.fromHtml(rawContent));
        }
    }

    /**
     * @param savedInstanceState a previously saved state (or null)
     * @return a Dialog object ready to be displayed
     */
    @NonNull @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        final Activity myParent = getActivity();
        LayoutInflater inflater = myParent.getLayoutInflater();
        @SuppressLint("InflateParams")
        View dialogContent = inflater.inflate(R.layout.layout_dialog_help, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(myParent);
        builder.setView(dialogContent);
        AlertDialog dialog = builder.create();

        initViews(dialogContent);
        if (savedInstanceState != null)
        {
            Log.d("DEBUG", "HelpDialog onCreate (restoreState)");
            rawContent = savedInstanceState.getString(KEY_HELPTEXT);
        }
        setContent(rawContent);
        return dialog;
    }

    /**
     *
     */
    private TextView txtView;
    public void initViews(View dialogView)
    {
        txtView = (TextView) dialogView.findViewById(R.id.txt_help_content);
    }

    @Override
    public void onSaveInstanceState( Bundle outState )
    {
        Log.d("DEBUG", "HelpDialog onSaveInstanceState");
        outState.putString(KEY_HELPTEXT, rawContent);
        super.onSaveInstanceState(outState);
    }

}
