package com.example.planalife.ui.note;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.planalife.R;

import java.io.IOException;
import java.util.Objects;

public class NotificationsFragment extends Fragment {

    private MediaRecorder recorder;
    private String fileName = "pomme";

    private CountDownTimer timer;

    NoteAdaptater noteAdaptater;

    long secondsRemaining;

    private Toast toast;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        fileName = Objects.requireNonNull(getActivity()).getFilesDir().getAbsolutePath();
        fileName += "/audio_recording.3gp";

        Button startButton = view.findViewById(R.id.start_recording_button);
        Button stopButton = view.findViewById(R.id.stop_recording_button);
        Button lecture = view.findViewById(R.id.lecture);

        noteAdaptater = new NoteAdaptater(Objects.requireNonNull(getContext()));


        startButton.setOnClickListener(v -> startRecording());

        lecture.setOnClickListener(v -> {

            System.out.println("truite");

            String filePath = getActivity().getFilesDir().getAbsolutePath() + "/audio_recording.3gp";
            MediaPlayer mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(filePath);
                mediaPlayer.prepare();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            mediaPlayer.start();


        });

        stopButton.setOnClickListener(v -> stopRecording());
        return view;
    }

    private void startRecording() {
        final int REQUEST_RECORD_AUDIO_PERMISSION = 1;
        final int REQUEST_WRITE_STORAGE_PERMISSION = 1;
        // Vérifie si la permission d'enregistrement audio a été accordée
        if (ContextCompat.checkSelfPermission(Objects.requireNonNull(getContext()),
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            // Demande la permission d'enregistrement audio à l'utilisateur
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO_PERMISSION);

        } else if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_STORAGE_PERMISSION);
        } else {
            // La permission a déjà été accordée, on peut démarrer l'enregistrement
            recorder = new MediaRecorder();
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            recorder.setOutputFile(fileName);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            try {
                recorder.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }

            recorder.start();
            timer = new CountDownTimer(60000, 1000) {
                public void onTick(long millisUntilFinished) {

                    secondsRemaining = millisUntilFinished / 1000;
                    toast = Toast.makeText(getContext(), "Temps restant: " + secondsRemaining + " secondes", Toast.LENGTH_SHORT);
                    toast.show();

                }
                public void onFinish() {
                    stopRecording();
                }
            }.start();
        }
    }

    private void stopRecording() {

        System.out.println("pomme pote");

        if (recorder != null) {

            System.out.println("poire");
            recorder.stop();
            recorder.release();
            recorder = null;

            if (timer != null) {
                System.out.println("pute");
                timer.cancel();
            }

            if (toast != null) {
                System.out.println("pomme");
                toast.cancel();
            }


        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}