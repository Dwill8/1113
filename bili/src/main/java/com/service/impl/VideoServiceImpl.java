package com.service.impl;

import com.dao.UserDAO;
import com.dao.VideoDAO;
import com.dao.impl.UserDAOImpl;
import com.dao.impl.VideoDAOImpl;
import com.service.VideoService;;import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VideoServiceImpl implements VideoService {
    private VideoDAO videoDAO = new VideoDAOImpl();
    @Override
    public Map rewardCoin(Integer uid, Integer vid) {
        Map result = new HashMap();
        //检查是否已投币
        if(videoDAO.checkReward(uid, vid).get("status").equals(0)) {
            // 检查投币者还有没有币
            // 有币
            if(videoDAO.checkCoinNum(uid) > 0) {
                //检查关注成功是否写入数据库
                if (!videoDAO.rewardCoin(uid, vid)) {
                    // 未写入
                    result.put("uid", null);
                    result.put("vid", null);
                    result.put("status", 34400); //
                } else {
                    // 写入
                    result.put("uid", uid);
                    result.put("vid", vid);
                    result.put("status", 34200);
                }
            } else{
                // 没币
                result.put("uid", null);
                result.put("vid", null);
                result.put("status", 34402);
            }
        } else {
            // 已关注
            result.put("uid", uid);
            result.put("vid", vid);
            result.put("status", 34401);
        }
        return result;
    }

    @Override
    public Map like(Integer uid, Integer vid) {
        return null;
    }

    @Override
    public List<String> bulletScreenList(Integer vid) {
        return null;
    }
}
