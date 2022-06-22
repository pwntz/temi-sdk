package com.robotic.robotsdks.TEMI;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import androidx.annotation.NonNull;


import com.robotemi.sdk.NlpResult;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.activitystream.ActivityStreamPublishMessage;
import com.robotemi.sdk.exception.OnSdkExceptionListener;
import com.robotemi.sdk.exception.SdkException;
import com.robotemi.sdk.face.ContactModel;
import com.robotemi.sdk.face.OnContinuousFaceRecognizedListener;
import com.robotemi.sdk.face.OnFaceRecognizedListener;
import com.robotemi.sdk.listeners.OnBeWithMeStatusChangedListener;
import com.robotemi.sdk.listeners.OnConstraintBeWithStatusChangedListener;
import com.robotemi.sdk.listeners.OnConversationStatusChangedListener;
import com.robotemi.sdk.listeners.OnDetectionDataChangedListener;
import com.robotemi.sdk.listeners.OnDetectionStateChangedListener;
import com.robotemi.sdk.listeners.OnDisabledFeatureListUpdatedListener;
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener;
import com.robotemi.sdk.listeners.OnGreetModeStateChangedListener;
import com.robotemi.sdk.listeners.OnLocationsUpdatedListener;
import com.robotemi.sdk.listeners.OnMovementStatusChangedListener;
import com.robotemi.sdk.listeners.OnMovementVelocityChangedListener;
import com.robotemi.sdk.listeners.OnRobotLiftedListener;
import com.robotemi.sdk.listeners.OnRobotReadyListener;
import com.robotemi.sdk.listeners.OnTelepresenceEventChangedListener;
import com.robotemi.sdk.listeners.OnTtsVisualizerFftDataChangedListener;
import com.robotemi.sdk.listeners.OnTtsVisualizerWaveFormDataChangedListener;
import com.robotemi.sdk.listeners.OnUserInteractionChangedListener;
import com.robotemi.sdk.map.OnLoadFloorStatusChangedListener;
import com.robotemi.sdk.map.OnLoadMapStatusChangedListener;
import com.robotemi.sdk.model.CallEventModel;
import com.robotemi.sdk.model.DetectionData;
import com.robotemi.sdk.navigation.listener.OnCurrentPositionChangedListener;
import com.robotemi.sdk.navigation.listener.OnDistanceToLocationChangedListener;
import com.robotemi.sdk.navigation.listener.OnReposeStatusChangedListener;
import com.robotemi.sdk.navigation.model.Position;
import com.robotemi.sdk.permission.OnRequestPermissionResultListener;
import com.robotemi.sdk.permission.Permission;
import com.robotemi.sdk.sequence.OnSequencePlayStatusChangedListener;
import com.robotemi.sdk.voice.ITtsService;

import java.util.List;
import java.util.Map;

