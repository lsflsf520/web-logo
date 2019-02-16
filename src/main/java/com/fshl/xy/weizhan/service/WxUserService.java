package com.fshl.xy.weizhan.service;

import com.fshl.xy.weizhan.dao.WxUserDao;
import com.fshl.xy.weizhan.entity.WxUser;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class WxUserService extends AbstractBaseService<Integer, WxUser> {
    @Resource
    private WxUserDao wxUserDao;

    @Override
    protected IBaseDao<Integer, WxUser> getBaseDao() {
        return wxUserDao;
    }

    public Integer insertReturnPK(WxUser wxUser) {
        wxUserDao.insertReturnPK(wxUser);
        return wxUser.getPK();
    }

    public Integer doSave(WxUser wxUser) {
        if (wxUser.getPK() == null) {
            return this.insertReturnPK(wxUser);
        }
        this.update(wxUser);
        return wxUser.getPK();
    }
}