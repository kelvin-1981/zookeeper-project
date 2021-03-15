package com.yykj.zookeeper.distribut;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class DistributServer {
	
	private static ZooKeeper zkClient = null;

	private static String connectString = "node21:2181,node22:2181,node23:2181";

	private static int sessionTimeout = 2000;

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		
		DistributServer server = new DistributServer();
		
		//获取连接
		server.getConnect();
		
		//注册
		server.regist(args[0]);
		
		// 3.业务逻辑
		server.business();
	}

	/**
	 * 模拟业务逻辑
	 * @throws InterruptedException 
	 */
	private void business() throws InterruptedException {
		// TODO Auto-generated method stub
		Thread.sleep(Long.MAX_VALUE);
	}

	/**
	 * 注册
	 * @throws InterruptedException 
	 * @throws KeeperException 
	 */
	private void regist(String hostname) throws KeeperException, InterruptedException {
		// TODO Auto-generated method stub
		String path = zkClient.create("/test/server", hostname.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		
		System.out.println(hostname + " is online !");
	}

	/**
	 * 获取连接
	 * @throws IOException
	 */
	private void getConnect() throws IOException {
		zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
			@Override
			public void process(WatchedEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	}
}
