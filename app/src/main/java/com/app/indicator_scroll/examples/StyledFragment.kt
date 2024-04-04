package com.app.indicator_scroll.examples

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.indicator_scroll.FastScrollItemIndicator
import com.app.indicator_scroll.FastScrollerThumbView
import com.app.indicator_scroll.FastScrollerView
import com.app.indicator_scroll.ListItem
import com.app.indicator_scroll.R
import com.app.indicator_scroll.SAMPLE_DATA_TEXT
import com.app.indicator_scroll.SampleAdapter

class StyledFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fastScrollerView: FastScrollerView
    private lateinit var fastScrollerThumbView: FastScrollerThumbView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.sample_styled, container, false)

        val data = SAMPLE_DATA_TEXT

        recyclerView = view.findViewById(R.id.sample_styled_recyclerview)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = SampleAdapter(data)
        }

        fastScrollerView = view.findViewById(R.id.sample_styled_fastscroller)
        fastScrollerView.apply {
            setupWithRecyclerView(
                recyclerView,
                { position ->
                    data[position]
                        .takeIf(ListItem::showInFastScroll)
                        ?.let { item ->
                            FastScrollItemIndicator.Text(
                                item
                                    .title
                                    .substring(0, 1)
                                    .toUpperCase()
                            )
                        }
                }
            )
        }

        fastScrollerThumbView = view.findViewById(R.id.sample_styled_fastscroller_thumb)
        fastScrollerThumbView.apply {
            setupWithFastScroller(fastScrollerView)
        }

        return view
    }

}
