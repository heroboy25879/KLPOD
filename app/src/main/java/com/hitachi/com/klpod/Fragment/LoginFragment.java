package com.hitachi.com.klpod.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hitachi.com.klpod.PlanListActivity;
import com.hitachi.com.klpod.R;
import com.hitachi.com.klpod.Utility.FuncGetUserLogin;
import com.hitachi.com.klpod.Utility.MasterAlert;
import com.hitachi.com.klpod.Utility.MasterServiceFunction;

import org.json.JSONArray;
import org.json.JSONObject;

public class LoginFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EditText userEditText = getView().findViewById(R.id.edtFLogin_User);
        EditText passEditText = getView().findViewById(R.id.edtFLogin_Password);
        userEditText.setText("72-1397");
        passEditText.setText("1234");
        //Login Check
        LoginCheck();


    }

    private void LoginCheck() {

        Button button = getView().findViewById(R.id.btnFLogin_Login);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userEditText = getView().findViewById(R.id.edtFLogin_User);
                EditText passEditText = getView().findViewById(R.id.edtFLogin_Password);

                String userString = userEditText.getText().toString().trim();
                String passString = passEditText.getText().toString().trim();

                MasterAlert masterAlert = new MasterAlert(getActivity());
                if (userString.isEmpty() || passString.isEmpty()) {
                    masterAlert.normalDialog("Warning!", "Please fill all data!");
                } else {
                    try {
                        MasterServiceFunction masterServiceFunction = new MasterServiceFunction();

                        FuncGetUserLogin funcGetUserLogin = new FuncGetUserLogin(getActivity());
                        funcGetUserLogin.execute(masterServiceFunction.getGetUserLogin(),userString,passString);
                        //funcGetUserLogin.execute(masterServiceFunction.getGetUserLogin());
                        Log.d("KLTag","JSON ==> " );
                        String resultJSON = funcGetUserLogin.get();
                        resultJSON = resultJSON.replace("\\\"","\"");
                        resultJSON = resultJSON.substring(1,resultJSON.length()-1);

                        Log.d("KLTag","JSON ==> " + resultJSON);
                        JSONArray jsonArray = new JSONArray(resultJSON);

                        if (jsonArray.length() > 0) {
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            Toast.makeText(getActivity(), "Welcome " + jsonObject.getString("truck_license"), Toast.LENGTH_SHORT).show();
                            String truckLicense = jsonObject.getString("truck_license");
                            Log.d("KLTag", "JSON ==> " + truckLicense);

                            Intent intent = new Intent(getActivity(),PlanListActivity.class);
                            intent.putExtra("Username", truckLicense);
                            startActivity(intent);

                            getActivity().finish();
                        } else {
                            masterAlert.normalDialog("Warning!", "Invalid Username or Password");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        return view;
    }


}