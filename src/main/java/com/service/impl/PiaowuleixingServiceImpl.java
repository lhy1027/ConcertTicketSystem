package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.PiaowuleixingDao;
import com.entity.PiaowuleixingEntity;
import com.service.PiaowuleixingService;
import com.entity.vo.PiaowuleixingVO;
import com.entity.view.PiaowuleixingView;

@Service("piaowuleixingService")
public class PiaowuleixingServiceImpl extends ServiceImpl<PiaowuleixingDao, PiaowuleixingEntity> implements PiaowuleixingService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<PiaowuleixingEntity> page = this.selectPage(
                new Query<PiaowuleixingEntity>(params).getPage(),
                new EntityWrapper<PiaowuleixingEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<PiaowuleixingEntity> wrapper) {
		  Page<PiaowuleixingView> page =new Query<PiaowuleixingView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<PiaowuleixingVO> selectListVO(Wrapper<PiaowuleixingEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public PiaowuleixingVO selectVO(Wrapper<PiaowuleixingEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<PiaowuleixingView> selectListView(Wrapper<PiaowuleixingEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public PiaowuleixingView selectView(Wrapper<PiaowuleixingEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
