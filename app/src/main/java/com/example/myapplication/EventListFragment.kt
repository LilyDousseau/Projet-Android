package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val LIST_EVENTS = "param1"
/**
 * A simple [Fragment] subclass.
 * Use the [EventListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EventListFragment : Fragment() {
    private lateinit var param1: ArrayList<Event>
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter : EventAdapter

    // Callback pour gérer le clic sur un événement
    var onEventClick: ((Event) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_event_list, container, false)

        param1 = requireArguments().getSerializable(LIST_EVENTS) as ArrayList<Event>
        arguments = null

        recyclerView = rootView.findViewById<RecyclerView>(R.id.a_rcv_events)

        // Créer l'adaptateur avec le callback
        adapter = EventAdapter(param1) { event ->
            onEventClick?.invoke(event) // Appeler le callback
        }
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(context)

        // Inflate the layout for this fragment
        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment EventListFragment.
         */
        @JvmStatic
        fun newInstance(param1 : ArrayList<Event>, onEventClick: (Event) -> Unit) : EventListFragment {
            val eventListFragment = EventListFragment()
            val bundle = Bundle()
            bundle.putSerializable(LIST_EVENTS, param1)
            eventListFragment.arguments = bundle
            eventListFragment.onEventClick = onEventClick // Passer le callback ici
            return eventListFragment
        }
    }
}