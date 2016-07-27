package com.woyi.towerzj_app.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

public class ClearEditText extends EditText {
	private Paint linePaint;  
    private int paperColor;  
  
    public ClearEditText(Context context,AttributeSet paramAttributeSet) {  
        super(context,paramAttributeSet);  
        // TODO Auto-generated constructor stub   
        this.linePaint = new Paint();  
        linePaint.setColor(Color.WHITE);//设置下划线颜色   
    }  
      
    protected void onDraw(Canvas paramCanvas) {  
        paramCanvas.drawColor(this.paperColor); //设置背景色   
        int i = getLineCount();  
        int j = getHeight();  
        int k = getLineHeight();  
        int m = 1 + j / k;  
        if (i < m) i = m;  
        int n = getCompoundPaddingTop();  
  
        for (int i2 = 0;; i2++) {  
            if (i2 >= i) {  
                super.onDraw(paramCanvas);  
                paramCanvas.restore();  
                return;  
            }  
              
            n += k;  
            paramCanvas.drawLine(0.0F, n, getRight(), n, this.linePaint);  
            paramCanvas.save();  
        }  
    }  



}
