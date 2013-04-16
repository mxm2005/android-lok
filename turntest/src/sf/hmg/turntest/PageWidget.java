package sf.hmg.turntest;

import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.view.View;

public class PageWidget extends View {

	private Vector<String> m_lines = new Vector<String>();
	private Paint mTextPaint;
	
	public Vector<String> getM_lines() {
		return m_lines;
	}

	public void setM_lines(Vector<String> m_lines) {
		this.m_lines = m_lines;
	}

	public PageWidget(Context context) {
		super(context);
		this.mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    	this.mTextPaint.setTextAlign(Align.LEFT);
    	this.mTextPaint.setTextSize(23);
    	this.mTextPaint.setColor(Color.BLACK);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(0xffff9e85);
		mTextPaint.setAntiAlias(true);
		int y = 23;
		int x = 8;
		for (String strLine : m_lines) {
			System.out.println("strLine   " + strLine);
			y += (23);
			canvas.drawText(strLine, x, y, mTextPaint);
		}
		int nPercentWidth = (int) mTextPaint.measureText("999.9%") + 1;//999.9%×Ö·û´®µÄ¿í¶È
		canvas.drawText("--------------------", 540 - nPercentWidth, 320 - 5, mTextPaint);
//		float fPercent = (float) (m_mbBufBegin * 1.0 / m_mbBufLen);
//		DecimalFormat df = new DecimalFormat("#0.0");
//		String strPercent = df.format(fPercent * 100) + "%";
//		int nPercentWidth = (int) mPaint.measureText("999.9%") + 1;//999.9%×Ö·û´®µÄ¿í¶È
//		canvas.drawText(strPercent, mWidth - nPercentWidth, mHeight - 5, mPaint);
		super.onDraw(canvas);
	}

}
