package uz.drop.sortedlistexample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val adapter = Adapter()
    private val list = ArrayList<Person>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
        loadList()
        val parcelableArrayListExtra = intent.getParcelableArrayListExtra<Person>("parce")
        startActivity(Intent(this, MainActivity::class.java).putExtra("parce", list[0]))
        adapter.submitList(list)
        fab.setOnClickListener {
            AlertDialog.Builder(this).setTitle("")
        }
    }

    private fun loadList() {
        list.addAll(listOf(Person("Abdulaziz", 22), Person("Ilhom", 23), Person("Akmal", 21)))

    }
}
