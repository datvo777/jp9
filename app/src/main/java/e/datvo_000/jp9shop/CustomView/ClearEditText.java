package e.datvo_000.jp9shop.CustomView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import e.datvo_000.jp9shop.R;

/**
 * Created by datvo_000 on 26/02/2019.
 */

public class ClearEditText extends AppCompatEditText {
    Drawable crossX,nonecrossX,drawable;
    boolean visible = false;
    public ClearEditText(Context context) {
        super(context);
        init();
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private  void  init()
    {
        crossX = ContextCompat.getDrawable(getContext(), R.drawable.delete_24px);
        nonecrossX=ContextCompat.getDrawable(getContext(),android.R.drawable.screen_background_light_transparent );
        install();

    }
    private void install()
    {
        setInputType(InputType.TYPE_CLASS_TEXT);
        drawable = visible ? crossX : nonecrossX;
        setCompoundDrawablesWithIntrinsicBounds(null,null,drawable,null);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (text.toString().equals(""))
        {
            visible=false;
            install();
        }else {
            visible=true;
            install();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP && event.getX() >= (getWidth() - drawable.getBounds().width()) ){
            setText("");
        }
        return super.onTouchEvent(event);
    }
}
