package com.hs.radiobuttonrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.hs.radiobuttonrecyclerview.adapter.UserAdapter
import com.hs.radiobuttonrecyclerview.bean.User
import com.hs.radiobuttonrecyclerview.utils.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val users = listOf(
            User("example"),
            User("execution"),
            User("trigger"),
            User("coroutine"),
            User("imagine"),
            User("particular"),
            User("decoration")
        )
        rv_user_list.adapter =
            UserAdapter(users)
        rv_user_list.layoutManager = GridLayoutManager(this, 2)
        rv_user_list.addItemDecoration(
            DividerItemDecoration(
                this
            )
        )

    }
}
