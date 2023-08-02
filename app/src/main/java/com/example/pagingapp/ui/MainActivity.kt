package com.example.pagingapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.pagingapp.databinding.ActivityMainBinding
import com.example.pagingapp.model.State
import com.example.pagingapp.viewModel.CharacterListViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: CharacterListViewModel
    private lateinit var mCharacterListAdapter: CharacterListAdapter
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        mViewModel = ViewModelProvider(this)[CharacterListViewModel::class.java]
        setAdapter()
        setState()
    }

    private fun setAdapter() {
        mCharacterListAdapter = CharacterListAdapter { mViewModel.retry() }
        mainBinding.recyclerView.adapter = mCharacterListAdapter
        mViewModel.mDataList.observe(this) {
            mCharacterListAdapter.submitList(it)
        }
    }

    private fun setState() {
        mainBinding.buttonRetry.setOnClickListener { mViewModel.retry() }
        mViewModel.getState().observe(this) { state ->
            mainBinding.progressBar.visibility =
                if (mViewModel.listIsEmpty() && state == State.LOADING) View.VISIBLE else View.GONE
            mainBinding.tvError.visibility =
                if (mViewModel.listIsEmpty() && state == State.ERROR) View.VISIBLE else View.GONE
            mainBinding.buttonRetry.visibility =
                if (mViewModel.listIsEmpty() && state == State.ERROR) View.VISIBLE else View.GONE
            if (!mViewModel.listIsEmpty()) {
                mCharacterListAdapter.setState(state ?: State.DONE)
            }
        }
    }
}