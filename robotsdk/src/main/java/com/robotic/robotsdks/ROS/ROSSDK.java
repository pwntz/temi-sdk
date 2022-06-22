package com.robotic.robotsdks.ROS;

import android.content.Context;
import android.os.HandlerThread;
import android.os.RemoteException;
import android.util.Log;

import com.ainirobot.coreservice.client.ApiListener;
import com.ainirobot.coreservice.client.Definition;
import com.ainirobot.coreservice.client.RobotApi;
import com.ainirobot.coreservice.client.actionbean.Pose;
import com.ainirobot.coreservice.client.listener.ActionListener;
import com.ainirobot.coreservice.client.listener.CommandListener;
import com.ainirobot.coreservice.client.module.ModuleCallbackApi;
import com.ainirobot.coreservice.client.speech.SkillApi;
import com.robotic.robotsdks.MainSDK;

import java.util.List;


public class ROSSDK {

    private SpeechCallback mSkillCallback;
    private HandlerThread mApiCallbackThread;
    private ModuleCallbackApi mModuleCallback;
    private ActionListener mActionListener;
    private SkillApi mSkillApi;
    private static final String TAG = MainSDK.class.getName();
    Context context;

    public ROSSDK(Context context){
        this.context = context;
        ROSinit();
        ROSinitRobotApi();
    }

    private void ROSinit(){
        mSkillCallback = new SpeechCallback();
        mModuleCallback = new ModuleCallback();
        mApiCallbackThread = new HandlerThread("RobotOSDemo");
        mApiCallbackThread.start();
    }

    private void ROSinitRobotApi() {
        RobotApi.getInstance().connectServer(context, new ApiListener() {
            @Override
            public void handleApiDisabled() {
                Log.i(TAG, "handleApiDisabled");
            }
            @Override
            public void handleApiConnected() {
                Log.i(TAG, "handleApiConnected");
                addApiCallBack();
                initSkillApi();
            }
            @Override
            public void handleApiDisconnected() {
                Log.i(TAG, "handleApiDisconnected");
            }
        });
    }

    private void addApiCallBack() {
        Log.d(TAG, "CoreService connected ");
        RobotApi.getInstance().setCallback(mModuleCallback);
        RobotApi.getInstance().setResponseThread(mApiCallbackThread);
    }

    private void initSkillApi() {
        mSkillApi = new SkillApi();
        ApiListener apiListener = new ApiListener() {
            @Override
            public void handleApiDisabled() {
            }
            @Override
            public void handleApiConnected() {
                mSkillApi.registerCallBack(mSkillCallback);
            }
            @Override
            public void handleApiDisconnected() {
            }
        };
        mSkillApi.addApiEventListener(apiListener);
        mSkillApi.connectApi(context);
    }


    private void initActionListener(){
        mActionListener = new ActionListener() {
            @Override
            public void onResult(int status, String response) throws RemoteException {

                switch (status) {
                    case Definition.RESULT_OK:
                        if ("true".equals(response)) {
                            LogTools.info("startNavigation result: " + status +"(导航成功)"+ " message: "+  response);
                        } else {
                            LogTools.info("startNavigation result: " + status +"(导航失败)"+ " message: "+  response);
                        }
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errorCode, String errorString) throws RemoteException {
                switch (errorCode) {
                    case Definition.ERROR_NOT_ESTIMATE:
                        LogTools.info("onError result: " + errorCode +"(当前未定位)"+ " message: "+  errorString);
                        break;
                    case Definition.ERROR_IN_DESTINATION:
                        LogTools.info("onError result: " + errorCode +"(当前机器人已经在目的地范围内)"+ " message: "+  errorString);
                        break;
                    case Definition.ERROR_DESTINATION_NOT_EXIST:
                        LogTools.info("onError result: " + errorCode +"(导航目的地不存在)"+ " message: "+  errorString);
                        break;
                    case Definition.ERROR_DESTINATION_CAN_NOT_ARRAIVE:
                        LogTools.info("onError result: " + errorCode +"(避障超时，目的地不能到达，超时时间通过参数设置)"+ " message: "+  errorString);
                        break;
                    case Definition.ACTION_RESPONSE_ALREADY_RUN:
                        LogTools.info("onError result: " + errorCode +"(当前接口已经调用，请先停止，才能再次调用)"+ " message: "+  errorString);
                        break;
                    case Definition.ACTION_RESPONSE_REQUEST_RES_ERROR:
                        LogTools.info("onError result: " + errorCode +"(已经有需要控制底盘的接口调用，请先停止，才能继续调用)"+ " message: "+  errorString);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onStatusUpdate(int status, String data) throws RemoteException {
                switch (status) {
                    case Definition.STATUS_NAVI_AVOID:
                        LogTools.info("onStatusUpdate result: " + status +"(当前路线已经被障碍物堵死)"+ " message: "+  data);
                        break;
                    case Definition.STATUS_NAVI_AVOID_END:
                        LogTools.info("onStatusUpdate result: " + status +"(障碍物已移除)"+ " message: "+  data);
                        break;
                    default:
                        break;
                }
            }
        };

    }

    public void startNavigation(String location) {
        RobotApi.getInstance().startNavigation(0, location, 1.5, 10 * 1000, mActionListener);
    }

    private void stopNavigation() {
        RobotApi.getInstance().stopNavigation(0);
    }
    public List<Pose> getLocation(){

        List<Pose> location =  RobotApi.getInstance().getPlaceList();

        return location;

    }
    public void setLocation(String location){
        RobotApi.getInstance().setLocation(0, location, new CommandListener() {
            @Override
            public void onResult(int result, String message) {
                LogTools.info("setLocation result: " + result + " message: " + message);
                if ("succeed".equals(message)) {

                } else {

                }
            }
        });
    }
    public void removeLocation(String location){
        RobotApi.getInstance().removeLocation(0, location, new CommandListener() {
            @Override
            public void onResult(int result, String message) {
                LogTools.info("removeLocation result: " + result + " message: "+ message);
                if ("succeed".equals(message)) {
                } else {
                }
            }
        });
    }


    public SkillApi getSkillApi() {
        if (mSkillApi.isApiConnectedService()) {
            return mSkillApi;
        }
        return null;
    }


}


