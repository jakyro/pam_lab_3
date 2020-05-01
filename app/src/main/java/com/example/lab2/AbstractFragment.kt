package com.example.lab2

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class AbstractFragment : Fragment() {

    private lateinit var progress: ProgressDialog

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    val scope = CoroutineScope(coroutineContext)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        progress = ProgressDialog(this.context)
        progress.setTitle("Loading")
        progress.setMessage("Load in progress...")
        progress.setCancelable(false)
        return view
    }

    fun showProgress() {
        progress.show()
    }

    fun dismissProgress() {
        progress.dismiss()
    }
}