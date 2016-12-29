package google.com.lab2.fragments.main_screen.view;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import google.com.lab2.R;
import google.com.lab2.fragments.base_fragment.view.BaseView;
import google.com.lab2.fragments.main_screen.GameNumber;
import google.com.lab2.fragments.main_screen.presenter.IMainScreenPresenter;
import google.com.lab2.utils.TextUtils;

/**
 * Created by Sergey on 13.11.2016.
 */

public class MainScreenView extends BaseView implements IMainScreenView{

    private final Context context;
    private final IMainScreenPresenter presenter;

    @BindView(R.id.message_text_view)
    TextView messageTextView;

    @BindView(R.id.input_number_edit_text)
    EditText inputNumberEditText;

    @BindView(R.id.guess_button)
    Button guessButton;

    @BindView(R.id.anim_background)
    View animBackground;

    public MainScreenView(View root, IMainScreenPresenter presenter, Context context) {
        this.context = context;
        this.presenter = presenter;
        ButterKnife.bind(this, root);
        setupView();
    }

    private void setupView() {
        setDefaultView();
        setupInputText();
    }

    private void setupInputText() {
        inputNumberEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearInputText();
            }
        });
    }

    @Override
    public void setDefaultView() {
        clearView();
        setDefaultMessage();
    }

    private void clearView() {
        clearInputText();
    }

    private void clearInputText() {
        inputNumberEditText.setText("");
    }

    private void setDefaultMessage() {
        messageTextView.setText(context.getString(R.string.default_number_message));
    }

    @OnClick(R.id.guess_button)
    void onGuessButtonClicked(){
        presenter.onGuessButtonClicked(inputNumberEditText.getText().toString());
        clearInputText();
        startAnimation();
    }

    @Override
    public void updateMessageStatus(int messageNumber) {
        String message = TextUtils.getNumberMessage(messageNumber, context);
        if(messageNumber == GameNumber.INVALID_NUMBER_ERROR_MESSAGE || messageNumber == GameNumber.NOT_NUMBER_ERROR_MESSAGE){
            inputNumberEditText.setError(message);
            messageTextView.setText(getCommonErrorString());
        }else{
            messageTextView.setText(message);
        }
        tryShowAgainView(messageNumber);
    }

    private void tryShowAgainView(int messageNumber) {
        if(messageNumber == GameNumber.CORRECT_NUMBER_MESSAGE){
            showAgainView();
        }
    }

    private void showAgainView() {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.success_dialog_title))
                .setMessage(getSuccessMessageText())
                .setPositiveButton(context.getString(R.string.success_dialog_positive_button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.onSuccessDialogAgainClicked();
                    }
                })
                .setNegativeButton(context.getString(R.string.success_dialog_negative_button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.onSuccessDialogStopClicked();
                    }
                })
                .create();
        dialog.show();
    }

    private String getSuccessMessageText() {
        return context.getString(R.string.success_dialog_message, presenter.getAttemptCount());
    }

    private String getCommonErrorString() {
        return context.getString(R.string.common_error_text);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startAnimation(){

        int centerX = (guessButton.getLeft() + guessButton.getRight()) / 2;
        int centerY = (guessButton.getTop() + guessButton.getBottom()) / 2;

        int startRadius = 0;
        int endRadius = Math.max(animBackground.getWidth(), animBackground.getHeight());

        Animator anim =
                ViewAnimationUtils.createCircularReveal(animBackground, centerX, centerY, startRadius, endRadius);

        animBackground.setVisibility(View.VISIBLE);
        anim.start();
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animBackground.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }
}
