package com.iuoon.app.reader.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.iuoon.app.reader.R;
import com.iuoon.app.reader.activity.LoginActivity;

import static android.content.Context.MODE_PRIVATE;


public class MyFragment extends Fragment {

    private Button loginBtn;
    private TextView userNameTV;
    private Button logoutBtn;

    public MyFragment() {

    }

    // TODO: Rename and change types and number of parameters
    public static MyFragment newInstance(String param1) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString("args1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("ddd", "====================");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        userNameTV=view.findViewById(R.id.userNameTV);
        loginBtn=view.findViewById(R.id.loginBtn);
        logoutBtn=view.findViewById(R.id.logoutBtn);

        SharedPreferences sp = this.getActivity().getSharedPreferences("user", MODE_PRIVATE);
        String userId=sp.getString("userId","");
        String strMobile=sp.getString("strMobile","");
        Log.d("sp----->",userId+" && "+strMobile);
        if("".equals(userId)){
            loginBtn.setVisibility(View.VISIBLE);
            userNameTV.setVisibility(View.INVISIBLE);
            logoutBtn.setVisibility(View.INVISIBLE);
        }else {
            userNameTV.setText(strMobile);
            userNameTV.setVisibility(View.VISIBLE);
            loginBtn.setVisibility(View.INVISIBLE);
            logoutBtn.setVisibility(View.VISIBLE);
        }
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        return view;
    }

    private void logout(){
        SharedPreferences sp = this.getActivity().getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("strMobile","");
        edit.putString("userId","");
        edit.putString("token","");
        edit.commit();
        loginBtn.setVisibility(View.VISIBLE);
        userNameTV.setText("");
        userNameTV.setVisibility(View.INVISIBLE);
        logoutBtn.setVisibility(View.INVISIBLE);
    }

}
