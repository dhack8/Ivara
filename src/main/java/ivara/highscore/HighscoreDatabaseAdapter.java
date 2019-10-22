package ivara.highscore;

import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class HighscoreDatabaseAdapter {

    public static CollectionReference HIGHSCORE_COLLECTION = null;

    public static void setupDatabase(){
        System.out.println(System.getenv("GOOGLE_APPLICATION_CREDENTIALS"));
        GoogleCredentials credentials = null;
        try {
            credentials = GoogleCredentials.getApplicationDefault();
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setDatabaseUrl("https://the-adventures-of-pablo.firebaseio.com/")
                    .setCredentials(credentials)
                    .build();
            FirebaseApp.initializeApp(options);

            HIGHSCORE_COLLECTION = FirestoreClient.getFirestore().collection("highscore");

        } catch (IOException | FirestoreException e) {
            System.out.println("Failed setup the database");
            e.printStackTrace();
        }
    }

    public static void setHighScore(String level, Highscore highScore){
        CollectionReference collection = HIGHSCORE_COLLECTION.document(level).collection("highscores");
        collection.add(highScore);
    }

    public static void getHighScores(String level, HighscoreCallback cb) {
        CollectionReference collection = HIGHSCORE_COLLECTION.document(level).collection("highscores");

        ApiFutures.addCallback(collection.get(), new ApiFutureCallback<QuerySnapshot>() {
            @Override
            public void onFailure(Throwable t) {
                System.out.println("Failed to collect the highscore");
            }

            @Override
            public void onSuccess(QuerySnapshot result) {
                cb.callback(result.getDocuments().stream().map(d -> d.toObject(Highscore.class)).collect(Collectors.toList()));
            }
        }, Executors.newSingleThreadExecutor());
    }






}
