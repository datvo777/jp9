package e.datvo_000.jp9shop.CustomView;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import e.datvo_000.jp9shop.R;

/**
 * Created by datvo_000 on 25/02/2019.
 */

public class PasswordEditText extends AppCompatEditText {
    Drawable eye , eyeStrike;
    boolean visible = false;
    boolean useStrike=false;
    Boolean useValidate = false;

    Drawable drawable;
    int ALPHA = (int) (255 * .70f);//mờ 70 %   //*:xh 0 hoac nhieu lan
    public static final String MATCHER_PATTERN = "(?=.*\\d)(?!.*#)(?=.*[A-Z])(?=.*[a-z]).{6,20}"; // Positive Lookahead ?=khớp với trong ngoặc đơn .:nhiuwf ký tự ;(x):Khớp 'x' và nhớ kết quả so khớp này
    public PasswordEditText(Context context) {//Required for creating an instance of a view
        super(context);
        init(null);
    }

    public PasswordEditText(Context context, AttributeSet attrs) { //required to inflate the view from an XML layout and apply XML attributes.
        super(context, attrs);
        init(attrs);
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {//Required to apply a default style to all UI elements without having to specify it in each layout file.
        super(context, attrs, defStyleAttr);
        init(attrs );
    }
    public void  init(AttributeSet attributeSet) //AttributeSet truyền từ xml vào
    {

        if (attributeSet !=null)
        {
            TypedArray array = getContext().getTheme().obtainStyledAttributes(attributeSet,R.styleable.PasswordEditText,0,0);
            this.useStrike = array.getBoolean(R.styleable.PasswordEditText_useStrike,false);//mặc định
            this.useValidate= array.getBoolean(R.styleable.PasswordEditText_useValidate,false);
        }
        //xài Appcompat nên dùng ContextCompat
        eye = ContextCompat.getDrawable(getContext(), R.drawable.visible_black);//.mutate();//
        eyeStrike= ContextCompat.getDrawable(getContext(), R.drawable.not_visible_black);//.mutate();

        if(this.useValidate){
            setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if(!b) {
                        //hết focus ->   kiểm tra

                        String chuoi = getText().toString();
                        TextInputLayout textInputLayout = (TextInputLayout) view.getParentForAccessibility();
                      //  matcher = pattern.matcher(chuoi);
                        boolean  isMatch = chuoi.matches(MATCHER_PATTERN);
                        if(!isMatch){
                            textInputLayout.setErrorEnabled(true);//mở hiển thị lỗi
                            textInputLayout.setError("Mật khẩu phải có ít nhất 6 ký tự , 1 chữ hoa và 1 số và ko chứa '$'");
                        }else{
                            textInputLayout.setErrorEnabled(false);
                            textInputLayout.setError("");
                        }


                    }
                }
            });
        }
        install();
    }
    public void install()
    {
        setInputType(InputType.TYPE_CLASS_TEXT |(visible? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD));
        drawable = useStrike && !visible? eyeStrike : eye;
        drawable.setAlpha(ALPHA); // làm hình mờ
        //set drawable nằm bên phải nên nó nằm ở [2]
        setCompoundDrawablesWithIntrinsicBounds(null,null,drawable,null);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //     chạm vô con mắt
        //getRight()(lấy chiều ngang của PasswordEditText ) - drawable.getBounds().width() ra dc phần drawable con mắt
        //ACTION_Down bị trùng sự kiện nên xài ACTION_UP ,super.onTouchEvent(event) mới không bị chồng lấn sự kiện (ko click dc)
        if(event.getAction() == MotionEvent.ACTION_UP && event.getX() >= (getWidth() - drawable.getBounds().width()) ){
            visible = !visible;
            install();
            //invalidate();
        }
        return super.onTouchEvent(event);
    }

}
