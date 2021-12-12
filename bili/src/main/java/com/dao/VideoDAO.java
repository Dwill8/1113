package com.dao;

import java.util.List;
import java.util.Map;

public interface VideoDAO {
    //检查是否已投币
    public Map checkReward(Integer uid, Integer vid);

    //检查是否还有币
    public int checkCoinNum(Integer uid);

    // 投币
    public boolean rewardCoin(Integer uid, Integer vid);

    //检查是否已点赞
    public Map checkLike(Integer uid, Integer vid);

    // 点赞
    public boolean like(Integer uid, Integer vid);

    // 弹幕生成
    public List<String> bulletScreenList(Integer vid);


}
