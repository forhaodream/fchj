package com.fchj.czglgz.chengzhangguanli_high.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.fchj.czglgz.chengzhangguanli_high.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AnimationPieView extends View {
    private Canvas mCanvas;
    private Bitmap mBitmap;
    private List<ArcInfo> positionList;
    private int currentAngle;
    private int centerX;
    private int centerY;
    private float radius;
    private RectF rectF;
    private Rect centerTextBound;
    private Rect dataTextBound;
    private Paint mArcPaint;
    private Paint centerTextPaint;
    private Paint dataPaint;
    private int[] numbers;
    private String[] names;
    private int sum;
    private int[] colors;
    private Random random;
    private float centerTextSize;
    private float dataTextSize;
    private int centerTextColor;
    private int dataTextColor;
    private float circleWidth;

    public AnimationPieView(Context context) {
        super(context);
        this.currentAngle = 0;
        this.centerTextBound = new Rect();
        this.dataTextBound = new Rect();
        this.random = new Random();
        this.centerTextSize = 50.0F;
        this.dataTextSize = 20.0F;
        this.centerTextColor = android.support.v7.appcompat.R.color.notification_icon_bg_color;
        this.dataTextColor = android.support.v7.appcompat.R.color.notification_icon_bg_color;
        this.circleWidth = 100.0F;
        this.init();
    }

    public AnimationPieView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimationPieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.currentAngle = 0;
        this.centerTextBound = new Rect();
        this.dataTextBound = new Rect();
        this.random = new Random();
        this.centerTextSize = 50.0F;
        this.dataTextSize = 20.0F;
        this.centerTextColor = android.support.v7.appcompat.R.color.notification_icon_bg_color;
        this.dataTextColor = android.support.v7.appcompat.R.color.notification_icon_bg_color;
        this.circleWidth = 100.0F;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PieView);
        this.centerTextSize = typedArray.getDimension(R.styleable.PieView_centerTextSize, this.centerTextSize);
        this.dataTextSize = typedArray.getDimension(R.styleable.PieView_dataTextSize, this.dataTextSize);
        this.circleWidth = typedArray.getDimension(R.styleable.PieView_circleWidth, this.circleWidth);
        this.centerTextColor = typedArray.getColor(R.styleable.PieView_centerTextColor, this.centerTextColor);
        this.dataTextColor = typedArray.getColor(R.styleable.PieView_dataTextColor, this.dataTextColor);
        typedArray.recycle();
        this.init();
    }

    @SuppressLint("ResourceAsColor")
    private void init() {
        this.mArcPaint = new Paint();
        this.mArcPaint.setStrokeWidth(this.circleWidth);
        this.mArcPaint.setAntiAlias(true);
        this.mArcPaint.setStyle(Paint.Style.STROKE);
        this.centerTextPaint = new Paint();
        this.centerTextPaint.setTextSize(this.centerTextSize);
        this.centerTextPaint.setAntiAlias(true);
        this.centerTextPaint.setColor(R.color.red);
        this.dataPaint = new Paint();
        this.dataPaint.setStrokeWidth(2.0F);
        this.dataPaint.setTextSize(this.dataTextSize);
        this.dataPaint.setAntiAlias(true);
        this.dataPaint.setColor(R.color.red);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeightSize = MeasureSpec.getSize(heightMeasureSpec);
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (measureWidthMode == -2147483648 && measureHeightMode == -2147483648) {
            this.setMeasuredDimension(600, 600);
        } else if (measureWidthMode == -2147483648) {
            this.setMeasuredDimension(600, measureHeightSize);
        } else if (measureHeightMode == -2147483648) {
            this.setMeasuredDimension(measureWidthSize, 600);
        }

    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.centerX = this.getMeasuredWidth() / 2;
        this.centerY = this.getMeasuredHeight() / 2;
        this.radius = (float) (Math.min(this.getMeasuredWidth(), this.getMeasuredHeight()) / 4);
        this.rectF = new RectF((float) this.centerX - this.radius, (float) this.centerY - this.radius, (float) this.centerX + this.radius, (float) this.centerY + this.radius);
        this.mBitmap = Bitmap.createBitmap(this.getMeasuredWidth(), this.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        this.mCanvas = new Canvas(this.mBitmap);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.positionList != null && this.positionList.size() > 0) {
            canvas.drawBitmap(this.mBitmap, 0.0F, 0.0F, (Paint) null);

            int i;
            ArcInfo arcInfo;
            for (i = 0; i < this.positionList.size(); ++i) {
                arcInfo = (ArcInfo) this.positionList.get(i);
                if ((float) this.currentAngle >= arcInfo.getStartAngle() && (float) this.currentAngle <= arcInfo.getStartAngle() + arcInfo.getAngle()) {
                    this.drawArc(this.mCanvas, (float) this.currentAngle, 1.0F, arcInfo.getColor());
                    break;
                }
            }

            ++this.currentAngle;
            if (this.currentAngle < 360) {
                this.invalidate();
            } else {
                for (i = 0; i < this.positionList.size(); ++i) {
                    arcInfo = (ArcInfo) this.positionList.get(i);
                    this.drawData(this.mCanvas, arcInfo.getCenterAngle(), i, arcInfo.getPercent());
                    this.mCanvas.drawText(this.sum + "", (float) (this.centerX - this.centerTextBound.width() / 2), (float) (this.centerY + this.centerTextBound.height() / 2), this.centerTextPaint);
                }
            }
        }

    }

    private void calculateArc() {
        if (this.numbers != null && this.numbers.length != 0) {
            int startAngle = 0;
            this.positionList = new ArrayList();

            for (int i = 0; i < this.numbers.length; ++i) {
                float percent = (float) this.numbers[i] / (float) this.sum;
                float angle;
                if (i == this.numbers.length - 1) {
                    angle = (float) (360 - startAngle);
                } else {
                    angle = (float) Math.ceil((double) (percent * 360.0F));
                }

                ArcInfo arcInfo = new ArcInfo();
                arcInfo.setStartAngle((float) startAngle);
                arcInfo.setAngle(angle);
                arcInfo.setPercent(percent);
                arcInfo.setColor(this.colors[i]);
                startAngle = (int) ((float) startAngle + angle);
                float arcCenterDegree = (float) (90 + startAngle) - angle / 2.0F;
                arcInfo.setCenterAngle(arcCenterDegree);
                this.positionList.add(arcInfo);
            }

        }
    }

    private float[] calculateCenterByDegree(float degree) {
        float x = (float) (Math.sin(Math.toRadians((double) degree)) * (double) this.radius);
        float y = (float) (Math.cos(Math.toRadians((double) degree)) * (double) this.radius);
        float startX = (float) this.centerX + x;
        float startY = (float) this.centerY - y;
        float[] position = new float[]{startX, startY};
        return position;
    }

    private void drawData(Canvas canvas, float degree, int i, float percent) {
        float startX = this.calculateCenterByDegree(degree)[0];
        float startY = this.calculateCenterByDegree(degree)[1];
        this.dataPaint.getTextBounds(this.names[i], 0, this.names[i].length(), this.dataTextBound);
        canvas.drawText(this.names[i], startX - (float) (this.dataTextBound.width() / 2), startY + (float) (this.dataTextBound.height() / 2) - 20.0F, this.dataPaint);
        DecimalFormat df = new DecimalFormat("0.0");
        String percentString = df.format((double) (percent * 100.0F)) + "%";
        this.dataPaint.getTextBounds(percentString, 0, percentString.length(), this.dataTextBound);
        canvas.drawText(percentString, startX - (float) (this.dataTextBound.width() / 2), startY + (float) (this.dataTextBound.height() * 2) - 20.0F, this.dataPaint);
    }

    private void drawArc(Canvas canvas, float startAngle, float angle, int color) {
        this.mArcPaint.setColor(color);
        canvas.drawArc(this.rectF, startAngle - 0.5F, angle + 0.5F, false, this.mArcPaint);
    }

    private int randomColor() {
        int red = this.random.nextInt(256);
        int green = this.random.nextInt(256);
        int blue = this.random.nextInt(256);
        return Color.rgb(red, green, blue);
    }

    public void setData(int[] numbers, String[] names) {
        if (numbers != null && numbers.length != 0 && names != null && names.length != 0) {
            if (numbers.length == names.length) {
                this.numbers = numbers;
                this.names = names;
                this.colors = new int[numbers.length];
                this.sum = 0;

                for (int i = 0; i < this.numbers.length; ++i) {
                    this.sum += numbers[i];
                    this.colors[i] = this.randomColor();
                }

                this.centerTextPaint.getTextBounds(this.sum + "", 0, (this.sum + "").length(), this.centerTextBound);
                this.calculateArc();
                this.invalidate();
            }
        }
    }

    public void setData(int[] numbers, String[] names, int[] colors) {
        if (numbers != null && numbers.length != 0 && names != null && names.length != 0 && colors != null && colors.length != 0) {
            if (numbers.length == names.length && numbers.length == colors.length) {
                this.numbers = numbers;
                this.names = names;
                this.colors = colors;
                this.sum = 0;

                for (int i = 0; i < this.numbers.length; ++i) {
                    this.sum += numbers[i];
                }

                this.centerTextPaint.getTextBounds(this.sum + "", 0, (this.sum + "").length(), this.centerTextBound);
                this.calculateArc();
                this.invalidate();
            }
        }
    }

    class ArcInfo {
        float startAngle;
        float angle;
        float centerAngle;
        float percent;
        int color;

        ArcInfo() {
        }

        float getCenterAngle() {
            return this.centerAngle;
        }

        void setCenterAngle(float centerAngle) {
            this.centerAngle = centerAngle;
        }

        float getStartAngle() {
            return this.startAngle;
        }

        void setStartAngle(float startAngle) {
            this.startAngle = startAngle;
        }

        float getAngle() {
            return this.angle;
        }

        void setAngle(float angle) {
            this.angle = angle;
        }

        float getPercent() {
            return this.percent;
        }

        void setPercent(float percent) {
            this.percent = percent;
        }

        int getColor() {
            return this.color;
        }

        void setColor(int color) {
            this.color = color;
        }
    }
