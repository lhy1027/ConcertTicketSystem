package com.entity.view;

import com.entity.PiaowuleixingEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 票务类型
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2030-09-10 21:46:19
 */
@TableName("piaowuleixing")
public class PiaowuleixingView  extends PiaowuleixingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public PiaowuleixingView(){
	}
 
 	public PiaowuleixingView(PiaowuleixingEntity piaowuleixingEntity){
 	try {
			BeanUtils.copyProperties(this, piaowuleixingEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
