package hackathon.thesesh.com.hackathon2017;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Sam on 28/10/2017.
 */

public class DodgyEditText extends android.support.v7.widget.AppCompatEditText {

    public DodgyEditText(Context context) {
        super(context);
    }

    public DodgyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DodgyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);

    }


}
