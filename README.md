
# 实现LinearLayout高度动画功能

## 效果
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190404113940164.gif)

## 代码

### 布局文件
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
  <LinearLayout
        android:id="@+id/layoutFunction"
        android:layout_width="wrap_content"
        android:layout_height="0dp"
        android:layout_alignParentBottom="true"
        android:layout_marginBottom="@dimen/space_01_0"
        android:background="@color/colorAccent"
        android:gravity="center_vertical">
  </LinearLayout>
</RelativeLayout>
```

### Java 代码
```java
   /**
     * 显示底部操作栏
     */
    private void showBottomLayout() {
        TypeEvaluator<ViewGroup.LayoutParams> evaluator = new HeightEvaluator();

        ViewGroup.LayoutParams start = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        ViewGroup.LayoutParams end = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 90);
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

        ViewGroup.LayoutParams start = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 90);
        ViewGroup.LayoutParams end = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        ValueAnimator animator = ObjectAnimator.ofObject(layoutFunction, "layoutParams", evaluator, start, end);

        AnimatorSet set = new AnimatorSet();
        set.play(animator);
        set.setDuration(300);
        set.start();
    }
```

```java
class HeightEvaluator implements TypeEvaluator<ViewGroup.LayoutParams> {

        @Override
        public ViewGroup.LayoutParams evaluate(float fraction, ViewGroup.LayoutParams startValue, ViewGroup.LayoutParams endValue) {
            ViewGroup.LayoutParams params = layoutFunction.getLayoutParams();
            params.height = (int) (startValue.height + fraction * (endValue.height - startValue.height));
            return params;
        }
    }
```

TypeEvaluator可根据实际情况进行定义，也可以定义宽度动画等等。

有问题可直接咨询我。
