package com.service;

import java.util.List;
import java.util.Map;

public interface VideoService {
    // 投币
    public Map rewardCoin(Integer uid, Integer vid);

    // 点赞
    public Map like(Integer uid, Integer vid);

    // 弹幕生成
    public List<String> bulletScreenList(Integer vid);

}
