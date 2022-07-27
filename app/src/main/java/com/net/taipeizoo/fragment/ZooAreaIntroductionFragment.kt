package com.net.taipeizoo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fe.gohappy.ui.customview.rememberViewInteropNestedScrollConnection
import com.net.taipeizoo.viewmodel.ZooAreaIntroductionViewModel

class ZooAreaIntroductionFragment : Fragment() {

    companion object {
        val ARG_TITLE = "title"
        val ARG_INTRODUCTION = "introduction"

        fun newInstance(title: String?, introduction: String?) = ZooAreaIntroductionFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_TITLE, title)
                putString(ARG_INTRODUCTION, introduction)
            }
        }
    }

    private var title: String? = null
    private var introduction: String? = null

    private lateinit var viewModel: ZooAreaIntroductionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        processArguments()
        return ComposeView(requireContext()).apply {
            setContent {
                Inflate()
            }
        }
    }

    private fun processArguments() {
        arguments?.let {
            title = it.getString(ARG_TITLE)
            introduction = it.getString(ARG_INTRODUCTION)
        }
    }

    @Composable
    @Preview
    fun Inflate() {
        Surface(
            color = Color.Black,
            modifier = Modifier.nestedScroll(rememberViewInteropNestedScrollConnection()),
        ) {
            Column(modifier = Modifier
                .verticalScroll(rememberScrollState())
            ) {
                title?.let { Text(it, style = TextStyle(color = Color.White), fontSize = 16.sp) }
                Spacer(modifier = Modifier.height(10.dp))
                introduction?.let { Text(it, style = TextStyle(color = Color.White), fontSize = 16.sp) }
            }
        }
    }


}