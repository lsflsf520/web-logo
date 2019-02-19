package com.fshl.xy.weizhan.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fshl.xy.weizhan.dao.ImgTextDao;
import com.fshl.xy.weizhan.entity.ImgText;
import com.xyz.tools.db.bean.PageData;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;

@Service
public class ImgTextService extends AbstractBaseService<Integer, ImgText> {
    @Resource
    private ImgTextDao imgTextDao;

    @Override
    protected IBaseDao<Integer, ImgText> getBaseDao() {
        return imgTextDao;
    }

    public Integer insertReturnPK(ImgText imgText) {
        imgTextDao.insertReturnPK(imgText);
        return imgText.getPK();
    }

    public Integer doSave(ImgText imgText) {
        if (imgText.getPK() == null) {
            return this.insertReturnPK(imgText);
        }
        this.update(imgText);
        return imgText.getPK();
    }
    
    public PageData<ImgText> loadBySiteId(int siteId, int currPage) {
    	ImgText query = new ImgText();
    	
    	return this.findByPage(query, currPage, 10);
    }
}