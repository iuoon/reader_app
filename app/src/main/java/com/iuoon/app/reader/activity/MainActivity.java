package com.iuoon.app.reader.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.iuoon.app.reader.R;
import com.iuoon.app.reader.fragment.HomeFragment;
import com.iuoon.app.reader.fragment.MyFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{

    private BottomNavigationBar bottomNavigationBar;//底部菜单
    int lastSelectedPosition = 0;
    private String TAG = MainActivity.class.getSimpleName();
    private HomeFragment mHomeFragment;
    private MyFragment mMyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //接收数据
        String tab="";
        Intent intent=getIntent();
        if (null !=intent){
            tab = intent.getStringExtra("tab");
        }

        /**bottomNavigation 设置**/
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        /** 导航基础设置 包括按钮选中效果 导航栏背景色等 */
        bottomNavigationBar
                .setTabSelectedListener(this)
                .setMode(BottomNavigationBar.MODE_FIXED)
                /**
                 *  setMode() 内的参数有三种模式类型：
                 *  MODE_DEFAULT 自动模式：导航栏Item的个数<=3 用 MODE_FIXED 模式，否则用 MODE_SHIFTING 模式
                 *  MODE_FIXED 固定模式：未选中的Item显示文字，无切换动画效果。
                 *  MODE_SHIFTING 切换模式：未选中的Item不显示文字，选中的显示文字，有切换动画效果。
                 */
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                /**
                 *  setBackgroundStyle() 内的参数有三种样式
                 *  BACKGROUND_STYLE_DEFAULT: 默认样式 如果设置的Mode为MODE_FIXED，将使用BACKGROUND_STYLE_STATIC
                 *                                    如果Mode为MODE_SHIFTING将使用BACKGROUND_STYLE_RIPPLE。
                 *  BACKGROUND_STYLE_STATIC: 静态样式 点击无波纹效果
                 *  BACKGROUND_STYLE_RIPPLE: 波纹样式 点击有波纹效果
                 */
                .setActiveColor("#FF0066") //选中颜色
                .setInActiveColor("#808080"); //未选中颜色
        //  .setBarBackgroundColor("#1ccbae");//导航栏背景色
        /** 添加导航按钮 */
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.ic_tick, "消息"))
                .addItem(new BottomNavigationItem(R.drawable.ic_my, "我的"))
                .setFirstSelectedPosition(lastSelectedPosition )
                .initialise(); //initialise 一定要放在 所有设置的最后一项
        if(null !=tab && !"".equals(tab)){
            Integer tabNum=Integer.valueOf(tab);
            bottomNavigationBar.selectTab(tabNum);
        }else{
            setDefaultFragment();//设置默认导航栏
        }
    }

    /**
     * 设置默认导航栏
     */
    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mHomeFragment = HomeFragment.newInstance("首页");
        transaction.replace(R.id.tb, mHomeFragment);
        transaction.commit();
    }
    /**
     * 设置导航选中的事件
     */
    @Override
    public void onTabSelected(int position) {
        Log.d(TAG, "onTabSelected() called with: " + "position = [" + position + "]");
        FragmentManager fm = this.getFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.newInstance("首页");
                }
                transaction.replace(R.id.tb, mHomeFragment);
                break;
            case 1:
                Intent intent=new Intent(this, AircleActivity.class);
                startActivity(intent);
                break;
            case 2:
                if (mMyFragment == null) {
                    mMyFragment = MyFragment.newInstance("我的");
                }
                transaction.replace(R.id.tb, mMyFragment);
                break;
            default:
                break;
        }
        transaction.commit();// 事务提交

    }

    /**
     * 设置未选中Fragment 事务
     */
    @Override
    public void onTabUnselected(int position) {

    }

    /**
     * 设置释放Fragment 事务
     */
    @Override
    public void onTabReselected(int position) {

    }
}
