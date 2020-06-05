package uz.drop.sortedlistexample

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog

class Dialog(context: Context,action: String) : AlertDialog(context) {
    init {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog, null, false)
        setView(view)
        setButton(DialogInterface.BUTTON_POSITIVE,action){dialog, which ->

        }
    }

}