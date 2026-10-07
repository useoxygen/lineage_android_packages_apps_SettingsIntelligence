/*
 * Copyright 2026 TextGavel. Licensed under the Apache License, Version 2.0.
 */

package com.android.settings.intelligence.search;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.android.settings.intelligence.R;

/**
 * Cyclon: a hairline under every search result, inset like the rows of Settings home
 * (design draft 4: flat rows split by hairlines, no cards).
 */
class CyclonHairlineDecoration extends RecyclerView.ItemDecoration {
    private final Paint mPaint = new Paint();
    private final int mHeight;
    private final int mInset;

    CyclonHairlineDecoration(Context context) {
        mPaint.setColor(context.getColor(R.color.cyclon_hairline));
        mHeight = context.getResources().getDimensionPixelSize(R.dimen.cyclon_hairline);
        mInset = context.getResources().getDimensionPixelSize(
                R.dimen.cyclon_row_padding_horizontal);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
            RecyclerView.State state) {
        outRect.set(0, 0, 0, mHeight);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        final int left = parent.getPaddingLeft() + mInset;
        final int right = parent.getWidth() - parent.getPaddingRight() - mInset;
        for (int i = 0; i < parent.getChildCount(); i++) {
            final View child = parent.getChildAt(i);
            final int top = child.getBottom() + Math.round(child.getTranslationY());
            c.drawRect(left, top, right, top + mHeight, mPaint);
        }
    }
}
