package com.meilishuo.linegrideview.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

import com.meilishuo.linegrideview.R;

/**
 * 自定义带有网格线的GridView
 *
 * @title:
 * @description:
 * @company: 美丽说（北京）网络科技有限公司
 * Created by Jeff on 15/4/27.
 * Update by J!nl!n on 15/8/13.
 */
public class LineGridView extends GridView {

    private int width;
    private int color;
    private boolean showButtonLine = true;
    private boolean showTopLine = true;
    private Paint localPaint;
    private boolean inScrollView;

    public LineGridView(Context context) {
        this(context, null);
    }

    public LineGridView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.gridViewStyle);
    }

    public LineGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        localPaint = new Paint();
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.LineGridView);
        width = ta.getDimensionPixelSize(R.styleable.LineGridView_dividerWidth, 1);
        color = ta.getColor(R.styleable.LineGridView_dividerColor, Color.BLACK);
        showButtonLine = ta.getBoolean(R.styleable.LineGridView_showButtonLine, true);
        showTopLine = ta.getBoolean(R.styleable.LineGridView_showTopLine,true);
        inScrollView = ta.getBoolean(R.styleable.LineGridView_inScrollView, false);
        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(inScrollView){
            //放置在scrollview中时，进行测量高度
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 注意这里的getLeft(), getTop(), getRight, getBottom()均是相对于父控件的位置
     * @param canvas
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (getChildAt(0) != null) {
            int column = getNumColumns(); // 每一列的宽度
            int childCount = getChildCount(); // 获取子元素个数
            int row;
            if (childCount % column == 0) { // 如果子元素被列整除，则不新增行
                row = childCount / column;
            } else {
                row = childCount / column + 1;
            }
            int endAllcolumn = (row - 1) * column;
            localPaint.setStyle(Paint.Style.STROKE);
            localPaint.setStrokeWidth(width);
            localPaint.setColor(color);
            if(showTopLine){
                canvas.drawLine(getLeft(), 0, getRight(), 0, localPaint); // Top Line
            }
            if(showButtonLine){
                canvas.drawLine(getLeft(), getMeasuredHeight() - 1, getRight(), getMeasuredHeight() - 1, localPaint); // Bottom Line
            }
            for (int i = 0; i < childCount; i++) {
                View cellView = getChildAt(i);
                if ((i + 1) % column != 0) {
                    canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint); // Vertical Line
                }
                if ((i + 1) <= endAllcolumn) {
                    canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint); // horizontal Line
                }
            }
            if (childCount % column != 0) {
                for (int j = 0; j < (column - childCount % column); j++) {
                    View lastView = getChildAt(childCount - 1);
                    canvas.drawLine(lastView.getRight() + lastView.getWidth() * j, lastView.getTop(),
                            lastView.getRight() + lastView.getWidth() * j, lastView.getBottom(), localPaint);
                }
            }
        }
    }
}
