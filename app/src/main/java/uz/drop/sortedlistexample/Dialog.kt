package uz.drop.sortedlistexample

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.dialog.view.*

class Dialog(context: Context, action: String) : AlertDialog(context) {
    private var person: Person? = null
    private var listener: SingleBlock? = null

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog, null, false)
        setView(view)
        setTitle(action)
        setButton(DialogInterface.BUTTON_POSITIVE, action) { _, _ ->
            val name = view.name
            val age = view.age
            val data = person ?: Person()
            data.name = name.text.toString()
            data.age = age.text.toString().toInt()
            listener?.invoke(data)
        }
        setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel") { _, _ -> }
    }

    fun setData(person: Person) {
        this.person = person
    }

    fun setOnClickListener(block: SingleBlock) {
        listener = block
    }

}

typealias SingleBlock = (Person) -> Unit
