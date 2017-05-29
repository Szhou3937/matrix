package cn.kylive.xzzs;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2017/5/30 0030.
 */

public class BaseButton extends AppCompatButton {
    public BaseButton(Context context) {
        super(context);
    }

    public BaseButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BaseButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
