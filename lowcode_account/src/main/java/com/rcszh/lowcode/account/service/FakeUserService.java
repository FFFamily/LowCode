package com.rcszh.lowcode.account.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rcszh.lowcode.account.entity.FakeUser;
import com.rcszh.lowcode.account.mapper.FakeUserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FakeUserService {
    @Resource
    private FakeUserMapper fakeUserMapper;

    public FakeUser findUserByUserName(String userName) {
        return fakeUserMapper.selectOne(new LambdaQueryWrapper<FakeUser>().eq(FakeUser::getUsername, userName));
    }

    public List<FakeUser> getAllUser() {
        return fakeUserMapper.selectList(null);
    }

    public FakeUser getById(String userId) {
        return fakeUserMapper.selectById(userId);
    }


}
