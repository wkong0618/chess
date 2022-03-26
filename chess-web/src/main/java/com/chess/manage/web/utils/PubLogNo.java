package com.chess.manage.web.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description : 唯一编号获取
 * @Author : wukong
 * @Date: 2021/11/7 19:30
 */
public class PubLogNo {
	private static final Logger logger = LoggerFactory.getLogger(PubLogNo.class);
	private static Object lock = new Object();
	private static PubLogNo instance;
	private SequenceGenerator idWorker;

	/**
	 * 序列 workerId 工作ID 0 datacenterId 数据中心ID 0
	 */
	private PubLogNo() {
		idWorker = new SequenceGenerator(0, 1);
	}

	public static PubLogNo getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new PubLogNo();
				}
			}
		}
		return instance;
	}

	public static long getPubLogNo() {
		long id = PubLogNo.getInstance().idWorker.nextId();
		logger.info("sequence generator is:{}", id);
		return id;
	}

}
