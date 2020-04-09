package com.example.loggg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class SpeechActivity extends AppCompatActivity{

        //Initialize Variable for all the Button ID's you created in the xml file
        EditText etInput;
        Button btConvert,btClear;

        //Variable for TextToSpeech Engine that will convert the Text into the Audio File
        TextToSpeech textToSpeech;

        //There's always an Override method in the Main Class
        //@Override also explained in the video

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech);

        //Assign Variables declared above to each and every Button by using findViewById...
        //... to find the ID of the buttons from the xml file so that it links to the Java file...
        //...and we can assign ant task that the particular tool will perform through the Java file
        etInput = findViewById(R.id.et_input);
        btConvert = findViewById(R.id.bt_convert);
        btClear = findViewById(R.id.bt_clear);

        //Creating a new Object for textToSpeech
        //getAppliationContext() is to Return the context of the single, global Application object of the current process.
        //OnInitListener() is nothing but an Interface definition of a callback to be invoked, indicating the completion of the TextToSpeech engine initialization.
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {  //Called to signal the completion of the TextToSpeech engine initialization.
                if (i == TextToSpeech.SUCCESS){
                    //Select Language
                    int lang = textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });

        //Setting the button (button variable ID of the Java file) as a clickable item
        //View.OnClickListener() is an Interface definition for a callback to be invoked, when a view is clicked.
        btConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){  //
                //Get EditText Value
                //ToString() Returns a string representation of the object.
                //getText() Returns the Text that the Text View is displaying.
                String s = etInput.getText().toString();

                //Text Convert to Speech
                //QUEUE_FLUSH  is Queue mode where all entries in the playback queue (media to be played and text to be synthesized) are dropped and replaced by the new entry.
                int speech = textToSpeech.speak(s, TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        btClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //Clear EditText
                etInput.setText("");  //Clear Button will set the text inside the Text area to blank
            }
        });

    }

}
