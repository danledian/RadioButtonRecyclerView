package com.hs.radiobuttonrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.hs.radiobuttonrecyclerview.adapter.UserAdapter
import com.hs.radiobuttonrecyclerview.bean.User
import com.hs.radiobuttonrecyclerview.utils.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var singleRow: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
    }

    private fun initRecyclerView(){
        val users = listOf(
            User("example"),
            User("execution"),
            User("trigger"),
            User("coroutine"),
            User("imagine"),
            User("particular"),
            User("decoration")
        )
        rv_user_list.adapter = UserAdapter(users).apply {
            setOnItemClickListener(object : UserAdapter.OnItemClickListener{
                override fun onItemClick(view: View?, position: Int) {
                    Toast.makeText(applicationContext, "$position", Toast.LENGTH_SHORT).show()
                }
            })
        }
        rv_user_list.layoutManager = GridLayoutManager(this, 2)
        rv_user_list.addItemDecoration(
            DividerItemDecoration(
                this
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_user_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.user_list -> {
                updateLayoutManager()
                true
            }else ->
                super.onOptionsItemSelected(item)
        }
    }

    private fun updateLayoutManager() {
        singleRow = !singleRow
        rv_user_list.layoutManager = GridLayoutManager(this, if(singleRow) 1 else 2)
    }
}