//    private Canvas mCanvas;
//    private Bitmap mBitmap;
//    private List<ArcInfo> positionList;
//    private int currentAngle;
//    private int centerX;
//    private int centerY;
//    private float radius;
//    private RectF rectF;
//    private Rect centerTextBound;
//    private Rect dataTextBound;
//    private Paint mArcPaint;
//    private Paint centerTextPaint;
//    private Paint dataPaint;
//    private int[] numbers;
//    private String[] names;
//    private int sum;
//    private int[] colors;
//    private Random random;
//    private float centerTextSize;
//    private float dataTextSize;
//    private int centerTextColor;
//    private int dataTextColor;
//    private float circleWidth;
//
//
//    public AnimationPieView(Context context) {
//        super(context);
//        this.currentAngle = 0;
//        this.centerTextBound = new Rect();
//        this.centerTextBound = new Rect();
//        this.random = new Random();
//        this.centerTextSize = 80.0F;
//        this.dataTextSize = 30.0F;
//        this.centerTextColor = -16777216;
//        this.dataTextColor = -16777216;
//        this.circleWidth = 100.0F;
//        this.init();
//    }
//
//    public AnimationPieView(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public AnimationPieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        this.currentAngle = 0;
//        this.centerTextBound = new Rect();
//        this.dataTextBound = new Rect();
//        this.random = new Random();
//        this.centerTextSize = 80.0F;
//        this.dataTextSize = 30.0F;
//        this.centerTextColor = -16777216;
//        this.dataTextColor = -16777216;
//        this.circleWidth = 100.0F;
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, com.don.pieviewlibrary.R.styleable.PieView);
//        this.centerTextSize = typedArray.getDimension(com.don.pieviewlibrary.R.styleable.PieView_centerTextSize, this.centerTextSize);
//        this.dataTextSize = typedArray.getDimension(com.don.pieviewlibrary.R.styleable.PieView_dataTextSize, this.dataTextSize);
//        this.circleWidth = typedArray.getDimension(com.don.pieviewlibrary.R.styleable.PieView_circleWidth, this.circleWidth);
//        this.centerTextColor = typedArray.getColor(com.don.pieviewlibrary.R.styleable.PieView_centerTextColor, this.centerTextColor);
//        this.dataTextColor = typedArray.getColor(com.don.pieviewlibrary.R.styleable.PieView_dataTextColor, this.dataTextColor);
//        typedArray.recycle();
//        this.init();
//    }
//
//    private void init() {
//        this.mArcPaint = new Paint();
//        this.mArcPaint.setStrokeWidth(this.circleWidth);
//        this.mArcPaint.setAntiAlias(true);
//        this.mArcPaint.setStyle(Paint.Style.STROKE);
//        this.centerTextPaint = new Paint();
//        this.centerTextPaint.setTextSize(this.centerTextSize);
//        this.centerTextPaint.setAntiAlias(true);
//        this.centerTextPaint.setColor(this.centerTextColor);
//        this.dataPaint = new Paint();
//        this.dataPaint.setStrokeWidth(2.0F);
//        this.dataPaint.setTextSize(this.dataTextSize);
//        this.dataPaint.setAntiAlias(true);
//        this.dataPaint.setColor(this.dataTextColor);
//    }
//
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int measureWidthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int measureHeightSize = MeasureSpec.getSize(heightMeasureSpec);
//        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);
//        if (measureWidthMode == -2147483648 && measureHeightMode == -2147483648) {
//            this.setMeasuredDimension(600, 600);
//        } else if (measureWidthMode == -2147483648) {
//            this.setMeasuredDimension(600, measureHeightSize);
//        } else if (measureHeightMode == -2147483648) {
//            this.setMeasuredDimension(measureWidthSize, 600);
//        }
//
//    }
//
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//        this.centerX = this.getMeasuredWidth() / 2;
//        this.centerY = this.getMeasuredHeight() / 2;
//        this.radius = (float) (Math.min(this.getMeasuredWidth(), this.getMeasuredHeight()) / 4);
//        this.rectF = new RectF((float) this.centerX - this.radius, (float) this.centerY - this.radius, (float) this.centerX + this.radius, (float) this.centerY + this.radius);
//        this.mBitmap = Bitmap.createBitmap(this.getMeasuredWidth(), this.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
//        this.mCanvas = new Canvas(this.mBitmap);
//    }
//
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        if (this.positionList != null && this.positionList.size() > 0) {
//            canvas.drawBitmap(this.mBitmap, 0.0F, 0.0F, (Paint) null);
//
//            int i;
//            ArcInfo arcInfo;
//            for (i = 0; i < this.positionList.size(); ++i) {
//                arcInfo = (ArcInfo) this.positionList.get(i);
//                if ((float) this.currentAngle >= arcInfo.getStartAngle() && (float) this.currentAngle <= arcInfo.getStartAngle() + arcInfo.getAngle()) {
//                    this.drawArc(this.mCanvas, (float) this.currentAngle, 1.0F, arcInfo.getColor());
//                    break;
//                }
//            }
//
//            ++this.currentAngle;
//            if (this.currentAngle < 360) {
//                this.invalidate();
//            } else {
//                for (i = 0; i < this.positionList.size(); ++i) {
//                    arcInfo = (ArcInfo) this.positionList.get(i);
//                    this.drawData(this.mCanvas, arcInfo.getCenterAngle(), i, arcInfo.getPercent());
//                    this.mCanvas.drawText(this.sum + "", (float) (this.centerX - this.centerTextBound.width() / 2), (float) (this.centerY + this.centerTextBound.height() / 2), this.centerTextPaint);
//                }
//            }
//        }
//
//    }
//
//    private void calculateArc() {
//        if (this.numbers != null && this.numbers.length != 0) {
//            int startAngle = 0;
//            this.positionList = new ArrayList();
//
//            for (int i = 0; i < this.numbers.length; ++i) {
//                float percent = (float) this.numbers[i] / (float) this.sum;
//                float angle;
//                if (i == this.numbers.length - 1) {
//                    angle = (float) (360 - startAngle);
//                } else {
//                    angle = (float) Math.ceil((double) (percent * 360.0F));
//                }
//
//                ArcInfo arcInfo = new ArcInfo();
//                arcInfo.setStartAngle((float) startAngle);
//                arcInfo.setAngle(angle);
//                arcInfo.setPercent(percent);
//                arcInfo.setColor(this.colors[i]);
//                startAngle = (int) ((float) startAngle + angle);
//                float arcCenterDegree = (float) (90 + startAngle) - angle / 2.0F;
//                arcInfo.setCenterAngle(arcCenterDegree);
//                this.positionList.add(arcInfo);
//            }
//
//        }
//    }
//
//    public void setData(int[] numbers, int[] colors) {
//        if (numbers != null && numbers.length != 0 && colors != null && colors.length != 0) {
//            if (numbers.length == colors.length) {
//                this.numbers = numbers;
//                this.colors = colors;
//                this.sum = 0;
//
//                for (int i = 0; i < this.numbers.length; i++) {
//                    this.sum += numbers[i];
//                }
//                this.centerTextPaint.getTextBounds(this.sum + "", 0, (this.sum + "").length(), this.centerTextBound);
//                this.calculateArc();
//                this.invalidate();
//
//
//            }
//
//        }
//
//    }
//
//
//    private float[] calculateCenterByDegree(float degree) {
//        float x = (float) (Math.sin(Math.toRadians((double) degree)) * (double) this.radius);
//        float y = (float) (Math.cos(Math.toRadians((double) degree)) * (double) this.radius);
//        float startX = (float) this.centerX + x;
//        float startY = (float) this.centerY - y;
//        float[] position = new float[]{startX, startY};
//        return position;
//    }
//
//    private void drawData(Canvas canvas, float degree, int i, float percent) {
//        float startX = this.calculateCenterByDegree(degree)[0];
//        float startY = this.calculateCenterByDegree(degree)[1];
//        this.dataPaint.getTextBounds(this.names[i], 0, this.names[i].length(), this.dataTextBound);
//        canvas.drawText(this.names[i], startX - (float) (this.dataTextBound.width() / 2), startY + (float) (this.dataTextBound.height() / 2) - 20.0F, this.dataPaint);
//        DecimalFormat df = new DecimalFormat("0.0");
//        String percentString = df.format((double) (percent * 100.0F)) + "%";
//        this.dataPaint.getTextBounds(percentString, 0, percentString.length(), this.dataTextBound);
//        canvas.drawText(percentString, startX - (float) (this.dataTextBound.width() / 2), startY + (float) (this.dataTextBound.height() * 2) - 20.0F, this.dataPaint);
//    }
//
//    private void drawArc(Canvas canvas, float startAngle, float angle, int color) {
//        this.mArcPaint.setColor(color);
//        canvas.drawArc(this.rectF, startAngle - 0.5F, angle + 0.5F, false, this.mArcPaint);
//    }
//
//    private int randomColor() {
//        int red = this.random.nextInt(256);
//        int green = this.random.nextInt(256);
//        int blue = this.random.nextInt(256);
//        return Color.rgb(red, green, blue);
//    }
//
//    class ArcInfo {
//        float startAngle;
//        float angle;
//        float centerAngle;
//        float percent;
//        int color;
//
//        ArcInfo() {
//        }
//
//        float getCenterAngle() {
//            return this.centerAngle;
//        }
//
//        void setCenterAngle(float centerAngle) {
//            this.centerAngle = centerAngle;
//        }
//
//        float getStartAngle() {
//            return this.startAngle;
//        }
//
//        void setStartAngle(float startAngle) {
//            this.startAngle = startAngle;
//        }
//
//        float getAngle() {
//            return this.angle;
//        }
//
//        void setAngle(float angle) {
//            this.angle = angle;
//        }
//
//        float getPercent() {
//            return this.percent;
//        }
//
//        void setPercent(float percent) {
//            this.percent = percent;
//        }
//
//        int getColor() {
//            return this.color;
//        }
//
//        void setColor(int color) {
//            this.color = color;
//        }
//    }
}
