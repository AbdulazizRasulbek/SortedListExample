package uz.drop.sortedlistexample

import android.os.Bundle
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
        fab.setOnClickListener {
            val dialog = Dialog(this, "Add")
            dialog.setOnClickListener(adapter::insert)
            dialog.show()
        }
        adapter.setOnEditClickListener {
            val dialog = Dialog(this, "Edit")
            dialog.setData(it)
            dialog.setOnClickListener(adapter::update)
            dialog.show()
        }


    }

    private fun loadList() {
        list.addAll(listOf(Person("Abdulaziz", 22), Person("Ilhom", 23), Person("Akmal", 21)))
        adapter.submitList(list)
    }
}
