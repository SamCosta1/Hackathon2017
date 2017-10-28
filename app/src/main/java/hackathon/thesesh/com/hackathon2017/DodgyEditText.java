package hackathon.thesesh.com.hackathon2017;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Sam on 28/10/2017.
 */

public class DodgyEditText extends android.support.v7.widget.AppCompatEditText {

    private boolean textChanged = false;

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
        if (textChanged) {
            textChanged = false;
            setText(mutateText(text.toString()));
            setSelection(getText().length());
        }
        else {
            textChanged = true;
        }

    }

    private String mutateText(String text) {
        double randomNumber = Math.random();
        if (randomNumber < 0.2 && text.length() != 0) {
            return text.substring(0, text.length() - 1) + "a";
        }
        return text;
    }


}
