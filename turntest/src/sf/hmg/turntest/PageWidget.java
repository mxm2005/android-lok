package sf.hmg.turntest;

import java.text.DecimalFormat;
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
	private int m_mbBufBegin;
	private static int m_mbBufLen;
	
	public void setM_mbBufBegin(int m_mbBufBegin) {
		this.m_mbBufBegin = m_mbBufBegin;
	}

	@SuppressWarnings("static-access")
	public void setM_mbBufLen(int m_mbBufLen) {
		this.m_mbBufLen = m_mbBufLen;
	}


	public void setM_lines(Vector<String> m_lines) {
		this.m_lines = m_lines;
	}

	public PageWidget(Context context) {
		super(context);
		this.mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    	this.mTextPaint.setTextAlign(Align.LEFT);
    	this.mTextPaint.setTextSize(9);
    	this.mTextPaint.setColor(Color.BLACK);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(0xffff9e85);
		mTextPaint.setAntiAlias(true);
		int y = 9;
		int x = 8;
		for (String strLine : m_lines) {
//			System.out.println("strLine   " + strLine);
			y += (9);
			canvas.drawText(strLine, x, y, mTextPaint);
		}
//		System.out.println("长度： " + m_mbBufLen + "\n" + "begin：　" + m_mbBufBegin);
		float fPercent = (float) (m_mbBufBegin * 1.0 / m_mbBufLen);
		DecimalFormat df = new DecimalFormat("#0.0");
		String strPercent = df.format(fPercent * 100) + "%";
		int nPercentWidth = (int) mTextPaint.measureText("999.9%") + 1;//999.9%字符串的宽度
		canvas.drawText(strPercent, 240 - nPercentWidth, 180 - 5, mTextPaint);
		super.onDraw(canvas);
	}

}
