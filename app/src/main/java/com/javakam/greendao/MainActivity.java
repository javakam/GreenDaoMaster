package com.javakam.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.javakam.greendao.entity.User;
import com.javakam.greendao.gen.UserDao;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {
    private TextView mContext;
    private User mUser;
    private UserDao mUserDao;

    private int temp = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mUserDao = GDApplication.getInstances().getDaoSession().getUserDao();
    }

    private void initView() {
        mContext = (TextView) findViewById(R.id.textView);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                addDate();
                break;
            case R.id.button2:
                deleteDate();
                break;
            case R.id.button3:
                updateDate();
                break;
            case R.id.button4:
                findDate();
                break;
        }
    }

    /**
     * 增加数据
     */
    private void addDate() {
        mUser = new User((long) temp, "lovekam" + temp);
        mUserDao.insert(mUser);//添加一个
        mContext.setText(mUser.getName());
        temp++;
    }

    /**
     * 删除数据
     */
    private void deleteDate() {
        deleteUserById(temp);
    }

    /**
     * 根据主键删除User
     *
     * @param id User的主键Id
     */
    public void deleteUserById(long id) {
        mUserDao.deleteByKey(id);
    }

    /**
     * 更改数据
     */
    private void updateDate() {
        mUser = new User((long) temp, "lovekam" + temp);
        if (mUserDao.hasKey(mUser)) {
            mUserDao.update(mUser);
        }
    }

    /**
     * 查找数据
     */
    private void findDate() {
        List<User> users = mUserDao.loadAll();
        String userName = "";
        for (int i = 0; i < users.size(); i++) {
            userName += users.get(i).getName() + ";";
        }
        mContext.setText("查询全部数据==>" + userName);
    }
}
