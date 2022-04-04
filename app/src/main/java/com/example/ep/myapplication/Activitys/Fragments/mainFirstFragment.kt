package com.example.ep.myapplication.Activitys.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.ep.myapplication.Activitys.Activitys.MainActivity
import com.example.ep.myapplication.Activitys.Adapters.StateAdapter
import com.example.ep.myapplication.Activitys.Model.State
import com.example.ep.myapplication.Activitys.Services.DataService
import com.example.ep.myapplication.R

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [mainFirstFragment.OnFirstFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [mainFirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class mainFirstFragment : Fragment() {
    private var mListener: OnFirstFragmentInteractionListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            // TODO: Rename and change types of parameters
            val mParam1 = requireArguments().getString(ARG_PARAM1)
            val mParam2 = requireArguments().getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val ds = DataService()
        val v = inflater.inflate(R.layout.fragment_main_first, container, false)
        val allstates = ds.getArrState()
        val theAdapter = StateAdapter(activity, allstates)
        val theListView = v.findViewById<View>(R.id.ListViewsir) as ListView
        theListView.adapter = theAdapter

        //  theListView.setOnTouchListener(sd);
        theListView.isTextFilterEnabled = true


        // Adding items to listview
        theListView.onItemClickListener =
            OnItemClickListener { parent, view, position, id -> //                Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
//                view.startAnimation(hyperspaceJumpAnimation);
                val s = parent.adapter.getItem(position) as State
                val ma = (activity as MainActivity?)!!
                ma.LoadSecFragment(s)
            }
        return v
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri?) {
        if (mListener != null) {
            mListener!!.onFirstFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = if (context is OnFirstFragmentInteractionListener) {
            context
        } else {
            throw RuntimeException(
                context.toString()
                        + " must implement OnFragmentInteractionListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFirstFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFirstFragmentInteraction(uri: Uri?)
    }

    companion object {
        // first fragment - listview with all states
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment mainFirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, param2: String?): mainFirstFragment {
            val fragment = mainFirstFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}