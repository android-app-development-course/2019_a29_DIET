package com.example.a84121.bao_diet;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by 84121 on 2019/11/24.
 */

public class RoundImageView extends AppCompatImageView {
    private Paint mPaint; //画笔

    private int mRadius; //圆形图片的半径

    private float mScale; //图片的缩放比例

    public RoundImageView(Context context) {
        super(context);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //因为是圆形图片，所以应该让宽高保持一致
        int size = Math.min(getMeasuredWidth(), getMeasuredHeight());
        mRadius = size / 2;
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mPaint = new Paint();
        Bitmap bitmap = drawableToBitmap(getDrawable());

        //初始化BitmapShader，传入bitmap对象
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        //计算缩放比例
        mScale = (mRadius * 2.0f) / Math.min(bitmap.getHeight(), bitmap.getWidth());

        Matrix matrix = new Matrix();
        matrix.setScale(mScale, mScale);
        bitmapShader.setLocalMatrix(matrix);


        mPaint.setShader(bitmapShader);

        //画圆形，指定好中心点坐标、半径、画笔
        canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);
    }

    //写一个drawble转BitMap的方法
    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }
}


//
//    float width,height;
//
//    public RoundImageView(Context context) {
//        this(context, null);
//    }
//
//    public RoundImageView(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        if (Build.VERSION.SDK_INT < 18) {
//            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        }
//    }
//
//    @Override
//    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        super.onLayout(changed, left, top, right, bottom);
//        width = getWidth();
//        height = getHeight();
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//
//        if (width > 1 && height > 1) {
//            Path path = new Path();
//            path.moveTo(50, 0);
//            path.lineTo(width - 50, 0);
//            path.quadTo(width, 0, width, 50);
//            path.lineTo(width, height - 50);
//            path.quadTo(width, height, width - 50, height);
//            path.lineTo(50, height);
//            path.quadTo(0, height, 0, height - 50);
//            path.lineTo(0, 50);
//            path.quadTo(0, 0, 50, 0);
//            canvas.clipPath(path);
//        }
//
//        super.onDraw(canvas);
//    }
//}