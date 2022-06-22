package com.robotic.robotsdks;

import android.content.Context;

import com.ainirobot.coreservice.client.actionbean.Pose;
import com.robotic.robotsdks.ROS.ROSSDK;
import com.robotic.robotsdks.TEMI.TemiSDK;

import java.util.ArrayList;
import java.util.List;


public class MainSDK {
    Context context;
    public ROSSDK rossdk;
    public TemiSDK temiSDK;
    private String robotType;



    public MainSDK(Context context, String robottype){
        this.context = context;
        switch (robottype){
            case "ROS" : rossdk = new ROSSDK(context); break;
            case "TEMI" :temiSDK = new TemiSDK(context);break;
        }
        setRobotType(robottype);
    }


    public void moveRobot(String location){
        switch (getRobotType()){
            case "TEMI" : temiSDK.goTo(location);break;
            case "ROS" : rossdk.startNavigation(location);break;

        }
    }

    public void moveRobot(String x,String y,String z){
        switch (getRobotType()){


        }
    }

    public List<String> getLocation(){
        List<String> locationName = new ArrayList<>();
        switch (getRobotType()){
            case "TEMI" : locationName = temiSDK.getLocation();break;
            case "ROS" :
                for(Pose location : rossdk.getLocation()){
                    locationName.add(location.getName());
                }
            break;

        }

        return locationName;
    }

    public void setLocation(String location){
        switch (getRobotType()){
            case "TEMI" : temiSDK.setLocation(location);break;
            case "ROS" : rossdk.setLocation(location);break;
        }
    }
    public void removeLocation(String location){
        switch (getRobotType()){
            case "TEMI" : temiSDK.removeLocation(location);break;
            case "ROS" : rossdk.removeLocation(location);break;
        }

    }


    public String getRobotType() {
        return robotType;
    }

    public void setRobotType(String robotType) {
        this.robotType = robotType;
    }
}
