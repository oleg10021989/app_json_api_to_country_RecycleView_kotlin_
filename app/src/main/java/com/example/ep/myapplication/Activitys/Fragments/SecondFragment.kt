package com.example.ep.myapplication.Activitys.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ep.myapplication.Activitys.Activitys.MainActivity
import com.example.ep.myapplication.Activitys.Adapters.StateAdapter
import com.example.ep.myapplication.Activitys.Model.State
import com.example.ep.myapplication.Activitys.Services.DataService
import com.example.ep.myapplication.R

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SecondFragment.OnSecondFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {
    private val s: State? = null
    private var mListener: OnSecondFragmentInteractionListener? = null
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
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        val v = inflater.inflate(R.layout.fragment_second, container, false)
        val b = v.findViewById<View>(R.id.button) as Button
        b.setOnClickListener { Go_back_fragment() }
        // Inflate the layout for this fragment
        val ds = DataService()
        val i = requireActivity().intent
        val co = i.getSerializableExtra("StateOb") as State
        var arrR: ArrayList<State>? = ArrayList()

        // find all the borders for one given state
        arrR = ds.getNstateFromBstate(co.borders)
        if (arrR.size == 0) Toast.makeText(activity, "sorry No Borders", Toast.LENGTH_LONG).show()
        val theAdapter = StateAdapter(activity, arrR)
        val theListView = v.findViewById<View>(R.id.ListViewRe) as ListView
        theListView.adapter = theAdapter
        return v
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri?) {
        if (mListener != null) {
            mListener!!.onSecondFragmentInteraction(uri)
        }
    }

    private fun Go_back_fragment() {
        val mainActivity = (activity as MainActivity?)!!
        mainActivity.GoBack()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = if (context is OnSecondFragmentInteractionListener) {
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
    interface OnSecondFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onSecondFragmentInteraction(uri: Uri?)
    }

    companion object {
        // second fragment - borders
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
         * @return A new instance of fragment SecondFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, param2: String?): SecondFragment {
            val fragment = SecondFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}