public class TemiSDK  implements Robot.NlpListener, OnRobotReadyListener,
        Robot.ConversationViewAttachesListener, Robot.WakeupWordListener, Robot.ActivityStreamPublishListener,
        Robot.TtsListener, OnBeWithMeStatusChangedListener, OnGoToLocationStatusChangedListener,
        OnLocationsUpdatedListener, OnConstraintBeWithStatusChangedListener,
        OnDetectionStateChangedListener, Robot.AsrListener, OnTelepresenceEventChangedListener,
        OnRequestPermissionResultListener, OnDistanceToLocationChangedListener,
        OnCurrentPositionChangedListener, OnSequencePlayStatusChangedListener, OnRobotLiftedListener,
        OnDetectionDataChangedListener, OnUserInteractionChangedListener, OnFaceRecognizedListener,
        OnConversationStatusChangedListener, OnTtsVisualizerWaveFormDataChangedListener,
        OnTtsVisualizerFftDataChangedListener, OnReposeStatusChangedListener,
        OnLoadMapStatusChangedListener, OnDisabledFeatureListUpdatedListener,
        OnMovementVelocityChangedListener, OnMovementStatusChangedListener,
        OnContinuousFaceRecognizedListener, ITtsService, OnGreetModeStateChangedListener,
        TextToSpeech.OnInitListener, OnLoadFloorStatusChangedListener, OnSdkExceptionListener {
    Context context;
    Robot robot;
    String LOG = "TemiSDK";
    TemiCallback temiCallback;

    public interface TemiCallback{
        void onUserInteraction(boolean b);
        void onRobotLifted(boolean b);

    }
    public TemiSDK(Context context){
        this.context = context;
        robot = Robot.getInstance();
        robot.addOnRobotReadyListener(this);
        robot.addNlpListener(this);
        robot.addOnBeWithMeStatusChangedListener(this);
        robot.addOnGoToLocationStatusChangedListener(this);
        robot.addConversationViewAttachesListenerListener(this);
        robot.addWakeupWordListener(this);
        robot.addTtsListener(this);
        robot.addOnLocationsUpdatedListener(this);
        robot.addOnConstraintBeWithStatusChangedListener(this);
        robot.addOnDetectionStateChangedListener(this);
        robot.addAsrListener(this);
        robot.addOnDistanceToLocationChangedListener(this);
        robot.addOnCurrentPositionChangedListener(this);
        robot.addOnSequencePlayStatusChangedListener(this);
        robot.addOnRobotLiftedListener(this);
        robot.addOnDetectionDataChangedListener(this);
        robot.addOnUserInteractionChangedListener(this);
        robot.hideTopBar();
    }

    public void TemiCallback(TemiCallback callback){
        temiCallback = callback;
    }

    public void goTo(String location){
        robot.goTo(location);
    }
    public List<String> getLocation(){

        List<String> location = robot.getLocations();

        return location;
    }
    public void setLocation(String location){
        robot.saveLocation(location);
        robot.speak(TtsRequest.create("Location " + location + "is Saved.",false));
    }

    public void removeLocation(String location){
        robot.deleteLocation(location);
        robot.speak(TtsRequest.create("Location " + location + "is Removed.",false));
    }


    @Override
    public void onInit(int i) {

    }

    @Override
    public void onSdkError(@NonNull SdkException e) {

    }

    @Override
    public void onLoadFloorStatusChanged(int i) {

    }

    @Override
    public void onPublish(@NonNull ActivityStreamPublishMessage activityStreamPublishMessage) {

    }

    @Override
    public void onAsrResult(@NonNull String s) {

    }

    @Override
    public void onConversationAttaches(boolean b) {

    }

    @Override
    public void onNlpCompleted(@NonNull NlpResult nlpResult) {

    }

    @Override
    public void onTtsStatusChanged(@NonNull TtsRequest ttsRequest) {

    }

    @Override
    public void onWakeupWord(@NonNull String s, int i) {

    }

    @Override
    public void onContinuousFaceRecognized(@NonNull List<ContactModel> list) {

    }

    @Override
    public void onFaceRecognized(@NonNull List<ContactModel> list) {

    }

    @Override
    public void onBeWithMeStatusChanged(@NonNull String s) {

    }

    @Override
    public void onConstraintBeWithStatusChanged(boolean b) {

    }

    @Override
    public void onConversationStatusChanged(int i, @NonNull String s) {

    }

    @Override
    public void onDetectionDataChanged(@NonNull DetectionData detectionData) {

    }

    @Override
    public void onDetectionStateChanged(int i) {

    }

    @Override
    public void onDisabledFeatureListUpdated(@NonNull List<String> list) {

    }

    @Override
    public void onGoToLocationStatusChanged(@NonNull String s, @NonNull String s1, int i, @NonNull String s2) {

    }

    @Override
    public void onGreetModeStateChanged(int i) {

    }

    @Override
    public void onLocationsUpdated(@NonNull List<String> list) {

    }

    @Override
    public void onMovementStatusChanged(@NonNull String s, @NonNull String s1) {

    }

    @Override
    public void onMovementVelocityChanged(float v) {

    }

    @Override
    public void onRobotLifted(boolean b, @NonNull String s) {
        temiCallback.onRobotLifted(b);
    }

    @Override
    public void onRobotReady(boolean isReady) {
        if (isReady) {
            try {
                ActivityInfo activityInfo = context.getPackageManager().getActivityInfo(ComponentName.unflattenFromString(context.getPackageName()), PackageManager.GET_META_DATA);
               robot.onStart(activityInfo);
                robot.hideTopBar();
                Log.d(LOG,"onRobotReady : Robot is ready");
            }catch (Exception exception){
                Log.d(LOG,"onRobotReady : " +exception.toString());
            }
        }else{
            Log.d(LOG,"onRobotReady : Robot is not ready");
        }
    }

    @Override
    public void onTelepresenceEventChanged(@NonNull CallEventModel callEventModel) {

    }

    @Override
    public void onTtsVisualizerFftDataChanged(@NonNull byte[] bytes) {

    }

    @Override
    public void onTtsVisualizerWaveFormDataChanged(@NonNull byte[] bytes) {

    }

    @Override
    public void onUserInteraction(boolean b) {
        temiCallback.onUserInteraction(b);
    }

    @Override
    public void onLoadMapStatusChanged(int i) {

    }

    @Override
    public void onCurrentPositionChanged(@NonNull Position position) {

    }

    @Override
    public void onDistanceToLocationChanged(@NonNull Map<String, Float> map) {

    }

    @Override
    public void onReposeStatusChanged(int i, @NonNull String s) {

    }

    @Override
    public void onRequestPermissionResult(@NonNull Permission permission, int i, int i1) {

    }

    @Override
    public void onSequencePlayStatusChanged(int i) {

    }

    @Override
    public void cancel() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void speak(@NonNull TtsRequest ttsRequest) {

    }
}
