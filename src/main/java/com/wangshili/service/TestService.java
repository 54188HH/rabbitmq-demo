package com.wangshili.service;


import com.wangshili.dao.QiangokDao;
import com.wangshili.pojo.Qiangok;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangshili.dao.KucunDao;
import com.wangshili.pojo.Kucun;

@Service
public class TestService {
	@Autowired
	private KucunDao kucunDao;
	@Autowired
	private QiangokDao qiangokDao;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	private int numbers = 0;

	//接收消息并处理请求
	@RabbitListener(queuesToDeclare = @Queue("miaosha"))
	public void receive1(Integer userId) { 
		//先判断库存
		Integer number= kucunDao.selectById(1).getNumber();//找出库存
		/**
		 * 库存一般都是取到缓存中等抢购结束后再进行数据库的增删改，这里不用redis，所以会有些麻烦
		 */
		if(number>0) {
			number--;
			Kucun kucun = new Kucun();
			kucun.setId(1);
			kucun.setNumber(number);
			kucunDao.updateById(kucun); //将修改后的值插入
			System.out.println("用户"+userId+"抢购成功");
			/**
			 * 抢购成功后可以将用户id加入秒杀成功的表，这里省略，只打印
			 */
			Qiangok ok = new Qiangok();
			ok.setUserId(++numbers);
			qiangokDao.insert(ok);
		}
		return;
	}
}
