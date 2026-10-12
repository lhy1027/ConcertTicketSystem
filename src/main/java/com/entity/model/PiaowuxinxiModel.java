package com.entity.model;

import com.entity.PiaowuxinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 票务信息
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2030-09-10 21:46:19
 */
public class PiaowuxinxiModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 票务类型
	 */
	
	private String piaowuleixing;
		
	/**
	 * 图片
	 */
	
	private String tupian;
		
	/**
	 * 演出日期
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date yanchuriqi;
		
	/**
	 * 演出时间
	 */
	
	private String yanchushijian;
		
	/**
	 * 结束时间
	 */
	
	private String jieshushijian;
		
	/**
	 * 演出城市
	 */
	
	private String yanchuchengshi;
		
	/**
	 * 座位类型
	 */
	
	private String zuoweileixing;
		
	/**
	 * 乐队介绍
	 */
	
	private String piaowujieshao;
		
	/**
	 * 单限
	 */
	
	private Integer onelimittimes;
		
	/**
	 * 余票
	 */
	
	private Integer alllimittimes;
		
	/**
	 * 价格
	 */
	
	private Double price;
		
	/**
	 * 收藏数
	 */
	
	private Integer storeupnum;
		
	/**
	 * 座位总数
	 */
	
	private Integer number;
		
	/**
	 * 已选座位[用,号隔开]
	 */
	
	private String selected;
				
	
	/**
	 * 设置：票务类型
	 */
	 
	public void setPiaowuleixing(String piaowuleixing) {
		this.piaowuleixing = piaowuleixing;
	}
	
	/**
	 * 获取：票务类型
	 */
	public String getPiaowuleixing() {
		return piaowuleixing;
	}
				
	
	/**
	 * 设置：图片
	 */
	 
	public void setTupian(String tupian) {
		this.tupian = tupian;
	}
	
	/**
	 * 获取：图片
	 */
	public String getTupian() {
		return tupian;
	}
				
	
	/**
	 * 设置：演出日期
	 */
	 
	public void setYanchuriqi(Date yanchuriqi) {
		this.yanchuriqi = yanchuriqi;
	}
	
	/**
	 * 获取：演出日期
	 */
	public Date getYanchuriqi() {
		return yanchuriqi;
	}
				
	
	/**
	 * 设置：演出时间
	 */
	 
	public void setYanchushijian(String yanchushijian) {
		this.yanchushijian = yanchushijian;
	}
	
	/**
	 * 获取：演出时间
	 */
	public String getYanchushijian() {
		return yanchushijian;
	}
				
	
	/**
	 * 设置：结束时间
	 */
	 
	public void setJieshushijian(String jieshushijian) {
		this.jieshushijian = jieshushijian;
	}
	
	/**
	 * 获取：结束时间
	 */
	public String getJieshushijian() {
		return jieshushijian;
	}
				
	
	/**
	 * 设置：演出城市
	 */
	 
	public void setYanchuchengshi(String yanchuchengshi) {
		this.yanchuchengshi = yanchuchengshi;
	}
	
	/**
	 * 获取：演出城市
	 */
	public String getYanchuchengshi() {
		return yanchuchengshi;
	}
				
	
	/**
	 * 设置：座位类型
	 */
	 
	public void setZuoweileixing(String zuoweileixing) {
		this.zuoweileixing = zuoweileixing;
	}
	
	/**
	 * 获取：座位类型
	 */
	public String getZuoweileixing() {
		return zuoweileixing;
	}
				
	
	/**
	 * 设置：乐队介绍
	 */
	 
	public void setPiaowujieshao(String piaowujieshao) {
		this.piaowujieshao = piaowujieshao;
	}
	
	/**
	 * 获取：乐队介绍
	 */
	public String getPiaowujieshao() {
		return piaowujieshao;
	}
				
	
	/**
	 * 设置：单限
	 */
	 
	public void setOnelimittimes(Integer onelimittimes) {
		this.onelimittimes = onelimittimes;
	}
	
	/**
	 * 获取：单限
	 */
	public Integer getOnelimittimes() {
		return onelimittimes;
	}
				
	
	/**
	 * 设置：余票
	 */
	 
	public void setAlllimittimes(Integer alllimittimes) {
		this.alllimittimes = alllimittimes;
	}
	
	/**
	 * 获取：余票
	 */
	public Integer getAlllimittimes() {
		return alllimittimes;
	}
				
	
	/**
	 * 设置：价格
	 */
	 
	public void setPrice(Double price) {
		this.price = price;
	}
	
	/**
	 * 获取：价格
	 */
	public Double getPrice() {
		return price;
	}
				
	
	/**
	 * 设置：收藏数
	 */
	 
	public void setStoreupnum(Integer storeupnum) {
		this.storeupnum = storeupnum;
	}
	
	/**
	 * 获取：收藏数
	 */
	public Integer getStoreupnum() {
		return storeupnum;
	}
				
	
	/**
	 * 设置：座位总数
	 */
	 
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	/**
	 * 获取：座位总数
	 */
	public Integer getNumber() {
		return number;
	}
				
	
	/**
	 * 设置：已选座位[用,号隔开]
	 */
	 
	public void setSelected(String selected) {
		this.selected = selected;
	}
	
	/**
	 * 获取：已选座位[用,号隔开]
	 */
	public String getSelected() {
		return selected;
	}
			
}
