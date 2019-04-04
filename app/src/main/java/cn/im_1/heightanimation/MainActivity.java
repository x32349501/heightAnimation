package cn.im_1.heightanimation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout layoutFunction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutFunction = findViewById(R.id.layout);
        findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getTag().equals("0")) {
            v.setTag("1");
            ((Button)v).setText("关闭");
            showBottomLayout();
        } else {
            v.setTag("0");
            ((Button)v).setText("打开");
            hideBottomLayout();
        }
    }

    /**
     * 显示底部操作栏
     */
    private void showBottomLayout() {
        TypeEvaluator<ViewGroup.LayoutParams> evaluator = new HeightEvaluator();

        ViewGroup.LayoutParams start = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        ViewGroup.LayoutParams end = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
        ValueAnimator animator = ObjectAnimator.ofObject(layoutFunction, "layoutParams", evaluator, start, end);

        AnimatorSet set = new AnimatorSet();
        set.play(animator);
        set.setDuration(300);
        set.start();
    }

    /**
     * 隐藏底部操作栏
     */
    private void hideBottomLayout() {
        TypeEvaluator<ViewGroup.LayoutParams> evaluator = new HeightEvaluator();

        ViewGroup.LayoutParams start = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        ViewGroup.LayoutParams end = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
        ValueAnimator animator = ObjectAnimator.ofObject(layoutFunction, "layoutParams", evaluator, start, end);

        AnimatorSet set = new AnimatorSet();
        set.play(animator);
        set.setDuration(300);
        set.start();
    }


    class HeightEvaluator implements TypeEvaluator<ViewGroup.LayoutParams> {

        @Override
        public ViewGroup.LayoutParams evaluate(float fraction, ViewGroup.LayoutParams startValue, ViewGroup.LayoutParams endValue) {
            ViewGroup.LayoutParams params = layoutFunction.getLayoutParams();
            params.height = (int) (startValue.height + fraction * (endValue.height - startValue.height));
            return params;
        }
    }
}
