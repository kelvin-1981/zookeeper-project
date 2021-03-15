package com.yykj.zookeeper.common;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

public class TestZookeeper {

	private String connectString = "node21:2181,node22:2181,node23:2181";
	
	private int sessionTimeout = 2000;
	
	private ZooKeeper zkClient = null;

	/**
	 *  初始化方法
	 * @throws IOException 
	 */
	@Before
	public void Init() throws IOException{
		zkClient = new ZooKeeper(connectString , sessionTimeout, new Watcher(){

			@Override
			public void process(WatchedEvent arg0) {
				
//				List<String> childrens = null;
//				try {
//					childrens = zkClient.getChildren("/test", true);
//					
//					for (String info : childrens) {
//						System.out.println("process:" + info);
//					} 
//				} catch (KeeperException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
		});
	}

	/**
	 * 创建Node
	 * @throws InterruptedException 
	 * @throws KeeperException 
	 */
	@Test
	public void createNode() throws KeeperException, InterruptedException {
		
		String path = zkClient.create("/test", "kevlin".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		
		System.out.println(path);
	}
	
	/**
	 * 获取子节点并监听
	 * @throws InterruptedException 
	 * @throws KeeperException 
	 */
	@Test
	public void getNodeAndWatch() throws KeeperException, InterruptedException{
		
		zkClient.getChildren("/test", true);
		
		Thread.sleep(Long.MAX_VALUE);
	}
	
	/**
	 * 检查节点是否存在
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	@Test
	public void exist() throws KeeperException, InterruptedException {

		Stat exists = zkClient.exists("/test/dir10", false);
		
		System.out.println(exists == null ? "no exits" : "exits");
	}
}
