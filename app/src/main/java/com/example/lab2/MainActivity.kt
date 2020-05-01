package com.example.lab2

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.viewpager.widget.ViewPager
import com.example.lab2.main.MyPagerAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vpPager = findViewById<ViewPager>(R.id.pager)
        vpPager.adapter = MyPagerAdapter(supportFragmentManager, this)
        vpPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(pos: Int, posOffset: Float, posOffsetPx: Int) {
                setActiveNavItem(pos)
            }

            override fun onPageSelected(position: Int) {
            }
        })

        val layout: LinearLayout = findViewById(R.id.navigation)
        layout.children.forEachIndexed { index, element ->
            (element as TextView).setOnClickListener {
                setActiveNavItem(index)
                vpPager.setCurrentItem(index, true)
            }
        }
    }

    private fun setActiveNavItem(position: Int) {
        val layout: LinearLayout = findViewById(R.id.navigation)
        layout.children.forEach { setActive(it as TextView, false) }
        val view: TextView = layout.getChildAt(position) as TextView
        setActive(view, true)
    }

    private fun setActive(textView: TextView, isActive: Boolean) {
        if (isActive) {
            textView.setTextColor(resources.getColor(R.color.colorAccent, null))
        } else {
            textView.setTextColor(resources.getColor(R.color.colorBasic, null))
        }

    }
}
