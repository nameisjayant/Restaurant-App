package com.codingwithjks.tvapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingwithjks.tvapp.container.BaseComponent
import com.codingwithjks.tvapp.data.util.State
import com.codingwithjks.tvapp.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.bind
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val baseComponent = BaseComponent()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initRecyclerview()
        getRestaurant()
    }

    private fun getRestaurant() {
        lifecycleScope.launchWhenStarted {
            baseComponent.mainViewModel.apiStateFlow
                .collect {
                    binding.apply {
                        when(it){
                            is State.Loading -> {
                                recyclerview.isVisible = false
                                progressBar.isVisible = true
                            }
                            is State.Success->{
                                if(it.data.isNotEmpty()){
                                    recyclerview.isVisible = true
                                    progressBar.isVisible = false
                                    baseComponent.restaurantAdapter.submitList(it.data)
                                }
                            }
                            is State.Failure->{
                                recyclerview.isVisible = false
                                progressBar.isVisible = false
                                Log.d("main", it.msg)
                            }
                            is State.Empty->{

                            }
                        }
                    }
                }
        }
    }

    private fun initRecyclerview() {
        binding.apply {
            recyclerview.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = baseComponent.restaurantAdapter
            }
        }
    }
}