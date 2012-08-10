/*package com.wz.doctor.widget;

import com.wz.doctor.HomeActivity;
import com.wz.doctor.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AdviceListItem extends LinearLayout {

	public AdviceListItem(Context context, String code, String symbol,
			String board, float lastPrice, float changePrice,
			float changePercent) {
		super(context);

		HomeActivity homeActivity = (HomeActivity) context;
		float textSize = homeActivity.getTextSize();

		LayoutInflater factory = LayoutInflater.from(context);
		factory.inflate(R.layout.advice_list_item, this);

		TextView codeTextView = (TextView) findViewById(R.id.stock_code);
		codeTextView.setTextSize(textSize);
		codeTextView.setText(code);

		TextView symbolTextView = (TextView) findViewById(R.id.stock_symbol);
		symbolTextView.setTextSize(textSize);
		symbolTextView.setText(symbol);

		TextView lastPriceTextView = (TextView) findViewById(R.id.stock_last_price);
		lastPriceTextView.setTextSize(textSize);
		lastPriceTextView.setText(Float.toString(lastPrice));

		TextView changePriceTextView = (TextView) findViewById(R.id.stock_change_price);
		changePriceTextView.setTextSize(textSize);
		changePriceTextView.setText(Float.toString(changePrice));

		TextView ChangePercentTextView = (TextView) findViewById(R.id.stock_change_percentage);
		ChangePercentTextView.setTextSize(textSize);
		ChangePercentTextView.setText(Float.toString(changePercent));

		if (changePrice > 0) {
			int textColor = homeActivity.getUpTextColor();

			// codeTextView.setTextColor(textColor);
			// symbolTextView.setTextColor(textColor);
			lastPriceTextView.setTextColor(textColor);
			changePriceTextView.setTextColor(textColor);
			ChangePercentTextView.setTextColor(textColor);
		} else if (changePrice < 0) {
			int textColor = homeActivity.getDownTextColor();

			// codeTextView.setTextColor(textColor);
			// symbolTextView.setTextColor(textColor);
			lastPriceTextView.setTextColor(textColor);
			changePriceTextView.setTextColor(textColor);
			ChangePercentTextView.setTextColor(textColor);
		}
	}

}
*/