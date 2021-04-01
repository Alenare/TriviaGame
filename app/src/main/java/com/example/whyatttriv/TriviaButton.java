package com.example.whyatttriv;

import android.content.Context;

import androidx.appcompat.widget.AppCompatButton;

public class TriviaButton extends AppCompatButton {
        private Trivia trivia;

        public TriviaButton(Context context, Trivia newTrivia ) {
            super( context );
            trivia = newTrivia;
        }

}
