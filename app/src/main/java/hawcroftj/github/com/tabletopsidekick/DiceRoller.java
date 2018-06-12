package hawcroftj.github.com.tabletopsidekick;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;

/**
 * @author Jadon H
 */
public class DiceRoller extends Fragment {
    private OnFragmentInteractionListener mListener;

    private Spinner spTypeDice;

    // required empty public constructor
    public DiceRoller() {  }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dice_roller, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // populate the dice type spinner
        spTypeDice = getView().findViewById(R.id.spTypeDice);
        ArrayAdapter<CharSequence> diceAdapter = ArrayAdapter.createFromResource(
                getContext(), R.array.all_dice, android.R.layout.simple_spinner_item);
        diceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTypeDice.setAdapter(diceAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
