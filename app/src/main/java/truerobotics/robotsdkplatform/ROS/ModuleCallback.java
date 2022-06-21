/*
 *  Copyright (C) 2017 OrionStar Technology Project
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package truerobotics.robotsdkplatform.ROS;

import android.os.RemoteException;
import android.util.Log;

import com.ainirobot.coreservice.client.module.ModuleCallbackApi;


public class ModuleCallback extends ModuleCallbackApi {

    private static final String TAG = ModuleCallback.class.getName();

    /**
     * 接收语音指令 底层发起request 请求
     *
     * @param reqId
     * @param reqType 语音指令类型
     * @param reqText 语音识别内容
     * @param reqParam 语音指令参数
     * @throws RemoteException
     */
    @Override
    public boolean onSendRequest(int reqId, String reqType, String reqText, String reqParam) throws RemoteException {
        Log.d(TAG, "New request: " + " type is:" + reqType + " text is:" + reqText + " reqParam = " + reqParam);
        String text = "New request: " + " type is:" + reqType + " text is:" + reqText + " reqParam = " + reqParam;
        LogTools.info(text);
        return true;
    }

    /**
     * 硬件出现异常回调
     * @param function
     * @param type
     * @param message
     * @throws RemoteException
     */
    @Override
    public void onHWReport(int function, String type, String message) throws RemoteException {
        Log.i(TAG, "onHWReport function:" + function + " type:" + type + " message:" + message);
        LogTools.info("onHWReport function:" + function + " type:" + type + " message:" + message);
    }

    /**
     * 控制权被系统剥夺，收到该事件后，所有Api调用无效
     * @throws RemoteException
     */
    @Override
    public void onSuspend() throws RemoteException {
        Log.d(TAG, "onSuspend");
        LogTools.info("onSuspend");
    }

    /**
     * 控制权恢复，收到该事件后，重新恢复对机器人的控制
     * @throws RemoteException
     */
    @Override
    public void onRecovery() throws RemoteException {
        Log.d(TAG, "onRecovery");
        LogTools.info("onRecovery");
    }
}
