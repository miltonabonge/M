package com.example.firstrealapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

// to build every trivia(multiple choice question) app the systematic approach to it is as follows:
//  --> you create your app, then you create the xml file for your main activity
//  then you create the fragments that you need, and the respective xml files.
//then you create a utils class; this class holds the string Map for all lists tha display in your spinner
//then you create your POJO resource; this resource carries two classes;
//        -->Trivia Response (sthis carries the response code from the API, and we see this in the post man app.)
//        --> the pojo resource folder also carries the Question Item; this is the same as the GSON from the API, but we just convert it into Java with the aid of
// a converter, n then we paste it in this class.

// the next thing we create is the retrofit resource folder;
// this resource carries the
//     --> RetrofitClientInstance; this is a singleton class that enables us to only have one retrofit object through out the whole project.
//  this class carries the base URL in our case.
//     --> TriviaService; this interface is the communication link between two or more classes or btn two fragments from diff classes. thus this is where we run our
// querys, for all the different criterias that are available in the API.
//

public class MainGson extends AppCompatActivity implements StartGame.OnFragmentInteractionListener, SetGameFragment.OnFragmentInteractionListener {
    private static final String TAG = "MainGson";

    // Fragments
    private QuestionAnsFragment questionAnsFragmen;
    private StartGame startGame;
    private SetGameFragment setGameFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_gson);
        startGame = new StartGame();
        setGameFragment = new SetGameFragment();
        questionAnsFragmen = new QuestionAnsFragment();
        replaceFragment(startGame, "StartGame");
    }

    private void replaceFragment(Fragment fragment, String tag) {
        getSupportFragmentManager().
                beginTransaction()
                .replace(R.id.frame_layout, fragment, tag)
                .commit();
    }

    @Override
    public void startGame() {
        replaceFragment(setGameFragment, "SetGame");
    }

    @Override
    public void loadQuestionAnsFragment(String category, String level) {
        questionAnsFragmen.loadAllQuestions(category, level);
        replaceFragment(questionAnsFragmen, "QuestionAnsFragment");
    }
}
