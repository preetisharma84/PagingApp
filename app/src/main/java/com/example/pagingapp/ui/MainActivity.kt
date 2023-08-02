package com.example.pagingapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pagingapp.R
import com.example.pagingapp.model.State
import com.example.pagingapp.viewModel.CharacterListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: CharacterListViewModel
    private lateinit var mCharacterListAdapter: CharacterListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewModel = ViewModelProvider(this).get(CharacterListViewModel::class.java)
        setAdapter()
        setState()
    }

    private fun setAdapter() {
        mCharacterListAdapter = CharacterListAdapter { mViewModel.retry() }
        recycler_view.adapter = mCharacterListAdapter
        mViewModel.mDataList.observe(this,
            Observer {
                mCharacterListAdapter.submitList(it)
            })
    }

    private fun setState() {
        buttonRetry.setOnClickListener { mViewModel.retry() }
        mViewModel.getState().observe(this, Observer { state ->
            progressBar.visibility =
                if (mViewModel.listIsEmpty() && state == State.LOADING) View.VISIBLE else View.GONE
            tvError.visibility =
                if (mViewModel.listIsEmpty() && state == State.ERROR) View.VISIBLE else View.GONE
            buttonRetry.visibility =
                if (mViewModel.listIsEmpty() && state == State.ERROR) View.VISIBLE else View.GONE
            if (!mViewModel.listIsEmpty()) {
                mCharacterListAdapter.setState(state ?: State.DONE)
            }
        })
    }
}